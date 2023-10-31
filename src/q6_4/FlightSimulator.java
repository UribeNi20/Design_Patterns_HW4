package q6_4;

import java.util.Scanner;

// FlightSimulator class
public class FlightSimulator {
    private Aircraft aircraft;  //selection for user

    /**
     * Runs the different scenarios depending on the aircraft chosen
     * pre: Type of aircraft through user input as string
     * post: simulation is over
     * @param aircraftType
     */
    public void runScenario(String aircraftType) {
        switch (aircraftType) {
            case "SingleEnginePropPlane":
                aircraft = new SingleEnginePropPlane();
                break;
            case "Helicopter":
                aircraft = new Helicopter();
                break;
            case "FighterJet":
                aircraft = new FighterJet();
                break;
            default:
                System.out.println("Unknown aircraft type");
                return;
        }
        aircraft.BeginFlying();
    }

    public static void main(String[] args) {
        FlightSimulator simulator = new FlightSimulator();

        //Prompt for user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select an aircraft type: SingleEnginePropPlane | Helicopter | FighterJet");
        String choice = scanner.nextLine();

        simulator.runScenario(choice);
    }

}
