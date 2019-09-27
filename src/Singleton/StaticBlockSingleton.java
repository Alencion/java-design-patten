package Singleton;

import java.io.File;
import java.io.IOException;

public class StaticBlockSingleton {
    private StaticBlockSingleton() throws IOException {
        System.out.println("singleton is initializing");
        File.createTempFile(".", ".");

    }

    private static StaticBlockSingleton instance;

    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            System.out.println("Failed to create singleton");
        }
    }

    public static StaticBlockSingleton getInstance(){
        return instance;
    }
}
