package tokens;

import exceptions.IncorrectExpressionException;
import visitors.TokenVisitor;

public interface Token {
    void accept(TokenVisitor visitor) throws IncorrectExpressionException;
}
