package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.lang.IllegalArgumentException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr"));
        assertThat(config.value("surname"), is("Arsentev"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithError() {
        String path = "./data/pair_with_error.properties";
        Config config = new Config(path);
        config.load();
    }
}
