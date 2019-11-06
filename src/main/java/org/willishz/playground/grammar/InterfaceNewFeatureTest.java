package org.willishz.playground.grammar;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 函数式接口
 */
public class InterfaceNewFeatureTest {

    public static void main(String[] args) {
        final Vehicle car = () -> System.out.println("car run");
        car.run();
        car.alarm();
        Vehicle.light();
        final Truck truck = Truck.create(Truck::new);
        final List<Truck> trucks = Arrays.asList(truck);
        trucks.forEach(Truck::collide);
    }
}

@FunctionalInterface
interface Vehicle {

    void run();

    default void alarm() {
        System.out.println("alarm");
    }

    static void light() {
        System.out.println("light");
    }
}

class Truck implements Vehicle {

    @Override
    public void run() {
        System.out.println("truck run");
    }

    public static Truck create(final Supplier<Truck> supplier) {
        return supplier.get();
    }

    public static void collide(final Truck truck) {
        System.out.println("Collided " + truck.toString());
    }

    public void follow(final Truck another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
}