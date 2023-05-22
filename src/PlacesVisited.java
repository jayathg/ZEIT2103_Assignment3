import java.time.LocalDate;

/**
 * The PlacesVisited class handles how each PlacesVisited Object is created
 * This method adds the location and Date as well as the associated toString method
 *
 * @author Jayath Gunawardena
 * created on 19/05/2023
 */
public class PlacesVisited {

    //Attributes

    private String location;

    private LocalDate date;

    //Methods

    /**
     * PlacesVisited Constructor method
     * @param location String representation of the location that was visited
     * @param date LocalDate representation of the time the location was visited
     */
    public PlacesVisited(String location, LocalDate date) {
        this.location = location;
        this.date = date;
    }

    /**
     * GetLocation method
     * @return String representation of the location that was visited
     */
    public String getLocation() {
        return location;
    }

    /**
     * GetDate method
     * @return LocalDate representation of the time the location was visited
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * toString method
     * Overrides, the default toString method
     * @return String, representation of the PlacesVisited object, with all the attributes outlined
     */
    @Override
    public String toString() {
        return "PlacesVisited{" +
                "location='" + location + '\'' +
                ", date=" + date +
                '}';
    }
}
