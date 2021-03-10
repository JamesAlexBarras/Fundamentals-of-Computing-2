import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 *
 * @author  James Barras (jab0217@auburn.edu)
 *
 */
public class BinarySearch {

   /**
    * Returns the index of the first key in a[] that equals the search key, 
    * or -1 if no such key exists. This method throws a NullPointerException
    * if any parameter is null.
    */
   public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) { 
      if ((a == null) || (key == null) || (comparator == null)) {
         throw new NullPointerException();
      }
   
      int left = 0;
      int right = a.length - 1;
      int currentI = -1;
      while (right >= left) {
         int middle = (left + right) / 2;
         Key currentK = a[middle];
         if ((comparator.compare(key, currentK) == -1) || (comparator.compare(key, currentK) == 0)) {
            if (comparator.compare(key, currentK) == 0) {
               currentI = middle;
            }
            right = middle - 1;
         } 
         else if (comparator.compare(key, currentK) == 1) {
            left = middle + 1;
         }
      }
   
      return currentI;
   }

   /**
    * Returns the index of the last key in a[] that equals the search key, 
    * or -1 if no such key exists. This method throws a NullPointerException
    * if any parameter is null.
    */
   public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) { 
      if ((a == null) || (key == null) || (comparator == null)) {
         throw new NullPointerException();
      }
    
      int left = 0;
      int right = a.length - 1;
      int currentI = -1;
      while (left <= right) {
         int middle = (left + right) / 2;
         Key currentK = a[middle];
         if (comparator.compare(key, currentK) == -1) {
            right = middle - 1;
         } 
         else if ((comparator.compare(key, currentK) == 1) || (comparator.compare(key, currentK) == 0)) {
            if (comparator.compare(key, currentK) == 0) {
               currentI = middle;
            }
            left = middle + 1;
         }
      }
   
      return currentI;
   }
}