package com.joshmlwood.rocketmiles.command;

import com.joshmlwood.rocketmiles.CashRegister;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandHandlerTest {

    CommandHandler testCommandHandler = new TestCommandHandler();

    @Test
    void parseInputBills_givenEmptyString_expectEmptyArray() {
        int[] expected = new int[]{};

        int[] actual = testCommandHandler.parseInputBills("");

        assertArrayEquals(expected, actual);
    }

    @Test
    void parseInputBills_givenSingleNumber_expectEmptyArray() {
        int[] expected = new int[]{};

        int[] actual = testCommandHandler.parseInputBills("10");

        assertArrayEquals(expected, actual);
    }

    @Test
    void parseInputBills_givenTwoNumbers_expectSecond() {
        int[] expected = new int[]{2};

        int[] actual = testCommandHandler.parseInputBills("10 2");

        assertArrayEquals(expected, actual);
    }

    @Test
    void parseInputBills_givenCommandAndNumber_expectNumber() {
        int[] expected = new int[]{2};

        int[] actual = testCommandHandler.parseInputBills("command 2");

        assertArrayEquals(expected, actual);
    }

    @Test
    void parseInputBills_givenNull_expectEmptyArray() {
        int[] expected = new int[]{};

        int[] actual = testCommandHandler.parseInputBills(null);

        assertArrayEquals(expected, actual);
    }

    private class TestCommandHandler extends CommandHandler {
        @Override
        public boolean validateFormat(String input) {
            return false;
        }

        @Override
        public String execute(CashRegister cashRegister, String input) {
            return null;
        }
    }
}
