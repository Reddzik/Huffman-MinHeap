package asd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class s18819 {

    public static void printCode(Node root, String code) {

        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            System.out.println(root.character + " " + code);
            return;
        }

        printCode(root.left, code + "0");
        printCode(root.right, code + "1");
    }


    public static void main(String[] args) throws FileNotFoundException {
        MinHeap minHeap = new MinHeap(27);

        Scanner scanner = new Scanner(new File(args[0]));
        while (scanner.hasNext()) {
            Node node = new Node(scanner.next().charAt(0), scanner.nextInt());
            minHeap.insert(node);
        }
        scanner.close();

        Node root = null;
        while (minHeap.size > 1) {
            Node x = minHeap.firstElement();
            minHeap.remove();
            Node y = minHeap.firstElement();
            minHeap.remove();

            Node tmp = new Node();

            tmp.value = x.value + y.value;
            tmp.left = x;
            tmp.right = y;

            root = tmp;
            minHeap.insert(tmp);
        }
        printCode(root, " ");
    }
}
 class Node {
        int value;
        char character;
        Node left;
        Node right;

        public Node() {
        }

        public Node(char character, int value) {
            this.value = value;
            this.character = character;
        }

    }

 class MinHeap {

        Node[] arrayHeap;
        int size;
        int maxsize;


        public MinHeap(int maxsize) {
            this.maxsize = maxsize;
            this.size = 0;
            arrayHeap = new Node[this.maxsize + 1];
        }

        private int parent(int position) {
            return position / 2;
        }

        private int leftChild(int position) {
            return (2 * position);
        }

        private int rightChild(int position) {
            return (2 * position) + 1;
        }

        private boolean isLeaf(int position) {
            if (position > (size / 2) && position <= size) {
                return true;
            }
            return false;
        }


        public void insert(Node element) {
            arrayHeap[++size] = element;
            int current = size;
            while (current > 1 && (arrayHeap[current].value < arrayHeap[parent(current)].value)) {
                swap(current, parent(current));
                current = parent(current);
            }
        }


        private void swap(int fpos, int spos) {
            Node tmp = arrayHeap[fpos];
            arrayHeap[fpos] = arrayHeap[spos];
            arrayHeap[spos] = tmp;


        }


        private void minHeapify(int position) {
            if (isLeaf(position)) {
                return;
            }
            int childLeft = leftChild(position);
            int childRight = rightChild(position);

            int smallestChild = childLeft;
            if (childRight <= size && arrayHeap[childRight].value < arrayHeap[childLeft].value) {
                smallestChild = childRight;
            }
            if (arrayHeap[position].value > arrayHeap[smallestChild].value) {
                swap(position, smallestChild);
                minHeapify(smallestChild);
            }


        }

        public void remove() {
            arrayHeap[1] = arrayHeap[size--];
            minHeapify(1);
        }

        public Node firstElement() {   //peek
            return arrayHeap[1];
        }
}


