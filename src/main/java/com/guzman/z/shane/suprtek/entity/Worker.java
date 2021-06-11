package com.guzman.z.shane.suprtek.entity;

import java.math.BigDecimal;

public interface Worker {
    /**
     * Mechanism to generate a report. As the depth increases, so does the indentation for level of
     * reporting.
     * @param depth The level at which the indent will correspond.
     * @return A string for the department's allocation report.
     */
    String generateReport(int depth);

    BigDecimal getAllocation();

    String name();
}
