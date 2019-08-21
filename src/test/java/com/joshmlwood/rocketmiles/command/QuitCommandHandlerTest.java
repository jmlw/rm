package com.joshmlwood.rocketmiles.command;

import com.joshmlwood.rocketmiles.CashRegister;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuitCommandHandlerTest {
    @Test
    void execute_givenNoOpExit_expectNullReturn() {
        QuitCommandHandler quitCommandHandler = new TestQuitCommandHandler();

        String output = quitCommandHandler.execute(new CashRegister(), null);

        assertNull(output);
    }

    @Test
    void validateFormat_givenAnyInput_expectTrue() {
        QuitCommandHandler quitCommandHandler = new QuitCommandHandler();

        assertTrue(quitCommandHandler.validateFormat("any input string"));
    }

    @Test
    void validateFormat_givenNull_expectTrue() {
        QuitCommandHandler quitCommandHandler = new QuitCommandHandler();

        assertTrue(quitCommandHandler.validateFormat(null));
    }

    private class TestQuitCommandHandler extends QuitCommandHandler {
        @Override
        protected void exit() {
            System.out.println("NO OP EXIT");
            // pass
        }
    }
}
