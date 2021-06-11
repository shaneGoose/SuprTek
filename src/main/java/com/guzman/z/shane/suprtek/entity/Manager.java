package com.guzman.z.shane.suprtek.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Manager extends Person implements Worker {
    public static final BigDecimal ALLOCATION = new BigDecimal(600);

    private Set<Manager> subordinateManagers;
    private Set<Worker> workers;

    public Manager(String name) {

        super(name);
        this.subordinateManagers = new HashSet<>();
        this.workers = new HashSet<>();
    }

    /**
     * Recursive node accumulator for self and subordinate's allocations.
     * @return Total allocations, including self.
     */
    public BigDecimal getProjectedAllocation() {
        // 1. Add self to projection
        BigDecimal finalProjection = ALLOCATION;

        // 2. Check for subordinate managers (continuing)
        for (Manager manager : this.subordinateManagers) {
            finalProjection = finalProjection.add(
                    manager.getProjectedAllocation()
            );
        }

        // 3. Check for workers (terminal)
        for (Worker worker : this.workers) {
            finalProjection = finalProjection.add(worker.getAllocation());
        }

        // 4. Exit (pass value back up to parent)
        return finalProjection;
    }

    @Override
    public String generateReport(int depth) {
        String finalReport = "";
        ++depth;
        // Self-report first
        finalReport = finalReport.concat(Person.tab(depth)).concat(
                String.format("-Manager (%s) projected allocation $%.2f\n", super.getName(), getProjectedAllocation())
        );

        // Check for underlings
        if (this.hasPersonnel()) {

            // Workers first
            ++depth;
            for (Worker worker : this.workers) {
                finalReport = finalReport.concat(worker.generateReport(depth));
            }
            --depth;

            // Managers next and recursive throughput
            for (Manager manager : this.subordinateManagers) {
                finalReport = finalReport.concat(manager.generateReport(depth));
            }

        } else {
            ++depth;
            finalReport = finalReport.concat(Person.tab(depth)).concat(
                    String.format("-MANAGER HAS NO SUBORDINATES.\n")
            );
            --depth;
        }

        --depth;
        return finalReport;
    }

    public boolean addSubordinateManager(Manager manager) { return this.subordinateManagers.add(manager); }

    public boolean removeSubordinateManager(String name)
    { return this.subordinateManagers.removeIf(manager -> manager.name().equals(name)); }

    public boolean addWorker(Worker worker) { return this.workers.add(worker); }

    public boolean removeWorker(String name) { return this.workers.removeIf(worker -> worker.name().equals(name)); }

    public boolean hasPersonnel() {
        return !this.subordinateManagers.isEmpty() || !this.workers.isEmpty();
    }

    public Set<Manager> getSubordinateManagers() { return this.subordinateManagers; }

    @Override
    public BigDecimal getAllocation() {
        return ALLOCATION;
    }

    @Override
    public String name() {
        return super.getName();
    }
}
