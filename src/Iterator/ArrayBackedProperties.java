package Iterator;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class ArrayBackedProperties {
}

class SimpleCreature {
    private int strength, agility, intelligence;

    public int max() {
        return Math.max(strength,
                Math.max(agility, intelligence));
    }

    public int sum() {
        return strength + agility + intelligence;
    }

    public double average() {
        return sum() / 3.0;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
}

class Creature implements Iterable<Integer> {
    private int[] stats = new int[3];
    private final int str = 0;

    public int getStrength() {
        return stats[str];
    }

    public void setStrength(int value) {
        stats[str] = value;
    }

    public int sum() {
        return IntStream.of(stats).sum();
    }

    public int max() {
        return IntStream.of(stats).max().getAsInt();
    }

    public double average() {
        return IntStream.of(stats).average().getAsDouble();
    }

    @Override
    public Iterator<Integer> iterator() {
        return IntStream.of(stats).iterator();
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        for (int x : stats)
            action.accept(x);
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return IntStream.of(stats).spliterator();
    }
}

class Demo2 {
    public static void main(String[] args) {
        Creature creature = new Creature();
        creature.setStrength(17);
        System.out.println(
                "Creature has a mas stat of " + creature.max()
                        + ", total stats = " + creature.sum()
                        + " average stat = " + creature.average()
        );
    }
}