package com.mygdx.game;

/*
    The class Chef uses the stack and it is how it carries items and interacts with stations
*/

public class Stack {
    Item arr[];
    public int top;
    int capacity;

    //Initialize stack
    public Stack(int size){
        arr = new Item[size];
        capacity = size;
        top = -1;
    }

    // push elements to top of stack
    public void push(Item x){
        // can't push item to stack if the stack is full
        if (isFull()){
            System.out.println("Stack OverFlow");
        }
        // increments top after adding x to stack
        arr[++top] = x;
    }

    // pops elements from top of stack
    public Item pop(){
        // if stack is empty, no ellement to pop
        if (isEmpty()){
            System.out.println("Stack Empty");
        }
        // decrements top after popping
        return arr[top--];
    }

    // return size of stack
    public int getSize(){
        return top +1;
    }

    // check if the stack is empty
    public boolean isEmpty(){
        return top == -1;
    }

    // check if the stack is full
    public boolean isFull(){
        return top == capacity -1;
    }

    // display elements of stack
    public void printStack(){
        System.out.print( "\n Items: ");
        for (int i = 0; i <= top; i++) {
            System.out.print("(" + arr[i].name + ", "+ arr[i].status+")" + ", ");
        }
    }

    // look at top item in the stack
    public Item peak(){
        return arr[top];
    }
    
}
