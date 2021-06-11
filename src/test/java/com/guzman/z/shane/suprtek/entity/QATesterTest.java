package com.guzman.z.shane.suprtek.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class QATesterTest {
    private QATester tester;

    @BeforeEach
    void setUp() {
        this.tester = new QATester("Shane");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getName() {
        assertEquals("Shane", this.tester.getName());
    }

    @Test
    void getAllocation() {
        assertEquals(QATester.ALLOCATION, this.tester.getAllocation());
    }

    @Test
    void generateReport() {
        String workerReportString = "-QA Tester (Shane) projected allocation $1000.00\n";
        assertEquals(workerReportString, this.tester.generateReport(0));
    }
}