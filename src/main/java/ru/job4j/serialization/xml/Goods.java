package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "goods")
@XmlAccessorType(XmlAccessType.FIELD)
public class Goods {
    @XmlAttribute
    String name;
    @XmlAttribute
    int weight;

    public Goods() {
    }

    public Goods(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Goods{"
                + "name='" + name + '\''
                + ", weight=" + weight
                + '}';
    }
}
