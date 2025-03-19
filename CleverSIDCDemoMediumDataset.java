import java.io.File;
import java.io.IOException;

public class CleverSIDCDemoMediumDataset {


        public static void main(String[] args) {
            CleverSIDC sidc = new CleverSIDC();

        // Importing a medium dataset from a file
            try {
                File mediumDatasetFile = new File("medium_dataset.txt"); // Assume this file has ~5,000 entries
                sidc = sidc.importKeys(mediumDatasetFile);
                System.out.println("Imported medium dataset.");
            } catch (Exception e) {  // Catch a general exception if any operation within the try block can throw it
                System.out.println("An error occurred while importing the dataset: " + e.getMessage());
                return;
            }

        // Display all keys to confirm they are sorted and the tree is balanced
        System.out.println("All keys after importing medium dataset:");
        sidc.allKeys(sidc);

        // Perform searches for specific keys
        int searchKey = 105000; // Example key to search for
        Node foundNode = sidc.findNode(searchKey);
        if (foundNode != null) {
            System.out.println("Found student with ID " + searchKey + ": " + foundNode.data);
        } else {
            System.out.println("Student with ID " + searchKey + " not found.");
        }

        // Test the efficiency of rangeKey to find the range between two keys
        int startRange = 102000;
        int endRange = 108000;
        System.out.println("Number of students in range from " + startRange + " to " + endRange + ":");
        sidc.rangeKey(String.valueOf(startRange), String.valueOf(endRange));

        // Check how quickly we can add a new student
        try {
            int newID = sidc.generate();
            sidc.add(newID, "New Student");
            System.out.println("Added a new student with ID: " + newID);
        } catch (Exception e) {
            System.out.println("An error occurred while adding a new student: " + e.getMessage());
        }

        // Remove a student and show the tree remains balanced
        try {
            sidc.remove(searchKey);
            System.out.println("Removed student with ID " + searchKey);
        } catch (Exception e) {
            System.out.println("An error occurred while removing a student: " + e.getMessage());
        }

        // Final display of all keys
        System.out.println("Final state of all keys after removal:");
        sidc.allKeys(sidc);
    }
}
