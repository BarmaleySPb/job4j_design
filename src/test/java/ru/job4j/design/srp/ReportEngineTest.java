package ru.job4j.design.srp;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        Assert.assertEquals(engine.generate(em -> true), expect.toString());
    }

    @Test
    public void whenGenerateReportForProgrammers() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportForProgrammers(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append(System.lineSeparator())
                .append("<html>")
                .append(System.lineSeparator())
                .append("<head>")
                .append(System.lineSeparator())
                .append("<title>ITReport</title>")
                .append(System.lineSeparator())
                .append("</head>")
                .append(System.lineSeparator())
                .append("<body>")
                .append(System.lineSeparator())
                .append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append("</body>")
                .append(System.lineSeparator())
                .append("</html>");
        assertEquals(engine.generate(em -> true), expect.toString());
    }

    @Test
    public void whenGenerateForAccountant() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportForAccountants(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append((worker.getSalary()) * 73)
                .append(" RUB")
                .append(";")
                .append(System.lineSeparator());
        Assert.assertEquals(engine.generate(em -> true), expect.toString());
    }

    @Test
    public void whenGenerateForHr() {
        MemStore store = new MemStore();
        Employee workerFirst = new Employee("Ivan", 100);
        Employee workerSecond = new Employee("Petr", 190);
        Employee workerThird = new Employee("Olga", 150);
        store.add(workerFirst);
        store.add(workerSecond);
        store.add(workerThird);
        Report engine = new ReportForHr(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary")
                .append(System.lineSeparator())
                .append(workerSecond.getName()).append(";")
                .append(workerSecond.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(workerThird.getName()).append(";")
                .append(workerThird.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(workerFirst.getName()).append(";")
                .append(workerFirst.getSalary()).append(";")
                .append(System.lineSeparator());
        Assert.assertEquals(engine.generate(em -> true), expect.toString());
    }
}