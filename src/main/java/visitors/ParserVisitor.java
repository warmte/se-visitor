package visitors;

import exceptions.IncorrectExpressionException;
import tokens.Brace;
import tokens.NumberToken;
import tokens.Operation;
import tokens.Token;

import java.util.ArrayList;

public class ParserVisitor implements TokenVisitor {

    private final ArrayList<Token> tokens;
    private final ArrayList<Token> result;
    private final ArrayList<Token> stack;

    public ParserVisitor(ArrayList<Token> tokens) {
        this.tokens = tokens;
        this.result = new ArrayList<>();
        this.stack = new ArrayList<>();
    }

    public void run() throws IncorrectExpressionException {
        result.clear();
        stack.clear();
        stack.add(Brace.OPEN);

        for (Token token : tokens) {
            token.accept(this);
        }

        Brace.CLOSE.accept(this);

        if (!stack.isEmpty()) {
            throw new IncorrectExpressionException(
                    "incorrect expression"
            );
        }
    }

    public ArrayList<Token> getResult() {
        return result;
    }

    @Override
    public void visit(NumberToken token) {
        result.add(token);
    }

    @Override
    public void visit(Brace token) throws IncorrectExpressionException {
        if (token == Brace.OPEN) {
            stack.add(token);
        } else if (token == Brace.CLOSE) {
            boolean flag = true;
            while (!stack.isEmpty()) {
                Token t = stack.get(stack.size() - 1);
                if (t instanceof Brace) {
                    Brace b = (Brace) t;
                    if (b == Brace.OPEN) {
                        stack.remove(stack.size() - 1);
                        flag = false;
                        break;
                    }
                } else {
                    stack.remove(stack.size() - 1);
                    result.add(t);
                }
            }
            if (stack.isEmpty() && flag) {
                throw new IncorrectExpressionException(
                        "missing opening bracket"
                );
            }
        } else {
            throw new RuntimeException("unexpected case");
        }
    }

    @Override
    public void visit(Operation token) {
        while (!stack.isEmpty()) {
            Token t = stack.get(stack.size() - 1);
            if (t instanceof Operation) {
                Operation op = (Operation) t;
                if (op.getPriority() >= token.getPriority()) {
                    stack.remove(stack.size() - 1);
                    result.add(op);
                    continue;
                }
            }
            break;
        }
        stack.add(token);
    }
}
