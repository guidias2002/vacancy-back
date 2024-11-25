package com.gcd.vacancy.enums;

import com.gcd.vacancy.exceptions.customExceptions.InvalidDateFieldException;
import lombok.Getter;

@Getter
public enum Month {
    JANEIRO(1), FEVEREIRO(2), MARCO(3), ABRIL(4), MAIO(5), JUNHO(6),
    JULHO(7), AGOSTO(8), SETEMBRO(9), OUTUBRO(10), NOVEMBRO(11), DEZEMBRO(12);

    private final int value;

    Month(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static int getMonthValue(String monthName) {
        try {
            return Month.valueOf(monthName.toUpperCase()).getValue();
        } catch (IllegalArgumentException e) {
            throw new InvalidDateFieldException("Mês inválido: " + monthName);
        }
    }
}
