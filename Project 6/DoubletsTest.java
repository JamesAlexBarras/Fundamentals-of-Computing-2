import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Iterator;

import java.util.stream.Collectors;
import java.io.*;


public class DoubletsTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void defaultTest() {
      File file = new File("CSW12.txt");
      InputStream in = null;
      try {
         in = new FileInputStream(file);
      } catch(FileNotFoundException e) {
      
      }
      Doublets d = new Doublets(in);
      
      List<String> str = d.getMinLadder("charge", "comedo");
   
      Assert.assertEquals(null, str);
   }
}
