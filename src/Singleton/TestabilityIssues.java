package Singleton;

import com.google.common.collect.Iterables;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

interface Database{
    int getPopulation(String name);
}
class SingletonDatabase implements Database{
    private Dictionary<String, Integer> capitals = new Hashtable<>();
    private static int instanceCount = 0;

    public static int getCount() {
        return instanceCount;
    }

    private SingletonDatabase() {
        instanceCount++;
        System.out.println("Initializing database");

        try {
            File file = new File(
                    SingletonDatabase.class.getProtectionDomain().getCodeSource().getLocation().getPath()
            );
            Path fullPath = Paths.get(file.getPath(), "capitals.txt");
            List<String> lines = Files.readAllLines(fullPath);

            Iterables.partition(lines, 2).forEach(kv -> capitals.put(kv.get(0).trim(), Integer.parseInt(kv.get(1))));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final SingletonDatabase INSTANCE = new SingletonDatabase();

    public static SingletonDatabase getInstance() {
        return INSTANCE;
    }

    public int getPopulation(String name) {
        return capitals.get(name);
    }
}

class SingletonRecordFinder {
    public int getTotalPopulation(List<String> names) {
        int result = 0;
        for (String name : names) {
            result += SingletonDatabase.getInstance().getPopulation(name);
        }
        return result;
    }
}
class ConfigurableRecordFinder{
    private Database database;

    public ConfigurableRecordFinder(Database database) {
        this.database = database;
    }

    public int getTotalPopulation(List<String> names) {
        int result = 0;
        for (String name : names) {
            result += database.getPopulation(name);
        }
        return result;
    }
}

class DummyDatabase implements Database{
    private Dictionary<String, Integer> data = new Hashtable<>();

    public DummyDatabase (){
        data.put("a", 1);
        data.put("b", 2);
        data.put("g", 3);

    }

    @Override
    public int getPopulation(String name) {
        return data.get(name);
    }
}
public class TestabilityIssues {
    @Test// not a unit test;
    public void singletonTotalPopulationTest() {
        SingletonRecordFinder recordFinder = new SingletonRecordFinder();
        List<String> names = Arrays.asList("Seoul", "Mexico City");

        int tp = recordFinder.getTotalPopulation(names);

        assertEquals(1750000+17400000, tp);


    }

    @Test
    public void dependentPopulationTest(){
        DummyDatabase db = new DummyDatabase();
        ConfigurableRecordFinder rf = new ConfigurableRecordFinder(db);

        assertEquals(4, rf.getTotalPopulation(
                Arrays.asList("a","g")
        ));
    }
}
