package Flyweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextFormatting {
}

class FormattedText {
    private String plainText;
    private boolean[] capitalize;

    public FormattedText(String plainText) {
        this.plainText = plainText;
        capitalize = new boolean[plainText.length()];
    }

    public void capitalize(int start, int end) {
        for (int i = start; i <= end; ++i) {
            capitalize[i] = true;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            sb.append(
                    capitalize[i]
                            ? Character.toUpperCase(c) : c
            );
        }
        return sb.toString();
    }
}

class BetterFormattedText {
    private String plainText;
    private List<TextRange> formatting = new ArrayList<>();

    public BetterFormattedText(String plainText) {
        this.plainText = plainText;
    }

    public TextRange getRange(int start, int end) {
        TextRange range = new TextRange(start, end);
        formatting.add(range);
        return range;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < plainText.length(); ++i) {
            char c = plainText.charAt(i);
            for (TextRange range : formatting)
                if (range.covers(i) && range.capitalize)
                    c = Character.toUpperCase(c);
            sb.append(c);
        }
        return sb.toString();
    }

    public class TextRange {
        public int start, end;
        public boolean capitalize, bold, italic;

        public TextRange(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean covers(int position) {
            return position >= start && position <= end;
        }
    }
}

class Demo2 {
    public static void main(String[] args) {
        FormattedText ft = new FormattedText("This is a brave");
        ft.capitalize(0, 10);
        System.out.println(ft);

        BetterFormattedText bf = new BetterFormattedText("Make me america Great Again");
        bf.getRange(5,10).capitalize = true;
        System.out.println(bf);

    }
}