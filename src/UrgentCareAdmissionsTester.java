// File Header comes here
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P01 UrgentCareAdmissions Tester
// Course: CS 300 Spring 2023
//
// Author: Matthew Frisch
// Email: mfrisch3@wisc.edu
// Lecturer: Mouna Kacem 
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: n/a
// Partner Email: n/a
// Partner Lecturer's Name: n/a
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: (identify each by URL and describe how it helped)
//
/////////////////////////////////////////////////////////////////////////////
import java.util.Arrays; // consider using Arrays.deepEquals() to test the contents of a 2D array

// Javadoc style class header comes here
/**
 * 
 * @author mattf
 *
 */
public class UrgentCareAdmissionsTester {

  /**
   * This test method is provided for you in its entirety, to give you a model for the rest of this
   * class. This method tests the getAdmissionIndex() method on a non-empty, non-full array of
   * patient records which we create and maintain here.
   * 
   * This method tests three scenarios:
   * 
   * 1. Adding a patient with a HIGHER triage priority than any currently present in the array. To
   * do this, we create an array with no RED priority patients and get the index to add a RED
   * priority patient. We expect this to be 0, so if we get any other value, the test fails.
   * 
   * 2. Adding a patient with a LOWER triage priority than any currently present in the array. To do
   * this, we create an array with no GREEN priority patients and get the index to add a GREEN
   * priority patient. We expect this to be the current size of the oversize array, so if we get any
   * other value, the test fails.
   * 
   * 3. Adding a patient with the SAME triage priority as existing patients. New patients at the
   * same priority should be added AFTER any existing patients. We test this for all three triage
   * levels on an array containing patients at all three levels.
   * 
   * @author hobbes
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testGetIndex() {

    // The non-empty, non-full oversize arrays to use in this test.
    // Note that we're using the UrgentCareAdmissions named constants to create these test records,
    // rather than their corresponding literal int values.
    // This way if the numbers were to change in UrgentCareAdmissions, our test will still be valid.
    int[][] patientsListAllLevels =
        new int[][] {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
            {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
            {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null};
    int allLevelsSize = 5;

    int[][] patientsListOnlyYellow = new int[][] {{21801, 2, UrgentCareAdmissions.YELLOW},
        {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW}, null,
        null, null, null, null};
    int onlyYellowSize = 3;

    // scenario 1: add a patient with a higher priority than any existing patient
    {
      int expected = 0;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED,
          patientsListOnlyYellow, onlyYellowSize);
      if (expected != actual) {
        return false;
      }

    }

    // scenario 2: add a patient with a lower priority than any existing patient
    {
      int expected = onlyYellowSize;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN,
          patientsListOnlyYellow, onlyYellowSize);

      if (expected != actual)
        return false;
    }

    // scenario 3: verify that a patient with the same priority as existing patients gets
    // added after all of those patients
    {
      int expected = 1;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED,
          patientsListAllLevels, allLevelsSize);
      if (expected != actual)
        return false;

      expected = 4;
      actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.YELLOW,
          patientsListAllLevels, allLevelsSize);
      if (expected != actual)
        return false;

      expected = allLevelsSize;
      actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN,
          patientsListAllLevels, allLevelsSize);
      if (expected != actual)
        return false;
    }

    // and finally, verify that the arrays were not changed at all
    {
      int[][] allLevelsCopy = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
          {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
          {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN}, null,
          null, null};
      if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy))
        return false;

      int[][] onlyYellowCopy = new int[][] {{21801, 2, UrgentCareAdmissions.YELLOW},
          {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW}, null,
          null, null, null, null};
      if (!Arrays.deepEquals(patientsListOnlyYellow, onlyYellowCopy)) {

        return false;
      }
    }

    return true;
  }

  // Tests the behavior of the addPatient method using a non-empty, non-full array. Each test
  // should verify that the returned size is as expected and that the array has been updated
  // (or not) appropriately
  public static boolean testAddPatient() {
    // (1) add a patient to the END of the patientsList
    int[][] allLevelsCopy =
        new int[][] {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
            {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
            {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null};
    int[][] allLevelsCopied = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
        {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
        {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN},
        {12345, 6, UrgentCareAdmissions.GREEN}, null, null};
    int size = 5;
    int[] patientRecord = {12345, 6, UrgentCareAdmissions.GREEN};
    UrgentCareAdmissions.addPatient(patientRecord,
        UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN, allLevelsCopy, size),
        allLevelsCopy, size);
    if (!Arrays.deepEquals(allLevelsCopy, allLevelsCopied)) {
      return false;
    }
    // (2) add a patient to the MIDDLE of the patientsList

    // (3) add a patient using an invalid (out-of-bounds) index
    return true;
  }

  // Tests the behavior of the removeNextPatient method using a non-empty, non-full array. Each
  // test should verify that the returned size is as expected and that the array has been updated
  // (or not) appropriately
  public static boolean testRemovePatient() {
    // (1) remove a patient from a patientsList containing more than one record
    int[][] allLevelsCopy =
        new int[][] {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
            {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
            {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null};
    int size = 5;
    int[][] allLevelsCopyRemoved = {{21801, 2, UrgentCareAdmissions.YELLOW},
        {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
        {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null, null};
    UrgentCareAdmissions.removeNextPatient(allLevelsCopy, size);
    if (!Arrays.deepEquals(allLevelsCopy, allLevelsCopyRemoved)) {
      return false;
    }

    // (2) remove a patient from a patientsList containing only one record
    int[][] onlyOneRecord = new int[][] {{32702, 3, UrgentCareAdmissions.RED}, null, null, null,
        null, null, null, null};
    int[][] emptyArrayList = {null, null, null, null, null, null, null, null};
    size = 1;
    UrgentCareAdmissions.removeNextPatient(onlyOneRecord, size);
    if (!Arrays.deepEquals(onlyOneRecord, onlyOneRecord)) {
      return false;
    }

    return true;
  }

  // Tests the behavior of the getPatientIndex method using a non-empty, non-full array.
  public static boolean testGetPatientIndex() {
    // (1) look for a patient at the end of the list
    int[][] allLevelsCopy =
        new int[][] {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
            {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
            {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null};
    int size = 5;


    if (UrgentCareAdmissions.getPatientIndex(31501, allLevelsCopy, size) == -1) {
      return false;
    }

    // (2) look for a patient in the middle of the list
    if (UrgentCareAdmissions.getPatientIndex(22002, allLevelsCopy, size) == -1) {
      return false;
    }
    // (3) look for a patient not present in the list
    if (UrgentCareAdmissions.getPatientIndex(11111, allLevelsCopy, size) != -1) {
      return false;
    }
    return true;
  }

  // Tests the getLongestWaitingPatientIndex method using a non-empty, non-full array. When
  // designing these tests, recall that arrivalOrder values will all be unique!
  public static boolean testLongestWaitingPatient() {
    // (1) call the method on a patientsList with only one patient
    int[][] allLevelsCopy = new int[][] {{32702, 3, UrgentCareAdmissions.RED}, null, null, null,
        null, null, null, null};

    int size = 1;
    if (UrgentCareAdmissions.getLongestWaitingPatientIndex(allLevelsCopy, size) != 0) {
      return false;
    }

    // (2) call the method on a patientsList with at least three patients
    int[][] threePatients =
        new int[][] {{32702, 3, UrgentCareAdmissions.RED}, {21801, 2, UrgentCareAdmissions.YELLOW},
            {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW},
            {31501, 1, UrgentCareAdmissions.GREEN}, null, null, null};

    size = 5;
    if (UrgentCareAdmissions.getLongestWaitingPatientIndex(threePatients, size) != 4) {
      return false;
    }

    return true;
  }

  // Tests the edge case behavior of the UrgentCareAdmissions methods using empty and full arrays
  public static boolean testEmptyAndFullArrays() {
    // (1) test getAdmissionIndex using an empty patientsList array and any triage level
    int[][] emptyList = new int[][] {null, null, null, null, null, null, null, null};
    int sizeEmpty = 0;
    if (UrgentCareAdmissions.getAdmissionIndex(2, emptyList, sizeEmpty) != 0) {
      return false;
    }
    // (2) test getAdmissionIndex using a full patientsList array and any triage level
    int[][] fullList = new int[][] {{32702, 3, UrgentCareAdmissions.RED},
        {21801, 2, UrgentCareAdmissions.YELLOW}, {22002, 4, UrgentCareAdmissions.YELLOW},
        {11901, 5, UrgentCareAdmissions.YELLOW}, {31501, 1, UrgentCareAdmissions.GREEN},
        {29423, 6, UrgentCareAdmissions.GREEN}, {32694, 7, UrgentCareAdmissions.GREEN}};
    int sizeFull = 7;
    if (UrgentCareAdmissions.getAdmissionIndex(2, fullList, sizeFull) != -1) {
      return false;
    }
    // (3) test addPatient using a full patientsList array
    int[] patientRecord = {32662, 8, UrgentCareAdmissions.RED};
    if (UrgentCareAdmissions.addPatient(patientRecord, sizeFull, fullList, sizeFull) != sizeFull) {
      return false;
    }
    // (4) test removeNextPatient using an empty patientsList array
    if (UrgentCareAdmissions.removeNextPatient(emptyList, sizeEmpty) != 0) {
      return false;
    }
    // (5) test getPatientIndex using an empty patientsList array
    if (UrgentCareAdmissions.getPatientIndex(sizeEmpty, fullList, sizeFull) != -1) {
      return false;
    }
    // (6) test getLongestWaitingPatientIndex using an empty patientsList array
    if (UrgentCareAdmissions.getLongestWaitingPatientIndex(emptyList, sizeEmpty) != -1) {
      return false;
    }
    return true;
  }

  // Tests the getSummary method using arrays in various states
  public static boolean testGetSummary() {
    // (1) test getSummary using an empty patientsList
    int[][] emptyList = new int[][] {null, null, null, null, null, null, null, null};
    int sizeEmpty = 0;
    String expectedSummary = "Total number of patients: " + 0 + "\r\n" + " RED: " + 0 + "\r\n"
        + " YELLOW: " + 0 + "\r\n" + " GREEN: " + 0;
    if (!UrgentCareAdmissions.getSummary(emptyList, sizeEmpty).equals(expectedSummary)) {
      return false;
    }
    // (2) test getSummary using a patientsList with multiple patients at a single triage level
    int[][] patientsListOnlyYellow = new int[][] {{21801, 2, UrgentCareAdmissions.YELLOW},
        {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW}, null,
        null, null, null, null};
    int onlyYellowSize = 3;
    String expectedOnlyYellowSummary = "Total number of patients: " + 3 + "\r\n" + " RED: " + 0
        + "\r\n" + " YELLOW: " + 3 + "\r\n" + " GREEN: " + 0;
    if (!UrgentCareAdmissions.getSummary(patientsListOnlyYellow, onlyYellowSize)
        .equals(expectedOnlyYellowSummary)) {
      return false;
    }
    // (3) test getSummary using a patientsList with patients at all triage levels
    return true;
  }

  /**
   * Runs each of the tester methods and displays their result
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("get index: " + testGetIndex());
    System.out.println("add patient: " + testAddPatient());
    System.out.println("remove patient: " + testRemovePatient());
    System.out.println("get patient: " + testGetPatientIndex());
    System.out.println("longest wait: " + testLongestWaitingPatient());
    System.out.println("empty/full: " + testEmptyAndFullArrays());
    System.out.println("get summary: " + testGetSummary());
  }

}
