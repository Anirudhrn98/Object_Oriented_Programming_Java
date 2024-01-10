//This program is linked list made by Jesus A Acosta
//Indspiration came from:
// https://www.codegrepper.com/code-examples/java/create+linked+list+in+java+without+using+collections
//This is for Questions 1 + 2 for HW5
public class LinkedListME {
    static Node head;
    static int length = 0;

    public LinkedListME() {
        this.head = new Node(null);
    }

    public class Node {
        static Object input;
        static Node next;

        public Node(Object input) {
            this.input = input;
            next = null;
        }

        public Object getInput() {
            return input;
        }

        public void setInput(Object input) {
            this.input = input;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public int size() {
        return length;
    }

    public void add(Object data) {
        Node node = new Node(data);
        Node hPoint = head;
        while (hPoint.getNext() != null) {
            hPoint = hPoint.getNext();
        }
        hPoint.setNext(node);
        length++;
    }

    public Object get(int index) {
        if (head.getNext() == null || index > length) {
            return null;
        }
        Node pHead = head.getNext();
        int counting = 0;
        while (counting < index) {
            pHead = pHead.getNext();
            counting++;
        }
        return pHead.getInput();
    }

    public int indexOf(Object input) {
        Node headP = head;
        for (int a = 0; a < length; a++) {
            headP = headP.getNext();
            if (headP.getInput().equals(input)) {
                return a;
            }
        }
        return -1;
    }

    public boolean remove(Object input) {
        if (head.getNext() == null) {
            return false;
        }
        Node header = head;
        while (header.getNext() != null) {
            if (header.getNext().getInput().equals(input)) {
                header.setNext(header.getNext().getNext());
                length--;
                return true;
            }
            header = header.getNext();
        }
        return false;
    }
}
