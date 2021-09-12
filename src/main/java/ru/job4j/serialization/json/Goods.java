package ru.job4j.serialization.json;

public class Goods {
    String name;
    int weight;

    public Goods(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Goods{"
                + "name='" + name + '\''
                + ", weight=" + weight
                + '}';
    }
}
