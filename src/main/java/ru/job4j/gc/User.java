package ru.job4j.gc;


public class User {

    public String name;
    public String surname;
    public int age;

    public User() {
    }

    public User(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed user: %s%n", name);
    }

    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        User firstUser = new User("Ivan", "Petrov", 20);
        User secondUser = new User("Ira", "Ivanova", 10);
        for (int ai = 0; ai < 2000; ai++) {
            User user = new User(String.valueOf(ai), String.valueOf(ai), ai);
        }
        info();
    }
}
