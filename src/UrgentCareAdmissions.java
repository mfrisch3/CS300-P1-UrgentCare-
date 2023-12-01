//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P01 UrgentCarAdmissions
// Course: CS 300 Spring 2023
//
// Author: Matthew Frisch
// Email: mfrisch3@wisc.edu
// Lecturer: (Mouna Kacem)
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
import java.util.Arrays;

/**
 * @author mattf
 *
 */
public class UrgentCareAdmissions {
  public static final int GREEN = 3;
  public static final int RED = 1;
  public static final int YELLOW = 2;


  /**
   * A helper method to find the correct index to insert a new patient at the given triage level.
   * 
   * @param triage
   * @param patientsList
   * @param size
   * @return
   */
  public static int getAdmissionIndex(int triage, int[][] patientsList, int size) {
    if (size == patientsList.length) { // checking if list is full
      return -1;
    }
    if (size == 0) {
      return 0;
    }

    // find index where to add the patient with the given triage
    for (int i = 0; i < size; i++) {
      if (patientsList[i][2] > triage) {
        return i;
      }
    }
    return size;
  }



  /**
   * Adds the patient record, a three-element perfect size array of ints, to the patients list in
   * the given position. This method must maintain the ordering of the rest of the array, so any
   * patients in higher index positions must be shifted out of the way.
   * 
   * @param patientRecord
   * @param index
   * @param patientsList
   * @param size
   * @return
   */
  public static int addPatient(int[] patientRecord, int index, int[][] patientsList, int size) {
    // check the validity of index and if the list is full
    if (size == patientsList.length) {
      return size;
    }
    if (index < 0 || index > size) {
      return -1;
    }

    // adding patient record to patient list
    for (int i = size - 1; i >= index; i--) {
      patientsList[i + 1] = patientsList[i];
      // patientsList[index][i] = patientRecord[i];
    }
    patientsList[index] = patientRecord;


    return size + 1;

  }

  /**
   * 
   * @param size
   * @return
   */
  private static int patientsList(int size) {
    // TODO Auto-generated method stub
    return 0;
  }



  /**
   * Removes the patient record at index 0 of the patientsList, if there is one, and updates the
   * rest of the list to maintain the oversize array in its current ordering.
   * 
   * @param patientsList
   * @param size
   * @return the number of patients in patientsList after this method has finished running
   */
  public static int removeNextPatient(int[][] patientsList, int size) {
    if (size == 0) {
      return 0;
    }


    for (int i = 0; i < size - 1; i++) {
      patientsList[i] = patientsList[i + 1];
      // patientsList[index][i] = patientRecord[i];
    }
    patientsList[size - 1] = null;
    return size - 1;
  }

  /**
   * Finds the index of a patient given their caseID number. This method must not modify
   * patientsList in any way.
   * 
   * @param caseID       the five-digit case number assigned to the patient record to find
   * @param patientsList - the current, active list of patient records
   * @param size-        the number of patients currently in the list
   * @return the index of the patient record matching the given caseID number, or -1 if the list is
   *         empty or the caseID is not found
   */
  public static int getPatientIndex(int caseID, int[][] patientsList, int size) {
    if (size == 0) { // Check if the list is empty
      return -1;
    }
    // look for a patient starting from the end of list
    for (int i = size - 1; i >= 0; i--) {
      if (patientsList[i][0] == caseID) {
        return i;
      }
    }


    return -1;
  }

  /**
   * Finds the patient who arrived earliest still currently present in the patientsList, and returns
   * the index of their patient record within the patientsList. The arrival value is strictly
   * increasing for each new patient, so you will not need to handle the case where two values are
   * equal.
   * 
   * @param patientsList
   * @param size
   * @return the index of the patient record with the smallest value in their arrival integer, or -1
   *         if the list is empty
   */
  public static int getLongestWaitingPatientIndex(int[][] patientsList, int size) {
    if (size == 0) { // if the array is empty
      return -1;
    }
    if (size == 1) { // if there's only one patient
      return 0;
    }
    int i;
    int minIndex = 0;
    for (i = 0; i < size - 1; i++) {
      for (int j = i + 1; j < size - 1; j++) {
        if (patientsList[i][1] < patientsList[j][1]) {
          minIndex = i;

        }
      }
    }
    if (minIndex != i) {
      minIndex = size - 1;
    }


    return minIndex;
  }

  /**
   * Creates a formatted String summary of the current state of the patientsList array
   * 
   * @param patientsList
   * @param size
   * @return
   */
  public static String getSummary(int[][] patientsList, int size) {
    int totalNumPatients = size;
    int numRed = 0;
    int numYellow = 0;
    int numGreen = 0;
    for (int i = 0; i < size; i++) {
      if (patientsList[i][2] == 1) {
        numRed++;
      } else if (patientsList[i][2] == 2) {
        numYellow++;
      } else if (patientsList[i][2] == 3) {
        numGreen++;
      }
    }
    String summary = "Total number of patients: " + totalNumPatients + "\r\n" + " RED: " + numRed
        + "\r\n" + " YELLOW: " + numYellow + "\r\n" + " GREEN: " + numGreen;
    return summary;

  }

  public static void main(String[] args) {
    int[][] patientsListOnlyYellow = new int[][] {{21801, 2, UrgentCareAdmissions.YELLOW},
        {22002, 4, UrgentCareAdmissions.YELLOW}, {11901, 5, UrgentCareAdmissions.YELLOW}, null,
        null, null, null, null};
    int onlyYellowSize = 3;

    // System.out.println(
    // getAdmissionIndex(UrgentCareAdmissions.GREEN, patientsListOnlyYellow, onlyYellowSize));
    // addPatient(int[] patientRecord, int index, int[][] patientsList, int size);
  
  System.out.println(getSummary(patientsListOnlyYellow,onlyYellowSize));
  }


}

