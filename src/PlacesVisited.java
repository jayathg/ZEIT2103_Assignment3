import java.time.LocalDate;

public class PlacesVisited {

    //Attributes

    private String location;

    private LocalDate date;

    //Methods

    public PlacesVisited(String location, LocalDate date) {
        this.location = location;
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "PlacesVisited{" +
                "location='" + location + '\'' +
                ", date=" + date +
                '}';
    }
}
