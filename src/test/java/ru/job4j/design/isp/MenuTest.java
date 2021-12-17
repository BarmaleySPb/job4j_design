package ru.job4j.design.isp;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.util.NoSuchElementException;


public class MenuTest {

    @Rule
    public final SystemOutRule log = new SystemOutRule().enableLog();

    @Test
    public void whenAddItemOfMenu() {
        Menu<String> menu = new Menu<>("Menu");
        menu.add("Menu", "First Item", new ActionString());
        StringBuilder expected = new StringBuilder()
                .append("Menu")
                .append(System.lineSeparator())
                .append("1. First Item")
                .append(System.lineSeparator());
        System.out.print(menu.print());
        Assert.assertEquals(expected.toString(), log.getLog());
    }

    @Test
    public void whenAddSubItem() {
        Menu<String> menu = new Menu<>("Menu");
        menu.add("Menu", "First Item", new ActionString());
        menu.add("First Item", "Second Item", new ActionString());
        StringBuilder expected = new StringBuilder()
                .append("Menu")
                .append(System.lineSeparator())
                .append("1. First Item")
                .append(System.lineSeparator())
                .append("  1.1. Second Item")
                .append(System.lineSeparator());
        System.out.print(menu.print());
        Assert.assertEquals(expected.toString(), log.getLog());
    }

    @Test
    public void whenPrintMenu() {
        Menu<String> menu = new Menu<>("Menu");
        menu.add("Menu", "First Item", new ActionString());
        menu.add("Menu", "Fourth Item", new ActionString());
        menu.add("First Item", "Second Item", new ActionString());
        menu.add("First Item", "Third Item", new ActionString());
        menu.add("Second Item", "Fifth Item", new ActionString());
        menu.add("Fifth Item", "Sixth Item", new ActionString());
        menu.add("First Item", "Seventh Item", new ActionString());
        menu.add("Third Item", "Eighth Item", new ActionString());
        StringBuilder expected = new StringBuilder()
                .append("Menu")
                .append(System.lineSeparator())
                .append("1. First Item")
                .append(System.lineSeparator())
                .append("  1.1. Second Item")
                .append(System.lineSeparator())
                .append("    1.1.1. Fifth Item")
                .append(System.lineSeparator())
                .append("      1.1.1.1. Sixth Item")
                .append(System.lineSeparator())
                .append("  1.2. Third Item")
                .append(System.lineSeparator())
                .append("    1.2.1. Eighth Item")
                .append(System.lineSeparator())
                .append("  1.3. Seventh Item")
                .append(System.lineSeparator())
                .append("2. Fourth Item")
                .append(System.lineSeparator());
        System.out.print(menu.print());
        Assert.assertEquals(expected.toString(), log.getLog());
    }

    @Test
    public void whenSelectAction() {
        Menu<String> menu = new Menu<>("Menu");
        menu.add("Menu", "First Item", new ActionString());
        menu.add("First Item", "Second Item", new ActionString());
        menu.select("Second Item").action();
        String expected = "Something is happening)";
        Assert.assertEquals(expected, log.getLog());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenSelectActionButNonExistentMenuItem() {
        Menu<String> menu = new Menu<>("Menu");
        menu.add("Menu", "First Item", new ActionString());
        menu.add("First Item", "Second Item", new ActionString());
        menu.add("Second Item", "Third Item", new ActionString());
        menu.select("Fourth Item");
    }
}