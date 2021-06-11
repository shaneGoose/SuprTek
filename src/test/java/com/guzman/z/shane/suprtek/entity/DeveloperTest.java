package com.guzman.z.shane.suprtek.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DeveloperTest {
    private Developer tester;

    @BeforeEach
    void setUp() {
        this.tester = new Developer("Shane");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void tab() {
        String tabbers = "";
        assertEquals(tabbers, Person.tab(0));

        tabbers = tabbers.concat("\t");
        assertEquals(tabbers, Person.tab(1));

        tabbers = tabbers.concat("\t");
        assertEquals(tabbers, Person.tab(2));
    }

    @Test
    void getName() {
        assertEquals("Shane", this.tester.getName());
        this.tester.setName("John");
        assertEquals("John", this.tester.getName());
    }

    @Test
    void getAllocation() {
        assertEquals(Developer.ALLOCATION, this.tester.getAllocation());
    }

    @Test
    void generateReport() {
        String workerReportString = "-Developer (Shane) projected allocation $2000.00\n";
        assertEquals(workerReportString, this.tester.generateReport(0));
    }
}