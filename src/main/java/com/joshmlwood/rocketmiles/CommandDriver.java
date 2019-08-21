package com.joshmlwood.rocketmiles;

import com.joshmlwood.rocketmiles.command.Command;
import com.joshmlwood.rocketmiles.command.CommandHandler;

import java.util.Arrays;
import java.util.Map;

public class CommandDriver {
    private CashRegister cashRegister;
    private Map<String, CommandHandler> handlers = Command.getHandlers();

    public CommandDriver() {
        cashRegister = new CashRegister();
    }

    CommandDriver(CashRegister cashRegister, Map<String, CommandHandler> handlers) {
        this.cashRegister = cashRegister;
        this.handlers = handlers;
    }

    public void processCommand(String input) {
        CommandHandler commandHandler = chooseCommand(input);
        if (commandHandler == null) {
            System.out.println("Invalid command. Type \"quit\" to quit");
        } else {
            evaluateCommand(commandHandler, input);
        }
    }

    private void evaluateCommand(CommandHandler commandHandler, String input) {
        if (commandHandler.validateFormat(input)) {
            System.out.println(commandHandler.execute(this.cashRegister, input));
        } else {
            System.out.println("Invalid command format");
        }
    }

    private CommandHandler chooseCommand(String input) {
        String command = getCommand(input);
        if (handlers.containsKey(command)) {
            return handlers.get(command);
        }
        return null;
    }

    private String getCommand(String input) {
        return Arrays.stream(input.split(" ")).findFirst().orElse(null);
    }
}
