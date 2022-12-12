package states;

import tokens.Token;

public interface State {
    State handle(char ch);

    Token getResult();
}
