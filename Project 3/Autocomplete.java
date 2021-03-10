import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 *
 * @author  James Barras (jab0217@auburn.edu)
 *
 */
public class Autocomplete {

	/**
	 * Initializes a data structure from the given array of terms.
	 * This method throws a NullPointerException if terms is null.
	 */
   Term[] termArray;
   public Autocomplete(Term[] terms) {
      if (terms == null) {
         throw new NullPointerException();
      }
      
      termArray = terms;
      for (int i = 0; i < termArray.length - 1; i++) {
         
         int index = i;
         for (int j = i + 1; j < termArray.length; j++) {
         
            if (termArray[j].compareTo(termArray[index]) == -1) {
               index = j;
            }
            
            
         }
         
         Term t = termArray[index];
         termArray[index] = termArray[i];
         termArray[i] = t;
      }
   
   }

	/** 
	 * Returns all terms that start with the given prefix, in descending order of weight. 
	 * This method throws a NullPointerException if prefix is null.
	 */
   public Term[] allMatches(String prefix) { 
      if (prefix == null) {
         throw new NullPointerException();
      }
      
      int length = prefix.length();
      Comparator<Term> comparator = Term.byPrefixOrder(length);
      Term key = new Term(prefix, 1);
      
      int firstI = BinarySearch.<Term>firstIndexOf(termArray, key, comparator);
      int lastI = BinarySearch.<Term>lastIndexOf(termArray, key, comparator);
      int alength = lastI - firstI + 1;
      Term[] returnArray = new Term[alength];
   
      int index = 0;
      for (int i = firstI; i <= lastI; i++) {
         returnArray[index] = termArray[i];
         index++;
      } 
      
      Comparator<Term> comparator2 = Term.byDescendingWeightOrder();
      for (int i = 0; i < returnArray.length - 1; i++) {
         
         int index1 = i;
         for (int j = i + 1; j < returnArray.length; j++) {
         
            if (comparator2.compare(returnArray[j], returnArray[index1]) == -1) {
               index1 = j;
            }
            
            
         }
         
         Term t = returnArray[index1];
         returnArray[index1] = returnArray[i];
         returnArray[i] = t;
      }
   
      return returnArray;
   }

}