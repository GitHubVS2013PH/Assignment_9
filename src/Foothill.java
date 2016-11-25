import javax.swing.*;

public class Foothill
{
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

      System.out.println("\nArray containing largest odd number of Students");
      System.out.println(StudentArrayUtilities.toString("Before: ", myClass16));
      StudentArrayUtilities.arraySort(myClass16);
      System.out.println(StudentArrayUtilities.toString("After: ", myClass16));
      Student.setSortKey(Student.SORT_BY_FIRST);
      StudentArrayUtilities.arraySort(myClass16);
      System.out.println(StudentArrayUtilities.toString("After: ", myClass16));
      Student.setSortKey(Student.SORT_BY_POINTS);
      StudentArrayUtilities.arraySort(myClass16);
      System.out.println(StudentArrayUtilities.toString("After: ", myClass16));
      Student.setSortKey(Student.SORT_BY_FIRST);
      System.out.print("Median score of even class is: ");
      System.out.println(StudentArrayUtilities.getMedianDestructive(myClass16));
      if (Student.getSortKey() == Student.SORT_BY_FIRST)
         System.out.println("Successfully preserved sortKey");
      else
         System.out.println("FAILED to preserve sortKey");

      System.out.println("\nArray containing largest odd number of Students");
      System.out.println(StudentArrayUtilities.toString("Before: ", myClass15));
      StudentArrayUtilities.arraySort(myClass15);
      System.out.println(StudentArrayUtilities.toString("After: ", myClass15));
      Student.setSortKey(Student.SORT_BY_FIRST);
      StudentArrayUtilities.arraySort(myClass15);
      System.out.println(StudentArrayUtilities.toString("After: ", myClass15));
      Student.setSortKey(Student.SORT_BY_POINTS);
      StudentArrayUtilities.arraySort(myClass15);
      System.out.println(StudentArrayUtilities.toString("After: ", myClass15));
      Student.setSortKey(Student.SORT_BY_FIRST);
      System.out.print("Median score of odd class is: ");
      System.out.println(StudentArrayUtilities.getMedianDestructive(myClass15));
      if (Student.getSortKey() == Student.SORT_BY_FIRST)
         System.out.println("Successfully preserved sortKey");
      else
         System.out.println("FAILED to preserve sortKey");  
      
      System.out.println("\nTesting with array containing 1 Student");
      System.out.print("Median score is: ");
      System.out.println(StudentArrayUtilities.getMedianDestructive(myClass1));

      System.out.println("\nTesting with array containing 0 Students");
      System.out.print("Median score is: ");
      System.out.println(StudentArrayUtilities.getMedianDestructive(myClass0)); 
   }
} // end class Foothill

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

   // accessors
   public String getLastName() { return lastName; }
   public String getFirstName() { return firstName; } 
   public int getTotalPoints() { return totalPoints; }
   public static int getSortKey() { return sortKey; }
    
   // mutators
   public static boolean setSortKey(int key)
   {
      if (!Student.keyOK(key))
         return false;
      sortKey = key;
      return true;
   }
   
   public boolean setLastName(String last)
   {
      if ( !Student.validString(last) )
         return false;
      lastName = last;
      return true;
   }

   public boolean setFirstName(String first)
   {
      if ( !Student.validString(first) )
         return false;
      firstName = first;
      return true;
   }

   public boolean setPoints(int pts)
   {
      if ( !Student.validPoints(pts) )
         return false;
      totalPoints = pts;
      return true;
   }

   // supporting methods
   // could be an instance method and, if so, would take one parameter
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
   public static boolean keyOK(int key)
   {
      if (key == Student.SORT_BY_FIRST 
            || key == Student.SORT_BY_LAST 
            || key == Student.SORT_BY_POINTS)
         return true;
      return false;
   }
   
   private static boolean validString( String testStr )
   {
      if (testStr != null && Character.isLetter(testStr.charAt(0)))
         return true;
      return false;
   }

   private static boolean validPoints( int testPoints )
   {
      if (testPoints >= 0 && testPoints <= Student.MAX_POINTS)
         return true;
      return false;
   }
} // end class Student

class StudentArrayUtilities
{
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
      int midPos = array.length / 2;
      return (array[midPos].getTotalPoints() 
            + array[midPos - 1].getTotalPoints()) / 2.0;
   }
   
   public static String toString(String title, Student[] data)           // ??? NOT OVERRIDE; KEEPING PARAMETER LIST
   {
      String output = title + "\n";

      // build the output string from the individual Students:
      for (int k = 0; k < data.length; k++)
         output += " "+ data[k].toString();

      return output;
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
} // end class StudentArrayUtilities

/************************** RUN ************************************************
*******************************************************************************/