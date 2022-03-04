/************************************************************************************************
* CLASS: Student implements Comparable<Student>
*
* DESCRIPTION
* The Student class stores the gradebook information for one Student. Implements Comparable
* to be used in sorting and searching for Students in Roster.

* COURSE AND PROJECT INFORMATION
* CSE205 Object Oriented Programming and Data Structures, 
* Spring Term A 2022
* Project Number: project-3 
*
* AUTHOR: Gavin Beaudry, gbeaudry, gbeaudry@asu.edu
* AUTHOR: Chavon Kattner, ckattner, ckattner@asu.edu
************************************************************************************************/
package proj3;

import java.util.ArrayList;

public class Student implements Comparable<Student> 
{

  /**
  * mCurrStudent is a reference to the Student object which is currently being displayed and
  * edited in the View. It is accessed via accessor/mutator methods.
  */
  private static Student mCurrStudent;
  

  //mExamList is an ArrayList of Integers storing the student's exam scores.
  private ArrayList<Integer> mExamList;

  //The student's first name.
  private String mFirstName;

  //mHomework List is an ArrayList of Integers storing the student's homework scores.
  private ArrayList<Integer> mHomeworkList;

  //The student's last name.
  private String mLastName;

  /**
  * Ctor takes two strings, first name and last name.
  * calls setFirstName() and setLastName() to update instance variables.
  * creates an empty ArrayList<Integer> to hold exam scores, calls setExamList().
  * creates an empty ArrayList<Integer> to hold homework scores, callse setHomeworkList().
  * calls setCurrStudent to this instance of the Student obj.
  */
  public Student (String pFirstName, String pLastName)
  {
    setFirstName(pFirstName);
    setLastName(pLastName);
      
    ArrayList<Integer> pExamList = new ArrayList<Integer>();
    setExamList(pExamList);
      
    ArrayList<Integer> pHomeworkList = new ArrayList<Integer>();
    setHomeworkList(pHomeworkList);
      
    setCurrStudent(this);
  }
    
  /**
  * method addExam adds an exam score pScore to the exam list
  * by calling getExamList and invoking add().
  * @param pScore : int - the exam score to be added to the exam list
  */
  public void addExam(int pScore)
  {
    getExamList().add(pScore);
  }

  /**
  * method addHomework adds a homework score pScore to the homework list
  * by calling getHomeworkList and invoking add().
  * @param pScore : int - the homework score to be added to the exam list
  */
  public void addHomework(int pScore)
  {
    getHomeworkList().add(pScore);
  }


  /**
  * Overriden compareTo(Student) calls getLastName() to compare last names for search or sort.
  * Returns -1 if 1 comes before 2, 0 if they're equal, 1 if 1 comes after 2.
  * @param pStudent is a Student
  */
  @Override
  public int compareTo(Student pStudent)
  {
    int i = this.getLastName().compareTo(pStudent.getLastName());
    
    if (i < 0) { return -1; }
      
    else if (i == 0) { return 0; }

    else { return 1; }
  }

  //Accessor method for mCurrStudent.
  public static Student getCurrStudent() { return mCurrStudent; }

  /**
  * accessor method to retrieve an exam score from the list of exams.
  * Used to populate the Exam score fields in View obj.
  * @param pNum The exam number for which we wish to retrieve the score.
  * @return The exam score.
  */
  public int getExam(int pNum) { return getExamList().get(pNum); }

  //Accessor method for mExamList.
  private ArrayList<Integer> getExamList() { return mExamList; }

  //Accessor method for mFirstName.
    public String getFirstName() { return mFirstName; }

  //Returns the student's full name in the format: "lastname, firstname".
  public String getFullName() { return getLastName() + ", " + getFirstName(); }

  /**
  * accessor method to retrieve a homework score from the list of homeworks.
  * Used to populate the homework score fields in View obj.
  * @param pNum The homework number for which we wish to retrieve the score.
  * @return The homework score.
  */
  public int getHomework(int pNum) { return getHomeworkList().get(pNum); }

  //Accessor method for mHomeworkList.
  private ArrayList<Integer> getHomeworkList() { return mHomeworkList; }

  //Accessor method for mLastName.
  public String getLastName() { return mLastName; }

  //Mutator method for mCurrStudent.
  public static void setCurrStudent(Student pCurrStudent) { mCurrStudent = pCurrStudent; }

  /**
  * Mutator method to store an exam score into the list of exam scores.
  * @param pNum is the index into the list of exams, where 0 is the index of the first exam score.
  * @parampScore is the score to be set into the ExamList
  */
    public void setExam(int pNum, int pScore) { getExamList().set(pNum, pScore); }

  //Mutator method for mExamList.
  private void setExamList(ArrayList<Integer> pExamList) { mExamList = pExamList; }

  //Mutator method for mFirstName.
  public void setFirstName(String pFirstName) { mFirstName = pFirstName; }

  /**
  * Mutator method to store a homework score into the list of homework scores.
  * @param pNum is the index into the list of homeworks, where 0 is the index of the first homework score.
  * @parampScore is the score to be set into the HomeworkList
  */
  public void setHomework(int pNum, int pScore) { getHomeworkList().set(pNum, pScore); }

  //Mutator method for mHomeworkList.
  private void setHomeworkList(ArrayList<Integer> pHomeworkList) 
  { mHomeworkList = pHomeworkList; }

  //Mutator method for mLastName.
  public void setLastName(String pLastName) { mLastName = pLastName; }

    /**
     * Overridden toString()
     *
     * Returns a String representation of this Student. The format of the returned string is
     * such that the Student information can be printed to the output file in this format:
     *
     *     lastname firstname exam1 exam2 exam2 hw1 hw2 hw3 hw4 hw5
     *
     * where the fields are separated by spaces, except no space following hw5.
     */
  @Override
  public String toString()
  {
    String info = getLastName() + " " + getFirstName() + " ";
    
    for (Integer a : getExamList())
    {
      info = info + a.toString() + " ";
    }

    for (Integer a : getHomeworkList())
    {
      info = info + a.toString() + " ";
    }
      
    return info.trim();
  }

  //end Student
}