package tokens;

import exceptions.IncorrectExpressionException;
import visitors.TokenVisitor;

public enum Brace implements Token {
    OPEN('('),
    CLOSE(')');

    private final char symbol;

    Brace(char symbol) {
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
        if (this == OPEN) {
            return "LEFT";
        }
        if (this == CLOSE) {
            return "RIGHT";
        }
        return "BRACE" + symbol;
    }
}
