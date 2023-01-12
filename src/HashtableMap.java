/**
 * This class creates a HashtableMap and performs many functions regarding the HashtableMap object.
 *
 * @author Quentin Holle
 */
import java.util.NoSuchElementException;

public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  private hashNode<KeyType, ValueType>[] hashBucket; // array of hashNode objects
  private int capacity; // array capacity
  private int size; // current array size

  /**
   * This method constructs a HashtableMap object of specified capacity
   * 
   * @param capacity the capacity wanted for the Hashtable
   * @return newly constructed HashtableMap object
   */
  @SuppressWarnings("unchecked")
  public HashtableMap(int capacity) {
    this.capacity = capacity;
    this.hashBucket = new hashNode[capacity];
    this.size = 0;
  }

  /**
   * This method constructs a HashtableMap object of default capacity
   * 
   * @return newly constructed HashtableMap object of default capacity
   */
  @SuppressWarnings("unchecked")
  public HashtableMap() {
    capacity = 20;
    this.hashBucket = new hashNode[capacity];
    this.size = 0;
  }

  /**
   * This method puts a HashtableNode object in the HashtableMap
   * 
   * @param key   the key of the object to be added. This key will be used to generate a hash value.
   * @param value the value of the object to be added
   * @return true if object is added, false otherwise
   */
  @Override
  public boolean put(KeyType key, ValueType value) {

    hashNode<KeyType, ValueType> hashNode = new hashNode<>(key, value, null);

    int location = Math.abs(key.hashCode()) % capacity;

    hashNode<KeyType, ValueType> currNode = hashBucket[location];

    if (this.containsKey(key) || key == null) {
      return false;
    }

    if (currNode == null) {
      hashBucket[location] = hashNode;
      size++;
      this.rehash();
    }

    else {
      while (currNode.hasNext()) {
        currNode = currNode.getNext();
      }
      currNode.setNext(hashNode);
      size++;
      this.rehash();
    }

    return true;
  }

  /**
   * This method locates a value in the HashTable using a key
   * 
   * @param key the key of the object to be located
   * @return the value of the object with associated key
   * @throws NoSuchElementException if the element cannot be located in the HashTable
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    for (int i = 0; i < hashBucket.length; i++) {
      try {

        if (hashBucket[i].getKey() == key) {
          return hashBucket[i].getValue();
        }
        hashNode curr = hashBucket[i];

        while (curr.hasNext()) {
          curr = curr.getNext();
          if (curr.getKey() == key) {
            return (ValueType) curr.getValue();
          }
        }

      } catch (NullPointerException e) {
      }
    }
    throw new NoSuchElementException();
  }

  /**
   * This method returns the current size of the HashTable
   * 
   * @return the current size of the HashTable
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * This method searches for a key in the HashTable
   * 
   * @param key the key to be located
   * @return true if the key is found in the HashTable, false otherwise
   */
  @Override
  public boolean containsKey(KeyType key) {
    for (int i = 0; i < hashBucket.length; i++) {
      try {
        if (hashBucket[i].getKey() == key) {
          return true;
        }
        hashNode curr = hashBucket[i];
        while (curr.hasNext()) {
          curr = curr.getNext();
          if (curr.getKey() == key) {
            return true;
          }
        }
      } catch (NullPointerException e) {
      }
    }
    return false;
  }

  /**
   * This method removes a HashNode from the HashTable
   * 
   * @param key the key to be removed
   * @return the value of the node removed from the HashTable, null if not found
   */
  @SuppressWarnings("unchecked")
  @Override
  public ValueType remove(KeyType key) {
    int location = Math.abs(key.hashCode()) % capacity;

    hashNode<KeyType, ValueType> currNode = hashBucket[location];

    if (currNode == null) {
      return null;
    }

    if (currNode != null && currNode.getKey().equals(key)) {
      if (currNode.hasNext()) {
        hashNode removed = currNode;
        currNode = currNode.getNext();
        size--;
        hashBucket[location] = currNode;
        return (ValueType) removed;
      } else {
        hashBucket[location] = null;
        size--;
        return (ValueType) currNode;
      }
    }

    while (currNode.hasNext()) {
      currNode = currNode.getNext();
      if (currNode.getKey().equals(key)) {
        if (currNode.hasNext()) {
          hashNode removed = currNode;
          currNode = currNode.getNext();
          size--;
          hashBucket[location] = currNode;
          return (ValueType) removed;
        } else {
          hashBucket[location] = null;
          size--;
          return (ValueType) currNode;
        }
      }

    }
    return null;
  }

  /**
   * This method completely clears a HashTable
   */
  @Override
  public void clear() {
    for (int i = 0; i < hashBucket.length; i++) {
      hashNode curr = hashBucket[i];
      if (hashBucket[i] != null) {
        while (curr.hasNext()) {
          size--;
          curr = curr.getNext();
        }
        hashBucket[i] = null;
        size--;
      }
    }
  }


  /**
   * This method rehashes a HashTable and all of its HashNodes if load is met or exceeded by an
   * added node.
   */
  @SuppressWarnings("unchecked")
  public void rehash() {
    if (.8 * (double) capacity <= size) {
      hashNode[] temp = new hashNode[capacity];
      for (int i = 0; i < hashBucket.length; i++) {
        temp[i] = hashBucket[i];
      }

      this.capacity = capacity * 2;
      this.hashBucket = new hashNode[capacity];
      this.size = 0;

      for (int i = 0; i < temp.length; i++) {
        hashNode curr = temp[i];
        if (curr != null) {
          if (curr.hasNext()) {
            while (curr.hasNext()) {
              this.put((KeyType) curr.getKey(), (ValueType) curr.getValue());
              curr = curr.getNext();
            }
          }

          this.put((KeyType) curr.getKey(), (ValueType) curr.getValue());
        }
      }
    }
  }
  
  /**
   * This method returns a string representation of the HashTable
   * 
   * @return a string representation of the HashTable object
   */
  public String toString() {
    String toString = "";
    String value = "";
    for (int i = 0; i < hashBucket.length; i++) {
      if (hashBucket[i] != null) {
        value = hashBucket[i].toString();
        hashNode curr = hashBucket[i];
        while (curr.hasNext()) {
          value = value + " - " + curr.getNext().toString();
          curr = curr.getNext();
        }
      }
      toString = toString + i + ": " + value + "\n";
      value = "";
    }
    return toString;
  }
}
