package ru.job4j.tdd;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.design.tdd.*;

import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Ignore
    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        Assert.assertEquals(ticket, new Ticket3D());
    }

    @Ignore
    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        Assert.assertEquals(sessions, List.of(new Session3D()));
    }

    @Ignore
    @Test
    public void add() {
        Cinema cinema = new Cinema3D();
        Session ses1 = new Session3D();
        Session ses2 = new Session3D();
        cinema.add(ses1);
        cinema.add(ses2);
        List<Session> sessions = cinema.find(session -> true);
        Assert.assertEquals(sessions, List.of(ses1, ses2));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void wrongDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 45, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void wrongPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 0, 1, date);
    }
}