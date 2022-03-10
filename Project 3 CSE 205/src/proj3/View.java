/************************************************************************************************
* CLASS: View (View.java) implements ActionListener
*
* DESCRIPTION
* View class implements the GUI. It is a subclass of 
* JFrame and implements the ActionListener
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class View extends JFrame implements ActionListener 
{
  //The width of the View frame.
  public static final int FRAME_WIDTH = 525;
  
  //The height of the View frame.
  public static final int FRAME_HEIGHT = 225;

  /**
  * The View() ctor is called from Main.run() to create the View, run() passes a reference
  * to the Main object as the argument to View(). We save that reference into mMain and then
  * later we can use mMain to communicate with the Main class.
  *
  * mMain is made accessible within this class via accessor/mutator methods getMain() and
  * setMain().
  */
  private Main mMain = new Main();

  //GUI related instance variables for the buttons and text fields.
    private JButton mClearButton;
    private JTextField[] mExamText;
    private JButton mExitButton;
    private JTextField[] mHomeworkText;
    private JButton mSaveButton;
    private JButton mSearchButton;
    private JTextField mStudentName;

  /**
  * The View constructor creates the GUI interface and makes the frame visible at the end.
  *
  * @param pMain is an instance of the Main class. 
  */
  public View(Main pMain) 
  {
    //call setMain to save param to instance
    setMain(pMain);

    //new JPanel (flow layout) with StudentName and Search button
    JPanel panelSearch = new JPanel();   
    
    panelSearch.add(new JLabel("Student Name: "));
    
    mStudentName = new JTextField (25);
    panelSearch.add(mStudentName);
    
    mSearchButton = new JButton("Search");
    mSearchButton.addActionListener(this);
    panelSearch.add(mSearchButton);

    // new JPanel (flow layout) for Homework scores
    JPanel panelHomework = new JPanel();
    
    panelHomework.add(new JLabel("Homework: "));

    //accesses NUM_HOMEWORKS from Main to set number of text fields 
    mHomeworkText = new JTextField[Main.getNumHomeworks()];
    for (int i=0; i<Main.getNumHomeworks(); i++)  
    {
      mHomeworkText[i] = new JTextField("", 5);
      panelHomework.add(mHomeworkText[i]);
    } 

    // new JPanel (flow layout) for Exam scores
    JPanel panelExam = new JPanel();
    
    panelExam.add(new JLabel("Exam: "));

    //accesses NUM_EXAMS from Main to set number of text fields 
    mExamText = new JTextField[Main.getNumExams()]; 
    for (int i=0; i<Main.getNumExams(); i++)  
    {
      mExamText[i] = new JTextField("", 5);
      panelExam.add(mExamText[i]);
    }

    //flow layout panel for Clear, Savve, and Exit buttons
    JPanel panelButtons = new JPanel();
        
    mClearButton = new JButton("Clear");
    mClearButton.addActionListener(this);
    panelButtons.add(mClearButton);
        
    mSaveButton = new JButton("Save");
    mSaveButton.addActionListener(this);
    panelButtons.add(mSaveButton);
        
    mExitButton = new JButton("Exit");
    mExitButton.addActionListener(this);
    panelButtons.add(mExitButton);

    //Vertical BoxLayout panel for all above panels    
    JPanel panelMain = new JPanel();
    panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
    panelMain.add(panelSearch);
    panelMain.add(panelHomework);
    panelMain.add(panelExam);
    panelMain.add(panelButtons);

    //title of the View
    setTitle("Grade Book for CSE 205 Students");

    // Set the size of the View 
    setSize(FRAME_WIDTH, FRAME_HEIGHT);

    // Make the View non-resizable
    setResizable(false);

    /** 
    * Set the default close operation to JFrame.DO_NOTHING_ON_CLOSE. 
    * This ensures that Main.exit() will be called so it will write the
    * student records back out to the gradebook database.
    */
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    // Add panelMain to the View.
    add(panelMain);

    // pack() method to cause this window to be sized to fit the 
    // preferred size and layouts of the subcomponents.
    pack();

    // display the View
    setVisible(true);
  }

  /**
  * Ovirredden actionPerformed()
  *
  * Called when one of the JButtons is clicked. Detects which button was clicked and handles it.
  * 
  * If Search button clicked: clears Number fields, makes sure last name is all that is entered, 
  * uses search() from Roster to find the Student, displays the obj in the appropriate text fields 
  * when found by calling displayStudent from this class, dialog "not found" when not found.
  *
  * If Save button clicked: calls saveStudent() from this class.
  * 
  * If Clear button clicked: calls clear() method from this class.
  *     
  * If Exit button clicked: saves current Student if not null, calls exit() from Main.
  */
  @Override
  public void actionPerformed(ActionEvent pEvent)
  {
    if(pEvent.getSource() == mSearchButton)
    {
      clearNumbers();   
      
      String lastName = mStudentName.getText();
      
      if(lastName.equals(""))
      {
        messageBox("Please enter the student's last name.");
      }
      
      else
      {
        Student.setCurrStudent(getMain().search(lastName));  
        
        if (Student.getCurrStudent() == null)
        {
          messageBox("Student not found. Please be sure to enter just the last name. Try again.");
        }
        else
        {
          displayStudent(Student.getCurrStudent());
        }
      }
    }      
    
    else if (pEvent.getSource() == mSaveButton)
    {
      if (Student.getCurrStudent() != null)        
      {
        saveStudent(Student.getCurrStudent());
      }
    }
    
    else if (pEvent.getSource() == mClearButton)
    {
      clear();
    }
    
    else if (pEvent.getSource() == mExitButton)
    {
      if (Student.getCurrStudent() != null)        
      {
        saveStudent(Student.getCurrStudent());
      } 

      getMain().exit();
    }  
  } 
  
  /**
  * Called when the Clear button is clicked. Clears all of the text fields by setting the
  * contents of each to the empty string. Calls clearNumbers()
  *
  * After clear() returns, no student information is being edited or displayed and mStudent
  * has been set to null.
  */
  private void clear()
  {
    mStudentName.setText("");
      
    clearNumbers();
      
    Student.setCurrStudent(null);
  }

  //Clears the homework and exam fields.
  private void clearNumbers()
  {
    for (int i=0; i<mHomeworkText.length; i++) { mHomeworkText[i].setText(""); }
    
    for (int i=0; i<mExamText.length; i++) { mExamText[i].setText(""); }
  }

  /**
  * Displays the homework and exam scores for a student in the mHomeworkText and mExamText text
  * fields using for loops.
  *
  * @param pStudent is the Student who's scores we are going to use to populate the text fields.
  */
  private void displayStudent(Student pStudent)
  {
    mStudentName.setText(pStudent.getFullName());
    
	for (int i=0; i<Main.getNumHomeworks(); i++)
    {
      int hw = pStudent.getHomework(i);
      
      String hwstr = Integer.toString(hw);
      
      mHomeworkText[i].setText(hwstr);
    }
    
    for (int i=0; i<Main.getNumExams(); i++)
    {
      int ex = pStudent.getExam(i);
      
      String exstr = Integer.toString(ex);
      
      mExamText[i].setText(exstr);
    }
  }

  //Accessor method for mMain.
  private Main getMain() { return mMain; }
  
  //Displays a message box containing some text centered in the middle of the View frame. 
  public void messageBox(String pMessage) { JOptionPane.showMessageDialog(this, pMessage); }

  /**
  * Retrieves the homework and exam scores for pStudent from the text fields and writes the
  * results to the Student record in the Roster using for loops.
  * @param pStudent : Student - the Student to be saved
  */
  private void saveStudent(Student pStudent)
  {
    for (int i=0; i<Main.getNumHomeworks(); i++)
    {
      String hwstr = mHomeworkText[i].getText();
      
      int hw = Integer.parseInt(hwstr);
      
      pStudent.setHomework(i, hw);
    }
    
    for (int i=0; i<Main.getNumExams(); i++)
    {
      String exstr = mExamText[i].getText();
      
      int ex = Integer.parseInt(exstr);
      
      pStudent.setExam(i, ex);
    }
  }

  //Mutator method for mMain.
  private void setMain(Main pMain) { mMain = pMain; }

//end View
}