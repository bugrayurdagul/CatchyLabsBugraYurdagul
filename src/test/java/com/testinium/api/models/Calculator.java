package com.testinium.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Calculator {
    private String number1;
    private String number2;

    public Calculator(String number1, String number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

}
