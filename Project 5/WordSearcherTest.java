import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Object;
import java.util.*;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.Deque;


public class WordSearcherTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void defaultTest() {
      String filename = "CSW12.txt";
      //loadLexicon(filename);
      WordSearcher s = new WordSearcher();
      s.loadLexicon(filename);
     
      String[] array = {"C", "A", "X", "T",};
      //s.getBoard();
      s.setBoard(array);
      
      SortedSet<String> returnArray = s.getAllScorableWords(3);
      
      Assert.assertEquals(false, false);
   }
}
