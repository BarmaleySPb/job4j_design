package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class MainForBox {

    public static void main(String[] args) throws Exception {
        Box box = new Box(10, 10, 10, "Coca-Cola Company", true,
               new Goods("apples", 10), "197375", "Saint-Petersberg", "Utochkina st., b. 5/1");
        JAXBContext context = JAXBContext.newInstance(Box.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(box, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Box result = (Box) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}