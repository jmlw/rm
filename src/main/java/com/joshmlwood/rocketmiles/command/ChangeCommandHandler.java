package com.joshmlwood.rocketmiles.command;

import com.joshmlwood.rocketmiles.CashRegister;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ChangeCommandHandler extends CommandHandler {
    private static final String DIGITS = "\\d+";
    private static final String WHITESPACE = "\\s";
    private Pattern inputPattern = Pattern.compile(
            "^change" + WHITESPACE + //command
                    DIGITS + // total
                    "$");
    private CommandHandler takeCommandHandler;

    public ChangeCommandHandler() {
        takeCommandHandler = new TakeCommandHandler();
    }

    public ChangeCommandHandler(CommandHandler takeCommandHandler) {
        this.takeCommandHandler = takeCommandHandler;
    }

    @Override
    public String execute(CashRegister cashRegister, String input) {
        int requestedChange = parseInputBills(input)[0];
        int[] change = new int[CashRegister.Denomination.values().length];
        for (CashRegister.Denomination d : CashRegister.Denomination.values()) {
            int count = cashRegister.count(d);
            while (d.getValue() <= requestedChange && count > 0) {
                requestedChange -= d.getValue();
                change[d.ordinal()]++;
                count--;
            }
        }
        if (requestedChange > 0) {
            return "Unable to make change from till!";
        }
        String takeChange = Arrays.stream(change)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));
        System.out.println(takeChange);
        String take = Command.take.toString() + " " + takeChange;
        return takeCommandHandler.execute(cashRegister, take);
    }

    @Override
    public boolean validateFormat(String input) {
        if (input == null) {
            return false;
        }
        return inputPattern.matcher(input).matches();
    }
}
