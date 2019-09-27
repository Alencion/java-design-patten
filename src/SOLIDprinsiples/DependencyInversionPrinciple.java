package SOLIDprinsiples;

//DIP
// A. High-level modules should not depend on low-level modules.
// Both should depend on abstractions.

// B. Abstractions should not depend on details.
// Details should depend on abstractions.

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DependencyInversionPrinciple {
    /**
     * 의존 관계 역전의 원칙
     * A. 고차원 모듈은 저차원 모듈에 의존 하면 안된다. 이 두 모듈 모두 다른 추상화된 것에 의존해야 한다.
     * B. 추상화된 것은 구체적인 것에 의존하면 안된다. 구체적인 것이 추상화된 것에 의존해야 한다.
     *
     */
}

enum Relationship {
    PARENT, CHILD, SIBLING
}

class Person {
    public String name;
    //bob

    public Person(String name) {
        this.name = name;
    }
}

interface RelationshipBrowser {
    List<Person> findAllChildenOf(String name);
}

class Relationships implements RelationshipBrowser { // low-level
    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    @Override
    public List<Person> findAllChildenOf(String name) {
        return relations.stream()
                .filter(x -> Objects.equals(x.getValue0().name, name)
                        && x.getValue1() == Relationship.PARENT)
                .map(Triplet::getValue2)
                .collect(Collectors.toList());
    }
}

class Research {  // high-level
    //    public Research(Relationships relationships) {
//        List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
//        relations.stream()
//                .filter(x -> x.getValue0().name.equals("John")
//                        && x.getValue1() == Relationship.PARENT)
//                .forEach(ch -> System.out.println("John has a child called" + ch.getValue2().name));
//    }
//
    public Research(RelationshipBrowser browser) {
        List<Person> children = browser.findAllChildenOf("John");
        for (Person child : children)
            System.out.println("John has a child called" + child.name);
    }
}

class Demo4 {
    public static void main(String[] args) {
        Person parent = new Person("John");
        Person child1 = new Person("Chris");
        Person child2 = new Person("Matt");

        Relationships relationship = new Relationships();
        relationship.addParentAndChild(parent, child1);
        relationship.addParentAndChild(parent, child2);

        new Research(relationship);
    }
}