package ru.job4j.user;

import java.util.*;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User() {

    }

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User userFirst = new User();
        userFirst.name = "Vasya";
        userFirst.children = 1;
        userFirst.birthday = new GregorianCalendar(2014, Calendar.APRIL, 21);
        User userSecond = new User();
        userSecond.name = "Vasya";
        userSecond.children = 1;
        userSecond.birthday = new GregorianCalendar(2014, Calendar.APRIL, 21);

        Map<User, Object> map = new HashMap<>();
        map.put(userFirst, new Object());
        map.put(userSecond, new Object());

        System.out.println("Print map: " + map);

        System.out.println("Hashcode userFirst key: " + userFirst.hashCode());
        System.out.println("Hashcode userFirst value: " + map.get(userFirst).hashCode());
        System.out.println("Hashcode userSecond key: " + userSecond.hashCode());
        System.out.println("Hashcode userSecond value: " + map.get(userSecond).hashCode());

        System.out.println("hashCode equals: " + (map.get(userFirst).hashCode() == map.get(userSecond).hashCode()));
        System.out.println("equals: " + map.get(userFirst).equals(map.get(userSecond)));

        System.out.println("userFirst: " + map.get(userFirst));
        System.out.println("userSecond: " + map.get(userSecond));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && name.equals(user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday
                + '}';
    }
}
