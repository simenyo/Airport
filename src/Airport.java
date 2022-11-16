import java.util.Scanner;

public class Airport {
    private int timeSteps;
    private float avgTakeOffFrequency;
    private float avgLandingFrequency;
    public int landingCount = 0;
    public int takeOffCount = 0;
    public int rejectedCount = 0;
    public int planeCounter = 0;
    public int airportDormant = 0;

    public Airport() {}

    public Airport(int timeSteps, float avgTakeOffFrequency, float avgLandingFrequency) {
        this.timeSteps = timeSteps;
        this.avgTakeOffFrequency = avgTakeOffFrequency;
        this.avgLandingFrequency = avgLandingFrequency;
    }

    public Airport CreateAirportByConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Halden Airport");
        System.out.print("Timestep amount?: ");
        timeSteps = scanner.nextInt();
        System.out.print("Avg. frequency of planes arriving? (< 1.0): ");
        avgLandingFrequency = scanner.nextFloat();
        while (avgLandingFrequency > 1.0) {
            System.out.print("Avg. frequency must be between 0 and 1.0, (< 1.0): ");
            avgLandingFrequency = scanner.nextFloat();
        }

        System.out.print("Avg. frequency of planes departing? (< 1.0): ");
        avgTakeOffFrequency = scanner.nextFloat();
        while (avgTakeOffFrequency + avgLandingFrequency > 1.0) {
            System.out.print("Avg. frequency total must be between 0 and 1.0, (< 1.0): \n");
            System.out.print("Avg. frequency of planes departing? (< 1.0): ");
            avgTakeOffFrequency = scanner.nextFloat();
        }
        return new Airport(timeSteps, avgTakeOffFrequency, avgLandingFrequency);
    }

    public int getTimeSteps() {
        return timeSteps;
    }

    public float getAvgTakeOffFrequency() {
        return avgTakeOffFrequency;
    }

    public float getAvgLandingFrequency() {
        return avgLandingFrequency;
    }

}
