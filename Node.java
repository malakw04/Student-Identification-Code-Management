public class Node {
    int StudentIdentificationCode; // Assuming it's a long because of its range
    String data;                    // Additional data associated with the student
    Node left;                      // Reference to the left child
    Node right;                     // Reference to the right child
    int height;                     // Height of the node for AVL balancing

    // Constructor for Node with only SIDC
    public Node(int StudentIdentificationCode) {
        this.StudentIdentificationCode = StudentIdentificationCode;
        this.data = ""; // Initialize data as empty or as per requirement
        this.left = null;
        this.right = null;
        this.height = 0; // Initial height is 0 for a new node
    }

    // Overloaded constructor for Node with SIDC and data
    public Node(int StudentIdentificationCode, String data) {
        this.StudentIdentificationCode = StudentIdentificationCode;
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 0;
    }
}
