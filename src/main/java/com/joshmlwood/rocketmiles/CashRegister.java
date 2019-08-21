package com.joshmlwood.rocketmiles;

import java.util.HashMap;
import java.util.Map;

public class CashRegister {
    public enum Denomination {
        TWENTY(20),
        TEN(10),
        FIVE(5),
        TWO(2),
        ONE(1);

        private int value;

        Denomination(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    protected Map<Integer, Integer> bills = new HashMap<>();

    public CashRegister() {
    }

    public void insert(Denomination denomination, Integer bills) {
        Integer count = count(denomination);
        this.bills.put(denomination.getValue(), count + bills);
    }

    public void take(Denomination denomination, Integer bills) {
        Integer count = count(denomination);
        if (count == 0 && bills > 0) {
            throw new InsufficientFundsException("Insufficient funds for denomination "+ denomination.name() + "!");
        }
        this.bills.put(denomination.getValue(), count - bills);
    }

    public Integer count(Denomination denomination) {
        return this.bills.getOrDefault(denomination.getValue(), 0);
    }

    public class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }
}
