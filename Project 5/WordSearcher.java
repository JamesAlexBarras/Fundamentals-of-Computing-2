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

// is a game engine that implements the WordSearchGame interface

public class WordSearcher implements WordSearchGame {
   private boolean lexiconcalled = false;
   private TreeSet<String> lexicon = new TreeSet<String>();
   private String[][] board = new String[][]{{"E", "E", "C", "A"}, {"A", "L", "E", "P"}, {"H", "N", "B", "O"}, {"Q", "T", "T", "Y"}};
   private int sidelength;
   private static final int MAX_NEIGHBORS = 8;
   private boolean[][] visited;
   private String wordtracker;
   private List<Integer> path;
   //private List<String> path2;
   private SortedSet<String> allWords; //= new TreeSet<String>();
   private boolean gate;

   public void loadLexicon(String filename) {
      if (filename == null) {
         throw new IllegalArgumentException();
      }
      Scanner scan;
      try {
         scan = new Scanner(new File(filename));
      } catch (FileNotFoundException ex) {
         throw new IllegalArgumentException();
      }
      
      while (scan.hasNextLine()) {
         String line = scan.nextLine();
         Scanner scan2 = new Scanner(line);
         String token = scan2.next();
         String addt = token.toUpperCase();
      
         lexicon.add(addt);
      
      }
      lexiconcalled = true;
   }

   public void setBoard(String[] letterArray) {
      if (letterArray == null) {
         throw new IllegalArgumentException();
      }
      double square = Math.sqrt(letterArray.length);
      if ((square % 1) != 0) {
         throw new IllegalArgumentException();
      }
      
      sidelength = (int) square;
      board = new String[sidelength][sidelength];
      int counter = 0;
      for (int i = 0; i < square; i++) {
         for (int j = 0; j < square; j++) {
            board[i][j] = letterArray[counter];
            counter++;
         }
      }
      markAllUnvisited();
   }

   public String getBoard() {
      return board.toString();
   }

   public SortedSet<String> getAllScorableWords(int minimumWordLength) {
      if (lexiconcalled == false) {
         throw new IllegalStateException();
      } 
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      
      markAllUnvisited();
      allWords = new TreeSet<String>();
      
      for (int i = 0; i < sidelength; i++) {
         for (int j = 0; j < sidelength; j++) {
            Position p = new Position(i, j);
            wordtracker = board[i][j];
            gate = true;
            
            recursiveSearch1(p, wordtracker, minimumWordLength, allWords);
         }
      }
      
       
      return allWords;
   }
   
   /////////////////////////////
   
   private boolean recursiveSearch1(Position k, String wordSoFar, int minWL, SortedSet<String> set) {
      //int wl = minWL;
      if (!isValid(k)) {
         return false;
      } else if (visited[k.x][k.y] == true) {
         return false;
      } else if ((board[k.x][k.y].length() >= minWL) && (isValidWord(board[k.x][k.y]))) {
         set.add(board[k.x][k.y]);
      }
   
      visited[k.x][k.y] = true;
      String placekeeper = wordSoFar;
      if (gate == false) {
         wordSoFar += board[k.x][k.y];
      }
      gate = false;
      
      if (!isValidPrefix(wordSoFar)) {
         wordSoFar = placekeeper;
         visited[k.x][k.y] = false;
         return false;
      }
      
      if ((isValidWord(wordSoFar)) && (wordSoFar.length() >= minWL)) {
         set.add(wordSoFar);
      }
      
      for (Position p: k.neighbors()) {
         if (recursiveSearch1(p, wordSoFar, minWL, set)) {
            return true;
         }
      }
         
      wordSoFar = placekeeper;
      visited[k.x][k.y] = false;
      return false;
   
   }

   
   
   
   //////////////////////////
   
   

   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      if (lexiconcalled == false) {
         throw new IllegalStateException();
      } 
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      
      int score = 0;
      Iterator<String> itr = words.iterator();
      
      while (itr.hasNext()) {
         String element = itr.next();
         
         if (element.length() == minimumWordLength) {
            score = score + 1;
            
         } else if (element.length() > minimumWordLength) {
            score = score + (1 + (element.length() - minimumWordLength));
         }
      }
      
      return score;
   }

   public boolean isValidWord(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (lexiconcalled == false) {
         throw new IllegalStateException();
      }
      
      String word = wordToCheck.toUpperCase();
      return lexicon.contains(word);
   }

   public boolean isValidPrefix(String prefixToCheck) {
      if (prefixToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (lexiconcalled == false) {
         throw new IllegalStateException();
      }
      
      String prefix = prefixToCheck.toUpperCase();
      String check = lexicon.ceiling(prefix);
      
      if (check == null) {
         return false;
      }
      for (int i = 0; i < prefix.length(); i++) {
         char p = prefix.charAt(i);
         char c = check.charAt(i);
         
         if (Character.compare(p, c) != 0) {
            return false;
         }
      }
      
      
      
      return true;
   }
   
   public List<Integer> isOnBoard(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (lexiconcalled == false) {
         throw new IllegalStateException();
      }
      
      path = new ArrayList<Integer>();
      
      markAllUnvisited();
      
      for (int i = 0; i < sidelength; i++) {
         for (int j = 0; j < sidelength; j++) {
            Position k = new Position(i, j);
            wordtracker = board[i][j];
            gate = true;
            boolean found = recursiveSearch(k, wordtracker, wordToCheck, path);
         
            if (found == true) {
               break;
            }
         }
      }
      
      return path;
   }
   
   
   
   
   //////////////////////////////
   
   private boolean recursiveSearch(Position k, String wordSoFar, String theWord, List<Integer> path) {
      if (!isValid(k)) {
         return false;
      } else if (visited[k.x][k.y] == true) {
         return false;
      } else if (!theWord.startsWith(wordSoFar)) {
         return false;
      }
   
      visited[k.x][k.y] = true;
      String placekeeper = wordSoFar;
      if (gate == false) {
         wordSoFar += board[k.x][k.y];
      }
      gate = false;
      path.add(((k.x * sidelength) + k.y));
      
      if (wordSoFar.equals(theWord)) {
         return true;
      }
      
      for (Position p: k.neighbors()) {
         if (recursiveSearch(p, wordSoFar, theWord, path)) {
            return true;
         }
      }
         
      wordSoFar = placekeeper;
      Object index = ((k.x * sidelength) + k.y);
      path.remove(index);
      visited[k.x][k.y] = false;
      return false;
   
   }
   
   /////////////////////////////////
   
   private void markAllUnvisited() {
      visited = new boolean[sidelength][sidelength];
      for (boolean[] row: visited) {
         Arrays.fill(row, false);
      }
   }
   
   

   
   
   
   
   
   /////////////////////////////////
   
   
   private class Position {
      int x;
      int y;
      
      public Position(int x, int y) {
         this.x = x;
         this.y = y;
      }
   /**
      private boolean isValid(Position k) {
         boolean check = ((k.x >= 0) && (k.x < sidelength) && (k.y >= 0) && (k.y < sidelength));
         return check;
      }
   
      private boolean isVisited(Position p) {
         return visited[p.x][p.y];
      }
      
      private void visit(Position k) {
         visited[k.x][k.y] = true;
      }
      */
      public Position[] neighbors() {
         Position[] neighbors = new Position[MAX_NEIGHBORS];
         int index = 0;
      
         Position k;
         for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
            
               if (!((j == 0) && (i == 0))) {
                  k = new Position(x + i, y + j);
               
                  if (isValid(k)) {
                     neighbors[index++] = k;
                  }
               }
            }
         }
      
         Position[] returnArray = Arrays.copyOf(neighbors, index);
         return returnArray;
      
      }
   
   }
   
   private boolean isValid(Position k) {
      boolean check = ((k.x >= 0) && (k.x < sidelength) && (k.y >= 0) && (k.y < sidelength));
      return check;
   }
   
   private boolean isVisited(Position p) {
      return visited[p.x][p.y];
   }
      
   private void visit(Position k) {
      visited[k.x][k.y] = true;
   }
   
   
   
   
   
   
   
}