/**
 * This class constructs and sets the values of hashNode objects
 *
 * @author Quentin Holle
 */
public class hashNode<KeyType, ValueType>{

  private KeyType key;
  private ValueType value;
  private hashNode<KeyType, ValueType> next;

  /**
   * This method constructs a hashNode object
   * 
   * @param key the key of the hashNode
   * @param value the value of the hashNode
   * @param next the next field of the hashNode
   * @return the newly constructed hashNode object
   */
  public hashNode(KeyType key, ValueType value, hashNode<KeyType, ValueType> next) {
    this.key = key;
    this.value = value;
    this.next = next;
  }
  
  /**
   * This method returns this hashNode's key
   * 
   * @return the key of this hashNode object
   */
  public KeyType getKey() {
    return this.key;
  }
  
  /**
   * This method returns this hashNode's value
   * 
   * @return the value of this hashNode object
   */
  public ValueType getValue() {
    return this.value;
  }
  
  /**
   * This method sets this hashNode's next field
   */
  @SuppressWarnings("unchecked")
  public void setNext(hashNode next) {
    this.next = next;
  }
  
  /**
   * This method returns this hashNode's next field
   * 
   * @return the next value of this hashNode object
   */
  public hashNode getNext() {
    return this.next;
  }
  
  /**
   * This method creates a string representation of this hashNode
   * 
   * @return a string representation of this hashNode
   */
  public String toString() {
    return ((String)this.key + ", " + (String)this.getValue());
  }
  
  /**
   * This method checks if this hashNode has a next value
   * 
   * @return false if this hashNode has a null next field, true otherwise 
   */
  public Boolean hasNext() {
    if (this.next != null) {
      return true;
    }
    return false;
  }
}

