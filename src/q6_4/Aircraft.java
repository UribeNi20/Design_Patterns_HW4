package q6_4;

/**
 * Abstract class representing Aircraft.
 */
public abstract class Aircraft {

    /**
     * Abstract Takeoff
     * Pre: Aircraft on the ground
     * Post: Aircraft in the air
     */
    protected abstract void takeoff();

    /**
     * Abstract Flying
     * Pre: Aircraft ascending
     * Post: Aircraft flying
     */
    protected abstract void fly();

    /**
     * Abstract Landing
     * Pre: Aircraft flying
     * Post: Aircraft on the ground
     */
    protected abstract void land();

    // Public template method for performing flight
    public final void BeginFlying() {
        takeoff();
        fly();
        land();
    }
}

