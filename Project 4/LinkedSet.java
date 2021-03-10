import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author YOUR NAME (you@auburn.edu)
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

   //////////////////////////////////////////////////////////
   // Do not change the following three fields in any way. //
   //////////////////////////////////////////////////////////

   /** References to the first and last node of the list. */
   Node front;
   Node rear;

   /** The number of nodes in the list. */
   int size;

   /////////////////////////////////////////////////////////
   // Do not change the following constructor in any way. //
   /////////////////////////////////////////////////////////

   /**
    * Instantiates an empty LinkedSet.
    */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }


   //////////////////////////////////////////////////
   // Public interface and class-specific methods. //
   //////////////////////////////////////////////////

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this LinkedSet.
    *
    * @return a string representation of this LinkedSet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }


   ///////////////////////////////////
   // DO NOT CHANGE THE SIZE METHOD //
   ///////////////////////////////////
   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   public int size() {
      return size;
   }

   //////////////////////////////////////
   // DO NOT CHANGE THE ISEMPTY METHOD //
   //////////////////////////////////////
   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return (size == 0);
   }


   /**
    * Ensures the collection contains the specified element. Neither duplicate
    * nor null values are allowed. This method ensures that the elements in the
    * linked list are maintained in ascending natural order.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   public boolean add(T element) {
      if ((element == null) || (contains(element) == true)) {
         return false;
      } 
      Node addN = new Node(element);
    
      if (size == 0) {
         front = addN;
         rear = addN;
         
         size++;
         return true;
      }
      
      Node n = front;
      Node p = n;
      while (n != null) {
         if (addN.element.compareTo(n.element) == -1) {
            
            if (n == front) {
               addN.next = n;
               n.prev = addN;
               front = addN;
               
               size++;
               return true;
            }
            
            addN.next = p.next;
            p.next = addN;
            addN.prev = p;
            n.prev = addN;
            
            size++;
            return true;
         }
         
         if (n.next == null) {
            n.next = addN;
            addN.prev = n;
            rear = addN;
            
            size++;
            return true;
         }
         
         p = n;
         n = n.next;
      }
    
      size++;
      return true;
   }

   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection. This method, consistent with add, ensures
    * that the elements in the linked lists are maintained in ascending
    * natural order.
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */
   public boolean remove(T element) {
      if (contains(element) == false) {
         return false;
      }
      
      Node n = front;
      
      if (size == 1) {
         front = null;
         rear = null;
         size--;
         return true;
      }
      
      while ((n != null) && (!n.element.equals(element))) {
         n = n.next;
      }
      
      if (n == front) {
         front = front.next;
         front.prev = null;
      } else {
         n.prev.next = n.next;
         
         if (n.next != null) {
         
            n.next.prev = n.prev;
            
         } 
         if (n.next == null) {
            rear = n.prev;
         }
      
      }
      
      size--;
      return true;
   }


   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection is to be tested.
    * @return  true if this collection contains the specified element, false otherwise.
    */
   public boolean contains(T element) {
      if (size == 0) {
         return false;
      }
      
      Node n = front;
    
      while (n != null) {
         if (n.element.equals(element)) {
            return true;
         }
         n = n.next;
      }
      return false;
    
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
    
      Iterator<T> itr = s.iterator();
      Iterator<T> itr2 = this.iterator();
      
      int counter1 = 0;
      T t;
      while (itr.hasNext()) {
         t = itr.next();
         counter1++;
      }
      int counter2 = 0;
      T t1;
      while (itr2.hasNext()) {
         t1 = itr2.next();
         counter2++;
      }
      
      if (counter1 != counter2) {
         return false;
      }
      
      //Iterator<T> iter2 = this.iterator();
      Iterator<T> iter1 = s.iterator();
      
      T st;
      T tt;
      boolean answer = false;
      boolean checker = false;
      int counter = 0;
      while (iter1.hasNext()) {
         
         if (counter != 0) {
            if (checker == false) {
               break;
            }
         }
         
         st = iter1.next();
         Iterator<T> iter2 = this.iterator();
         
         while (iter2.hasNext()) {
            counter++;
            tt = iter2.next();
         
            if (tt.compareTo(st) == 0) {
               checker = true;
               break;
            }
         
         
         }
         
         answer = true;
      }
      
      
      
      return answer;
    
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(LinkedSet<T> s) {
      Node n1 = this.front;
      Node n2 = s.front;
      
      int counter1 = 0;
      while (n1 != null) {
         n1 = n1.next;
         counter1++;
      } 
      int counter2 = 0;
      while (n2 != null) {
         n2 = n2.next;
         counter2++;
      }
      if (counter1 != counter2) {
         return false;
      }
      
      
      Node check1 = this.front;
      Node check2 = s.front;
      
      boolean answer = false;
      
      while (check1 != null) {
      
      
         if (check1.element.compareTo(check2.element) != 0) {
            answer = false;
            break;
         }
      
         check1 = check1.next;
         check2 = check2.next;
        
         answer = true;
       
      
      }
      
      
      
      
      return answer;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(Set<T> s){
      Set<T> returnSet = new LinkedSet<T>();
      
      Iterator<T> itr1 = this.iterator();
      Iterator<T> itr2 = s.iterator();
      
      T n;
      while (itr1.hasNext()) {
         n = itr1.next();
         returnSet.add(n);
      }
      
      T t;
      while(itr2.hasNext()) {
         t = itr2.next();
         returnSet.add(t);
      }
      
      return returnSet;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(LinkedSet<T> s){
      LinkedSet<T> returnSet = new LinkedSet<T>();
      
      Node n = s.front;
      Node n2 = this.front;
      
      while ((n != null) || (n2 != null)) {
      
         if (n == null) {
            if (returnSet.front == null) {
               Node addN = new Node(n2.element);
               
               returnSet.front = addN;
               returnSet.rear = addN;
               
               n2 = n2.next;
               continue;
            }
            Node addN1 = new Node(n2.element);
          
            returnSet.rear.next = addN1;
            addN1.prev = returnSet.rear;
            returnSet.rear = addN1;
           
            n2 = n2.next;
            continue;
         
         }
         else if (n2 == null) {
            if (returnSet.front == null) {
               Node addN2 = new Node(n.element);
               
               returnSet.front = addN2;
               returnSet.rear = addN2;
               
               n = n.next;
               continue;
            }
         
            Node addN3 = new Node(n.element);
         
            returnSet.rear.next = addN3;
            addN3.prev = returnSet.rear;
            returnSet.rear = addN3;
            
            n = n.next;
            continue;
         } 
      
         if (returnSet.front == null) {
            int v = n.element.compareTo(n2.element);
            if (v == 1) {
               Node addN4 = new Node(n2.element);
               
               returnSet.front = addN4;
               returnSet.rear = addN4;
              
              
               n2 = n2.next;
               continue;
            
            } else if (v == -1) {
               Node addN7 = new Node(n.element);
               
               returnSet.front = addN7;
               returnSet.rear = addN7;
               
             
               n = n.next;
               continue;
            } 
            Node addN8 = new Node(n.element);
            
            returnSet.front = addN8;
            returnSet.rear = addN8;
            
            n = n.next;
            n2 = n2.next;
            continue;
         }
         
         int c = n.element.compareTo(n2.element);
         if (c == 1) {
            Node addN9 = new Node(n2.element);
            
            returnSet.rear.next = addN9;
            addN9.prev = returnSet.rear;
            returnSet.rear = addN9;
            
            n2 = n2.next;
            continue;
            
         } else if (c == -1) {
            Node addN12 = new Node(n.element);
            
            returnSet.rear.next = addN12;
            addN12.prev = returnSet.rear;
            returnSet.rear = addN12;
            
            n = n.next;
            continue;
            
         }else if (c == 0) {
            Node addN13 = new Node(n.element);
            
            returnSet.rear.next = addN13;
            addN13.prev = returnSet.rear;
            returnSet.rear = addN13;
            
            n = n.next;
            n2 = n2.next;
            
         }
         
         
         
      }
      
      Node f = returnSet.front;
      int counter = 0;
      while (f != null) {
         counter++;
         f = f.next;
      }
      returnSet.size = counter;
      
      
      return returnSet;
   }


   /**
    * Returns a set that is the intersection of this set and the parameter set.
    *
    * @return  a set that contains elements that are in both this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) {
      Set<T> returnSet = new LinkedSet<T>();
      
      Iterator<T> itr1 = this.iterator();
      
      T t1;
      boolean check = false;
      while(itr1.hasNext()) {
         check = false;
         t1 = itr1.next();
         if (s.contains(t1)) {
            check = true;
            //returnSet.add(t1);
         }
         if (check == true) {
            returnSet.add(t1);
         }
         
      }
      
      
      return returnSet;
   }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(LinkedSet<T> s) {
      LinkedSet<T> returnSet = new LinkedSet<T>();
      
      Node n = s.front;
      Node n2 = this.front;
      
      while ((n != null) && (n2 != null)) {
         int v = n.element.compareTo(n2.element);
      
         if (v == 1) {
            n2 = n2.next;
            continue;
                       
         }else if (v == -1) {
            n = n.next;
            continue;
         
         }
      
         if (returnSet.front == null) {
            Node addN = new Node(n.element);
         
            returnSet.front = addN;
            returnSet.rear = addN;
         
            n = n.next;
            n2 = n2.next;
            continue;
         }
      
         Node addN1 = new Node(n.element);
         
         returnSet.rear.next = addN1;
         addN1.prev = returnSet.rear;
         returnSet.rear = addN1;
      
         n = n.next;
         n2 = n2.next;
      
      
      }
      
      Node f = returnSet.front;
      int counter = 0;
      while (f != null) {
         counter++;
         f = f.next;
      }
      returnSet.size = counter;
      
      
      
      return returnSet;
   }


   /**
    * Returns a set that is the complement of this set and the parameter set.
    *
    * @return  a set that contains elements that are in this set but not the parameter set
    */
   public Set<T> complement(Set<T> s) {
      Set<T> returnSet = new LinkedSet<T>();
      
      Iterator<T> itr = this.iterator();
      
      T t;
      boolean check = false;
      while (itr.hasNext()) {
         check = false;
         t = itr.next();
         if (!s.contains(t)) {
            check = true;
            //returnSet.add(t);
         }
         if (check == true) {
            returnSet.add(t);
         }
      
      
      
      }
      
      
      return returnSet;
   }


   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(LinkedSet<T> s) {
      LinkedSet<T> returnSet = new LinkedSet<T>();
      
      Node n = s.front;
      Node n2 = this.front;
      
      while (n2 != null) {
         if (n == null) {
            Node addN3 = new Node(n2.element);
         
            if (returnSet.front == null) {
               returnSet.front = addN3;
               returnSet.rear = addN3;
               n2 = n2.next;
               continue;
            }
         
            returnSet.rear.next = addN3;
            addN3.prev = returnSet.rear;
            returnSet.rear = addN3;
            
            n2 = n2.next;
            continue;
         
         }
         
         
         
         int v = n.element.compareTo(n2.element);
      
         if (v == 1) {
            Node addN = new Node(n2.element);
            if (returnSet.front == null) {
               returnSet.front = addN;
               returnSet.rear = addN;
               
               n2 = n2.next;
               continue;
            }
         
            returnSet.rear.next = addN;
            addN.prev = returnSet.rear;
            returnSet.rear = addN;
         
            n2 = n2.next;
            continue;
         }
         else if (v == -1) {
         
            n = n.next;
            continue;
         
         }
      
         n = n.next;
         n2 = n2.next;
      
      }
      
      
      Node f = returnSet.front;
      int counter = 0;
      while (f != null) {
         counter++;
         f = f.next;
      } 
      returnSet.size = counter;
      
     
      return returnSet;
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in ascending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> iterator() {
      return new NodeIterator();
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in descending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> descendingIterator() {
      return new DescendingNodeIterator();
   }


   /**
    * Returns an iterator over the members of the power set
    * of this LinkedSet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
      return new powerSetIterator();
   }



   //////////////////////////////
   // Private utility methods. //
   //////////////////////////////
   
   private class NodeIterator implements Iterator<T> {
      private Node current = front;
      
      public boolean hasNext() { 
         return current != null;
      }
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         T result = current.element;
         current = current.next;
         return result;
      }
      
   }
   
   private class DescendingNodeIterator implements Iterator<T> {
      private Node current = rear;
      
      public boolean hasNext() { 
         return current != null;
      }
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         T result = current.element;
         current = current.prev;
         return result;
      }
      
   }
   
   public class powerSetIterator implements Iterator<Set<T>> {
      private Node current1 = front;
      private int counter = 0;
      private LinkedSet<T> subset; //= new LinkedSet<T>();
      
      
      public boolean hasNext() { 
         return counter < Math.pow(2, size);
      }
      
      public Set<T> next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         LinkedSet<T> subset = new LinkedSet<T>();
         Node current = current1;
        
       
         String binaryS = Integer.toBinaryString(counter);
         
         
         for (int i = binaryS.length() - 1; i >= 0; i--) {
            if (current == null) {
               break;
            }
            
            
            Node addN = new Node(current.element);
            char c = binaryS.charAt(i);
            int n = Character.getNumericValue(c);
         
            if (n == 1) {
            
               if (subset.front == null) {
                  subset.front = addN;
                  subset.rear = addN;
                  
                  current = current.next;
                  continue;
               }
               
               subset.rear.next = addN;
               addN.prev = subset.rear;
               subset.rear = addN;
            
            }
            
         
            current = current.next;
         }
        
         Node f = subset.front;
         int sizec = 0;
         while (f != null) {
            sizec++;
            f = f.next;
         }
         subset.size = sizec;
        
         counter++;
         return subset;
      }
      
   }

   // Feel free to add as many private methods as you need.

   ////////////////////
   // Nested classes //
   ////////////////////

   //////////////////////////////////////////////
   // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
   //////////////////////////////////////////////

   /**
    * Defines a node class for a doubly-linked list.
    */
   class Node {
      /** the value stored in this node. */
      T element;
      /** a reference to the node after this node. */
      Node next;
      /** a reference to the node before this node. */
      Node prev;
   
      /**
       * Instantiate an empty node.
       */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }
   
      /**
       * Instantiate a node that containts element
       * and with no node before or after it.
       */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
     
   }

}
