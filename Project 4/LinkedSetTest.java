import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

public class LinkedSetTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void containsemptyTest() {
     
      LinkedSet set = new LinkedSet();
      boolean returnV = set.contains(4);
      Assert.assertEquals(false, returnV);
   }
   
   @Test public void containsfalseTest() {
      LinkedSet<Integer> set = new LinkedSet<Integer>();
      set.add(4);
      set.add(8);
      set.add(6);
      set.add(2);
   
   
      Iterator<Set<Integer>> psIterator = set.powerSetIterator();
      while (psIterator.hasNext()) {
         System.out.println(psIterator.next());
      }
      
      
      
   }
   
   
   
}
