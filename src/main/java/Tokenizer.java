import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import exceptions.UnknownCharacterException;
import states.EndState;
import states.ErrorState;
import states.StartState;
import states.State;
import tokens.*;

public class Tokenizer {
    private final BufferedReader reader;
    private final ArrayList<Token> result;
    private State state;

    public Tokenizer(InputStream inputStream) {
        this.reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8)
        );
        this.result = new ArrayList<>();
        this.state = new StartState(null);
    }

    public void processOneChar(char ch) {
        State newState = state.handle(ch);
        if (state.getResult() != null) {
            result.add(state.getResult());
        }
        state = newState;
    }

    public void run() throws IOException, UnknownCharacterException {
        int ch;
        while ((ch = reader.read()) != -1) {
            processOneChar((char) ch);
        }
        processOneChar((char) 0x03);
        if (state instanceof ErrorState) {
            throw new UnknownCharacterException(
                    ((ErrorState) state).getMessage()
            );
        }
        if (!(state instanceof EndState)) {
            throw new UnknownCharacterException(
                    "end of file not reached, " +
                            "processing ended with final state: " +
                            state
            );
        }
    }

    public ArrayList<Token> getResult() {
        return result;
    }
}
