import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  YOUR NAME HERE (you@auburn.edu)
 *
 */
public final class Selector {

/**
* Can't instantiate this class.
*
* D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
*
*/
   private Selector() { }


   /**
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty() == true) {
         throw new NoSuchElementException();
      }
      
      
      
      Iterator<T> itr = coll.iterator();
      
      if (coll.size() == 1) {
         T answer = itr.next();
         return answer;
      }
      
      T min = itr.next();
      while (itr.hasNext()) { 
         T compareV = itr.next();
         int result = comp.compare(min, compareV);
         if (result == 1) {
            min = compareV;
         } 
      }
      
      return min;
      
      
      
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty() == true) {
         throw new NoSuchElementException();
      }
      
      
      
      Iterator<T> itr = coll.iterator();
      
      if (coll.size() == 1) {
         T answer = itr.next();
         return answer;
      }
      
      T max = itr.next();
      while (itr.hasNext()) { 
         T compareV = itr.next();
         int result = comp.compare(max, compareV);
         if (result == -1) {
            max = compareV;
         } 
      }
      
      return max;
      
      
      
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty() == true) {
         throw new NoSuchElementException();
      }
      
      ArrayList<T> collection = new ArrayList<T>();
      Iterator<T> itr1 = coll.iterator();
      
      while (itr1.hasNext()) {
         collection.add(itr1.next());
      }
      
      java.util.Collections.sort(collection, comp);
      
      Iterator<T> itr2 = collection.iterator();
      T current = itr2.next();
      int dvalues = 1;
      while (itr2.hasNext()) {
         T comparison = itr2.next();
         if (comparison != current) {
            dvalues++;
            current = comparison;
         }
      }
      
      if ((dvalues < k) || (k < 1) || (k > collection.size())) {
         throw new NoSuchElementException();
      }
      
      
      Iterator<T> itr3 = collection.iterator();
      T kmin = itr3.next();
      int counter = 1;
      while (itr3.hasNext()) {
         if (k == counter) {
            break;
         }
         T compareT = itr3.next();
         if (compareT != kmin) {
            counter++;
            kmin = compareT;
         
         }
      
      
      }
      
      return kmin;
      
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty() == true) {
         throw new NoSuchElementException();
      }
      
      ArrayList<T> collection = new ArrayList<T>();
      Iterator<T> itr1 = coll.iterator();
      
      while (itr1.hasNext()) {
         collection.add(itr1.next());
      }
      
      java.util.Collections.sort(collection, comp);
      
      Iterator<T> itr2 = collection.iterator();
      T current = itr2.next();
      int dvalues = 1;
      while (itr2.hasNext()) {
         T comparison = itr2.next();
         if (comparison != current) {
            dvalues++;
            current = comparison;
         }
      }
      
      if ((k > dvalues) || (k < 1) || (k > collection.size())) {
         throw new NoSuchElementException();
      }    
      
      int target = (dvalues - k);
      Iterator<T> itr3 = collection.iterator();
      int counter = 0;
      T kmax = itr3.next();
      while (itr3.hasNext()) {
         if (counter == target) {
            break;
         }
         T compareT = itr3.next();
         if (compareT != kmax) {
            counter++;
            kmax = compareT;
         
         }
      
      
      }
      
      return kmax;
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                                     Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty() == true) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> checker = coll.iterator();
      int qcounter = 0;
      while (checker.hasNext()) {
         T potentialV = checker.next();
         int comph = comp.compare(potentialV, high);
         int compl = comp.compare(potentialV, low);
         
         if (((comph == -1) || (comph == 0)) && ((compl == 1) || (compl == 0))) {
            qcounter++;
         }
      }
      
      if (qcounter == 0) {
         throw new NoSuchElementException();
      }
      
      Collection<T> returnC = new ArrayList<T>();
      Iterator<T> checker2 = coll.iterator();
      while (checker2.hasNext()) {
         T tvalue = checker2.next();
         int comph = comp.compare(tvalue, high);
         int compl = comp.compare(tvalue, low);
      
         if (((comph == -1) || (comph == 0)) && ((compl == 1) || (compl == 0))) {
            returnC.add(tvalue);
         }
      }
      
      return returnC;
      
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty() == true) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> qchecker = coll.iterator();
      int qcounter = 0;
      while (qchecker.hasNext()) {
         int comparison = comp.compare(qchecker.next(), key);
         if ((comparison == 1) || (comparison == 0)) {
            qcounter++;
         }
      }
      
      if (qcounter == 0) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> itr = coll.iterator();
      T ceiling = itr.next();
      while (itr.hasNext()) {
         int comparison = comp.compare(ceiling, key);
         if (comparison == -1) {
            ceiling = itr.next();
            continue;
         }
         
         T compareV = itr.next();
         int comp1 = comp.compare(compareV, key); 
         if ((comp1 == 1) || (comp1 == 0)) {
            int comp2 = comp.compare(compareV, ceiling); 
            
            if (comp2 == -1) {
               ceiling = compareV;
            
            }
         }
         
         
         
      }
      
      return ceiling;
   
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      if ((coll == null) || (comp == null)) {
         throw new IllegalArgumentException();
      }
      
      if (coll.isEmpty() == true) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> qchecker = coll.iterator();
      int qcounter = 0;
      while (qchecker.hasNext()) {
         int comparison = comp.compare(qchecker.next(), key);
         if ((comparison == -1) || (comparison == 0)) {
            qcounter++;
         }
      }
      
      if (qcounter == 0) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> itr = coll.iterator();
      T floor = itr.next();
      while (itr.hasNext()) {
         int comparison = comp.compare(floor, key);
         if (comparison == 1) {
            floor = itr.next();
            continue;
         }
         
         T compareV = itr.next();
         int comp1 = comp.compare(compareV, key); 
         if ((comp1 == -1) || (comp1 == 0)) {
            int comp2 = comp.compare(compareV, floor); 
            
            if (comp2 == 1) {
               floor = compareV;
            
            }
         }
         
         
         
      }
      
      return floor;
   }

}
