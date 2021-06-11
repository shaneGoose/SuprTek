package com.guzman.z.shane.suprtek;

import com.guzman.z.shane.suprtek.entity.Department;
import com.guzman.z.shane.suprtek.entity.Developer;
import com.guzman.z.shane.suprtek.entity.Manager;
import com.guzman.z.shane.suprtek.entity.QATester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SuprTekController {
    private BigDecimal tester = new BigDecimal(600);

    @GetMapping("/report")
    public String report() {
        Department department = getDepartment();
        List<String> rawStrings = new ArrayList<>();
        String[] body = department.generateReport().split("---");

        String html = "";
        for (String s : body) {
            if (s.contains("-")) {
                String[] temp = s.split("-");
                for (String s1 : temp) {
                    rawStrings.add(s1);
                }
            }
            else {
                rawStrings.add(s);
            }
        }

        for (String rawString : rawStrings) {
            html = html.concat(pTag(rawString));
        }

        return html;
    }

    @GetMapping("/managerReport")
    String getReportFor(@RequestParam("manager") String manager) {
        Department department = getDepartment();

        List<String> rawStrings = new ArrayList<>();
        String[] body = department.generateReport(manager).split("---");

        String html = "";
        for (String s : body) {
            if (s.contains("-")) {
                String[] temp = s.split("-");
                for (String s1 : temp) {
                    rawStrings.add(s1);
                }
            }
            else {
                rawStrings.add(s);
            }
        }

        for (String rawString : rawStrings) {
            html = html.concat(pTag(rawString));
        }

        return html;
    }

    // Stub department for proof of concept
    private Department getDepartment() {
        Department department = new Department();
        Manager managerA = new Manager("A");
        Manager managerB = new Manager("B");
        Manager managerC = new Manager("C");
        Manager managerD = new Manager("D");
        Manager managerE = new Manager("E");
        Developer developer1 = new Developer("ONE");
        Developer developer2 = new Developer("TWO");
        QATester qaTester = new QATester("Alpha");
        managerB.addWorker(developer1);
        managerB.addWorker(qaTester);
        managerC.addSubordinateManager(managerD);
        managerE.addWorker(developer2);
        managerA.addSubordinateManager(managerB);
        department.addManager(managerA);
        department.addManager(managerC);
        department.addManager(managerE);
        return department;
    }

    private String pTag(String body) {
        return "<p>".concat(body).concat("</p>");
    }
}
