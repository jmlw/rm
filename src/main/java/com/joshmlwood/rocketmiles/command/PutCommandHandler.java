package com.joshmlwood.rocketmiles.command;

import com.joshmlwood.rocketmiles.CashRegister;

import java.util.regex.Pattern;

public class PutCommandHandler extends CommandHandler {
    private static final String DIGITS = "\\d+";
    private static final String WHITESPACE = "\\s";
    private Pattern inputPattern = Pattern.compile(
            "^put" + WHITESPACE + //command
                    DIGITS + WHITESPACE + // twenties
                    DIGITS + WHITESPACE + // tens
                    DIGITS + WHITESPACE + // fives
                    DIGITS + WHITESPACE + // twos
                    DIGITS + // ones
                    "$");

    @Override
    public String execute(CashRegister cashRegister, String input) {
        int[] bills = parseInputBills(input);
        for (int i = 0; i < bills.length; i++) {
            cashRegister.insert(CashRegister.Denomination.values()[i], bills[i]);
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
