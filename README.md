# CleverSIDC - Student Identification Code Management

## Overview
CleverSIDC is a **Self-Balancing AVL Tree** implementation designed for efficient management of **Student Identification Codes (SIDC)**. It supports operations such as insertion, deletion, retrieval, and range queries while maintaining balanced performance.

## Features
- **AVL Tree-Based Implementation**: Ensures logarithmic time complexity \(O(\log N)\) for insertions, deletions, and searches.
- **Efficient Searching**: Supports finding a specific ID, next and previous keys, and range queries.
- **Dynamic Dataset Handling**: Demonstrated with small and medium datasets.
- **Automatic Balancing**: Maintains a balanced tree for optimized performance.

## File Structure
```
├── CleverSIDC.java                   # Core AVL tree implementation for SIDC management
├── Node.java                          # Node structure with balancing properties
├── CleverSIDCDemoSmallDataset.java    # Demonstration with a small dataset
├── CleverSIDCDemoMediumDataset.java   # Demonstration with a medium dataset
```

## Installation & Setup
### Prerequisites
- **Java 8 or later** must be installed.

### Compilation
```bash
javac CleverSIDC.java Node.java CleverSIDCDemoSmallDataset.java CleverSIDCDemoMediumDataset.java
```

### Running the Program
#### Small Dataset Example
```bash
java CleverSIDCDemoSmallDataset
```
#### Medium Dataset Example
```bash
java CleverSIDCDemoMediumDataset
```

## Functionalities
### **Insertion**
- Inserts a **new Student Identification Code** into the AVL tree.
- Prevents duplicate entries.
- Example:
  ```java
  sidc.add(10000001, "Student One");
  sidc.add(10000002, "Student Two");
  ```

### **Search**
- Finds a student by their ID.
- Example:
  ```java
  Node student = sidc.findNode(10000001);
  ```

### **Next & Previous Key**
- Retrieves the **next** and **previous** student ID in order.
- Example:
  ```java
  String nextID = sidc.nextKey(sidc, "10000003");
  String prevID = sidc.prevKey(sidc, "10000003");
  ```

### **Range Query**
- Retrieves all student IDs within a given range.
- Example:
  ```java
  sidc.rangeKey("10000010", "10000020");
  ```

### **Deletion**
- Removes a **specific Student Identification Code** while maintaining tree balance.
- Example:
  ```java
  sidc.remove(10000001);
  ```

### **Import Dataset**
- Loads a dataset from a file and constructs the AVL tree.
- Example:
  ```java
  File dataset = new File("medium_dataset.txt");
  sidc = sidc.importKeys(dataset);
  ```

## Performance Analysis
| Operation       | Time Complexity |
|----------------|----------------|
| Insertion      | \(O(\log N)\)  |
| Search         | \(O(\log N)\)  |
| Deletion       | \(O(\log N)\)  |
| Range Query    | \(O(K + \log N)\) (where K is the number of keys in range) |

## Example Output
```
Added five students.
All keys after initial insertions:
10000001 10000002 10000003 10000004 10000005 
Successor of 10000003: 10000004
Predecessor of 10000003: 10000002
Removed Student Three.
All keys after removal:
10000001 10000002 10000004 10000005 
```

## Contributions
Feel free to fork the repository and submit pull requests for improvements!



