package Singleton;

public class LazinessAndThreadSafetySingleton {
    private static LazinessAndThreadSafetySingleton instance;

    private LazinessAndThreadSafetySingleton() {
        System.out.println("initializing a lazy singleton");
    }

//    public static synchronized LazinessAndThreadSafetySingleton getInstance() {
//        if (instance == null) instance = new LazinessAndThreadSafetySingleton();
//        return instance;
//    }

    //double-checked locking
    public static LazinessAndThreadSafetySingleton getInstance() {
        if (instance == null)
            synchronized (LazinessAndThreadSafetySingleton.class) {
                if (instance == null)
                    instance = new LazinessAndThreadSafetySingleton();
            }
        return instance;
    }
}