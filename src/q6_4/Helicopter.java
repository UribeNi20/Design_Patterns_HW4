package q6_4;

/**
 * Concrete class for Helicopter
 * has takeoff, fly, land
 */
public class Helicopter extends Aircraft {
    private double maxAltitude;

    @Override
    protected void takeoff() {
        System.out.println("Helicopter blades spinning rapidly - TAKEOFF");
    }

    @Override
    protected void fly() {
        System.out.println("Helicopter hovering - FLY");
    }

    @Override
    protected void land() {
        System.out.println("Helicopter descending vertically - LAND");
    }
}
