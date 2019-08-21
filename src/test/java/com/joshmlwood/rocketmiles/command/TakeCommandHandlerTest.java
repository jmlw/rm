package com.joshmlwood.rocketmiles.command;

import com.joshmlwood.rocketmiles.CashRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TakeCommandHandlerTest {
    private TakeCommandHandler takeCommandHandler;

    @Mock
    private CashRegister cashRegister;

    @BeforeEach
    void setUp() {
        takeCommandHandler = new TakeCommandHandler();
    }


    @Test
    void execute_givenNoBills_expectZeroRemoved() throws Exception {
        String given = "take 0 0 0 0 0";
        when(cashRegister.count(any())).thenReturn(0, 0, 0, 1, 1);

        takeCommandHandler.execute(cashRegister, given);

        Mockito.verify(cashRegister).take(CashRegister.Denomination.TWENTY, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.TEN, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.FIVE, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.TWO, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.ONE, 0);
    }

    @Test
    void execute_givenTwenty_expectTwentyRemoved() throws Exception {
        String given = "take 1 0 0 0 0";
        when(cashRegister.count(any())).thenReturn(1, 0, 0, 0, 0);

        takeCommandHandler.execute(cashRegister, given);

        Mockito.verify(cashRegister).take(CashRegister.Denomination.TWENTY, 1);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.TEN, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.FIVE, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.TWO, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.ONE, 0);
    }

    @Test
    void execute_givenTen_expectTenRemoved() throws Exception {
        String given = "take 0 1 0 0 0";
        when(cashRegister.count(any())).thenReturn(0, 1, 0, 0, 0);

        takeCommandHandler.execute(cashRegister, given);

        Mockito.verify(cashRegister).take(CashRegister.Denomination.TWENTY, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.TEN, 1);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.FIVE, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.TWO, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.ONE, 0);
    }

    @Test
    void execute_givenFive_expectFiveRemoved() throws Exception {
        String given = "take 0 0 1 0 0";
        when(cashRegister.count(any())).thenReturn(0, 0, 1, 0, 0);

        takeCommandHandler.execute(cashRegister, given);

        Mockito.verify(cashRegister).take(CashRegister.Denomination.TWENTY, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.TEN, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.FIVE, 1);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.TWO, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.ONE, 0);
    }

    @Test
    void execute_givenTwo_expectTwoRemoved() throws Exception {
        String given = "take 0 0 0 1 0";
        when(cashRegister.count(any())).thenReturn(0, 0, 0, 1, 1);

        takeCommandHandler.execute(cashRegister, given);

        Mockito.verify(cashRegister).take(CashRegister.Denomination.TWENTY, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.TEN, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.FIVE, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.TWO, 1);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.ONE, 0);
    }

    @Test
    void execute_givenOne_expectOneRemoved() throws Exception {
        String given = "take 0 0 0 0 1";
        when(cashRegister.count(any())).thenReturn(0, 0, 0, 0, 1);

        takeCommandHandler.execute(cashRegister, given);

        Mockito.verify(cashRegister).take(CashRegister.Denomination.TWENTY, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.TEN, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.FIVE, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.TWO, 0);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.ONE, 1);
    }

    @Test
    void execute_givenAll_expectAllRemoved() throws Exception {
        String given = "take 1 1 1 1 1";
        when(cashRegister.count(any())).thenReturn(1, 1, 1, 1, 1);

        takeCommandHandler.execute(cashRegister, given);

        Mockito.verify(cashRegister).take(CashRegister.Denomination.TWENTY, 1);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.TEN, 1);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.FIVE, 1);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.TWO, 1);
        Mockito.verify(cashRegister).take(CashRegister.Denomination.ONE, 1);
    }

    @Test
    void execute_givenInsufficientTwenty_expectNoneRemoved() throws Exception {
        String given = "take 1 0 0 0 0";
        String expected = "Insufficient funds to take!";
        when(cashRegister.count(any())).thenReturn(0, 1, 1, 1, 1);

        String actual = takeCommandHandler.execute(cashRegister, given);

        Mockito.verify(cashRegister, times(0)).take(CashRegister.Denomination.TWENTY, 1);
        Mockito.verify(cashRegister, times(0)).take(CashRegister.Denomination.TEN, 1);
        Mockito.verify(cashRegister, times(0)).take(CashRegister.Denomination.FIVE, 1);
        Mockito.verify(cashRegister, times(0)).take(CashRegister.Denomination.TWO, 1);
        Mockito.verify(cashRegister, times(0)).take(CashRegister.Denomination.ONE, 1);
    }

    @Test
    void validateFormat_givenGoodString_expectTrue() {
        String given = "take 0123 044 12341234 0 0";

        assertTrue(takeCommandHandler.validateFormat(given));
    }

    @Test
    void validateFormat_givenNull_expectFalse() {
        assertFalse(takeCommandHandler.validateFormat(null));
    }

    @Test
    void validateFormat_givenEmpty_expectFalse() {
        String given = "";

        assertFalse(takeCommandHandler.validateFormat(given));
    }

    @Test
    void validateFormat_givenBadString_expectFalse() {
        String given = "take 0 0 0 0";

        assertFalse(takeCommandHandler.validateFormat(given));
    }

    @Test
    void validateFormat_givenBadStringExtraDigits_expectFalse() {
        String given = "take 0 0 0 0 0 0";

        assertFalse(takeCommandHandler.validateFormat(given));
    }
}
