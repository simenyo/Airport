import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Main {


    //Poisson-distribution. generates random numbers with an average value equal to input value
    private static int getPoissonRandom(double mean)
    {
        Random r = new Random();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do
        {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }

    public static void main(String[] args) {
        // Method from Airport Obj that fetches commands from user to Airport constructor
        Airport airport = new Airport().CreateAirportByConsole();

        int totalWaitTimeLanding = 0, totalWaitTimeTakeOff = 0;
        Plane removedPlane;

        // The two queues for landing/takeoff
        Queue<Plane> LandingQueue = new LinkedList<>();
        Queue<Plane> TakeOffQueue = new LinkedList<>();

        // Simulation loop based on user input time steps
        for (int time = 0; time < airport.getTimeSteps(); time++) {
            System.out.printf("%d: ", time + 1);

            // Sequence for calculating Arriving planes this timeunit
            int planesLanding = getPoissonRandom(airport.getAvgLandingFrequency());
            if (planesLanding != 0)
            {
                for (int i = 0; i < planesLanding; i++)
                {
                    // Constricting queue size, if current queue size and the random number of planes generated this
                    // timeunit exceeds 10, reduce amount of planes generated until >10.
                    // count the ones removed to var rejectedCount
                    if (planesLanding + LandingQueue.size() > 10) {
                        System.out.println("Landing queue full, plane was redirected!");
                        airport.rejectedCount++;
                        planesLanding--;
                        continue;
                    }

                    // Creating specific plane object and adding to queue
                    Plane planeComingToLand = new Plane(time, airport.planeCounter);
                    airport.planeCounter++;
                    System.out.printf("Plane %d ready for landing\n", planeComingToLand.getPlaneNumber());
                    LandingQueue.add(planeComingToLand);
                }
            }

            // Sequence for calculating take off planes
            int planesDeparting = getPoissonRandom(airport.getAvgTakeOffFrequency());
            if (planesDeparting != 0)
            {
                for (int j = 0; j < planesDeparting; j++)
                {
                    // Constricting queue size, if current queue size and the random number of planes generated this
                    // timeunit exceeds 10, reduce amount of planes generated until >10.
                    // count the ones removed to var rejectedCount
                    if (planesDeparting + TakeOffQueue.size() > 10) {
                        System.out.println("Take off queue full, plane was cancelled!");
                        airport.rejectedCount++;
                        planesDeparting--;
                        continue;
                    }

                    // Creating plane to take-off and adding to queue
                    Plane planeReadyForTakeOff = new Plane(time, airport.planeCounter);
                    airport.planeCounter++;
                    System.out.printf("Plane %d ready for take off\n", planeReadyForTakeOff.getPlaneNumber());
                    TakeOffQueue.add(planeReadyForTakeOff);
                }
            }

            // Prioritizing landingQ, if empty check takeoffQ, else idle. Storing removed plane in its own variable to
            // fetch relevant data.
            if (!LandingQueue.isEmpty()) {
                removedPlane = LandingQueue.remove();
                totalWaitTimeLanding += removedPlane.WaitTime(time);
                System.out.printf("Plane %d landed, wait time %d\n", removedPlane.getPlaneNumber(),
                        removedPlane.WaitTime(time));
                airport.landingCount++;
            }else if (!TakeOffQueue.isEmpty()) {
                removedPlane = TakeOffQueue.remove();
                totalWaitTimeTakeOff += removedPlane.WaitTime(time);
                System.out.printf("Plane %d departed, wait time %d\n", removedPlane.getPlaneNumber(),
                        removedPlane.WaitTime(time));
                airport.takeOffCount++;
            }else {
                System.out.println("The Airport is empty");
                airport.airportDormant++;
            }
        }

        // Calculating numbers to show at end of simulation
        float idlePercent = (float) airport.airportDormant / (float) airport.getTimeSteps();
        idlePercent = idlePercent * 100;

        float avgWaitLanding, avgWaitTakeOff;
        avgWaitLanding = (float) totalWaitTimeLanding / (float) airport.landingCount;
        avgWaitTakeOff = (float) totalWaitTimeTakeOff / (float) airport.takeOffCount;

        // Simulation end output
        System.out.println("\n**************************************************************************************");
        System.out.printf("Airport simulation complete after:       %d units of time.\n", airport.getTimeSteps());
        System.out.printf("Total number of planes landing and departing:        %d\n", airport.planeCounter);
        System.out.printf("Number of planes landed:        %d\n", airport.landingCount);
        System.out.printf("Number of planes departed:        %d\n", airport.takeOffCount);
        System.out.printf("Number of planes rejected:        %d\n", airport.rejectedCount);
        System.out.printf("Number of planes waiting to land:        %d\n", LandingQueue.size());
        System.out.printf("Number of planes waiting to take off:        %d\n", TakeOffQueue.size());
        System.out.printf("Percent of time airport was dormant:        %.0f%%\n", idlePercent);
        System.out.printf("Average waiting time for landing:        %.6f units of time\n", avgWaitLanding);
        System.out.printf("Average waiting time for take off:        %.6f units of time\n", avgWaitTakeOff);
        System.out.println("**************************************************************************************");
    }
}
