/**
 * Author: Samuel Apoya
 */

import java.text.ParseException;
import java.util.Comparator;


public class Heap<T> implements PriorityQueue<T>{
    private static class Node<T>{
        Node<T> left, right, parent;
        T data;

        public Node(T data, Node<T> left, Node<T> right, Node<T> parent){
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    private int size;
    private Node<T> root, last;
    private Comparator<T> comparator;

    public Heap(){
        this(null,false);
    }

    public Heap(boolean maxHeap){
        this(null, maxHeap);

    }

    public Heap(Comparator<T> comparator){
        this(comparator, false);

    }

    public Heap(Comparator<T> comparator, boolean maxHeap){
        if(comparator != null){
            this.comparator = comparator;
        }else{
            this.comparator = new Comparator<T>() {
                @Override
                public int compare(T o1, T o2){
                    return ((Comparable<T>) o1).compareTo(o2);

                }
                
            };

        }if(maxHeap){
            this.comparator = new Comparator<T>() {
                @Override
                public int compare(T o1, T o2){
                    return Heap.this.comparator.compare(o2, o1);
                }
                
            };
        }
        size = 0;
        root = null;
        last = null;
    }

    

    @Override
    public void offer(T item) {
        if(size == 0){
            root = new Node<T>(item, null, null, null);
            last = root;
            size++;
            return;
        }
        if(size % 2 == 0){
            // if(last.parent == null){
            //     root.left = new Node<T>(item, null, null, root);
            //     last = root.left;
            // }else{
                last.parent.right = new Node<T>(item, null, null, last.parent);
                last = last.parent.right;

            // }
           

        }else{
            Node<T> curNode = last;
            while(curNode != root && curNode == curNode.parent.right){
                curNode = curNode.parent;

            }
            if(curNode != root){
                curNode = curNode.parent.right;
            }

            while(curNode.left != null){
                curNode = curNode.left;
            }
            curNode.left = new Node<T> (item, null, null, curNode);
            last = curNode.left;
        }
        size++;
        bubbleUp(last); 
            
    }



    private void swap(Node<T> node1, Node<T> node2){
        T temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }



    private void bubbleUp(Node<T> curNode){
        if(curNode == root){
            return;
        }
        T myData = curNode.data;
        T parentData = curNode.parent.data;
        if(comparator.compare(myData, parentData) < 0){
            swap(curNode, curNode.parent);
            bubbleUp(curNode.parent);

        }
    }



    @Override
    public int size() {
       return size;
    }

    @Override
    public T peek() {
        return root.data;
    }



    @Override
    public T poll() {
        if(size == 0){ 
            return null;
        } 
        
        T rootData = root.data;
        if(size == 1){
            root = null;
            last = null;
            size--;
            return rootData;
        }
        if(size % 2 != 0){
            root.data = last.data;
            last = last.parent.left;
            last.parent.right = null;
        }
        if(size % 2 == 0){
            root.data = last.data;
            Node<T> curNode = last;
            while(curNode != root && curNode == curNode.parent.left){
                curNode = curNode.parent;

            }
            if(curNode != root){
                curNode = curNode.parent.left;
            }

            while(curNode.right != null){
                curNode = curNode.right;
            }
            last.parent.left = null;
            last = curNode;
        }
        bubbleDown(root);
        size--;
        return rootData;
    }


    private void bubbleDown(Node<T> curNode){
        if (curNode.left == null){
            // then we know curNode has no children, so we can just end
            return;
        } else if (curNode.right == null){
            // then we know curNode has exactly one child, just its left
            // so we just need to determine if we need to swap to the left
            if (comparator.compare(curNode.data, curNode.left.data) > 0){
                swap(curNode, curNode.left);
                bubbleDown(curNode.left);
            }
        } else {
            // then we know that curNode has both a left and right child
            // so we first have to determine which child is of greater priority
            // then determine if we have to swap with that child
            if (comparator.compare(curNode.left.data, curNode.right.data) < 0){
                if (comparator.compare(curNode.data, curNode.left.data) > 0){
                    swap(curNode, curNode.left);
                    bubbleDown(curNode.left);
                }
            } else {
                if (comparator.compare(curNode.data, curNode.right.data) > 0){
                    swap(curNode, curNode.right);
                    bubbleDown(curNode.right);
                }
            }
        }
    }



    @Override
    public void updatePriority(T item) {
        Node<T> node = findNode(root, item);
        if(root == null){
            return;
        }
        node.data = item;
        bubbleUp(node);
        bubbleDown(node);

    }

    private Node<T> findNode(Node<T> current, T item){
        if(current == null){
            return null;
        }
        if(current.data.equals(item)){
            return current;
        }
        Node<T> leftResult = findNode(current.left, item);
        if(leftResult != null){
            return leftResult;
        }
        Node<T> rightResult = findNode(current.right, item);
        if(rightResult != null){
            return rightResult;
        }
        return null;
    }
   

}