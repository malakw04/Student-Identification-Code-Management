public class CleverSIDCDemoSmallDataset {

    public static void main(String[] args) {
        CleverSIDC sidc = new CleverSIDC();

        // Adding a few student IDs to simulate a small dataset
        try {
            sidc.add(10000001, "Student One");
            sidc.add(10000002, "Student Two");
            sidc.add(10000003, "Student Three");
            sidc.add(10000004, "Student Four");
            sidc.add(10000005, "Student Five");

            System.out.println("Added five students.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Display all keys
        System.out.println("All keys after initial insertions:");
        sidc.allKeys(sidc);

        // Finding the successor and predecessor
        String successor = sidc.nextKey(sidc, "10000003");
        String predecessor = sidc.prevKey(sidc, "10000003");
        System.out.println("Successor of 10000003: " + successor);
        System.out.println("Predecessor of 10000003: " + predecessor);

        // Removing a student
        sidc.remove(10000003);
        System.out.println("Removed Student Three.");
        System.out.println("All keys after removal:");
        sidc.allKeys(sidc);

        // Generating a new student ID and adding it
        try {
            int newID = sidc.generate();
            sidc.add(newID, "Random Student");
            System.out.println("Added random student with ID: " + newID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Final display of all keys
        System.out.println("Final state of all keys:");
        sidc.allKeys(sidc);
    }
}
