package tokens;

import exceptions.IncorrectExpressionException;
import visitors.TokenVisitor;

public enum Operation implements Token {
    ADD('+'),
    SUB('-'),
    MUL('*'),
    DIV('/');

    private final char symbol;

    Operation(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public void accept(TokenVisitor visitor) throws IncorrectExpressionException {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        if (this == ADD) {
            return "PLUS";
        }
        if (this == SUB) {
            return "MINUS";
        }
        if (this == MUL) {
            return "MUL";
        }
        if (this == DIV) {
            return "DIV";
        }
        return "OPERATION" + symbol;
    }

    public int getPriority() {
        if (this == ADD) {
            return 1;
        }
        if (this == SUB) {
            return 1;
        }
        if (this == MUL) {
            return 2;
        }
        if (this == DIV) {
            return 2;
        }
        throw new RuntimeException("unexpected case");
    }

    public double eval(double x, double y) {
        if (this == ADD) {
            return (x + y);
        }
        if (this == SUB) {
            return (x - y);
        }
        if (this == MUL) {
            return (x * y);
        }
        if (this == DIV) {
            return (x / y);
        }
        throw new RuntimeException("unexpected case");
    }

}
