/**
 * @ Node.java
 * @ This implements a structure for the node's used in the BST class
 * @ author: Destiny
 * @ date: September 25, 2024
 */
public class Node<E extends Comparable<? super E>> {
//  constructor method
    protected E element;
    protected Node<E> right;
    protected Node<E> left;

    public Node(E element){
        this.element = element;
        this.left = null;
        this.right = null;
    }

// this method sets the value of a generic element
    public void setElement(E element) {
        this.element = element;
    }


// this method sets the value for a left node
    public void setLeft(Node<E> l){
        this.left = l;
    }


// this method sets value for a right node
public void setRight(Node<E> r){
    this.right = r; // I wrote right instead of r here and it cost me a hour of confusion
}


// Method gets value of left node
    public Node<E> getLeft(){
        return left;
    }


// Method gets value of right Node
    public Node<E> getRight(){
        return right;
    }


// Method gets value of element.
    public E getElement(){
        return element;
    }

// This method checks if a node has no children
    public boolean isLeaf(){
        if(getLeft()== null && getRight() == null){
            return true;
        }
        return false;
    }
}