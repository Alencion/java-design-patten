package ChainOfResponsibility;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class BrokerChain {
}

// cor + observer + mediator + (-) memento
// CQS
class Event<Args> {
    private int index = 0;
    private Map<Integer, Consumer<Args>> handlers = new HashMap<>();

    public int subscribe(Consumer<Args> handler) {
        int i = index;
        handlers.put(index++, handler);
        return i;
    }

    public void unsubscribe(int key) {
        handlers.remove(key);
    }

    public void fire(Args args) {
        for (Consumer<Args> handler : handlers.values()) {
            handler.accept(args);
        }
    }
}

class Query {
    public String creatureName;

    enum Argument {
        ATTACK,
        DEFENCE
    }

    public Argument argument;
    public int result;

    public Query(String creatureName, Argument argument, int result) {
        this.creatureName = creatureName;
        this.argument = argument;
        this.result = result;
    }
}

class Game {
    public Event<Query> queries = new Event<>();
}

class Creature2 {
    private Game game;
    public String name;
    public int baseAttack, baseDefence;

    public Creature2(Game game, String name, int baseAttack, int baseDefence) {
        this.game = game;
        this.name = name;
        this.baseAttack = baseAttack;
        this.baseDefence = baseDefence;
    }

    int getAttack() {
        Query q = new Query(name, Query.Argument.ATTACK, baseAttack);
        game.queries.fire(q);
        return q.result;
    }

    int getDefence() {
        Query q = new Query(name, Query.Argument.DEFENCE, baseDefence);
        game.queries.fire(q);
        return q.result;
    }

    @Override
    public String toString() {
        return "Creature2{" +
                "name='" + name + '\'' +
                ", baseAttack=" + getAttack() +
                ", baseDefence=" + getDefence() +
                '}';
    }
}

class CreatureModifier2 {
    protected Game game;
    protected Creature2 creature;

    public CreatureModifier2(Game game, Creature2 creature) {
        this.game = game;
        this.creature = creature;
    }
}

class DoubleAttackModifier2 extends CreatureModifier2 implements AutoCloseable {
    private final int token;

    public DoubleAttackModifier2(Game game, Creature2 creature) {
        super(game, creature);

        token = game.queries.subscribe(q -> {
            if (q.creatureName.equals(creature.name)
                    && q.argument == Query.Argument.ATTACK) {
                q.result *= 2;
            }
        });
    }

    @Override
    public void close() /*throws Exception*/ {
        game.queries.unsubscribe(token);
    }
}

class IncreasedDefenceModifier2 extends CreatureModifier2 {

    public IncreasedDefenceModifier2(Game game, Creature2 creature) {
        super(game, creature);

        game.queries.subscribe(q -> {
            if (q.creatureName.equals(creature.name)
                    && q.argument == Query.Argument.DEFENCE) {
                q.result += 3;
            }
        });
    }
}


class Demo2 {
    public static void main(String[] args) {
        Game game = new Game();
        Creature2 goblin = new Creature2(game, "Strong Goblin", 2, 2);

        System.out.println(goblin);

        IncreasedDefenceModifier2 icm = new IncreasedDefenceModifier2(game, goblin);
        DoubleAttackModifier2 dam = new DoubleAttackModifier2(game, goblin);
        System.out.println(goblin);
        dam.close();
        System.out.println(goblin);
    }
}