package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BoxTwo {

    public static void main(String[] args) {
        JSONObject jsonGoods = new JSONObject("{\"name\":\"apples\",\"weight\":100}");
        List<String> list = new ArrayList<>();
        list.add("197375");
        list.add("Saint-Petersberg");
        list.add("Utochkina st., b. 5/1");
        JSONArray jsonOwnerAddress = new JSONArray(list);

        final Box box = new Box(10, 10, 10, "Coca-Cola Company", true,
                new Goods("apples", 100), "197375", "Saint-Petersberg",
                "Utochkina st., b. 5/1");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("height", box.getHeight());
        jsonObject.put("width", box.getWidth());
        jsonObject.put("depth", box.getDepth());
        jsonObject.put("owner", box.getOwner());
        jsonObject.put("dblBottom", box.isDblBottom());
        jsonObject.put("goods", jsonGoods);
        jsonObject.put("ownerAddress", jsonOwnerAddress);

        System.out.println(jsonObject);
        System.out.println(new JSONObject(box));
    }
}
