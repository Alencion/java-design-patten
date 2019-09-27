package Memento;

public class Memento {
    private int balance;

    public int getBalance() {
        return balance;
    }

    public Memento(int balance) {
        this.balance = balance;
    }
}

class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public Memento deposit(int amount) {
        balance += amount;
        return new Memento(balance);
    }

    public void restore(Memento m) {
        balance = m.getBalance();
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}

class Demo {
    public static void main(String[] args) {
        BankAccount ba = new BankAccount(100);
        Memento m1 = ba.deposit(50);
        Memento m2 = ba.deposit(25);
        System.out.println(ba);

        ba.restore(m1);
        System.out.println(ba);

        ba.restore(m2);
        System.out.println(ba);
    }
}