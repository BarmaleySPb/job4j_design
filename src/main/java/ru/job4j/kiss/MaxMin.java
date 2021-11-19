package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return condition(value, comparator, i -> i < 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return condition(value, comparator, i -> i > 0);
    }

    private <T> T condition(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        T t = value.get(0);
        for (T val : value) {
            int temp = comparator.compare(t, val);
            t = predicate.test(temp) ? val : t;
        }
        return t;
    }
}
