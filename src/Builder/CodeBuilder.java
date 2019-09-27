package Builder;

import java.util.LinkedList;
import java.util.Queue;

public class CodeBuilder {
    public String className;
    public Queue<String> field = new LinkedList<>();
    private final String newLine = System.lineSeparator();

    public CodeBuilder(String className) {
        this.className = className;
    }

    public CodeBuilder addField(String name, String type) {
        // todo
        field.add("public " + type + " " + name + ";");
        return this;
    }

    @Override
    public String toString() {
        // todo
        StringBuilder sb = new StringBuilder();
        sb.append("public class " + className + newLine);
        sb.append("{" + newLine);

        while (!field.isEmpty()) {
            sb.append("  " + field.poll() + newLine);
        }
        sb.append("}");
        return sb.toString();
    }
}
class Demo {
    public static void main(String[] args) {
        CodeBuilder cb  = new CodeBuilder("Person").addField("name","String").addField("age", "int");
        System.out.println(cb);
    }
}