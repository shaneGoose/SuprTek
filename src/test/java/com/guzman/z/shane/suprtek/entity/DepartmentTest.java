package com.guzman.z.shane.suprtek.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {
    private DataServiceMock dataServiceMock = new DataServiceMock();
    private Department department;

    @BeforeEach
    void setUp() {
        this.department = this.dataServiceMock.getDepartmentData();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findDepartmentManager() {
        assertNull(this.department.findDepartmentManager("Obi-Won"));
        assertNotNull(this.department.findDepartmentManager("A"));
        assertNotNull(this.department.findDepartmentManager("C"));
        assertNotNull(this.department.findDepartmentManager("E"));
    }

    @Test
    void generateReport() {
        System.out.println(this.department.generateReport());
    }

    @Test
    void testGenerateReport() {
        System.out.println(this.department.generateReport("A"));

        assertEquals("Could not locate Manager.", this.department.generateReport("B"));
    }
}