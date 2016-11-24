import javax.swing.*;

public class Foothill
{
   public static void main (String[] args)
   {
      Student[] myClass = { new Student("smith","fred", 95), 
         new Student("bauer","jack",123),
         new Student("jacobs","carrie", 195), 
         new Student("renquist","abe",148),
         new Student("3ackson","trevor", 108), 
         new Student("perry","fred",225),
         new Student("loceff","fred", 44), 
         new Student("stollings","pamela",452),
         new Student("charters","rodney", 295), 
         new Student("cassar","john",321),
      };

      StudentArrayUtilities.printArray("Before: ", myClass);
      StudentArrayUtilities.arraySort(myClass);
      StudentArrayUtilities.printArray("After: ", myClass);
   }
}

class Student
{
   private String lastName;
   private String firstName;
   private int totalPoints;

   public static final String DEFAULT_NAME = "zz-error";
   public static final int DEFAULT_POINTS = 0;
   public static final int MAX_POINTS = 1000;

   // constructor requires parameters - no default supplied
   public Student( String last, String first, int points)
   {
      if ( !setLastName(last) )
         lastName = DEFAULT_NAME;
      if ( !setFirstName(first) )
         firstName = DEFAULT_NAME;
      if ( !setPoints(points) )
         totalPoints = DEFAULT_POINTS;   
   }

   public String getLastName() { return lastName; }
   public String getFirstName() { return firstName; } 
   public int getTotalPoints() { return totalPoints; }

   public boolean setLastName(String last)
   {
      if ( !validString(last) )
         return false;
      lastName = last;
      return true;
   }

   public boolean setFirstName(String first)
   {
      if ( !validString(first) )
         return false;
      firstName = first;
      return true;
   }

   public boolean setPoints(int pts)
   {
      if ( !validPoints(pts) )
         return false;
      totalPoints = pts;
      return true;
   }

   // could be an instance method and, if so, would take one parameter
   public static int compareTwoStudents( Student firstStud, Student secondStud )
   {
      int result;

      // this particular version based on last name only (case insensitive)
      result = firstStud.lastName.compareToIgnoreCase(secondStud.lastName);

      return result;
   }

   public String toString()
   {
      String resultString;

      resultString = " "+ lastName 
         + ", " + firstName
         + " points: " + totalPoints
         + "\n";
      return resultString;
   }

   private static boolean validString( String testStr )
   {
      if (testStr != null && Character.isLetter(testStr.charAt(0)))
         return true;
      return false;
   }

   private static boolean validPoints( int testPoints )
   {
      if (testPoints >= 0 && testPoints <= MAX_POINTS)
         return true;
      return false;
   }
}

class StudentArrayUtilities
{
   // print the array with string as a title for the message box
   // this is somewhat controversial - we may or may not want an I/O
   // methods in this class.  we'll accept it today
   public static void printArray(String title, Student[] data)
   {
      String output = "";

      // build the output string from the individual Students:
      for (int k = 0; k < data.length; k++)
         output += " "+ data[k].toString();

      // now put it in a JOptionPane
      JOptionPane.showMessageDialog( null, output, title, 
         JOptionPane.OK_OPTION);
   }

   // returns true if a modification was made to the array
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

   // public callable arraySort() - assumes Student class has a compareTo()
   public static void arraySort(Student[] array)
   {
      for (int k = 0; k < array.length; k++)
         // compare with method def to see where inner loop stops
         if ( !floatLargestToTop(array, array.length-1-k) )
            return;
   }
}