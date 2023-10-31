package q6_4;

/**
 * Concrete class for FighterJet
 * has takeoff, fly, land
 */
public class FighterJet extends Aircraft {
    private double maxSpeed;

    @Override
    protected void takeoff() {
        System.out.println("FighterJet afterburners engaged - TAKEOFF");
    }

    @Override
    protected void fly() {
        System.out.println("FighterJet flying at supersonic speeds - FLY");
    }

    @Override
    protected void land() {
        System.out.println("FighterJet making a rapid descent - LAND");
    }
}
