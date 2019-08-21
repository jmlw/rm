package com.joshmlwood.rocketmiles.command;

import com.joshmlwood.rocketmiles.CashRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShowCommandHandlerTest {
    private ShowCommandHandler showCommandHandler;
    @Mock
    private CashRegister cashRegister;

    @BeforeEach
    void setUp() {
        showCommandHandler = new ShowCommandHandler();
    }

    @Test
    void execute_givenNoBills_expectZeroDollarsNoBillsPrintOut() {
        String expected = "$0 0 0 0 0 0";

        String actual = showCommandHandler.execute(cashRegister, "show");

        assertEquals(expected, actual);
    }

    @Test
    void execute_givenTwenty_expectTwentyOutput() {
        String expected = "$20 1 0 0 0 0";
        when(cashRegister.count(CashRegister.Denomination.TWENTY)).thenReturn(1);

        String actual = showCommandHandler.execute(cashRegister, "show");

        assertEquals(expected, actual);
    }

    @Test
    void execute_givenTen_expectTenOutput() {
        String expected = "$10 0 1 0 0 0";
        when(cashRegister.count(any())).thenReturn(0, 1, 0, 0, 0);

        String actual = showCommandHandler.execute(cashRegister, "show");

        assertEquals(expected, actual);
    }

    @Test
    void execute_givenFive_expectFiveOutput() {
        String expected = "$5 0 0 1 0 0";
        when(cashRegister.count(any())).thenReturn(0, 0, 1, 0, 0);

        String actual = showCommandHandler.execute(cashRegister, "show");

        assertEquals(expected, actual);
    }

    @Test
    void execute_givenTwo_expectTwoOutput() {
        String expected = "$2 0 0 0 1 0";
        when(cashRegister.count(any())).thenReturn(0, 0, 0, 1, 0);

        String actual = showCommandHandler.execute(cashRegister, "show");

        assertEquals(expected, actual);
    }

    @Test
    void execute_givenOne_expectOneOutput() {
        String expected = "$1 0 0 0 0 1";
        when(cashRegister.count(any())).thenReturn(0, 0, 0, 0, 1);

        String actual = showCommandHandler.execute(cashRegister, "show");

        assertEquals(expected, actual);
    }

    @Test
    void execute_givenAll_expectCorrectOutput() {
        String expected = "$38 1 1 1 1 1";
        when(cashRegister.count(any(CashRegister.Denomination.class))).thenReturn(1);

        String actual = showCommandHandler.execute(cashRegister, "show");

        assertEquals(expected, actual);
    }

    @Test
    void validateFormat_givenNullInput_expectFalse() {
        assertFalse(showCommandHandler.validateFormat(null));
    }

    @Test
    void validateFormat_givenBadInput_expectFalse() {
        assertFalse(showCommandHandler.validateFormat("anything"));
    }

    @Test
    void validateFormat_givenCorrectInput_expectTrue() {
        assertTrue(showCommandHandler.validateFormat("show"));
    }
}
