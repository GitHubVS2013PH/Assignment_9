//
// Paul Hayter, Assignment 9
//
// CS1A, Anand Venkataraman, Fall 2016
//

/**
 * Contains class Student and class StudentArrayUtilities with Foothill class
 * for testing these.
 * @author Paul Hayter
 */
public class Foothill
{
   /**
    * Tests class Student and class StudentArrayUtilities.
    * @param args not used
    */
   public static void main (String[] args)
   {
      Student[] myClass16 = {
         new Student("smith","fred", 95),
         new Student("bauer","jack",123),
         new Student("jacobs","carrie", 195), 
         new Student("renquist","abe",148),
         new Student("3ackson","trevor", 108), 
         new Student("perry","fred",225),
         new Student("loceff","fred", 44), 
         new Student("stollings","pamela",452),
         new Student("charters","rodney", 295), 
         new Student("le pen","marine",45),
         new Student("may","theresa",404),
         new Student("churchill","winston",498),
         new Student("putin","vladimir",299),
         new Student("erdgogan","recep",305),
         new Student("francis","pope",303),
         new Student("franco","francisco",43)
      };
      
      Student[] myClass15 = {
         new Student("Man", "Spider", 180),
         new Student("smith","fred", 95), 
         new Student("bauer","jack",123),
         new Student("jacobs","carrie", 195), 
         new Student("renquist","abe",148),
         new Student("3ackson","trevor", 108), 
         new Student("perry","fred",225),
         new Student("loceff","fred", 44), 
         new Student("stollings","pamela",452),
         new Student("charters","rodney", 295), 
         new Student("cassar","john",321),
         new Student("le pen","marine",45),
         new Student("may","theresa",404),
         new Student("putin","vladimir",299),
         new Student("erdgogan","recep",305)
      };
      
      Student[] myClass1 = { new Student("America", "Captain", 199) };
      
      Student[] myClass0 = { };

      // Test with array with largest even number of Students
      System.out.println("Array containing largest even number of Students");
      System.out.println(StudentArrayUtilities.toString("Before: ", myClass16));
      StudentArrayUtilities.arraySort(myClass16);
      System.out.println(StudentArrayUtilities.toString(
            "After sort by default (last name):", myClass16));
      
      Student.setSortKey(Student.SORT_BY_FIRST);
      StudentArrayUtilities.arraySort(myClass16);
      System.out.println(StudentArrayUtilities.toString(
            "After sort by first name:", myClass16));
      
      Student.setSortKey(Student.SORT_BY_POINTS);
      StudentArrayUtilities.arraySort(myClass16);
      System.out.println(StudentArrayUtilities.toString(
            "After sort by points:", myClass16));
      
      Student.setSortKey(Student.SORT_BY_FIRST);
      System.out.print("Median score of largest even class is: ");
      System.out.println(StudentArrayUtilities.getMedianDestructive(myClass16));
      
      if (Student.getSortKey() == Student.SORT_BY_FIRST)
         System.out.println("Successfully preserved sortKey");
      else
         System.out.println("FAILED to preserve sortKey");

      // Test with array with largest odd number of Students
      Student.setSortKey(Student.SORT_BY_LAST); // restore default 
      System.out.println("\nArray containing largest odd number of Students");
      System.out.println(StudentArrayUtilities.toString("Before: ", myClass15));
      StudentArrayUtilities.arraySort(myClass15);
      System.out.println(StudentArrayUtilities.toString(
            "After sort by last name (default): ", myClass15));
      
      Student.setSortKey(Student.SORT_BY_FIRST);
      StudentArrayUtilities.arraySort(myClass15);
      System.out.println(StudentArrayUtilities.toString(
            "After sort by first name: ", myClass15));
      
      Student.setSortKey(Student.SORT_BY_POINTS);
      StudentArrayUtilities.arraySort(myClass15);
      System.out.println(StudentArrayUtilities.toString(
            "After sort by points: ", myClass15));
      
      Student.setSortKey(Student.SORT_BY_FIRST);
      System.out.print("Median score of odd class is: ");
      System.out.println(StudentArrayUtilities.getMedianDestructive(myClass15));
      
      if (Student.getSortKey() == Student.SORT_BY_FIRST)
         System.out.println("Successfully preserved sortKey");
      else
         System.out.println("FAILED to preserve sortKey");  
      
      // test Student array of size 1
      System.out.println("\nTesting with array containing 1 Student"
            + " (expect " + (double) myClass1[0].getTotalPoints() + ")");
      System.out.print("Median score is: ");
      System.out.println(StudentArrayUtilities.getMedianDestructive(myClass1));

      // test Student array of size 0
      System.out.println("\nTesting with array containing 0 Students"
            + " (expect 0.0)");
      System.out.print("Median score is: ");
      System.out.println(StudentArrayUtilities.getMedianDestructive(myClass0)); 
   }
} // end class Foothill

/**
 * One instance of this class manages a student name (first and last) along with
 * their grade points. In addition to mutator and accessor methods, there are
 * methods for comparing students by first name, last name and grade points, a
 * a toString() method and a method for selecting comparison criterion.
 * @author Paul Hayter (modified from code supplied in module 8)
 */
class Student
{
   public static final String DEFAULT_NAME = "zz-error";
   public static final int DEFAULT_POINTS = 0;
   public static final int MAX_POINTS = 1000;
   public static final int SORT_BY_FIRST = 88;
   public static final int SORT_BY_LAST = 98;
   public static final int SORT_BY_POINTS = 108;

   private String lastName;
   private String firstName;
   private int totalPoints;
   private static int sortKey = SORT_BY_LAST;

   /**
    * Parameterized constructor.
    * @param last String containing student last name.
    * @param first String containing student first name.
    * @param points int containing student grade points.
    */
   public Student( String last, String first, int points)
   {
      if ( !setLastName(last) )
         lastName = Student.DEFAULT_NAME;
      if ( !setFirstName(first) )
         firstName = Student.DEFAULT_NAME;
      if ( !setPoints(points) )
         totalPoints = Student.DEFAULT_POINTS;   
   }

   // accessors
   public String getLastName() { return lastName; }
   public String getFirstName() { return firstName; } 
   public int getTotalPoints() { return totalPoints; }
   public static int getSortKey() { return sortKey; }
    
   // mutators
   /**
    * Returns true after setting sortKey with an acceptable value; otherwise
    * returns false if key is unacceptable. Acceptable values are SORT_BY_FIRST,
    * SORT_BY_LAST and SORT_BY_POINTS.
    * @param key int containing key to be used for compareTwoStudents() method.
    * @return specified boolean.
    */
   public static boolean setSortKey(int key)
   {
      if (!Student.keyOK(key))
         return false;
      sortKey = key;
      return true;
   }
   
   /**
    * Returns true after setting lastName with an acceptable value; otherwise
    * returns false if input last String is unacceptable.
    * @param last String containing student last name.
    * @return specified boolean.
    */
   public boolean setLastName(String last)
   {
      if ( !Student.validString(last) )
         return false;
      lastName = last;
      return true;
   }

   /**
    * Returns true after setting firstName with an acceptable value; otherwise
    * returns false if input first String is unacceptable.
    * @param first String containing student first name.
    * @return specified boolean.
    */
   public boolean setFirstName(String first)
   {
      if ( !Student.validString(first) )
         return false;
      firstName = first;
      return true;
   }

   /**
    * Returns true after setting totalPoints with an acceptable value; otherwise
    * returns false if input int pts is unacceptable.
    * @param pts int containing student grade points.
    * @return specified boolean.
    */
   public boolean setPoints(int pts)
   {
      if ( !Student.validPoints(pts) )
         return false;
      totalPoints = pts;
      return true;
   }

   // supporting methods
   /**
    * Compares two students and returns 0 if they are the same, a negative int
    * if firstStud is less than secondStud or a positive int if firstStud is
    * greater than the secondStud. Method uses member field Student.sortKey to 
    * determine which member field to use for comparison.
    * @param firstStud Student object for comparison.
    * @param secondStud Student object for comparison.
    * @return specified int.
    */
   public static int compareTwoStudents( Student firstStud, Student secondStud )
   {
      int result = 0;

      switch (Student.sortKey)
      {
         case Student.SORT_BY_FIRST:
            result = firstStud.firstName.compareToIgnoreCase(
                  secondStud.firstName);
            break;
         case Student.SORT_BY_LAST:
            result = firstStud.lastName.compareToIgnoreCase(
                  secondStud.lastName);
            break;
         case Student.SORT_BY_POINTS:
            result = firstStud.totalPoints - secondStud.totalPoints;
            break;
      }
      return result;
   }

   @Override
   /**
    * Returns String with formatted member fields.
    * @return specified String
    */
   public String toString()
   {
      String resultString;

      resultString = " "+ lastName 
         + ", " + firstName
         + " points: " + totalPoints
         + "\n";
      return resultString;
   }
   
   // validators
   /**
    * Returns true if int key is an acceptable value; otherwise returns false;
    * Acceptable values are SORT_BY_FIRST, SORT_BY_LAST and SORT_BY_POINTS.
    * @param key int containing key value to be checked.
    * @return specified boolean.
    */
   public static boolean keyOK(int key)
   {
      if (key == Student.SORT_BY_FIRST 
            || key == Student.SORT_BY_LAST 
            || key == Student.SORT_BY_POINTS)
         return true;
      return false;
   }
   
   /**
    * Returns true if String testStr is not null and first character is a letter
    *  otherwise returns false.
    * @param testStr String of student name (first or last) to be checked.
    * @return specified boolean.
    */
   private static boolean validString( String testStr )
   {
      if (testStr != null && Character.isLetter(testStr.charAt(0)))
         return true;
      return false;
   }

   /**
    * Returns true if testPoints are within the valid range of 0 and MAX_POINTS,
    * inclusive; otherwise returns false.
    * @param testPoints int of student points to be checked for being in range.
    * @return specified boolean.
    */
   private static boolean validPoints( int testPoints )
   {
      if (testPoints >= 0 && testPoints <= Student.MAX_POINTS)
         return true;
      return false;
   }
} // end class Student

/**
 * Class contains Student class utilities as static methods. The public methods
 * include getMedianDestructive(), toString(), and arraySort().
 * @author Paul Hayter (modified from code supplied in module 8)
 */
class StudentArrayUtilities
{
   /**
    * Returns double containing median of points in the Student[] array. Input
    * Student array ordering is destroyed; client sortKey is unaffected.
    * @param array Student object array for calculation of median points.
    * @return specified double.
    */
   public static double getMedianDestructive(Student[] array)
   {
      if (array == null || array.length == 0)
         return 0.0;
      if (array.length == 1)
         return (double) array[0].getTotalPoints();
      
      int clientSortKey = Student.getSortKey();
      Student.setSortKey(Student.SORT_BY_POINTS);
      arraySort(array);
      Student.setSortKey(clientSortKey);
      
      if (array.length % 2 == 1)
         return (double) array[array.length / 2].getTotalPoints();
      // else array contains even number of elements
      int midPosition = array.length / 2;
      return (array[midPosition].getTotalPoints() 
            + array[midPosition - 1].getTotalPoints()) / 2.0;
   }
   
   /**
    * Returns formatted String with title containing all Student array data.
    * Note: does not override Java toString() default.
    * @param title String title of formatted output String. If null, then no
    * title is given.
    * @param data array of Student objects for the formatted output.
    * @return specified String.
    */
   public static String toString(String title, Student[] data)
   {
      String output = "";
      if (title != null)
         output = title + "\n";

      // build the output string from the individual Students:
      for (int k = 0; k < data.length; k++)
         output += " "+ data[k].toString();

      return output;
   }

   /**
    * Moves largest value of Student array to index specified by top (except for
    * those Student values already in positions between top+1 and data.length-1,
    * inclusive). Sets boolean flag if a change to array is made.
    * @param data array of Student objects.
    * @param top index of top-most place in array to "float" largest Student to.
    * @return true if a modification was made to the Student array; otherwise
    * returns false.
    */
   private static boolean floatLargestToTop(Student[] data, int top)
   {
      boolean changed = false;
      Student temp;

      // compare with client call to see where the loop stops
      for (int k = 0; k < top; k++)
         if ( Student.compareTwoStudents(data[k], data[k+1]) > 0 )
         {
            temp = data[k];
            data[k] = data[k+1];
            data[k+1] = temp;
            changed = true;
         }
      return changed;
   }

   /**
    * Sorts Student array; assumes Student class has compareTwoStudents().
    * @param array Student objects array to be sorted.
    */
   public static void arraySort(Student[] array)
   {
      for (int k = 0; k < array.length; k++)
         // compare with method def to see where inner loop stops
         if ( !floatLargestToTop(array, array.length-1-k) )
            return;
   }
} // end class StudentArrayUtilities

/************************** RUN ************************************************
Array containing largest even number of Students
Before: 
  smith, fred points: 95
  bauer, jack points: 123
  jacobs, carrie points: 195
  renquist, abe points: 148
  zz-error, trevor points: 108
  perry, fred points: 225
  loceff, fred points: 44
  stollings, pamela points: 452
  charters, rodney points: 295
  le pen, marine points: 45
  may, theresa points: 404
  churchill, winston points: 498
  putin, vladimir points: 299
  erdgogan, recep points: 305
  francis, pope points: 303
  franco, francisco points: 43

After sort by default (last name):
  bauer, jack points: 123
  charters, rodney points: 295
  churchill, winston points: 498
  erdgogan, recep points: 305
  francis, pope points: 303
  franco, francisco points: 43
  jacobs, carrie points: 195
  le pen, marine points: 45
  loceff, fred points: 44
  may, theresa points: 404
  perry, fred points: 225
  putin, vladimir points: 299
  renquist, abe points: 148
  smith, fred points: 95
  stollings, pamela points: 452
  zz-error, trevor points: 108

After sort by first name:
  renquist, abe points: 148
  jacobs, carrie points: 195
  franco, francisco points: 43
  loceff, fred points: 44
  perry, fred points: 225
  smith, fred points: 95
  bauer, jack points: 123
  le pen, marine points: 45
  stollings, pamela points: 452
  francis, pope points: 303
  erdgogan, recep points: 305
  charters, rodney points: 295
  may, theresa points: 404
  zz-error, trevor points: 108
  putin, vladimir points: 299
  churchill, winston points: 498

After sort by points:
  franco, francisco points: 43
  loceff, fred points: 44
  le pen, marine points: 45
  smith, fred points: 95
  zz-error, trevor points: 108
  bauer, jack points: 123
  renquist, abe points: 148
  jacobs, carrie points: 195
  perry, fred points: 225
  charters, rodney points: 295
  putin, vladimir points: 299
  francis, pope points: 303
  erdgogan, recep points: 305
  may, theresa points: 404
  stollings, pamela points: 452
  churchill, winston points: 498

Median score of largest even class is: 210.0
Successfully preserved sortKey

Array containing largest odd number of Students
Before: 
  Man, Spider points: 180
  smith, fred points: 95
  bauer, jack points: 123
  jacobs, carrie points: 195
  renquist, abe points: 148
  zz-error, trevor points: 108
  perry, fred points: 225
  loceff, fred points: 44
  stollings, pamela points: 452
  charters, rodney points: 295
  cassar, john points: 321
  le pen, marine points: 45
  may, theresa points: 404
  putin, vladimir points: 299
  erdgogan, recep points: 305

After sort by last name (default): 
  bauer, jack points: 123
  cassar, john points: 321
  charters, rodney points: 295
  erdgogan, recep points: 305
  jacobs, carrie points: 195
  le pen, marine points: 45
  loceff, fred points: 44
  Man, Spider points: 180
  may, theresa points: 404
  perry, fred points: 225
  putin, vladimir points: 299
  renquist, abe points: 148
  smith, fred points: 95
  stollings, pamela points: 452
  zz-error, trevor points: 108

After sort by first name: 
  renquist, abe points: 148
  jacobs, carrie points: 195
  loceff, fred points: 44
  perry, fred points: 225
  smith, fred points: 95
  bauer, jack points: 123
  cassar, john points: 321
  le pen, marine points: 45
  stollings, pamela points: 452
  erdgogan, recep points: 305
  charters, rodney points: 295
  Man, Spider points: 180
  may, theresa points: 404
  zz-error, trevor points: 108
  putin, vladimir points: 299

After sort by points: 
  loceff, fred points: 44
  le pen, marine points: 45
  smith, fred points: 95
  zz-error, trevor points: 108
  bauer, jack points: 123
  renquist, abe points: 148
  Man, Spider points: 180
  jacobs, carrie points: 195
  perry, fred points: 225
  charters, rodney points: 295
  putin, vladimir points: 299
  erdgogan, recep points: 305
  cassar, john points: 321
  may, theresa points: 404
  stollings, pamela points: 452

Median score of odd class is: 195.0
Successfully preserved sortKey

Testing with array containing 1 Student (expect 199.0)
Median score is: 199.0

Testing with array containing 0 Students (expect 0.0)
Median score is: 0.0
*******************************************************************************/