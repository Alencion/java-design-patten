package State;

public class ClassicImplementation {
}

class State2 {
    void on(LightSwitch ls){
        System.out.println("Light turned on");
    }
    void off(LightSwitch ls){
        System.out.println("Light is already off");
    }
}

class OnState2 extends State2 {
    public OnState2() {
        System.out.println("Light turned on");
    }

    @Override
    void off(LightSwitch ls) {
        System.out.println("Switching light off...");
        ls.setState2(new OffState2());
    }
}

class OffState2 extends State2 {
    public OffState2() {
        System.out.println("Light turned off");
    }

    @Override
    void on(LightSwitch ls) {
        System.out.println("Switching light on...");
        ls.setState2(new OnState2());
    }
}
class LightSwitch{
    private State2 state2; // OnState, OffState

    public LightSwitch() {
        state2 = new OffState2();
    }

    public void setState2(State2 state2) {
        this.state2 = state2;
    }

    void on() { state2.on(this); }
    void off() { state2.off(this); }
}

class Demo{
    public static void main(String[] args) {
        LightSwitch lightSwitch = new LightSwitch();
        lightSwitch.on();
        lightSwitch.off();
        lightSwitch.off();
    }
}
