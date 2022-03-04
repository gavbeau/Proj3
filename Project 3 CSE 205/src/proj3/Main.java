/************************************************************************************************
* CLASS: Main (Main.java)
*
* DESCRIPTION
* This program is an editable gradebook displaying exams and hw grades from a roster of students.
* It displays data in a GUI and allows the user to search, edit, and update the roster.
*
* COURSE AND PROJECT INFORMATION
* CSE205 Object Oriented Programming and Data Structures, 
* Spring Term A 2022
* Project Number: project-3 
*
* AUTHOR: Gavin Beaudry, gbeaudry, gbeaudry@asu.edu
* AUTHOR: Chavon Kattner, ckattner, ckattner@asu.edu
************************************************************************************************/
package proj3;

import java.io.FileNotFoundException;
import javax.swing.JFrame;


//The Main class containing the main() and run() methods.
public class Main 
{

  //the number of homework assignments and exams.
  private static final int NUM_HOMEWORKS = 5;
  private static final int NUM_EXAMS = 3;
    
  //The Roster of students that is read from the input file "gradebook.dat".
  private Roster mRoster;

  //A reference to the View (GUI) object.
  private View mView;

  //This is where execution starts.
  public static void main (String[] pArgs)
  {
    new Main().run();
  }

  /**
  * exit() is called when the Exit button in the View is clicked. 
  * Tries to write the updated roster to "gradebook.dat", catches FileNotFoundException.
  */
  public void exit()
  {
    try
    {
      GradebookWriter gbWriter = new GradebookWriter("gradebook.dat");
      gbWriter.writeGradebook(getRoster());
      System.exit(0);
      gbWriter.close();
    }
    catch (FileNotFoundException e)
    {
        getView().messageBox("Could not open gradebook.dat for writing. Exiting without saving.");
        System.exit(-1);
    }
  }

  //returns the number of exams in the class.
  public static int getNumExams() { return NUM_EXAMS; }

  //returns the number of homework assignments in the class.
  public static int getNumHomeworks() { return NUM_HOMEWORKS; }

  //Accessor method for mRoster.
  private Roster getRoster() { return mRoster; }

  //Accessor method for mView.
  private View getView() { return mView; }

  /**
  * run() is the main routine called from main().
  * Opens View object.
  * Tries to read "gradebook.dat" using GradeBookReader object. Catches FileNotFoundException.
  */
  private void run()
  {
    JFrame.setDefaultLookAndFeelDecorated(true);
    setView(new View(this));       
    try
    {
      GradebookReader gbReader = new GradebookReader("gradebook.dat");
        
      setRoster(gbReader.readGradebook());
    } 
    catch(FileNotFoundException e)
    {
      getView().messageBox("Could not open gradebook.dat for reading. Exiting.");
      System.exit(-1);
    }
  }

  /**
  * search() is called when the Search button is clicked in the View. the program searches the 
  * Student objects in the Roster object, attempting to find a student with the matching last name. 
  * @param String pLastName - the last name of the student.
  * @return Student - the Student object matching the input last name.
  */
  public Student search(String pLastName)
  {
    return getRoster().getStudent(pLastName);
  }

  //Mutator method for mRoster.
    private void setRoster(Roster pRoster) { mRoster = pRoster; }

  //Mutator method for mView.
  private void setView(View pView) { mView = pView; }

  //end main.
}
