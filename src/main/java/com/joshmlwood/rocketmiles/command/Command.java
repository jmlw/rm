package com.joshmlwood.rocketmiles.command;

import java.util.HashMap;
import java.util.Map;

public enum Command {
    change(new ChangeCommandHandler()),
    put(new PutCommandHandler()),
    quit(new QuitCommandHandler()),
    show(new ShowCommandHandler()),
    take(new TakeCommandHandler());

    private static final Map<String, CommandHandler> handlers = initializeHandles();
    private final CommandHandler handler;

    Command(CommandHandler handler) {
        this.handler = handler;
    }

    public static CommandHandler getHandler(String command) {
        if (handlers.containsKey(command)) {
            return handlers.get(command);
        }
        return null;
    }

    public static Map<String, CommandHandler> getHandlers() {
        return handlers;
    }

    private static Map<String, CommandHandler> initializeHandles() {
        Map<String, CommandHandler> handlers = new HashMap<>();
        for (Command c : Command.values()) {
            handlers.put(c.name(), c.handler);
        }
        return handlers;
    }
}
