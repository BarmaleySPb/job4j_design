package ru.job4j.design.srp;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

    @Test
    public void whenGenerateXmlReport() {
        MemStore store = new MemStore();
        Calendar hired = new GregorianCalendar(2015, Calendar.FEBRUARY, 2);
        Calendar fired = new GregorianCalendar(2020, Calendar.JANUARY, 10);
        Employee workerFirst = new Employee("Ivan", fired, hired, 100);
        Employee workerSecond = new Employee("Petr", fired, hired, 190);
        Employee workerThird = new Employee("Olga", fired, hired, 150);
        store.add(workerFirst);
        store.add(workerSecond);
        store.add(workerThird);
        Report engine = new ReportXml(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employees>\n")
                .append("    <employees>\n")
                .append("        <fired>2015-02-02T00:00:00+03:00</fired>\n")
                .append("        <hired>2020-01-10T00:00:00+03:00</hired>\n")
                .append("        <name>Petr</name>\n")
                .append("        <salary>190.0</salary>\n")
                .append("    </employees>\n")
                .append("</employees>\n");
        Assert.assertEquals(engine.generate(m -> m.getSalary() > 150), expect.toString());
    }

    @Test
    public void whenGenerateJsonReport() {
        MemStore store = new MemStore();
        Calendar hired = new GregorianCalendar(2015, Calendar.FEBRUARY, 2);
        Calendar fired = new GregorianCalendar(2020, Calendar.JANUARY, 10);
        Employee workerFirst = new Employee("Ivan", null, null, 100);
        Employee workerSecond = new Employee("Petr", fired, hired, 190);
        Employee workerThird = new Employee("Olga", fired, hired, 150);
        store.add(workerFirst);
        store.add(workerSecond);
        store.add(workerThird);
        Report engine = new ReportJson(store);
        String expect = "{\"name\":\"Ivan\",\"salary\":100.0}";
        Assert.assertEquals(engine.generate(m -> m.getSalary() < 150), expect);
    }

    @Test
    public void whenGenerateJsonReportAndSeveralItems() {
        MemStore store = new MemStore();
        Calendar hired = new GregorianCalendar(2015, Calendar.FEBRUARY, 2);
        Calendar fired = new GregorianCalendar(2020, Calendar.JANUARY, 10);
        Employee workerFirst = new Employee("Ivan", fired, hired, 100);
        Employee workerSecond = new Employee("Petr", fired, hired, 190);
        Employee workerThird = new Employee("Olga", fired, hired, 150);
        store.add(workerFirst);
        store.add(workerSecond);
        store.add(workerThird);
        Report engine = new ReportJson(store);
        String expect = "{\"name\":\"Ivan\",\"hired\":{\"year\":2020,\"month\":0,\"dayOfMonth\":10,"
                + "\"hourOfDay\":0,\"minute\":0,\"second\":0},\"fired\":{\"year\":2015,\"month\":1,"
                + "\"dayOfMonth\":2,\"hourOfDay\":0,\"minute\":0,\"second\":0},\"salary\":100.0}"
                + "{\"name\":\"Petr\",\"hired\":{\"year\":2020,\"month\":0,\"dayOfMonth\":10,\"hourOfDay\":0,"
                + "\"minute\":0,\"second\":0},\"fired\":{\"year\":2015,\"month\":1,\"dayOfMonth\":2,"
                + "\"hourOfDay\":0,\"minute\":0,\"second\":0},\"salary\":190.0}"
                + "{\"name\":\"Olga\",\"hired\":{\"year\":2020,\"month\":0,\"dayOfMonth\":10,\"hourOfDay\":0,"
                + "\"minute\":0,\"second\":0},\"fired\":{\"year\":2015,\"month\":1,\"dayOfMonth\":2,\"hourOfDay\":0,"
                + "\"minute\":0,\"second\":0},\"salary\":150.0}";
        Assert.assertEquals(engine.generate(m -> true), expect);
    }
}