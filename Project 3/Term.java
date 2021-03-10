import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 *
 * @author  James Barras (jab0217@auburn.edu)
 *
 */
public class Term implements Comparable<Term> {

   /**
    * Initialize a term with the given query and weight.
    * This method throws a NullPointerException if query is null,
    * and an IllegalArgumentException if weight is negative.
    */
   private String queryV;
   private long weightV;
   public Term(String query, long weight) { 
      if (query == null) {
         throw new NullPointerException();
      }
      if (weight < 0) {
         throw new IllegalArgumentException();
      }
      
      queryV = query;
      weightV = weight;
   
   
   }

   /**
    * Compares the two terms in descending order of weight.
    */
   public static Comparator<Term> byDescendingWeightOrder() {
      return 
         new Comparator<Term>() {
            public int compare(Term t1, Term t2) {
               if (t1.weightV > t2.weightV) {
                  return -1;
               } else if (t1.weightV < t2.weightV) {
                  return 1;
               } 
               return 0;
            }
         };
      
   }

   /**
    * Compares the two terms in ascending lexicographic order of query,
    * but using only the first length characters of query. This method
    * throws an IllegalArgumentException if length is less than or equal
    * to zero.
    */
   public static Comparator<Term> byPrefixOrder(int length) { 
      if (length <= 0) {
         throw new IllegalArgumentException();
      }
     
      return 
         new Comparator<Term>() {
            public int compare(Term t1, Term t2) {
               if ((t2.queryV.length() < length) || (t1.queryV.length() < length)) {
               
                  if ((t2.queryV.length() < length) && (t1.queryV.length() < length)) {
                     return (t2.queryV.compareTo(t1.queryV));
                  }
                  if (t2.queryV.length() < length) {
                     return -1;
                  }
                  if (t1.queryV.length() < length) {
                     return 1;
                  }
               }
              
              
              
              
              
               String t1s = t1.queryV.substring(0, length);
               String t2s = t2.queryV.substring(0, length);
              
               int result = t1s.compareTo(t2s);
               if (result == 1 ) {
                  return 1;
               }
               else if (result == -1) {
                  return -1;
               }
               return 0;
            }
         };
   
   }

   /**
    * Compares this term with the other term in ascending lexicographic order
    * of query.
    */
   @Override
   public int compareTo(Term other) { 
      if (this.queryV.compareTo(other.queryV) == -1) {
         return -1;
      } else if (this.queryV.compareTo(other.queryV) == 1) {
         return 1;
      } 
      
      return 0;
   }

   /**
    * Returns a string representation of this term in the following format:
    * query followed by a tab followed by weight
    */
   @Override
   public String toString(){ 
      String returnS = (queryV + "\t" + weightV);
      return returnS; 
   }

}