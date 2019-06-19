package algorithms;

import java.util.Arrays;


class InSort {

    void sort(int[] input, int kElements) {
        int min = input[0];
        int max = input[0];
        int groupSize;
        for (int i = 1; i < input.length; i++) {
            if (input[i] < min) {
                min = input[i];
            }
            if (input[i] > max) {
                max = input[i];
            }
        }

        groupSize = (int) Math.ceil((max - min + 1.0) / kElements);
        Node[] nodeGroup = new Node[kElements];

        for (int element : input) {
            for (int bucketIndex = 0; bucketIndex < nodeGroup.length; bucketIndex++) {
                if (element < min + groupSize * (bucketIndex + 1)) {

                    if (nodeGroup[bucketIndex] == null || element < nodeGroup[bucketIndex]
                        .getData()) {
                        nodeGroup[bucketIndex] = new Node(element, nodeGroup[bucketIndex]);
                    } else {
                        Node current = findNodeToAddTo(element, nodeGroup[bucketIndex]);
                        current.setNext(new Node(element, current.getNext()));
                    }
                    break;
                }
            }
        }

        System.out.println("Original: " + Arrays.toString(input));

        placeBackInArray(nodeGroup, input);

        System.out.println("\nSorting with " + kElements + " groups");
        System.out.println("Min/Max: [" + min + ", " + max + "]");
        System.out.println("group size: " + groupSize);
        System.out.println("Group thresholds: \n");

        printBucketGroups(nodeGroup, groupSize, min);

        System.out.println("\n" + Arrays.toString(input));
    }

    private Node findNodeToAddTo(int number, Node head) {
        Node current = head;
        while (current.getNext() != null) {
            if (current.getData() <= number && number < current.getNext().getData()) {
                return current;
            }
            current = current.getNext();
        }
        return current;
    }

    private void printBucketGroups(Node[] nodeGroups, int groupSize, int low) {
        int oldHigh = low;
        for (int i = 0; i < nodeGroups.length; i++) {
            int high = (low + (i + 1) * groupSize);

            System.out.print(i + "[" + oldHigh +
                "-" + high + "]:");
            oldHigh = high + 1;
            Node current = nodeGroups[i];
            if (current != null) {
                while (current != null) {
                    System.out.print(current.getData());
                    if (current.getNext() != null) {
                        System.out.print(" -> ");
                    }
                    current = current.getNext();
                }
            } else {
                System.out.print((String) null);
            }
            System.out.println();
        }
    }

    private void placeBackInArray(Node[] nodeGroups, int[] array) {
        int counter = 0;
        for (Node nodeGroup : nodeGroups) {
            Node current = nodeGroup;
            while (current != null) {
                array[counter] = current.getData();
                counter++;
                current = current.getNext();
            }
        }
    }

    private class Node {

        private int data;
        private Node next;

        Node(int data, Node next) {
            this.setData(data);
            this.setNext(next);
        }

        @Override
        public String toString() {
            return "Node{" +
                "data=" + getData() +
                ", next=" + getNext() +
                '}';
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}