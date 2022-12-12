package visitors;

import exceptions.IncorrectExpressionException;
import tokens.Brace;
import tokens.NumberToken;
import tokens.Operation;

public interface TokenVisitor {
    void visit(NumberToken token);

    void visit(Brace token) throws IncorrectExpressionException;

    void visit(Operation token) throws IncorrectExpressionException;
}
