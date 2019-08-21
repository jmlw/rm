package com.joshmlwood.rocketmiles.command;

import com.joshmlwood.rocketmiles.CashRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChangeCommandHandlerTest {
    private ChangeCommandHandler changeCommandHandler;

    @Mock
    private CashRegister cashRegister;
    @Mock
    private TakeCommandHandler takeCommandHandler;

    @BeforeEach
    void setUp() {
        changeCommandHandler = new ChangeCommandHandler(takeCommandHandler);
    }

    @Test
    void execute_givenNoBills_expectZeroDollarsNoBillsPrintOut() {
        String expected = "take 0 0 0 0 0";
        String given = "change 0";

        changeCommandHandler.execute(cashRegister, given);

        verify(takeCommandHandler).execute(cashRegister, expected);
    }

    @Test
    void execute_givenTwenty_expectTwentyOutput() {
        String expected = "take 1 0 0 0 0";
        String given = "change 20";
        when(cashRegister.count(CashRegister.Denomination.TWENTY)).thenReturn(1);

        changeCommandHandler.execute(cashRegister, given);

        verify(takeCommandHandler).execute(cashRegister, expected);
    }

    @Test
    void execute_givenTen_expectTenOutput() {
        String expected = "take 0 1 0 0 0";
        String given = "change 10";
        when(cashRegister.count(any())).thenReturn(0, 1, 0, 0, 0);

        changeCommandHandler.execute(cashRegister, given);

        verify(takeCommandHandler).execute(cashRegister, expected);
    }

    @Test
    void execute_givenFive_expectFiveOutput() {
        String expected = "take 0 0 1 0 0";
        String given = "change 5";
        when(cashRegister.count(any())).thenReturn(0, 0, 1, 0, 0);

        changeCommandHandler.execute(cashRegister, given);

        verify(takeCommandHandler).execute(cashRegister, expected);
    }

    @Test
    void execute_givenTwo_expectTwoOutput() {
        String expected = "take 0 0 0 1 0";
        String given = "change 2";
        when(cashRegister.count(any())).thenReturn(0, 0, 0, 1, 0);

        changeCommandHandler.execute(cashRegister, given);

        verify(takeCommandHandler).execute(cashRegister, expected);
    }

    @Test
    void execute_givenOne_expectOneOutput() {
        String expected = "take 0 0 0 0 1";
        String given = "change 1";
        when(cashRegister.count(any())).thenReturn(0, 0, 0, 0, 1);

        changeCommandHandler.execute(cashRegister, given);

        verify(takeCommandHandler).execute(cashRegister, expected);
    }

    @Test
    void execute_givenFourteenAndEnoughInTill_expectCorrectOutput() {
        String expected = "take 0 0 2 2 0";
        String given = "change 14";
        when(cashRegister.count(any(CashRegister.Denomination.class))).thenReturn(0, 0, 3, 2, 1);

        changeCommandHandler.execute(cashRegister, given);

        verify(takeCommandHandler).execute(cashRegister, expected);
    }

    @Test
    void validateFormat_givenGoodString_expectTrue() {
        String given = "change 10";

        assertTrue(changeCommandHandler.validateFormat(given));
    }

    @Test
    void validateFormat_givenNull_expectFalse() {
        assertFalse(changeCommandHandler.validateFormat(null));
    }

    @Test
    void validateFormat_givenEmpty_expectFalse() {
        String given = "";

        assertFalse(changeCommandHandler.validateFormat(given));
    }

    @Test
    void validateFormat_givenBadString_expectFalse() {
        String given = "change 0 0";

        assertFalse(changeCommandHandler.validateFormat(given));
    }

    @Test
    void validateFormat_givenBadStringExtraDigits_expectFalse() {
        String given = "change";

        assertFalse(changeCommandHandler.validateFormat(given));
    }
}
