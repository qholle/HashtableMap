/**
 * This class tests the HashtableMap class.
 *
 * @author Quentin Holle
 */
import java.util.NoSuchElementException;

public class HashtableMapTests {
  
  /**
   * This test method tests the HashtableMap.put method.
   * 
   * @return true if all tests pass
   */
  @SuppressWarnings("unchecked")
  public static boolean test1() {
    HashtableMap test1 = new HashtableMap(8);

    test1.put("United States", "New York");

    if (!test1.containsKey("United States")) {
      System.out
          .println("Test 1 HashtableMap.put failed. Key \"United States\" with associated value "
              + "\"New York\" was not added to the hashtable.");
      return false;
    }

    test1.put("Germany", "Munich");
    
    if (!test1.containsKey("Germany")) {
      System.out.println("Test 2 HashtableMap.put failed. Key \"Germany\" with associated value "
          + "\"Munich\" was not added to the hashtable.");
      return false;
    }

    if (test1.put("United States", "Ohio")) {
      System.out.println("Test 3 HashtableMap.put failed. Put method returned true when there was "
          + "already a value with that key in the hashtable.");
      return false;
    }

    if (!test1.get("United States").equals("New York")) {
      System.out.println("Test 4 HashtableMap.put failed. Something other than \"New York\" is "
          + "associated to the key \"United States\".");
      return false;
    }

    return true;
  }

  /**
   * This test method tests the HashtableMap.get method.
   * 
   * @return true if all tests pass
   */
  @SuppressWarnings("unchecked")
  public static boolean test2() {
    HashtableMap test2 = new HashtableMap(5);

    test2.put("United States", "New York");

    if (!test2.get("United States").equals("New York")) {
      System.out.println("Test 1 HashtableMap.get failed. Value "
          + "\"New York\" was not returned by the get function.");
      return false;
    }

    test2.put("United States", "Alabama");

    if (test2.get("United States").equals("Alabama")) {
      System.out.println("Test 2 HashtableMap.get failed. Value "
          + "\"New York\" was not returned by the get function.");
      return false;
    }

    try {
      test2.get("India").equals("New Dehli");
    } catch (NoSuchElementException e) {
      return true;
    }

    System.out.println("Test 3 HashtableMap.get failed. A value was returned when a "
        + "NoSuchElementException was expected.");
    return false;
  }

  /**
   * This test method tests the HashtableMap.remove method.
   * 
   * @return true if all tests pass
   */
  @SuppressWarnings("unchecked")
  public static boolean test3() {
    HashtableMap test3 = new HashtableMap(5);

    test3.put("United States", "New York");
    if (!test3.remove("United States").toString().equals("United States, New York")) {
      System.out.println("Test 1 HashtableMap.remove failed. Value "
          + "\"New York\" was supposed to be returned by the remove function.");
      return false;
    }
    
    if(test3.remove("Macedonia") != null) {
      System.out.println("Test 2 HashtableMap.remove failed. Something other than null was returned");
      return false;
    }
    
    if(test3.containsKey("United States")) {
      System.out.println("Test 3 HashtableMap.remove failed. Value "
          + "\"New York\" remained in array even after it was supposed to be removed");
      return false;
    }

    return true;

  }
  /**
   * This test method tests the HashtableMap.size method.
   * 
   * @return true if all tests pass
   */
  @SuppressWarnings("unchecked")
  public static boolean test4() {
    HashtableMap test4 = new HashtableMap(6);
    
    test4.put("Japan", "Tokyo");
    test4.put("United States", "New York");
    test4.put("Russia", "Moscow");
    test4.put("France", "Paris");

    if(test4.size() != 4) {
      System.out.println("Test 1 HashtableMap.size failed. Expected Size: 4 Actual Size: " 
    + test4.size());
      return false;
    }
    
    test4.clear();
    
    if(test4.size() != 0) {
      System.out.println("Test 2 HashtableMap.size failed. Expected Size: 0 Actual Size: " 
    + test4.size());
      return false;
    }
    
    if(test4.containsKey("United States")) {
      System.out.println("Test 3 HashtableMap.size failed. Value "
          + "\"New York\" remained in array even after it was supposed to be removed");
      return false;
    }
    
    
    return true;

  }

  /**
   * This test method tests the HashtableMap.rehash method.
   * 
   * @return true if all tests pass
   */
  @SuppressWarnings("unchecked")
  public static boolean test5() {
    HashtableMap test5 = new HashtableMap(2);
    test5.put("Japan", "Tokyo");
    test5.put("United States", "New York");
    test5.put("Russia", "Moscow");
    test5.put("France", "Paris");
    
    if (test5.size() != 4) {
      System.out.println("Test 1 HashtableMap.rehash failed. A value was not added to the Hashtable "
          + "Map when it was rehashed.");
      return false;
    }
    
    if (!test5.containsKey("Japan") || !test5.containsKey("United States")) {
      System.out.println("Test 2 HashtableMap.rehash failed. Keys shouldn't have been removed during"
          + "the rehashing process");
      return false;
    }
    return true;

  }

  /**
   * Main method runs all HashtableMap tests.
   */
  public static void main(String[] args) {
    if (test1()) {
      System.out.println("HashtableMap.put tests passed!");
    }
    if (test2()) {
      System.out.println("HashtableMap.get tests passed!");
    }
    if (test3()) {
      System.out.println("HashtableMap.remove tests passed!");
    }
    if (test4()) {
      System.out.println("HashtableMap.size tests passed!");
    }
    if (test5()) {
      System.out.println("HashtableMap.rehash tests passed!");
    }
  }
}
