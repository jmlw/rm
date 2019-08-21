package com.joshmlwood.rocketmiles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CashRegisterTest {

    private TestCashRegister cashRegister;

    @BeforeEach
    void setUp() {
        cashRegister = new TestCashRegister();
    }

    @Test
    void insert_givenTwenty_expectSingleTwentyAdded() {
        cashRegister.insert(CashRegister.Denomination.TWENTY, 1);

        assertEquals(cashRegister.getBills().get(CashRegister.Denomination.TWENTY.getValue()), Integer.valueOf(1));
    }

    @Test
    void insert_givenNoTwenty_expectNoTwentyAdded() {
        cashRegister.insert(CashRegister.Denomination.TWENTY, 0);

        assertEquals(cashRegister.getBills().get(CashRegister.Denomination.TWENTY.getValue()), Integer.valueOf(0));
    }

    @Test
    void insert_givenTen_expectSingleTenAdded() {
        cashRegister.insert(CashRegister.Denomination.TEN, 1);

        assertEquals(cashRegister.getBills().get(CashRegister.Denomination.TEN.getValue()), Integer.valueOf(1));
    }

    @Test
    void insert_givenNoTen_expectNoTenAdded() {
        cashRegister.insert(CashRegister.Denomination.TEN, 0);

        assertEquals(cashRegister.getBills().get(CashRegister.Denomination.TEN.getValue()), Integer.valueOf(0));
    }

    @Test
    void insert_givenFive_expectSingleFiveAdded() {
        cashRegister.insert(CashRegister.Denomination.FIVE, 1);

        assertEquals(cashRegister.getBills().get(CashRegister.Denomination.FIVE.getValue()), Integer.valueOf(1));
    }

    @Test
    void insert_givenNoFive_expectNoFiveAdded() {
        cashRegister.insert(CashRegister.Denomination.FIVE, 0);

        assertEquals(cashRegister.getBills().get(CashRegister.Denomination.FIVE.getValue()), Integer.valueOf(0));
    }

    @Test
    void insert_givenTwo_expectSingleTwoAdded() {
        cashRegister.insert(CashRegister.Denomination.TWO, 1);

        assertEquals(cashRegister.getBills().get(CashRegister.Denomination.TWO.getValue()), Integer.valueOf(1));
    }

    @Test
    void insert_givenNoTwo_expectNoTwoAdded() {
        cashRegister.insert(CashRegister.Denomination.TWO, 0);

        assertEquals(cashRegister.getBills().get(CashRegister.Denomination.TWO.getValue()), Integer.valueOf(0));
    }

    @Test
    void insert_givenOne_expectSingleOneAdded() {
        cashRegister.insert(CashRegister.Denomination.ONE, 1);

        assertEquals(cashRegister.getBills().get(CashRegister.Denomination.ONE.getValue()), Integer.valueOf(1));
    }

    @Test
    void insert_givenNoOne_expectNoOneAdded() {
        cashRegister.insert(CashRegister.Denomination.ONE, 0);

        assertEquals(cashRegister.getBills().get(CashRegister.Denomination.ONE.getValue()), Integer.valueOf(0));
    }

    @Test
    void take_givenExistingBill_expectRemoved() {
        cashRegister.insert(CashRegister.Denomination.ONE, 1);

        cashRegister.take(CashRegister.Denomination.ONE, 1);

        assertEquals(cashRegister.getBills().get(CashRegister.Denomination.ONE.getValue()), Integer.valueOf(0));
    }

    @Test
    void take_givenNoExistingBill_expectException() {
        assertThrows(CashRegister.InsufficientFundsException.class,
                () -> cashRegister.take(CashRegister.Denomination.ONE, 1));
    }

    @Test
    void count_givenExistingBill_expectOne() {
        cashRegister.insert(CashRegister.Denomination.ONE, 1);

        Integer actual = cashRegister.count(CashRegister.Denomination.ONE);

        assertEquals(actual, Integer.valueOf(1));
    }

    @Test
    void count_givenNoExistingBill_expectZero() {
        Integer actual = cashRegister.count(CashRegister.Denomination.ONE);

        assertEquals(actual, Integer.valueOf(0));
    }

    private class TestCashRegister extends CashRegister {
        Map<Integer, Integer> getBills() {
            return this.bills;
        }
    }
}
