package main;

import main.Dog;

public class Main {
    public static final boolean enabled = true;
    public static void main(String[] args) {
        Dog dog = new Dog(10);
        System.out.println("dog.getHeight: " + dog.getHeight());
        System.out.println("dog.noise: " + dog.makeNoise());
        dog.eat();
    }
}
