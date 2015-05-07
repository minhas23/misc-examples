package com.manjeet.sample;

/**
 * Stack implementation with 
 * push // push the element at top of stack
 * pop // removes and returns the element from top of stack
 * findMiddle // returns the element from middle of stack
 * deleteMiddle // deletes the element from middle of stack
 * @author manjeet
 * @param <E>
 */

public class StackWithDeleteMiddle<E> {
  private final int size;
  private int top;
  private E[] elements;

  /**
   * Constructor with default size
   */
  public StackWithDeleteMiddle() {
    this(10);
  }

  public StackWithDeleteMiddle(int s) {
    size = s > 0 ? s : 10;
    top = -1;
    elements = (E[]) new Object[size]; 
  }
  
  /**
   * Push element e to stack if it is not full
   * @param e
   */
  public void push(E e) {
    if (top == size - 1){
     // if stack is full
        System.out.println("Stack is full, cannot push element " +  e);
    }else{
        elements[++top] = e; // place e on Stack
    } 
  }
  
  /**
   * pop element from the top of the stack if it is not empty
   * @return top of the stack
   */
  public E pop() {
    E e = null;
    if (top == -1){
     // if stack is empty
        System.out.println("Stack is empty, cannot pop");
    }else{
        e = elements[top--]; // remove and return top element of Stack
    } 
    return e;
  }
  
  /**
   * Give middle element of the stack if it is not empty
   * @return middle element of the stack
   */
  E findMiddle() {
      if (top == -1){
          System.out.println("Stack is empty, cannot pop");
          return null;
      }
      return elements[top/2];
  }
  
  /**
   * Delete middle element of the stack if it is not empty
   * @return middle element of the stack
   */
  E deleteMiddle(){
      if (top == -1){
          System.out.println("Stack is empty, cannot pop");
          return null;
      }
      int index = (int)top/2;
      E middle = elements[index];
      System.arraycopy(elements, index+1 , elements, index, (top-index));
      top--;
      return middle;
  }
 
  public static void main(String args[]) {
        
      StackWithDeleteMiddle<Integer> stack = new StackWithDeleteMiddle<Integer>();
      stack.push(1);
      stack.push(2);
      stack.push(3);
      stack.push(4);
      stack.push(5);
      stack.push(6);
      stack.push(7);
      
      System.out.println("deleted=" + stack.deleteMiddle());
      System.out.println("middle=" + stack.findMiddle());
      System.out.println("popped=" + stack.pop());
     
     
      
    }
}
