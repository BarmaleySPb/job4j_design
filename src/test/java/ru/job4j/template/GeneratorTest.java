package ru.job4j.template;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;


public class GeneratorTest {

    @Ignore
    @Test
    public void produce() {
        Generator generator = new TemplateGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of(
                "name", "Petr Arsentev",
                "subject", "you"
        );
        String result = generator.produce(template, args);
        Assert.assertEquals("I am a Petr Arsentev, Who are you? ", result);
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void wrongArgs() {
        Generator generator = new TemplateGenerator();
        String template = "I am a ${user}";
        Map<String, String> args = Map.of(
                "name", "Petr Arsentev",
                "subject", "you"
        );
        String result = generator.produce(template, args);
    }

    @Ignore
    @Test (expected = IllegalArgumentException.class)
    public void wrongMap() {
        Generator generator = new TemplateGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of(
                "name", "Petr Arsentev",
                "subject", "you",
                "user", "Petr"
        );
        String result = generator.produce(template, args);
    }
}