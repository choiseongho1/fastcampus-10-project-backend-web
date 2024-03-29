package org.example.calculate.domain;

import org.example.calculate.tobe.MultiplicationOperator;
import org.example.calculate.tobe.ArithmeticOperator;
import org.example.calculate.tobe.SubtractionOperator;
import org.example.calculate.tobe.AdditionOperator;
import org.example.calculate.tobe.DivisionOperator;

import java.util.List;

public class Calculator {

    private static final List<ArithmeticOperator> arithmeticOperators
            = List.of(new AdditionOperator(), new SubtractionOperator(), new MultiplicationOperator(), new DivisionOperator());


    public static int calculate(PositiveNumber operand1, String operator, PositiveNumber operand2) {
        return arithmeticOperators.stream()
                .filter(arithmeticOperator -> arithmeticOperator.supports(operator))
                .map(arithmeticOperator -> arithmeticOperator.calculate(operand1, operand2))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("올바른 사칙연산이 아닙니다"));

//        return 0;

    }
}
