package Interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lexing {
}

interface Element{
    int eval();
}

class Integer implements Element{
    private int value;

    public Integer(int value) {
        this.value = value;
    }

    @Override
    public int eval() {
        return value;
    }
}

class BinaryOperation{

}

class Token {

    public enum Type {
        INTEGER,
        PLUS,
        MINUS,
        LPAREN,
        RPAREN
    }

    public Type type;
    public String text;

    public Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "`" + text + "`";
    }
}

class Demo {
    static List<Token> lex(String input){
        ArrayList<Token> result = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)){
                case '+':
                    result.add(new Token(Token.Type.PLUS, "+"));
                    break;
                case '-':
                result.add(new Token(Token.Type.MINUS, "-"));
                    break;
                case '(':
                result.add(new Token(Token.Type.RPAREN, "("));
                    break;
                case ')':
                result.add(new Token(Token.Type.LPAREN, ")"));
                    break;
                default:
                    StringBuilder sb = new StringBuilder("" + input.charAt(i));
                    for (int j= i+1; j<input.length(); ++j){
                        if (Character.isDigit(input.charAt(j))){
                            sb.append(input.charAt(j));
                            ++i;
                        }
                        else {
                            result.add(new Token(
                                    Token.Type.INTEGER, sb.toString()
                            ));
                            break;
                        }
                    }
                    break;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        String input = "(13+4)-(12+1)";
        List<Token> tokens = lex(input);
        System.out.println(tokens.stream()
        .map(t -> t.toString())
        .collect(Collectors.joining("\t")));
    }
}