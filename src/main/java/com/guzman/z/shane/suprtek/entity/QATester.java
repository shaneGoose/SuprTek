package com.guzman.z.shane.suprtek.entity;

import java.math.BigDecimal;

public class QATester extends Person implements Worker {
    public static final BigDecimal ALLOCATION = new BigDecimal(1000);

    public QATester(String name) {
        super(name);
    }

    @Override
    public BigDecimal getAllocation() {
        return ALLOCATION;
    }

    @Override
    public String generateReport(int depth) {
        return Person.tab(depth).concat(
                String.format("-QA Tester (%s) projected allocation $%.2f\n",
                        super.getName(),
                        getAllocation())
        );
    }

    @Override
    public String name() {
        return super.getName();
    }
}
