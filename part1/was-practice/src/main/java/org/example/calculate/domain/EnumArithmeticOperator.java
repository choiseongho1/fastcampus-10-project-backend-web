package org.example.calculate.domain;

import java.util.Arrays;

public enum EnumArithmeticOperator {
    ADDITION("+") {
        @Override
        public int arithmeticCalculate(int operand1, int operand2) {
            return operand1 + operand2;
        }
    }, SUBTRACTION("-") {
        @Override
        public int arithmeticCalculate(int operand1, int operand2) {
            return operand1 - operand2;
        }
    }, MULTIPLICATION("*") {
        @Override
        public int arithmeticCalculate(int operand1, int operand2) {
            return operand1 * operand2;
        }
    }, DIVISION("/") {
        @Override
        public int arithmeticCalculate(int operand1, int operand2) {
            return operand1 / operand2;
        }
    };

    private final String operator;

    EnumArithmeticOperator(String operator) {
        this.operator = operator;
    }

    public abstract int arithmeticCalculate(final int operand1, final int operand2);

    public static int calculate(int operand1, String operator, int operand2) throws Exception {
        EnumArithmeticOperator arithmeticOperator = Arrays.stream((values()))
                .filter(v -> v.operator.equals(operator))
                .findFirst()
                .orElseThrow(()-> new IllegalAccessException("올바른 사칙 연산이 아닙니다."));

        return arithmeticOperator.arithmeticCalculate(operand1, operand2);
    }
}
