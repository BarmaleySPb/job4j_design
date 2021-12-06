package ru.job4j.design.isp;

public class ActionString implements Action {
    @Override
    public void action() {
        System.out.print("Something is happening)");
    }
}
