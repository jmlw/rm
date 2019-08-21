package com.joshmlwood.rocketmiles.command;

import com.joshmlwood.rocketmiles.CashRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShowCommandHandler extends CommandHandler {
    private static final String COMMAND = "show";

    @Override
    public String execute(CashRegister cashRegister, String input) {
        List<Integer> currentValues = new ArrayList<>();
        int currentTotal = 0;
        for (CashRegister.Denomination d : CashRegister.Denomination.values()) {
            Integer count = cashRegister.count(d);
            currentValues.add(count);
            currentTotal += count * d.getValue();
        }
        String billCounts = currentValues.stream().map(Object::toString).collect(Collectors.joining(" "));
        return String.format("$%s %s", currentTotal, billCounts);
    }

    @Override
    public boolean validateFormat(String input) {
        return COMMAND.equals(input);
    }
}
