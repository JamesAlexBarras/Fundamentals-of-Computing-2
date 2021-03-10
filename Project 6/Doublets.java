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

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author James Barras (jab0217@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2019-03-29
 */
public class Doublets implements WordLadderGame {

   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   /////////////////////////////////////////////////////////////////////////////
   // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
   // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
   // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
   // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
   // table with chaining).
   /////////////////////////////////////////////////////////////////////////////
   
   private TreeSet<String> lexicon;
   private List<String> returnLadder;
   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
         //////////////////////////////////////
         // INSTANTIATE lexicon OBJECT HERE  //
         //////////////////////////////////////
         lexicon = new TreeSet<String>();
         
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            /////////////////////////////////////////////////////////////
            // INSERT CODE HERE TO APPROPRIATELY STORE str IN lexicon. //
            /////////////////////////////////////////////////////////////
            lexicon.add(str.toLowerCase());
            
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }
   
   


   //////////////////////////////////////////////////////////////
   // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
   //////////////////////////////////////////////////////////////

   public int getHammingDistance(String str1, String str2) {
      if (str1.length() != str2.length()) {
         return -1;
      }
      
      str1 = str1.toLowerCase();
      str2 = str2.toLowerCase();
      
      int counter = 0;
      
      for (int i = 0; i < str1.length(); i++) {
         Character c1 = str1.charAt(i);
         Character c2 = str2.charAt(i);
      
         if (!c1.equals(c2)) {
            counter++;
         }
      }
      
      
      return counter;
   }
   
  //////////////////////////
 
   public List<String> getMinLadder(String start, String end) {
      returnLadder = new ArrayList<String>();
      
      breadthFirst(start.toLowerCase(), end.toLowerCase());
      
      return returnLadder;
   }
   
   private void breadthFirst(String start, String end) {
      HashSet<String> seenWords = new HashSet<String>();
      seenWords.add(start);
      Node startNode = new Node(start, null);
      Node endNode = new Node(end, null);
      ArrayList<String> temp = new ArrayList<String>();
      boolean called = false;
      Node prev = null;
      
      Deque<Node> queue = new ArrayDeque<Node>();
      queue.addLast(startNode);
      
      if (start.equals(end)) {
         //returnLadder = new ArrayList<String>();
         returnLadder.add(start);
         return;
      }
      
      while(!queue.isEmpty()) {
         if (called == true) {
            break;
         }
         
         Node n = queue.removeFirst();
         //seenWords.add(n.element);
         List<String> neighbors = getNeighbors(n.element);
      
         //Iterator<String> itr = neighbors.iterator();
         for (String str : neighbors) {
            //String str = itr.next();
            
            if (!seenWords.contains(str)) {
               Node addN = new Node(str, n);
               queue.addLast(addN);
               seenWords.add(str);
               
               if (str.equals(end)) {
                  endNode.predecessor = n;
                  /**temp.add(str);
                  prev = addN.predecessor;
                  */
                  /**
                  while (prev != null) {
                     temp.add(prev.element);
                     prev = prev.predecessor;
                  } */
                  
                  called = true;
                  break;
                  
                  /**
                  if ((temp.size() < returnLadder.size()) || (called == false)) {
                     called = true;
                     returnLadder.clear();
                     //returnLadder = new ArrayList<String>();
                     for (int i = temp.size() - 1; i >= 0; i--) {
                        returnLadder.add(temp.get(i));
                     }
                     
                  }
                  */
               }
            }
         }
      }
      
      if (endNode.predecessor == null) {
         return;
      }
      
      
      Node current = endNode;
      while (current != null) {
         temp.add(current.element);
         current = current.predecessor;
      }
   
      
      for (int i = temp.size() - 1; i >= 0; i--) {
         returnLadder.add(temp.get(i));
      }
      
   } 
   
   private class Node {        
      String element;        
      Node predecessor;        
      public Node(String c, Node pred) {            
         element = c;            
         predecessor = pred;        
      }    
   }
   
   /////////////////////////
   
   public List<String> getNeighbors(String word) {
      List<String> returnList = new ArrayList<String>();
      /**
      for (int i = 0; i < word.length(); i++) {
         for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
         
            if (Character.compare(word.charAt(i), alphabet) != 0) {
               String str = word.replace(word.charAt(i), alphabet);
               if (isWord(str)) {
                  returnList.add(str);
               }
            
            }
         }
      } */
      
      Iterator<String> itr = lexicon.iterator();
      while (itr.hasNext()) {
         String str = itr.next();
         
         if (getHammingDistance(word, str) == 1) {
            returnList.add(str);
         }
      }
      
      return returnList;
   }
   
   /////////////////////////
   
   public int getWordCount() {
      return lexicon.size();
   }
   
   /////////////////////////
   
   public boolean isWord(String str) {
      return lexicon.contains(str.toLowerCase());
   }
   
   ////////////////////////

   public boolean isWordLadder(List<String> sequence) {
      Iterator itr = sequence.iterator();
      
      if (sequence.size() < 1) {
         return false;
      }
      
      String one = (String)itr.next();
      while(itr.hasNext()) {
         String two = (String)itr.next();
      
         if (!isWord(two) || !isWord(one)) {
            return false;
         }
         
         if (getHammingDistance(two, one) != 1) {
            return false;
         }
      
         one = two;
      }
      
      
      
      
      
      return true;
   }


}

