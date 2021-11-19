package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class MaxMinTest {

    @Test
    public void whenMax() {
        List<Integer> input = List.of(1, 4, 3, 5, 2, 0);
        MaxMin maxMin = new MaxMin();
        int result = maxMin.max(input, Integer::compareTo);
        Assert.assertEquals(result, 5);
    }

    @Test
    public void whenMin() {
        List<Integer> input = List.of(5, 0, 2, 1, 3, 4);
        MaxMin maxMin = new MaxMin();
        int result = maxMin.min(input, Integer::compareTo);
        Assert.assertEquals(result, 0);
    }

    @Test
    public void whenHaveMinString() {
        List<String> input = List.of("citrus", "banana", "apple");
        MaxMin maxMin = new MaxMin();
        String result = maxMin.min(input, String::compareTo);
        Assert.assertEquals(result, "apple");
    }

    @Test
    public void whenHaveMaxString() {
        List<String> input = List.of("zero", "one", "two", "three", "four");
        MaxMin maxMin = new MaxMin();
        String result = maxMin.max(input, String::compareTo);
        Assert.assertEquals(result, "zero");
    }
}