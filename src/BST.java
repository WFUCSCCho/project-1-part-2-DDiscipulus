/**
 * @ BST.java
 * @ This program implements a binary search tree and an iterator class and method
 * @ author: Destiny
 * @ date: September 25, 2024
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

// constructor

class BST<E extends Comparable<E>> {
    private Node<E> root; // Root
    private int nodecount; // # nodes

    // Constructors
    BST() {
        root = null;
        nodecount = 0;
    }

    // helper methods
        // inserts a node in the right place using comparison
    private Node<E> inserthelp(Node<E> root, E e) {
        if (root == null) {
            return new Node<>(e);
        } else if (root.getElement().compareTo(e) >= 0) {
            root.setLeft(inserthelp(root.getLeft(), e));
        } else {
            root.setRight(inserthelp(root.getRight(), e));
        }
        return root;
    }

        // searches for given node using compareTo
    private Comparable searchHelp(Node root, Comparable e) {
        if (root == null) {
            return null;
        } else if (root.getElement().compareTo(e) > 0) {
            return searchHelp(root.getLeft(), e);
        } else if (root.getElement().compareTo(e) == 0) {
            return root.getElement();
        } else {
            return searchHelp(root.getRight(), e);
        }
    }

        // Removes given Node using compareTo
    private Node<E> removehelp(Node<E> root, E e) {
        if (root == null) {
            return null;
        }
        if (root.getElement().compareTo(e) > 0) {
            root.setLeft((removehelp(root.getLeft(), e)));
        } else if (root.getElement().compareTo(e) < 0) {
            root.setRight(removehelp(root.getRight(), e));
        } else {
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.left;
            } else {
                Node temp = getmax(root.getLeft());
                root.setElement((E) temp.getElement());
                root.setLeft(deleteMax(root.getLeft()));
            }
        }
        return root;
    }

     // Gets the maximum element in a subtree
    private Node<E> getmax(Node<E> rt) {
        if (root.getRight() == null) {
            return rt;
        }
        return getmax(root.getRight());
    }
    // gets the minimum element ina subtree
    public Node<E> getMin(Node<E> rt) {
        if (rt.getLeft() == null) {
            return rt; // Return the current node if there is no left child
        }
        return getMin(rt.getLeft());
    }
        // Deletes the maximum valued element in a subtree
    private Node deleteMax(Node root) {
        if (root.getRight() == null) {
            return root.left;
        }
        root.setRight(deleteMax(root.getRight()));
        return root;
    }

    // returns root
    public Node<E> getRoot() {
        return root;
    }
    // clears the tree
    public void clear() {
        root = null;
        nodecount = 0;
    }

    // returns size of tree
    public int size() {
        return nodecount;
    }


    // inserts a node
    public void insert(E e) {
        root = inserthelp(root, e);
        nodecount++;
    }


    // removes a node
    public E remove(E key) {
        E temp = (E) searchHelp(root, key); // First find it
        if (temp != null) {
            root = removehelp(root, key); // Now remove it
            nodecount--;
        }
        return temp;
    }


    // finds a node
    public E search(E key) {
        return (E) searchHelp(root, key);

    }

    //This method prints a tree and returns an ArrayList of numbers printed
    public String print() {
        ArrayList<String> listPrintedValues = new ArrayList<>(); // stores printed values in order
        Iterator<E> it = iterator(); // iterator for traversal

        //formatting
        System.out.println();

        // prints values
        while (it.hasNext()) {
            E nodeElement = it.next();
            listPrintedValues.add(nodeElement.toString());
            System.out.print(nodeElement + " ");
        }
        // Return the list of printed values
        return toString(listPrintedValues);
    }
    public String rankedCaloricPrint(int treeSize) {
        ArrayList<String> listPrintedValues = new ArrayList<>(); // stores printed values in order
        Iterator<E> it = iterator(); // iterator for traversal
        int tracker = treeSize; // tracker

        //formatting
        System.out.println();
        System.out.println("Here are you options from most to least caloric");
        // prints values
        while (it.hasNext()) {

            E nodeElement = it.next();
            listPrintedValues.add(nodeElement.toString());
            System.out.print(tracker + ". " + nodeElement + "\n");
            tracker--;
        }
        // Return the list of printed values
        return toString(listPrintedValues);
    }

    // To String method to make sure printed Array gives us formatted use info
    private String toString(ArrayList<String> listPrintedValues) {
        return String.join(" ", listPrintedValues);
    }

    // Iterator method
    public Iterator<E> iterator(){
        return new BSTIterator();
    }

    // BSTIterator class
    private class BSTIterator implements Iterator<E> {
        public Stack<Node<E>> stack;

        public BSTIterator() {
            // constructor
            stack = new Stack<>();
            if (root != null) {
                goLeftFrom(root);
            }
        }

        // determines if there is a next node in tree
        @Override
        public boolean hasNext() {
            if (stack.isEmpty()) {
                return false;
            } else {
                return true;
            }
        }
        // actual moves to next node in tree inOrder
        @Override
        public E next() {
            Node<E> current = null;
            if (hasNext()) {
                current = stack.peek();
                stack.pop();
                if (current.getRight() != null) {
                    goLeftFrom(current.right);
                }
               return current.getElement();
            }
            return null;


        }
        // goes left from given node
        private void goLeftFrom(Node<E> e) {
            while (e != null) {
                stack.push(e);
                e = e.getLeft();
            }
        }
    }
}






