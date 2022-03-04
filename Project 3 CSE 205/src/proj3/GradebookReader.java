/************************************************************************************************
* CLASS: GradebookReader
*
* DESCRIPTION
* The GradebookReader class attempts to open the input file "gradebook.dat", 
* read the various aspects, create Student objects from the data, 
* and add the students to a Roster object.
*
* COURSE AND PROJECT INFORMATION
* CSE205 Object Oriented Programming and Data Structures, 
* Spring Term A 2022
* Project Number: project-3 
*
* AUTHOR: Gavin Beaudry, gbeaudry, gbeaudry@asu.edu
* AUTHOR: Chavon Kattner, ckattner, ckattner@asu.edu
*************************************************************************************************/
package proj3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * GradebookReader() reads the gradebook info from the file whose name is passed to the ctor.
 * Once the input file has been read, it will return a Roster object containing the list of
 * Students in the course.
 */
public class GradebookReader  
{

  // Scanner object mIn reads from the input file.
  private Scanner mIn;

  /**
  * Ctor attempts to open the gradebook file for reading. If successful, mIn will be used to read
  * from the file. If the file cannot be opened, a FileNotFoundException will be thrown.
  * @throws FileNotFoundException
  * @param pFname The name of the file to be opened for reading.
  */
  public GradebookReader(String pFname) throws FileNotFoundException 
  {
    mIn = new Scanner(new File(pFname));
  }

  /**
  * Reads the exam scores for a Student.
  * The number of exams is retrieved by calling the static getNumExams() method in Main.
  * @param pStudent - The student for whom we are going to read the exam scores from the 
  * input file.
  */
  private void readExam(Student pStudent) 
  {
    for (int n = 0; n < Main.getNumExams(); ++n) 
    { pStudent.addExam(mIn.nextInt()); }
  }

  /**
  * Called to read the gradebook information. Calls methods: 
  * readRoster() from the this class to read the student records and 
  * sortRoster() from the Roster class to sort the roster by last name.
  *
  * Called from Main.run().
  *
  * @return The roster of students that was read from the input file.
  */
  public Roster readGradebook() 
  {
    Roster roster = readRoster();
    roster.sortRoster();
    return roster;
  }

  /**
  * Reads the homework scores for a Student.
  *
  * The number of homework assignments is retrieved by calling the static getNumHomeworks()
  * method in Main.
  *
  * @param pStudent - The student for whom we are going to read the homework scores from the input
  * file.
  */
  private void readHomework(Student pStudent) 
  {
    for (int n = 0; n < Main.getNumHomeworks(); ++n) 
    {
      pStudent.addHomework(mIn.nextInt());
    }
  }

  /**
  * Reads the student information for each student in the input file, adding each student to
  * the roster.
  *
  * Called from readGradebook().
  *
  * @return The roster of students that was read from the input file.
  */
  private Roster readRoster() 
  {
    
    Roster roster = new Roster();
    
    while (mIn.hasNext()) 
    {
      String lastName = mIn.next();
      String firstName = mIn.next();
      
      Student student = new Student(firstName, lastName);
            
      readExam(student);
      readHomework(student);
      
      roster.addStudent(student);
    }
    
    return roster;
  }

  //end GradebookReader
}
