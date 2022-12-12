package states;

import tokens.Brace;
import tokens.NumberToken;
import tokens.Operation;
import tokens.Token;

import java.util.regex.Pattern;

public class NumberState implements State {

    private Token result = null;
    private final String number;

    public NumberState(String number) {
        this.number = number;
    }

    @Override
    public State handle(char ch) {
        if (Character.isDigit(ch)) {
            return new NumberState(number + ch);
        }
        result = new NumberToken(Long.parseLong(number));

        if (ch == Operation.ADD.getSymbol()) {
            return new StartState(Operation.ADD);
        }
        if (ch == Operation.SUB.getSymbol()) {
            return new StartState(Operation.SUB);
        }
        if (ch == Operation.MUL.getSymbol()) {
            return new StartState(Operation.MUL);
        }
        if (ch == Operation.DIV.getSymbol()) {
            return new StartState(Operation.DIV);
        }
        if (ch == Brace.OPEN.getSymbol()) {
            return new StartState(Brace.OPEN);
        }
        if (ch == Brace.CLOSE.getSymbol()) {
            return new StartState(Brace.CLOSE);
        }
        if (Pattern.matches("\\s", String.valueOf(ch))) {
            return new StartState(null);
        }
        if ((int) ch == 0x03) {
            return new EndState();
        }
        return new ErrorState(
                "an unknown character was encountered: " + (int) ch
        );
    }

    @Override
    public Token getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "NumberState{" + number + "}";
    }
}
