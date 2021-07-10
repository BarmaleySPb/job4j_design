package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 2));
        Integer filter = 4;
        Predicate<Integer> pred = value -> value.equals(filter);
        ListUtils.removeIf(input, pred);
        assertThat(Arrays.asList(0, 1, 2, 3, 2), Is.is(input));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 2));
        Integer filter = 2;
        Predicate<Integer> pred = value -> value.equals(filter);
        ListUtils.replaceIf(input, pred, 9);
        assertThat(Arrays.asList(0, 1, 9, 3, 4, 9), Is.is(input));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 2));
        List<Integer> filter = new ArrayList<>(Arrays.asList(1, 2));
        ListUtils.removeAll(input, filter);
        assertThat(Arrays.asList(0, 3, 4), Is.is(input));
    }
}