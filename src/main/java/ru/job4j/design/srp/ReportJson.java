package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

public class ReportJson implements Report {

    private Store store;

    ReportJson(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String text;
        Gson gson = new GsonBuilder().create();
        text = gson.toJson(store.findBy(filter));
        return text;
    }
}
