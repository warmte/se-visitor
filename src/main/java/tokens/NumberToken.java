package tokens;

import visitors.TokenVisitor;

public class NumberToken implements Token {

    private final long value;

    public NumberToken(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "NUMBER(" + value + ")";
    }
}
