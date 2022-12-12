package states;

import tokens.Token;

public class EndState implements State {
    @Override
    public State handle(char ch) {
        return this;
    }

    @Override
    public Token getResult() {
        return null;
    }

    @Override
    public String toString() {
        return "EndState{}";
    }
}
