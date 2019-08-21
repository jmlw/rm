package com.joshmlwood.rocketmiles;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        runApplicationLoop();
    }

    private static void runApplicationLoop() {
        CommandDriver commandDriver = new CommandDriver();
        boolean done = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ready...");
        while (!done && scanner.hasNextLine()) {
            String input = scanner.nextLine();
            commandDriver.processCommand(input);
        }
    }
}
