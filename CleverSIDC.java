import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

//AVL Tree
public class CleverSIDC {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public CleverSIDC(){
    }

    public CleverSIDC(Node root){
        this.root = root;
    }

    /**
     * Returns height of passed Node
     * If subtree is empty, return -1
     * If subtree is filled, return height
     * @param n Node whose height needs retrieval
     * @return height of Node n
     */
    int getHeight(Node n) {
        return n == null ? -1 : n.height;
    }

    /**
     * Sets a node's height to height of children + 1
     * @param n Node whose height needs calculating
     */
    void setHeight(Node n) {
        n.height = 1 + Math.max(getHeight(n.left), getHeight(n.right));
    }

    /**
     * Calculates a node's balance factor
     * @param n Node whose balance factor needs calculating
     * @return Node's balance factor
     */
    int getBalance(Node n){
        return n == null ? 0 : getHeight(n.right) - getHeight(n.left);
    }

    /**
     * Rotates subtree to the right
     * @param n parent Node of subtree
     * @return left child of subtree
     */
    Node rotateR (Node n) {
        Node lChild = n.left;
        n.left = lChild.right;
        lChild.right = n;
        setHeight(n);
        setHeight(lChild);
        return lChild;
    }

    /**
     * Rotates subtree to the left
     * @param n parent Node of subtree
     * @return right child of subtree
     */
    Node rotateL (Node n) {
        Node rChild = n.right;
        n.right = rChild.left;
        rChild.left = n;
        setHeight(n);
        setHeight(rChild);
        return rChild;
    }

    /**
     * Rebalances subtree depending on the imbalance formed
     * @param n parent Node of subtree
     * @return parent Node of subtree
     */
    Node rebalance(Node n) {
        setHeight(n);
        int balanceFactor = getBalance(n);
        if (balanceFactor > 1){             //right heavy tree
            if (getHeight(n.right.right) > getHeight(n.right.left)) {
                n = rotateL(n);
            }
            else {
                n.right = rotateR(n.right);
                n = rotateL(n);
            }
        }
        else if(balanceFactor < -1) {       //left heavy tree
            if (getHeight(n.left.left) > getHeight(n.left.right)) {
                n = rotateR(n);
            }
            else {
                n.left = rotateL(n.left);
                n = rotateR(n);
            }
        }
        return n;
    }

    /**
     * Insertion with only the ID code
     * @param StudentIdentificationCode ID code to be inserted
     * @throws Exception duplicated student ID
     */
    public void add(int StudentIdentificationCode) throws Exception {
        root = add(root, StudentIdentificationCode);
    }

    /**
     * @Overload
     * Insertion with ID code and data
     * @param StudentIdentificationCode ID code to be inserted
     * @param data data associated with ID code
     * @throws Exception duplicated student ID
     */
    public void add(int StudentIdentificationCode, String data) throws Exception {
        root = add(root, StudentIdentificationCode, data);
    }

    /**
     * @Overload
     * Inserts node by finding correct location
     * @param n traversal node
     * @param StudentIdentificationCode ID code from file import
     * @return parent Node of rebalanced subtree
     * @throws Exception duplicated student ID
     */
    Node add(Node n, int StudentIdentificationCode) throws Exception {
        //no node at current position, store node here (i.e. empty trees)
        if (n == null) {
            return new Node(StudentIdentificationCode, "");
        }
        //no node in Lchild, store in Lchild
        else if (n.StudentIdentificationCode > StudentIdentificationCode) {
            n.left = add(n.left, StudentIdentificationCode);
        }
        //no node in Rchild, store in Rchild
        else if (n.StudentIdentificationCode < StudentIdentificationCode) {
            n.right = add(n.right, StudentIdentificationCode);
        }
        //duplicate ID exception
        else {
            throw new Exception("Duplicate ID: " + StudentIdentificationCode);
        }
        //sets height of node and rebalances
        setHeight(n);
        return rebalance(n);
    }

    /**
     * @Overload
     * Inserts node by finding correct location
     * @param n traversal node
     * @param StudentIdentificationCode ID code from file import
     * @param data data attached to keys
     * @return parent Node of rebalanced subtree
     * @throws Exception duplicated student ID
     */
    Node add(Node n, int StudentIdentificationCode, String data) throws Exception {
        //no node at current position, store node here (i.e. empty trees)
        if (n == null) {
            return new Node(StudentIdentificationCode, data);
        }
        //no node in Lchild, store in Lchild
        else if (n.StudentIdentificationCode > StudentIdentificationCode) {
            n.left = add(n.left, StudentIdentificationCode, data);
        }
        //no node in Rchild, store in Rchild
        else if (n.StudentIdentificationCode < StudentIdentificationCode) {
            n.right = add(n.right, StudentIdentificationCode, data);
        }
        //duplicate ID exception
        else {
            throw new Exception("Duplicate ID: " + StudentIdentificationCode);
        }
        //sets height of node and rebalances
        setHeight(n);
        return rebalance(n);
    }

    /**
     * Deletion with only the ID code
     * @param StudentIdentificationCode ID code to be deleted
     */
    public void remove(int StudentIdentificationCode) {
        root = remove(root, StudentIdentificationCode);
    }

    /**
     * @Overload
     * Deletes node and rebalances approriately
     * @param n traversal node
     * @param StudentIdentificationCode ID code to be deleted
     * @return new node after deletion
     */
    Node remove(Node n, int StudentIdentificationCode) {
        if (n == null) {            //node is empty at current position
            return n;
        } else if (n.StudentIdentificationCode > StudentIdentificationCode) {   //traverse left
            n.left = remove(n.left, StudentIdentificationCode);
        } else if (n.StudentIdentificationCode < StudentIdentificationCode) {   //traverse right
            n.right = remove(n.right, StudentIdentificationCode);
        } else {
            if (n.left == null && n.right == null) {        //node has no children, delete it
                n = null;
            } else if (n.left == null) {                    //node has one Rchild, replace with Rchild
                n = n.right;
            } else if (n.right == null) {                   //node has one Lchild, replace with Lchild
                n = n.left;
            } else {                                        //node has two children
                removeNodeWithChildren(n);
            }
        }
        return n;                                           //return new node after deletion
    }

    /**
     * If Node for deletion has children, use this function
     * @param n Node for deletion
     */
    private void removeNodeWithChildren(Node n) {
        //finds minimum node (inorder successor) of right subtree
        Node successor = smallestNode(n.right);
        //copies data of inorder successor to current Node
        n.StudentIdentificationCode = successor.StudentIdentificationCode;
        n.data = successor.data;
        //deletes inorder successor recursively
        n.right = remove(n.right, successor.StudentIdentificationCode);
    }

    /**
     * Finds smallest node of subtree with root node n
     * @param n root node of subtree
     * @param n root node of subtree
     * @param n root node of subtree
     * @param n root node of subtree
     * @return smallest node in subtree with root node n
     */
    private Node smallestNode(Node n) {
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }

    /**
     * Finds biggest node of subtree with root node n
     * @param n root node of subtree
     * @return biggest node in subtree with root node n
     */
    public Node biggestNode(Node n) {
        while (n.right != null) {
            n = n.right;
        }
        return n;
    }

    /**
     * Finds a node given a student ID code
     * @param StudentIdentificationCode ID code that needs to be found
     * @return Node with ID code, if found
     */
    Node findNode (int StudentIdentificationCode) {
        Node current = root;
        while (current != null){
            if (current.StudentIdentificationCode == StudentIdentificationCode) {
                break;
            }
            if (current.StudentIdentificationCode < StudentIdentificationCode) {
                current = current.right;
            }
            else {
                current = current.left;
            }
        }
        return current;
    }

    /**
     * Import all keys and construct AVL tree from a given file
     * @param text file to be parsed
     * @return constructed AVL tree
     */
    CleverSIDC importKeys(File text) {
        Scanner scanner = null;
        CleverSIDC AVL = new CleverSIDC(root);
        try {
            scanner = new Scanner(text);
            while (scanner.hasNextLine()) {
                int StudentIdentificationCode = Integer.parseInt(scanner.nextLine());
                try {
                    add(StudentIdentificationCode);
                } catch (Exception e) {
                    System.out.println("Duplicate ID found: " + StudentIdentificationCode);
                }
            }
            AVL.root = this.root;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + text.getPath());
            return null; // Or handle it in a way that's appropriate for your application
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return AVL;
    }


    /**
     * Shows all keys of a given AVL tree in inorder notation
     * @param AVL Tree to be shown
     */
    void allKeys(CleverSIDC AVL) {
        inOrder(AVL.root);
    }

    /**
     * Produces inorder notation of the tree
     * @param n Root node
     */
    void inOrder(Node n) {
        if(n == null){
            return;
        }
        inOrder(n.left);
        String formatted = String.format("%08d", n.StudentIdentificationCode);      //provides leading zeroes up to 8 digits
        System.out.print(formatted + " ");
        inOrder(n.right);
    }

    /**
     * Provides the inorder successor of the node provided
     * @param AVL AVL tree containing node with key
     * @param key Key of node to find successor
     * @return successor of node
     */
    String nextKey(CleverSIDC AVL, String key){
        counter = 0;
        index = 0;
        int size = getSize(AVL.root);
        int arr[] = new int[size];
        arr = toArray(AVL.root, arr);
        boolean lock = true;
        int answer = -1;
        for(int i=0; i<arr.length; i++){
            if(!lock){
                answer = arr[i];
                break;
            }
            if(arr[i] == Integer.parseInt(key)){
                lock = false;
            }
        }
        if(answer == -1){
            return "\nThe inputted key has no next key as it is the last key.";
        }
        return "\nThe key that is next in line is: " + String.format("%08d", answer);
    }

    /**
     * Provides the inorder predecessor of the node provided
     * @param AVL AVL tree containing node with key
     * @param key Key of node to find predecessor
     * @return predecessor of node
     */
    String prevKey(CleverSIDC AVL, String key){
        counter = 0;
        index = 0;
        int size = getSize(AVL.root);
        int arr[] = new int[size];
        arr = toArray(AVL.root, arr);
        boolean lock = true;
        int answer = -1;

        for(int i=0; i<arr.length; i++){
            if(!lock){
                answer = arr[i-1];
            }
            if(arr[i] == Integer.parseInt(key)){
                break;
            }
            lock = false;
        }
        if(answer == -1){
            return "\nThe inputted key has no previous key as it is the first key.";
        }
        return "\nThe key that is previous to the one inputted is: " + String.format("%08d", answer);
    }

    int index = 0;
    int[] toArray(Node n, int[] arr){
        if(n == null){
            return arr;
        }
        toArray(n.left, arr);
        arr[index] = n.StudentIdentificationCode;
        index++;
        toArray(n.right, arr);
        return arr;
    }

    int counter = 0;
    int getSize(Node n){
        if(n == null){
            return counter;
        }
        getSize(n.left);
        counter++;
        getSize(n.right);
        return counter;
    }


    /**
     * Sets size threshold of database
     * Function not implemented; see reason in accompanying documentation
     * @param size new size of ADT
     */
    public static void setSIDCThreshold (int size) {
        //this function n
    }

    /**
     * Generate random 8-digit key at end of tree and appends
     */
    public int generate() {
        Random r = new Random();
        r.setSeed(352);
        return r.nextInt(10);
    }

    /**
     * Get values of given key in a tree
     * @param AVL tree to be searched
     * @param key key to be retrieved
     */
    public void getValues(CleverSIDC AVL, String key) {
        Node n = findNode(Integer.parseInt(key));
        if(n.data.equals("")){
            System.out.println("\nThe key (" + key + ") holds no data.");
            return;
        }
        System.out.println("\nThe data that the node with key (" + key + ") holds is: " + n.data);
    }

    /**
     * Finds the range of keys between two keys
     * @param key1 lower limit
     * @param key2 upper limit
     */
    public void rangeKey(String key1, String key2) {
        int f, l;
        if(Integer.parseInt(key1) < Integer.parseInt(key2)){
            f = Integer.parseInt(key1);
            l = Integer.parseInt(key2);
        }
        else if(Integer.parseInt(key1) > Integer.parseInt(key2)){
            l = Integer.parseInt(key1);
            f = Integer.parseInt(key2);
        }
        else {
            System.out.println("The range is : (" + Integer.parseInt(key1) + ")");
            return;
        }

        counter = 0;
        index = 0;

        int size = getSize(root);
        int arr[] = new int[size];
        arr = toArray(root, arr);
        boolean orLock = false;
        System.out.print("\nThe range is : ( ");
        for(int i=0; i<arr.length; i++){
            if(arr[i] == f || orLock){
                orLock = true;
                System.out.print(String.format("%08d", arr[i]) + " ");
                if(arr[i] == l){
                    System.out.print(")\n");
                    break;
                }
            }
        }
    }


} //end of class