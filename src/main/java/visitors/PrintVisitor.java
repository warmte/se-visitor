package visitors;

import exceptions.IncorrectExpressionException;
import tokens.Brace;
import tokens.NumberToken;
import tokens.Operation;
import tokens.Token;

import java.io.Closeable;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PrintVisitor implements TokenVisitor, Closeable {

    private final ArrayList<Token> tokens;
    private final PrintWriter out;

    public PrintVisitor(
            ArrayList<Token> tokens,
            OutputStream outputStream
    ) {
        this.tokens = tokens;
        this.out = new PrintWriter(outputStream);
    }

    public void run() throws IncorrectExpressionException {
        for (Token token : tokens) {
            token.accept(this);
        }
    }

    @Override
    public void visit(NumberToken token) {
        out.print(token);
        out.print(" ");
    }

    @Override
    public void visit(Brace token) {
        out.print(token);
        out.print(" ");
    }

    @Override
    public void visit(Operation token) {
        out.print(token);
        out.print(" ");
    }

    @Override
    public void close() {
        out.close();
    }
}
