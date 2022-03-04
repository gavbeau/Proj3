/************************************************************************************************
* CLASS: Roster
*
* DESCRIPTION
* The Roster class encapsulates an ArrayList<Student> object named mStudentList which stores the
* information for each student that was read from "gradebook.txt" and is updated in View.
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

import java.util.ArrayList;

public class Roster 
{

  //encapsulated ArrayList<Student>
  private ArrayList<Student> mStudentList;

  /**
  * Ctor creates an ArrayList<Student> object and then passes that object as the argument to
  * setStudentList() to make mStudentList refer to the ArrayList.
  */
  public Roster()
	{
    ArrayList<Student> pStudentList = new ArrayList<>();
	  setStudentList(pStudentList);
  }

  /**
  * Adds a Student to mStudentList.
  * @param pStudent - a Student object to be added to the ArrayList 
  */
  public void addStudent(Student pStudent) { mStudentList.add(pStudent); }

  /**
  * Creates a Searcher (inner class) object to search mStudentList for a Student with pLastName.
  * Returns the index of the Student in mStudentList or null if not found.
  */
  public Student getStudent(String pLastName)
  {
  	Searcher searcher = new Searcher();
    int index = searcher.search(getStudentList(), pLastName, 0, getStudentList().size() -1);
    	
    if (index == -1) {return null;}

    else {return mStudentList.get(index);}
  }

  /**
  * Inner class Searcher, utilizes binary search to find the desired Student. Calls methods:
  * getLastName() and overrdiden compareTo() from Student class.
  * @param pList : ArrayList<Student> - the list of Students to search.
  * @param pKey : String - the last name of the Student to be found.
  * @param pLow: Int - the low index used in the search method.
  * @param pHigh: Int - the high index used in the search method.
  * @return int: the index of the Student or -1 if not found.
  */
  class Searcher
  {
  	public int search(ArrayList<Student> pList, String pKey, int pLow, int pHigh)
    {
      int middle = (pLow + pHigh)/2;
      
      if(pLow>pHigh) { return -1; }
      
      if ((pList.get(middle).getLastName()).equals(pKey)) { return middle; }
      
      else if (((pList.get(middle).getLastName()).compareTo(pKey))<0)
      {
        return search(pList, pKey, middle+1, pHigh);
      }
      
      else
      {
        return search(pList, pKey, pLow, middle-1);
      }
    }
    //end Searcher
  }


  /**
  * Accessor method for mStudentList.
  * Creates an accessor ArrayList so user doesn't have access to instance ArrayList.
  * @return gStudentList : ArrayList<Student>
  */
  public ArrayList<Student> getStudentList() 
  {
    ArrayList<Student> gStudentList = new ArrayList<>();
        
    for (Student i : mStudentList)
      {
        gStudentList.add(i);
      }
        
    return gStudentList;
  }

  /** 
  * Mutator method for mStudentList.
  * @param pStudnetList : ArrayList<Student> - to set mStudentList
  */
  private void setStudentList(ArrayList<Student> pStudentList) 
    { mStudentList = pStudentList; }


  //method to sort the roster by last name. Uses inner static class Sorter
  public void sortRoster() { Sorter.sort(mStudentList); }

  /**
  * Inner static class Sorter
  * Uses Quick Sort method to put ArrayList in ascending order by last name.
  * methods: + sort(), - quickSort(), - partition(), and - swap()
  */
  static class Sorter
  {
    
    /**
    * public static method sort takes an ArrayList<Student>, sets sorting indices, calls quickSort()
    * @param pList : ArrayList<Student> - the list to be sorted
    */
    public static void sort(ArrayList<Student> pList)
    	{
    		int pFromIdx = 0;
    		int pToIdx = pList.size()-1;
    		quickSort(pList, pFromIdx, pToIdx);
    	}

    /**
    * private static method quickSort takes an ArrayList<Student>, and ints from and to index,
    * either returns to sort or calls partition() to divide the ArrayList further 
    * and callse itself again on new partitions.
    * @param pList : ArrayList<Student> - the list to be sorted
    * @param pFromIdx : int - the low index
    * @param pToIdx : int - the high index
    */
    private static void quickSort(ArrayList<Student> pList, int pFromIdx, int pToIdx)
    {
      if (pFromIdx >= pToIdx) {return;}
    		
      int p = partition(pList, pFromIdx, pToIdx);
    	
      quickSort(pList, pFromIdx, p);
    	quickSort(pList, p+1, pToIdx);
    }
      
    /**
    * private static method partition takes an ArrayList<Student> and ints from and to index,
    * sets a pivot and finds out of order indices by incrementing left index 
    * and decrementing right index and comparing to pivot. 
    * Calls swap() as needed. Returns the position of the right index.
    * @param pList : ArrayList<Student> - the list to be partitioned
    * @param pFromIdx : int - the left index
    * @param pToIdx : int - the right index
    * @return int - the position of the right index
    */
    private static int partition(ArrayList<Student> pList, int pFromIdx, int pToIdx)
    	{
    		Student pivot = pList.get(pFromIdx);
    		
        int l = pFromIdx - 1;
    		int r = pToIdx + 1;
    		
        while (l < r)
    		{
    			l++; while (pList.get(l).compareTo(pivot) < 0) {l++;}
    			r--; while (pList.get(r).compareTo(pivot) > 0) {r--;}
    			if (l < r) {swap(pList, l, r);}
    		}
    		
        return r;
    	}

    /**
    * private static method swap takes an ArrayList<Student> and ints of the two indices to be swapped.
    * Sets one index to temp, sets second to index of first, set temp to index of second.
    * @param pList : ArrayList<Student> the list that is being sorted
    * @param pIdx1 : int - the index of the first element to be swapped
    * @param pIdx2 : int - the index of the second element to be swapped
    */
    private static void swap(ArrayList<Student> pList, int pIdx1, int pIdx2)
    {
    	Student temp = pList.get(pIdx1);
    	pList.set(pIdx1, pList.get(pIdx2));
    	pList.set(pIdx2, temp);
    }

  //end Sorter
  }

  /**
  * Overridden toString().
  * Returns a String representation of this Roster,
  */
  @Override
  public String toString() 
  {
    String result = "";
    
    for (Student student : getStudentList()) { result += student + "\n"; }
    
    return result;
  }

//end Roster
}