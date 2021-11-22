package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReportForHr implements Report {

    private Store store;

    public ReportForHr(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)
                .stream()
                .sorted(Comparator.comparing(Employee::getSalary)
                        .reversed())
                .collect(Collectors.toList())) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}