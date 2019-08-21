package com.joshmlwood.rocketmiles.command;

import com.joshmlwood.rocketmiles.CashRegister;

public class QuitCommandHandler extends CommandHandler {
    @Override
    public String execute(CashRegister cashRegister, String input) {
        System.out.println("Bye!");
        exit();
        return null;
    }

    @Override
    public boolean validateFormat(String input) {
        return true;
    }

    protected void exit() {
        System.exit(0);
    }
}
