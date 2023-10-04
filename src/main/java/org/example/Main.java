package org.example;

import org.example.annotation.CustomAutowired;
import org.example.ioc.CustomApplicationContext;

public class Main {

    public static void main(String[] args) throws Exception {
        TestService testService = CustomApplicationContext.getInstance(TestService.class);

        testService.start();
    }
}

class Robot {
    public void fight() {
        System.out.println("로봇이 싸웁니다.");
    }

    public void clean() {
        System.out.println("로봇이 청소합니다.");
    }

    private void destroy() {
        System.out.println("로봇이 파괴됩니다.");
    }
}

class TestService {
    @CustomAutowired
    private Robot robot;

    public Robot getRobot() {
        return robot;
    }

    public void start() {
        robot.fight();
        robot.clean();
    }
}

