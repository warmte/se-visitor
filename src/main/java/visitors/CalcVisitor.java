package visitors;

import exceptions.IncorrectExpressionException;
import tokens.Brace;
import tokens.NumberToken;
import tokens.Operation;
import tokens.Token;

import java.util.ArrayList;

public class CalcVisitor implements TokenVisitor {

    private final ArrayList<Token> tokens;
    private double result = 0;
    private final ArrayList<Double> stack;

    public CalcVisitor(ArrayList<Token> tokens) {
        this.tokens = tokens;
        this.stack = new ArrayList<>();
    }

    public void run() throws IncorrectExpressionException {
        stack.clear();

        for (Token token : tokens) {
            token.accept(this);
        }

        if (stack.size() != 1) {
            throw new IncorrectExpressionException(
                    "incorrect expression"
            );
        }
        result = stack.get(0);
    }

    public double getResult() {
        return result;
    }

    @Override
    public void visit(NumberToken token) {
        stack.add((double) token.getValue());
    }

    @Override
    public void visit(Brace token) throws IncorrectExpressionException {
        throw new IncorrectExpressionException(
                "unexpected parenthesis in postfix notation"
        );
    }

    @Override
    public void visit(Operation token) throws IncorrectExpressionException {
        if (stack.size() >= 2) {
            double y = stack.remove(stack.size() - 1);
            double x = stack.remove(stack.size() - 1);
            stack.add(token.eval(x, y));
        } else {
            throw new IncorrectExpressionException(
                    "incorrect expression, expected two argument"
            );
        }
    }
}
