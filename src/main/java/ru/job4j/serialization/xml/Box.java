package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "box")
@XmlAccessorType(XmlAccessType.FIELD)
public class Box {
    @XmlAttribute
    int height;
    @XmlAttribute
    int width;
    @XmlAttribute
    int depth;
    @XmlAttribute
    String owner;
    @XmlAttribute
    boolean dblBottom;
    Goods goods;
    @XmlElementWrapper(name = "ownerAddresses")
    @XmlElement(name = "ownerAddress")
    String[] ownerAddress;

    public Box() {
    }

    public Box(int height, int width, int depth, String owner, boolean dblBottom, Goods goods, String... ownerAddress) {
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.owner = owner;
        this.dblBottom = dblBottom;
        this.goods = goods;
        this.ownerAddress = ownerAddress;
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
}
