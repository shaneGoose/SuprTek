package com.guzman.z.shane.suprtek.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Department {
    private Set<Manager> topLevel;

    public Department() {
        this.topLevel = new HashSet<>();
    }

    /**
     * Method to find department manager. Treat name as employee ID.
     * @param name A stand in for employee ID.
     * @return A manager in the top-level that matches, null otherwise
     */
    public Manager findDepartmentManager(String name) {
        if (name == null) throw new IllegalArgumentException("Name cannot be null!");

        Manager found = null;
        for (Manager manager : this.topLevel) {
            if (manager.name().equals(name)) found = manager;
        }

        return found;
    }

    public boolean addManager(Manager manager) { return this.topLevel.add(manager); }

    public String generateReport() {
        String finalReport = "";
        BigDecimal total = BigDecimal.ZERO;

        // The numbers
        for (Manager manager : this.topLevel) {
            finalReport = getTheNumbers(manager, finalReport);
            total = total.add(manager.getProjectedAllocation());
        }

        List<Manager> managerWithNoSubs = managersWithNoSubordinates(this.topLevel);
        String noSubComposite = managerWithNoSubs.stream().map(Manager::name).collect(Collectors.joining(", "));

        finalReport = finalReport.concat(String.format("-Managers with no subordinates: %s\n", noSubComposite));
        finalReport = finalReport.concat(String.format("-TOTAL ALLOCATION: $%.2f\n", total));

        return finalReport;
    }

    public String generateReport(String managerName) {
        String finalReport = "";
        Manager manager = this.findDepartmentManager(managerName);

        // Short circuit if null
        if (manager == null) return "Could not locate Manager.";
        BigDecimal total = manager.getProjectedAllocation();

        finalReport = getTheNumbers(manager, finalReport);
        finalReport = finalReport.concat(String.format("-TOTAL ALLOCATION: $%.2f\n", total));

        return finalReport;
    }

    private String getTheNumbers(Manager manager, String report) {
        report = report.concat(String.format("--- Manager %s's Group ---\n", manager.getName()));
        report = report.concat(manager.generateReport(0));
        report = report.concat("---\n");
        return report;
    }

    private List<Manager> managersWithNoSubordinates(Set<Manager> toSearch) {
        List<Manager> managers = new ArrayList<>();
        // 1. Start
        for (Manager manager : toSearch) {
            if (!manager.hasPersonnel()) {
                managers.add(manager);
            }
            else {
                managers.addAll(managersWithNoSubordinates(manager.getSubordinateManagers()));
            }
        }

        return managers;
    }

}
