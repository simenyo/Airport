public class Plane {
    private final int planeNumber;
    private final int arrival;

    public Plane(int arrival, int planeNumber) {
        this.arrival = arrival;
        this.planeNumber = planeNumber;
    }

    public int WaitTime(int time) {
        return time - arrival;
    }

    public int getPlaneNumber() {
        return planeNumber;
    }
}
