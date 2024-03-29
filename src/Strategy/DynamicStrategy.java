package Strategy;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class DynamicStrategy {
}

enum OutputFormat{
    MARKDOWN, HTML
}

interface ListStrategy{
    default void start(StringBuilder sb) {}
    void addListItem(StringBuilder sb, String item);
    default void end(StringBuilder sb) {}
}
class MarkdownListStrategy implements ListStrategy{

    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append(" * ").append(item)
                .append(System.lineSeparator());
    }
}

class HtmlListStrategy implements ListStrategy{

    @Override
    public void start(StringBuilder sb) {
        sb.append("<ul> ").append(System.lineSeparator());
    }

    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append("  <li>")
                .append(item)
                .append("</li>")
                .append(System.lineSeparator());
    }

    @Override
    public void end(StringBuilder sb) {
        sb.append("</ul>").append(System.lineSeparator());
    }
}

class TextProcessor<LS extends ListStrategy>{
    private StringBuilder sb = new StringBuilder();
    private LS listStrategy;

    public TextProcessor(Supplier<? extends LS> ctor) {
        listStrategy = ctor.get();
    }

    public void appendList(List<String> items){
        listStrategy.start(sb);
        for (String item : items)
            listStrategy.addListItem(sb, item);
        listStrategy.end(sb);
    }

    public void clear(){
        sb.setLength(0);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

class Demo{
    public static void main(String[] args) {
//        TextProcessor tp = new TextProcessor(OutputFormat.MARKDOWN);
//        tp.appendList(Arrays.asList("liberte", "egalite", "fraternite"));
//        System.out.println(tp);
//
//        tp.clear();
//        tp.setOutputFormat(OutputFormat.HTML);
//        tp.appendList(Arrays.asList("inheritance", "encapsulation," + "polymorphism"));
//        System.out.println(tp);
        TextProcessor<MarkdownListStrategy> tp
                = new TextProcessor<>(MarkdownListStrategy::new);

        tp.appendList(Arrays.asList("alpha", "beta", "gamma"));
        System.out.println(tp);

        TextProcessor<HtmlListStrategy> tp2
                = new TextProcessor<>(HtmlListStrategy::new);
        tp2.appendList(Arrays.asList("alpha", "beta", "gamma"));
        System.out.println(tp2);
    }
}