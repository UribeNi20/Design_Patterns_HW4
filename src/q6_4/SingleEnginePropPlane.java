package q6_4;

/**
 * Concrete class for SingleEnginePropPlane
 * has takeoff, fly, land
 */
public class SingleEnginePropPlane extends Aircraft {
    private String engineType;

    @Override
    protected void takeoff() {
        System.out.println("SingleEnginePropPlane revving engine - TAKEOFF");
    }

    @Override
    protected void fly() {
        System.out.println("SingleEnginePropPlane soaring in the skies - FLY");
    }

    @Override
    protected void land() {
        System.out.println("SingleEnginePropPlane descending - LAND");
    }
}
