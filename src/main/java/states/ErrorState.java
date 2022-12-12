package states;

import tokens.Token;

public class ErrorState implements State {
    private final String message;

    public ErrorState(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

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
        return "ErrorState{" + message + "}";
    }
}
