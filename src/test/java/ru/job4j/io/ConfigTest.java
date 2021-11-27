package ru.job4j.io;

import org.junit.Assert;
import org.junit.Test;

import java.lang.IllegalArgumentException;


public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        Assert.assertEquals(config.value("name"), "Petr Arsentev");
        Assert.assertNull(config.value("surname"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        Assert.assertEquals(config.value("name"), "Petr");
        Assert.assertEquals(config.value("surname"), "Arsentev");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithError() {
        String path = "./data/pair_with_error.properties";
        Config config = new Config(path);
        config.load();
    }
}
