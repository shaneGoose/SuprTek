package com.guzman.z.shane.suprtek.entity;

import java.math.BigDecimal;

public class Developer extends Person implements Worker {
    public static final BigDecimal ALLOCATION = new BigDecimal(2000);

    public Developer(String name) {
        super(name);
    }

    @Override
    public String generateReport(int depth) {
        return Person.tab(depth).concat(
                String.format("-Developer (%s) projected allocation $%.2f\n",
                        super.getName(),
                        getAllocation())
        );
    }

    @Override
    public BigDecimal getAllocation() {
        return ALLOCATION;
    }

    @Override
    public String name() {
        return super.getName();
    }
}
