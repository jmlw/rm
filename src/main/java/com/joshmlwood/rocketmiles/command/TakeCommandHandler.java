package com.joshmlwood.rocketmiles.command;

import com.joshmlwood.rocketmiles.CashRegister;

import java.util.regex.Pattern;

public class TakeCommandHandler extends CommandHandler {
    private static final String DIGITS = "\\d+";
    private static final String WHITESPACE = "\\s";
    private Pattern inputPattern = Pattern.compile(
            "^take" + WHITESPACE + //command
                    DIGITS + WHITESPACE + // twenties
                    DIGITS + WHITESPACE + // tens
                    DIGITS + WHITESPACE + // fives
                    DIGITS + WHITESPACE + // twos
                    DIGITS + // ones
                    "$");

    @Override
    public String execute(CashRegister cashRegister, String input) {
        int[] requestedTake = parseInputBills(input);

        for (CashRegister.Denomination d : CashRegister.Denomination.values()) {
            int count = cashRegister.count(d);
            if (count < requestedTake[d.ordinal()]) {
                return "Insufficient funds to take!";
            }
        }

        for (int i = 0; i < requestedTake.length; i++) {
            cashRegister.take(CashRegister.Denomination.values()[i], requestedTake[i]);

        }

        return Command.getHandler(Command.show.toString()).execute(cashRegister, Command.show.toString());
    }

    @Override
    public boolean validateFormat(String input) {
        if (input == null) {
            return false;
        }
        return inputPattern.matcher(input).matches();
    }
}
