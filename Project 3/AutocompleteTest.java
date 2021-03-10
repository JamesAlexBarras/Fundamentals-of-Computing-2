import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class AutocompleteTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void autoCompleteTest() {
      Term one = new Term("a", 4);
      Term two = new Term("ab", 8);
      Term three = new Term("abc", 10);
      Term four = new Term("abcd", 2);
      Term five = new Term("abcde", 6);
   
   
      Term[] terms = {two, one, three, four, five};
      Autocomplete actual = new Autocomplete(terms);
      Term[] expected = {one, two, three, four, five};
      Assert.assertEquals(expected, actual);
   
   
   
   }
   
   @Test public void allMatchesTest() {
      Term one = new Term("a", 4);
      Term two = new Term("ab", 8);
      Term three = new Term("abc", 10);
      Term four = new Term("abcd", 2);
      Term five = new Term("abcde", 6);
   
      Term[] terms = {two, one, three, four, five};
      Autocomplete actual = new Autocomplete(terms);
      
      Term[] returnT = actual.allMatches("ab");
      Term[] expected = {four, five, two, three};
      
      Assert.assertEquals(expected, returnT);
   
   
   
   }
}
