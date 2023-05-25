import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PlacesVisitedTest {

    private PlacesVisited placesVisited;

    @BeforeEach
    void setUp() {
        placesVisited = new PlacesVisited("Location1", LocalDate.of(2023, 5, 19));
    }

    @Test
    void getLocation() {
        assertEquals("Location1", placesVisited.getLocation());
    }

    @Test
    void getDate() {
        assertEquals(LocalDate.of(2023, 5, 19), placesVisited.getDate());
    }

    @Test
    void testToString() {
        String expectedString = "PlacesVisited{location='Location1', date=2023-05-19}";
        assertEquals(expectedString, placesVisited.toString());
    }
}
