package main;

@EnabledSubsystem(enabled = Main.enabled)
public class Dog {
    int height;

    public Dog(int height) {
       this.height = height;
    }

    @WhenDisabled(defaultReturn = "\"Dog is disabled\"")
    public String makeNoise() {
        return "woof";
    }

    public int getHeight() {
        return height;
    }

    @WhenDisabled
    public void eat() {
        System.out.println("dog is eating");
    }
}
