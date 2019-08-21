package com.joshmlwood.rocketmiles.command;

import com.joshmlwood.rocketmiles.CashRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PutCommandHandlerTest {
    private PutCommandHandler putCommandHandler;

    @Mock
    private CashRegister cashRegister;

    @BeforeEach
    void setUp() {
        putCommandHandler = new PutCommandHandler();
    }

    @Test
    void execute_givenNoBills_expectZeroAdded() {
        String given = "put 0 0 0 0 0";

        putCommandHandler.execute(cashRegister, given);

        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TWENTY, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TEN, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.FIVE, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TWO, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.ONE, 0);
    }

    @Test
    void execute_givenTwenty_expectTwentyAdded() {
        String given = "put 1 0 0 0 0";

        putCommandHandler.execute(cashRegister, given);

        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TWENTY, 1);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TEN, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.FIVE, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TWO, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.ONE, 0);
    }

    @Test
    void execute_givenTen_expectTenAdded() {
        String given = "put 0 1 0 0 0";

        putCommandHandler.execute(cashRegister, given);

        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TWENTY, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TEN, 1);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.FIVE, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TWO, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.ONE, 0);
    }

    @Test
    void execute_givenFive_expectFiveAdded() {
        String given = "put 0 0 1 0 0";

        putCommandHandler.execute(cashRegister, given);

        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TWENTY, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TEN, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.FIVE, 1);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TWO, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.ONE, 0);
    }

    @Test
    void execute_givenTwo_expectTwoAdded() {
        String given = "put 0 0 0 1 0";

        putCommandHandler.execute(cashRegister, given);

        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TWENTY, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TEN, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.FIVE, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TWO, 1);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.ONE, 0);
    }

    @Test
    void execute_givenOne_expectOneAdded() {
        String given = "put 0 0 0 0 1";

        putCommandHandler.execute(cashRegister, given);

        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TWENTY, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TEN, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.FIVE, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TWO, 0);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.ONE, 1);
    }

    @Test
    void execute_givenAll_expectAllAdded() {
        String given = "put 1 1 1 1 1";

        putCommandHandler.execute(cashRegister, given);

        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TWENTY, 1);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TEN, 1);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.FIVE, 1);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.TWO, 1);
        Mockito.verify(cashRegister).insert(CashRegister.Denomination.ONE, 1);
    }

    @Test
    void validateFormat_givenGoodString_expectTrue() {
        String given = "put 0 0 0 0 0";

        assertTrue(putCommandHandler.validateFormat(given));
    }

    @Test
    void validateFormat_givenNull_expectFalse() {
        assertFalse(putCommandHandler.validateFormat(null));
    }

    @Test
    void validateFormat_givenEmpty_expectFalse() {
        String given = "";

        assertFalse(putCommandHandler.validateFormat(given));
    }

    @Test
    void validateFormat_givenBadString_expectFalse() {
        String given = "put 0 0 0 0";

        assertFalse(putCommandHandler.validateFormat(given));
    }

    @Test
    void validateFormat_givenBadStringExtraDigits_expectFalse() {
        String given = "put 0 0 0 0 0 0";

        assertFalse(putCommandHandler.validateFormat(given));
    }
}
