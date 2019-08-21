package com.joshmlwood.rocketmiles;

import com.joshmlwood.rocketmiles.command.CommandHandler;
import com.joshmlwood.rocketmiles.command.ShowCommandHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommandDriverTest {
    private CommandDriver commandDriver;

    @Mock
    private CashRegister cashRegister;
    @Mock
    private Map<String, CommandHandler> handlers;

    @BeforeEach
    void setUp() {
        commandDriver = new CommandDriver(cashRegister, handlers);
    }

    @Test
    void processCommand_givenUnknown_expectNoInteractions() {
        when(handlers.containsKey("command")).thenReturn(false);

        commandDriver.processCommand("command");

        verifyNoMoreInteractions(handlers);
    }

    @Test
    void processCommand_givenValidCommandBadInput_expectHandlerCalled() {
        ShowCommandHandler mockShowCommandHandler = mock(ShowCommandHandler.class);
        when(handlers.containsKey("show")).thenReturn(true);
        when(handlers.get("show")).thenReturn(mockShowCommandHandler);
        when(mockShowCommandHandler.validateFormat(any())).thenReturn(false);

        commandDriver.processCommand("show");

        verifyNoMoreInteractions(mockShowCommandHandler);
    }

    @Test
    void processCommand_givenValidCommand_expectHandlerCalled() {
        ShowCommandHandler mockShowCommandHandler = mock(ShowCommandHandler.class);
        when(handlers.containsKey("show")).thenReturn(true);
        when(handlers.get("show")).thenReturn(mockShowCommandHandler);
        when(mockShowCommandHandler.validateFormat(any())).thenReturn(true);

        commandDriver.processCommand("show");

        verify(mockShowCommandHandler).execute(cashRegister, "show");
    }
}
