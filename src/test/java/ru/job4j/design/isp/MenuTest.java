package ru.job4j.design.isp;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class MenuTest {

    @Test
    public void whenAddItemOfMenu() {
        Menu menu = new Menu();
        Item firstItem = new Item("First Item");
        Item secondItem = new Item("Second Item");
        menu.addItem(firstItem);
        menu.addItem(secondItem);
        Assert.assertEquals(menu.getItem("1."), firstItem);
        Assert.assertEquals(menu.getItem("2."), secondItem);
    }

    @Test
    public void whenAddSubItem() {
        Menu menu = new Menu();
        Item firstItem = new Item("First Item");
        Item secondItem = new Item("Second Item");
        menu.addItem(firstItem);
        menu.addSubItem(firstItem, secondItem);
        Assert.assertEquals(1, menu.getItem("1.").getChildrenItems().size());
    }

    @Rule
    public final SystemOutRule log = new SystemOutRule().enableLog();
    @Test
    public void whenDisplayAllMenu() {
        Menu menu = new Menu();
        Item firstItem = new Item("First Item");
        Item secondItem = new Item("Second Item");
        Item thirdItem = new Item("Third Item");
        Item fourthItem = new Item("Fourth Item");
        Item fifthItem = new Item("Fifth Item");
        Item sixthItem = new Item("Sixth Item");
        Item seventhItem = new Item("Seventh Item");
        Item eighthItem = new Item("Eighth Item");
        menu.addItem(firstItem);
        menu.addItem(fourthItem);
        menu.addSubItem(firstItem, secondItem);
        menu.addSubItem(firstItem, thirdItem);
        menu.addSubItem(secondItem, fifthItem);
        menu.addSubItem(fifthItem, sixthItem);
        menu.addSubItem(firstItem, seventhItem);
        menu.addSubItem(thirdItem, eighthItem);
        StringBuilder expired = new StringBuilder()
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
        menu.displayFullMenu();
        Assert.assertEquals(expired.toString(), log.getLog());
    }

    @Test
    public void whenDisplayOneItem() {
        Menu menu = new Menu();
        Item firstItem = new Item("First Item");
        Item secondItem = new Item("Second Item");
        Item thirdItem = new Item("Third Item");
        menu.addItem(firstItem);
        menu.addSubItem(firstItem, secondItem);
        menu.addSubItem(firstItem, thirdItem);
        menu.displayOneItem(firstItem);
        StringBuilder expired = new StringBuilder()
                .append("1. First Item")
                .append(System.lineSeparator())
                .append("  1.1. Second Item")
                .append(System.lineSeparator())
                .append("  1.2. Third Item")
                .append(System.lineSeparator());
        Assert.assertEquals(expired.toString(), log.getLog());
    }

    @Test
    public void whenDoItOneDotOneDot() {
        Menu menu = new Menu();
        Item firstItem = new Item("First Item");
        Item secondItem = new Item("Second Item");
        Item thirdItem = new Item("Third Item", new ActionString());
        menu.addItem(firstItem);
        menu.addSubItem(firstItem, secondItem);
        menu.addSubItem(secondItem, thirdItem);
        menu.doIt("1.1.1.");
        String expired = "Something is happening)";
        Assert.assertEquals(expired, log.getLog());
    }

    @Test(expected = NullPointerException.class)
    public void whenDoItOneDotOneDotButActionIsNull() {
        Menu menu = new Menu();
        Item firstItem = new Item("First Item");
        Item secondItem = new Item("Second Item");
        Item thirdItem = new Item("Third Item");
        menu.addItem(firstItem);
        menu.addSubItem(firstItem, secondItem);
        menu.addSubItem(secondItem, thirdItem);
        menu.doIt("1.1.1.");
        String expired = "Something is happening)";
        Assert.assertEquals(expired, log.getLog());
    }
}