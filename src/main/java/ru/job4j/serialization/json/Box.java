package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Box {
    int height;
    int width;
    int depth;
    String owner;
    boolean dblBottom;
    Goods goods;
    String[] ownerAddress;

    public Box(int height, int width, int depth, String owner, boolean dblBottom, Goods goods, String... ownerAddress) {
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.owner = owner;
        this.dblBottom = dblBottom;
        this.goods = goods;
        this.ownerAddress = ownerAddress;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getDepth() {
        return depth;
    }

    public String getOwner() {
        return owner;
    }

    public boolean isDblBottom() {
        return dblBottom;
    }

    public Goods getGoods() {
        return goods;
    }

    public String[] getOwnerAddress() {
        return ownerAddress;
    }

    @Override
    public String toString() {
        return "Box{"
                + "height=" + height
                + ", width=" + width
                + ", depth=" + depth
                + ", owner='" + owner + '\''
                + ", dblBottom=" + dblBottom
                + ", goods=" + goods
                + ", ownerAddress" + Arrays.toString(ownerAddress)
                + '}';
    }

    public static void main(String[] args) {
        Goods apple = new Goods("apples", 100);
        Box box = new Box(10, 10, 10, "Coca-Cola Company", true, apple,
                "197375", "Saint-Petersberg", "Utochkina st., b. 5/1");
        System.out.println(box);

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(box));

        final String boxJson =
                "{"
                        + "\"height\":5,"
                        + "\"width\":5,"
                        + "\"depth\":5,"
                        + "\"owner\":\"Pepsi Co\","
                        + "\"dblBottom\":false,"
                        + "\"goods\":"
                            + "{"
                                + "\"name\":\"bananas\","
                                + "\"weight\":50"
                            + "},"
                        + "\"ownerAddress\":"
                            + "[\"197350\",\"Moscow\",\"Pupkina st, b. 10/2\"]"
                + "}";

        final Box boxMod = gson.fromJson(boxJson, Box.class);
        System.out.println(boxMod);
    }
}
