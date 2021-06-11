package com.guzman.z.shane.suprtek.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    private Manager tester;
    private Developer developer = new Developer("Mike");
    private QATester qaTester = new QATester("Julia");
    private Manager lowerManager = new Manager("Dave");

    @BeforeEach
    void setUp() {
        this.tester = new Manager("Shane");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getName() {
        assertEquals("Shane", this.tester.getName());
    }

    @Test
    void getProjectedAllocation() {
        // No underlings case - Total should be $600
        BigDecimal total = Manager.ALLOCATION;
        assertEquals(total, this.tester.getProjectedAllocation());

        // 1 Dev - Total should be $2600
        this.tester.addWorker(developer);
        total = total.add(Developer.ALLOCATION);
        assertEquals(total, this.tester.getProjectedAllocation());

        // 1 QA - Total should be $1600
        this.tester.removeWorker(developer.getName());
        this.tester.addWorker(qaTester);
        total = total.subtract(Developer.ALLOCATION).add(QATester.ALLOCATION);
        assertEquals(total, this.tester.getProjectedAllocation());

        // 1 DEV and 1 QA - Total should be $3600
        this.tester.addWorker(developer);
        total = total.add(Developer.ALLOCATION);
        assertEquals(total, this.tester.getProjectedAllocation());

        // 1 DEV, 1 QA, and 1 Manager (no underlings) - Total should be $4200
        this.tester.addSubordinateManager(lowerManager);
        total = total.add(Manager.ALLOCATION);
        assertEquals(total, this.tester.getProjectedAllocation());

        // 1 DEV, 1 QA, and 1 Manager (1 DEV) - Total should be $6200
        this.tester.removeSubordinateManager(lowerManager.name());
        lowerManager.addWorker(new Developer("Desmond"));
        this.tester.addSubordinateManager(lowerManager);
        total = total.add(Developer.ALLOCATION);
        assertEquals(total, this.tester.getProjectedAllocation());
    }

    @Test
    void generateReport() {
        BigDecimal total = Manager.ALLOCATION;
        String managerReportString = "\t-Manager (Shane) projected allocation $%.2f\n";
        verifyManagerString(
                String.format(managerReportString, total), "\t\t-MANAGER HAS NO SUBORDINATES.\n", this.tester
        );

        //Add worker (DEV) (Depth level is 2)
        this.tester.addWorker(developer);
        total = total.add(Developer.ALLOCATION);
        verifyManagerString(
                String.format(managerReportString, total), developer.generateReport(2), this.tester
        );

        // Add worker (QA) depth = 2
        this.tester.addWorker(qaTester);
        total = total.add(QATester.ALLOCATION);
        verifyManagerString(
                String.format(managerReportString, total), qaTester.generateReport(2), this.tester
        );

        // Add another manager
        this.tester.addSubordinateManager(lowerManager);
        total = total.add(Manager.ALLOCATION);
        verifyManagerString(
                String.format(managerReportString, total), lowerManager.generateReport(1), this.tester
        );

        // Add worker to lower manager
        managerReportString.replace(lowerManager.generateReport(2), "");
        this.lowerManager.addWorker(new Developer("Zorro"));
        total = total.add(Developer.ALLOCATION);
        verifyManagerString(
                String.format(managerReportString, total), lowerManager.generateReport(1), this.tester
        );
    }

    void sampleReportString() {}

    private void verifyManagerString(String managerOverallTotal, String component, Manager manager) {
        String reportString = manager.generateReport(0);
        assertTrue(reportString.contains(managerOverallTotal));
        assertTrue(reportString.contains(component));
    }

    @Test
    void addSubordinateManager() {
        assertTrue(this.tester.addSubordinateManager(lowerManager));
        assertFalse(this.tester.addSubordinateManager(lowerManager));
    }

    @Test
    void addWorker() {
        assertTrue(this.tester.addWorker(qaTester));
        assertFalse(this.tester.addWorker(qaTester));
        assertTrue(this.tester.addWorker(developer));
        assertFalse(this.tester.addWorker(developer));
    }

    @Test
    void hasPersonnel() {
        assertFalse(this.tester.hasPersonnel());

        this.tester.addSubordinateManager(lowerManager);
        assertTrue(this.tester.hasPersonnel());

        this.tester.removeSubordinateManager(lowerManager.name());
        assertFalse(this.tester.hasPersonnel());

        this.tester.addWorker(qaTester);
        assertTrue(this.tester.hasPersonnel());

        this.tester.addSubordinateManager(lowerManager);
        assertTrue(this.tester.hasPersonnel());

    }

    @Test
    void getAllocation() {
        assertEquals(Manager.ALLOCATION, this.tester.getAllocation());
    }
}