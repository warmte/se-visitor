import tokens.Token;
import visitors.CalcVisitor;
import visitors.ParserVisitor;
import visitors.PrintVisitor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Tokenizer tokenizer = null;
        try {

            tokenizer = new Tokenizer(new FileInputStream("src/main/java/input.txt"));
            tokenizer.run();
            ArrayList<Token> tokens = tokenizer.getResult();

            System.out.println(tokens);

            ParserVisitor parserVisitor = new ParserVisitor(tokens);
            parserVisitor.run();
            ArrayList<Token> tokensInPostfixNotation = parserVisitor.getResult();

            System.out.println(tokensInPostfixNotation);

            PrintVisitor printVisitor = new PrintVisitor(
                    tokensInPostfixNotation,
                    new FileOutputStream("src/main/java/output.txt")
            );
            printVisitor.run();
            printVisitor.close();

            CalcVisitor calcVisitor = new CalcVisitor(tokensInPostfixNotation);
            calcVisitor.run();

            System.out.println(calcVisitor.getResult());

        } catch (Exception e) {
            if (tokenizer != null) {
                System.out.println(tokenizer.getResult());
            }
            e.printStackTrace();
        }
    }
}
