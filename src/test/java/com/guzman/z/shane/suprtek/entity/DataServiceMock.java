package com.guzman.z.shane.suprtek.entity;

import com.guzman.z.shane.suprtek.service.DataService;

public class DataServiceMock extends DataService {
    private Department department;
    private Manager managerA = new Manager("A");
    private Manager managerB = new Manager("B");
    private Manager managerC = new Manager("C");
    private Manager managerD = new Manager("D");
    private Manager managerE = new Manager("E");
    private Developer developer1 = new Developer("ONE");
    private Developer developer2 = new Developer("TWO");
    private QATester qaTester = new QATester("Alpha");

    public DataServiceMock() {
        super();
        this.department = new Department();
        managerB.addWorker(developer1);
        managerB.addWorker(qaTester);
        managerC.addSubordinateManager(managerD);
        managerE.addWorker(developer2);
        managerA.addSubordinateManager(managerB);
        this.department.addManager(managerA);
        this.department.addManager(managerC);
        this.department.addManager(managerE);
    }

    @Override
    public Department getDepartmentData() {
        return this.department;
    }
}
