package com.joshmlwood.rocketmiles.command;

import com.joshmlwood.rocketmiles.CashRegister;

public abstract class CommandHandler {
    CommandHandler() {
    }

    public abstract boolean validateFormat(String input);

    public abstract String execute(CashRegister cashRegister, String input);

    protected int[] parseInputBills(String inputWithCommand) {
        if (inputWithCommand == null) {
            return new int[0];
        }
        String[] split = inputWithCommand.split("\\s");
        int[] parsed = new int[split.length - 1];
        for (int i = 0; i < split.length - 1; i++) {
            parsed[i] = Integer.parseInt(split[i + 1]);
        }
        return parsed;
    }
}
