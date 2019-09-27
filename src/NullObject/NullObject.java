package NullObject;

import java.lang.reflect.Proxy;

public class NullObject {
}

interface Log {
    void info(String msg);

    void warn(String msg);
}

class ConsoleLog implements Log {

    @Override
    public void info(String msg) {
        System.out.println(msg);
    }

    @Override
    public void warn(String msg) {
        System.out.println("WARNING: " + msg);
    }
}

class BankAccount {
    private Log log;
    private int balance;

    public BankAccount(Log log) {
        this.log = log;
    }

    public void deposit(int amount) {
        balance += amount;

        if (log != null)
            log.info("Deposited " + amount);
    }

    public void withdraw(int amount){
        if (balance < amount) return;
        balance -= amount;

        if (log != null)
            log.info("Deposited " + amount);

    }
}

final class NullLog implements Log {

    @Override
    public void info(String msg) {

    }

    @Override
    public void warn(String msg) {

    }
}

class Demo {
    @SuppressWarnings("unchecked")
    public static <T> T noOp(Class<T> ift) {
        return (T) Proxy.newProxyInstance(
                ift.getClassLoader(),
                new Class<?>[]{ift},
                (((proxy, method, args) -> {
                    if (method.getReturnType().equals(Void.TYPE))
                        return null;
                    else
                        return method.getReturnType().getConstructor().newInstance();
                }))
        );
    }

    public static void main(String[] args) {
//        Log log = new NullLog();

        Log log = noOp(Log.class);

        BankAccount ba = new BankAccount(log);
        ba.deposit(100);
        ba.withdraw(200);
    }
}