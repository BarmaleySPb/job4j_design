package ru.job4j.design.lsp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;


public class ControlQualityTest {

    @Test
    public void remainingShelfLife() {
        Calendar firstCreateDate = Calendar.getInstance();
        firstCreateDate.roll(Calendar.YEAR, -1);
        Calendar firstExpireDate = Calendar.getInstance();
        firstExpireDate.roll(Calendar.YEAR, +5);
        Calendar secondCreateDate = Calendar.getInstance();
        secondCreateDate.roll(Calendar.YEAR, -3);
        Calendar secondExpireDate = Calendar.getInstance();
        secondExpireDate.roll(Calendar.YEAR, -1);
        Food milk = new Food("milk", firstCreateDate, firstExpireDate, 100D, 0);
        Food bread = new Food("bread", secondCreateDate, secondExpireDate, 100D, 0);
        ControlQuality controlQuality = new ControlQuality(List.of(new Warehouse()));
        int result = controlQuality.remainingShelfLife(milk);
        int result2 = controlQuality.remainingShelfLife(bread);
        Assert.assertEquals(result, 83);
        Assert.assertEquals(result2, -49);
    }

    @Test
    public void whenSortingOneToTrashAndTwoToShopWithOneDiscount() {
        Calendar firstCreateDate = Calendar.getInstance();
        firstCreateDate.roll(Calendar.YEAR, -3);
        Calendar firstExpireDate = Calendar.getInstance();
        firstExpireDate.roll(Calendar.YEAR, -1);
        Calendar secondCreateDate = Calendar.getInstance();
        secondCreateDate.roll(Calendar.DAY_OF_YEAR, -10);
        Calendar secondExpireDate = Calendar.getInstance();
        secondExpireDate.roll(Calendar.DAY_OF_YEAR, +10);
        Calendar thirdExpireDate = Calendar.getInstance();
        thirdExpireDate.roll(Calendar.DAY_OF_YEAR, +1);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        List<Food> foods = List.of(
                new Food("milk", firstCreateDate, firstExpireDate, 100D, 0),
                new Food("sous", secondCreateDate, thirdExpireDate, 100D, 0),
                new Food("bread", secondCreateDate, secondExpireDate, 100D, 0));
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        controlQuality.sorting(foods);
        int resultTrash = trash.size();
        int resultShop = shop.size();
        int resultDiscount = foods.get(1).getDiscount();
        Assert.assertEquals(1, resultTrash);
        Assert.assertEquals(2, resultShop);
        Assert.assertEquals(20, resultDiscount);
    }
}