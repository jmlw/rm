package com.joshmlwood.rocketmiles.command;

import com.joshmlwood.rocketmiles.CashRegister;

public class NoOpCommandHandler extends CommandHandler {
    @Override
    public boolean validateFormat(String input) {
        return true;
    }

    @Override
    public String execute(CashRegister cashRegister, String input) {
        return input;
    }
}
