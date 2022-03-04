/************************************************************************************************
* CLASS: GradebookWriter extends PrintWriter
*
* DESCRIPTION
* The GradebookWriter class attempts to write the current Roster to the input file "gradebook.dat". 
* @throws FileNotFoundException.
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
import java.io.PrintWriter;

public class GradebookWriter extends PrintWriter 
{

  /**
  * Calls the super class ctor that takes a String as the argument, i.e, PrintWriter(String).
  * The PrintWriter ctor opens the file named by pFname for writing. It will throw a
  * FileNotFoundException if the file could not be opened for writing, to be caught in Main.
  * @param pFname The name of the output file to be opened for writing.
  */
  public GradebookWriter(String pFileName) throws FileNotFoundException
	{
		super(pFileName);
	}

  /**
  * Writes the gradebook info to the output file which was opened in the ctor.
  *
  * @param pRoster The roster of students.
  */
  public void writeGradebook(Roster pRoster)
	{
		for (Student e : pRoster.getStudentList())
		{
			println(e.toString());
		}
    close();
	}

  //end GradebookWriter
}