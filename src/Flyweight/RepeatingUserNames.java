package Flyweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class RepeatingUserNames {
    //MMOPRG
    //bold or italic text in the console
}

class User {
    private String fullName;

    public User(String fullName) {
        this.fullName = fullName;
    }
}

class User2 {
    private static List<String> strings = new ArrayList<>();
    private int[] names;

    public User2(String fullName) {
        Function<String, Integer> getOrAdd = (String s) -> {
            int idx = strings.indexOf(s);
            if (idx != -1) return idx;
            else {
                strings.add(s);
                return strings.size() - 1;
            }
        };

        names = Arrays.stream(fullName.split(" "))
                .mapToInt(getOrAdd::apply)
                .toArray();
    }

}

class Demo {
    public static void main(String[] args) {
        User2 user = new User2("John Smith");
        User2 user2 = new User2("Jane Smith");


    }
}
