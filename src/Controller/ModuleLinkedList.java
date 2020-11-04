package Controller;

import Model.SmartModule;

//We are going to store each rooms Modules inside a linked list as they are efficient, easy to access and easy to modify.
public class ModuleLinkedList {


     class Node {
        private final SmartModule module;
        private Node next;
        private Node prev;

        public Node(SmartModule module, Node next, Node prev) {
            this.module = module;
            this.next = next;
            this.prev = prev;
        }

        public SmartModule getModule() {
            return module;
        }
    }

    private Node head = null;
    private Node whereAmI;

    /**
     * Creating linked list
     */
    public ModuleLinkedList() {
        this.head = null;
        this.whereAmI = null;
    }

    /**
     * @return the head of the list
     */
    public Node getHead() {
        return head;
    }

    public Node getWhereAmI() {
        return whereAmI;
    }

    /**
     * This will set the whereAmI node
     * @param n
     * @return
     */
    public void setWhereAmI(Node n) {
        whereAmI = n;
    }

    /**
     * Adding a node to the head of the linked list
     *
     * @param module
     * @return it will return true if the addition was successful
     */
    public boolean addHead(SmartModule module) {
        if (head == null)
            head = new Node(module, null, null);
        else {
            Node temp = new Node(module, head, null);
            head.prev = temp;
            head = temp;
        }
        return true;
    }

    /**
     * My idea for this method is to build it in a way where it is kind of sorted, the elements will be bundled together by what room they are in together.
     *
     * @param module
     * @return
     */
    public boolean addModuleToList(SmartModule module) {
        if (head == null) {
            addHead(module);
            return true;
        }
        whereAmI = head;
        if (module.getLocation().equals(whereAmI.module.getLocation())) {
            addHead(module);
            return true;
        }

        while (whereAmI.module.getLocation().equals(whereAmI.next.module.getLocation()) && whereAmI.next != null) {
            whereAmI = whereAmI.next;
        }

        if (whereAmI.next == null)
            whereAmI.next = new Node(module, null, whereAmI);
        else {
            whereAmI.next = new Node(module, whereAmI.next, whereAmI);
            whereAmI.next.next.prev = whereAmI.next;
        }
        return true;
    }

    /**
     * The purpose of this method is to retrieve all the smart modules that are found in a particular room in the house.
     *
     * @param location
     */
    public void printAllElementsInRoom(String location) {
        whereAmI = head;
        while (whereAmI != null) {
            if (whereAmI.module.getLocation().equals(location)) {
                //I'll add a toString method later
                System.out.println(whereAmI.module.toString());
            }
        }
        whereAmI = head;
    }

    /**
     * If a module needs to be removed then this method will remove it from the list.
     *
     * @param module
     * @return
     */

    public boolean removeModule(SmartModule module) {
        if (head.module == module) {
            head = head.next;
            head.prev = null;
            return true;
        }
        whereAmI = head;
        while (whereAmI.next.module != module) {
            whereAmI = whereAmI.next;
        }
        whereAmI.next = whereAmI.next.next;
        whereAmI.next.prev = whereAmI;
        return true;
    }

}
