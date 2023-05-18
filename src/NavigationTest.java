import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NavigationTest {

    @Test
    void loadData() {
    }

    @Test
    void loadData_ValidFile_True(){
        Navigation nav = new Navigation();
        assertFalse(nav.graph.nodeNames.isEmpty());
        assertEquals(6, nav.graph.nodeNames.get("City").getAmenity().size());
        assertEquals(4, nav.graph.nodeNames.get("City").getAdj().size());
    }

    @Test
    void loadData_FileReadCorrectly_True(){
        Navigation nav = new Navigation();
        assertTrue(nav.graph.nodeNames.get("City").getAmenity().contains("Hotel"));
        assertFalse(nav.graph.nodeNames.get("City").getAmenity().contains("Hospital"));
        assertFalse(nav.graph.nodeNames.get("City").getAdj().contains("Canberra Airport"));
    }

    @Test
    void getAllAmenities() {
    }

    @Test
    void getAllAmenities_ValidPostcode_True(){
        Navigation nav = new Navigation();
        ArrayList<Object> canAirportAmenitiesList = new ArrayList<>();
        canAirportAmenitiesList.add("Airport");
        canAirportAmenitiesList.add("Patrol Station");
        canAirportAmenitiesList.add("Hotel");
        assertArrayEquals(canAirportAmenitiesList.toArray(),nav.getAllAmenities(2609).toArray());
    }

    @Test
    void getAllAmenities_InvalidPostcode_False(){
        Navigation nav = new Navigation();
        assertThrows(IllegalArgumentException.class, () -> nav.getAllAmenities(1111));
    }

    @Test
    void getAllSuburbs() {
    }

    @Test
    void getAllSuburbs_ValidAmenity_True(){
        Navigation nav = new Navigation();
        ArrayList<Object> amenityList = new ArrayList<>();
        amenityList.add("Canberra Airport");
        assertEquals(amenityList, nav.getAllSuburbs("Airport"));
    }

    @Test
    void getAllSuburbs_InvalidAmenity_False(){
        Navigation nav = new Navigation();
        assertThrows(IllegalArgumentException.class, () -> nav.getAllSuburbs("Police Station"));
    }

    @Test
    void addToVisitedPlaces() {
    }

    @Test
    void addToVisited_ValidDetails_True(){
        Navigation nav = new Navigation();
        PlacesVisited visited = new PlacesVisited("City", LocalDate.parse("2023-10-12"));
        ArrayList<PlacesVisited> placesVisitedList = new ArrayList<>();
        placesVisitedList.add(visited);
        nav.addToVisitedPlaces("City", LocalDate.parse("2023-10-12"));
        for (int i = 0; i < placesVisitedList.size(); i++) {
            assertEquals(placesVisitedList.get(i).toString(), nav.getVisitedPlaces().get(i).toString());
        }
    }

    @Test
    void addToVisited_InvalidLocation_False(){
        Navigation nav = new Navigation();
        assertThrows(IllegalArgumentException.class, () -> nav.addToVisitedPlaces("Fake Town",LocalDate.parse("2023-10-12")) );
    }

    @Test
    void getVisitedPlaces() {
    }

    @Test
    void getVisitedPlaces_ValidPlaces_True(){
        Navigation nav = new Navigation();
        nav.addToVisitedPlaces("City", LocalDate.parse("2023-10-12"));
        ArrayList<PlacesVisited> visitedPlace = new ArrayList<>();
        visitedPlace.add(new PlacesVisited("City", LocalDate.parse("2023-10-12")));
        for (int i = 0; i < visitedPlace.size(); i++) {
            assertEquals(visitedPlace.get(i).toString(), nav.getVisitedPlaces().get(i).toString());
        }
    }

    @Test
    void getVisitedPlaces_NoPlaces_False(){
        Navigation nav = new Navigation();
        assertThrows(IllegalArgumentException.class, nav::getVisitedPlaces);
    }

    @Test
    void getDate() {
    }

    @Test
    void getDaysVisited_ValidDays_True(){
        Navigation nav = new Navigation();
        nav.addToVisitedPlaces("City", LocalDate.parse("2012-10-10"));
        ArrayList<LocalDate> visitedDays = new ArrayList<>();
        visitedDays.add(LocalDate.parse("2012-10-10"));
        for (int i = 0; i < visitedDays.size(); i++) {
            assertEquals(visitedDays.get(i).toString(), nav.getDate("City").get(i).toString());
        }
    }

    @Test
    void getDaysVisited_NoDays_False(){
        Navigation nav = new Navigation();
        assertThrows(IllegalArgumentException.class, () -> nav.getDate("City"));
    }

    @Test
    void getDaysVisited_IllegalLocation_False(){
        Navigation nav = new Navigation();
        assertThrows(IllegalArgumentException.class, () -> nav.getDate("Random Location"));

    }
    @Test
    void calculateShortestDistances() {
    }

    @Test
    void shortestDistance_ValidNode_True(){
        Navigation nav = new Navigation();
        Node source = nav.graph.getNode("City");
        Map<String,Object> shortest = nav.calculateShortestDistances(source);
        Map<String, Double> distance = (Map<String, Double>) shortest.get("distance");
        Map<String, String> previous = (Map<String, String>) shortest.get("previous");
        assertEquals(1.4, distance.get("Acton"));
        assertEquals("Braddon", previous.get("Turner"));
        assertEquals(29,distance.size());
        assertEquals(29, previous.size());
    }

    @Test
    void shortestDistance_InvalidNode_False(){
        Navigation nav = new Navigation();
        Node source = new Node(2000, "Sydney");
        assertThrows(IllegalArgumentException.class, () -> nav.calculateShortestDistances(source));
    }

    @Test
    void getShortestPath() {
    }

    @Test
    void shortestPath_ActonOConnor_True(){
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("Acton"); expectedList.add("Turner"); expectedList.add("O'Connor");
        Navigation nav = new Navigation();
        Node source = nav.graph.getNode("Acton");
        Node target = nav.graph.getNode("O'Connor");
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(nav.getShortestPath(source,target).get(i), expectedList.get(i)  );
        }
    }

    @Test
    void shortestPath_CampbellCrace_True(){
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("Campbell"); expectedList.add("Reid"); expectedList.add("Braddon");
        expectedList.add("Turner");expectedList.add("O'Connor");expectedList.add("Bruce");
        expectedList.add("Crace");
        Navigation nav = new Navigation();
        Node source = nav.graph.getNode("Campbell");
        Node target = nav.graph.getNode("Crace");
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(nav.getShortestPath(source,target).get(i), expectedList.get(i)  );
        }
    }

    @Test
    void shortestPath_CaseyCity_True(){
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("Casey"); expectedList.add("Ngunnawal"); expectedList.add("Gungahlin");
        expectedList.add("Franklin");expectedList.add("Mitchell");expectedList.add("Watson");
        expectedList.add("Dickson");expectedList.add("Ainslie");
        expectedList.add("Braddon");expectedList.add("City");
        Navigation nav = new Navigation();
        Node source = nav.graph.getNode("Casey");
        Node target = nav.graph.getNode("City");
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(nav.getShortestPath(source,target).get(i), expectedList.get(i)  );
        }
    }

    @Test
    void shortestPath_IllegalSource_False(){
        Navigation nav = new Navigation();
        Node source = new Node(2000, "Sydney");
        assertThrows(IllegalArgumentException.class, () -> nav.getShortestPath(source, nav.graph.getNode("City")));
    }

    @Test
    void shortestPath_IllegalTarget_False(){
        Navigation nav = new Navigation();
        Node target = new Node(2000, "Sydney");
        assertThrows(IllegalArgumentException.class, () -> nav.getShortestPath(nav.graph.getNode("City"),target));
    }
    @Test
    void calculateDistanceToAmenity() {
    }

    @Test
    void distToAmenity_CityAirport_True(){
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("City"); expectedList.add("Russell");
        expectedList.add("Canberra Airport");expectedList.add("6.7");
        Navigation nav = new Navigation();
        Node source = nav.graph.getNode("City");
        String target = "Airport";
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(nav.calculateDistanceToAmenity(source,target).get(i), expectedList.get(i)  );
        }
    }

    @Test
    void distToAmenity_WatsonSchool_True(){
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("Watson"); expectedList.add("0.0");
        Navigation nav = new Navigation();
        Node source = nav.graph.getNode("Watson");
        String target = "School";
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(nav.calculateDistanceToAmenity(source,target).get(i), expectedList.get(i)  );
        }
    }

    @Test
    void distToAmenity_HacketAIS_True(){
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("Hacket"); expectedList.add("Dickson");expectedList.add("O'Connor");
        expectedList.add("Bruce");expectedList.add("11.6");
        Navigation nav = new Navigation();
        Node source = nav.graph.getNode("Hacket");
        String target = "AIS";
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(nav.calculateDistanceToAmenity(source,target).get(i), expectedList.get(i)  );
        }
    }

    @Test
    void distToAmenity_IllegalSource_False(){
        Navigation nav = new Navigation();
        Node source = new Node(2000, "Sydney");
        assertThrows(IllegalArgumentException.class, () -> nav.calculateDistanceToAmenity(source, "Airport"));
    }

    @Test
    void distToAmenity_IllegalAmenity_False(){
        Navigation nav = new Navigation();
        assertThrows(IllegalArgumentException.class, () -> nav.calculateDistanceToAmenity(nav.graph.getNode("City"), "Police Station"));
    }

}