package com.gcd.vacancy.enums;

import com.gcd.vacancy.exceptions.customExceptions.InvalidDateFieldException;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

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

    private static final Map<String, String> MONTH_ALIASES = new HashMap<>();

    static {
        MONTH_ALIASES.put("JANEIRO", "JANEIRO");
        MONTH_ALIASES.put("FEVEREIRO", "FEVEREIRO");
        MONTH_ALIASES.put("MARÇO", "MARCO");
        MONTH_ALIASES.put("ABRIL", "ABRIL");
        MONTH_ALIASES.put("MAIO", "MAIO");
        MONTH_ALIASES.put("JUNHO", "JUNHO");
        MONTH_ALIASES.put("JULHO", "JULHO");
        MONTH_ALIASES.put("AGOSTO", "AGOSTO");
        MONTH_ALIASES.put("SETEMBRO", "SETEMBRO");
        MONTH_ALIASES.put("OUTUBRO", "OUTUBRO");
        MONTH_ALIASES.put("NOVEMBRO", "NOVEMBRO");
        MONTH_ALIASES.put("DEZEMBRO", "DEZEMBRO");
    }


    public static Integer getMonthValue(String monthName) {
        if (monthName == null || monthName.trim().isEmpty()) {
            return null;
        }

        String normalizedMonth = MONTH_ALIASES.get(monthName.toUpperCase());
        if (normalizedMonth == null) {
            throw new InvalidDateFieldException("Mês inválido: " + monthName);
        }

        return Month.valueOf(normalizedMonth).getValue();
    }
}
