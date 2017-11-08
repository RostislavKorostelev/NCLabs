package buildings.office;

import buildings.Floor;
import buildings.Space;
import buildings.SpaceIndexOutOfBoundsException;
import buildings.SpaceIterator;

import java.io.Serializable;
import java.util.Iterator;

public class OfficeFloor implements Floor, Serializable, Cloneable{
    class Node implements Serializable, Cloneable
    {
        Node next;
        Space data;
    public Node(){}
        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Space getData() {
            return data;
        }

        public void setData(Space data) {
            this.data = data;
        }
        @Override
        public Object clone() throws CloneNotSupportedException{
            Node node = (Node)super.clone();

            return node;
        }

    }


        private Node head;

        public Node getHead() {
            return head;
        }

        public void setHead(Node head) {
            this.head = head;
        }

        private Node getNode(int n) {
            if (head == null) {
                return null;
            }
            int i = 0;
            Node tmp = head.next;
            while (tmp != head) {
                if (n == i) {
                    return tmp;
                }
                tmp = tmp.next;
                i++;
            }
            return null;
        }

        private void addNode(int n, Node node) {

            if (n == 0 || head == null) {
                if (head == null) {
                    head = new Node();
                    head.next = head;
                    node.next = head.next;
                    head.next = node;
                } else {
                    Node tmp = head.next;
                    node.next = tmp;
                    head.next = node;

                }
            } else {
                int i = 0;
                Node tmp = head.next;
                while (i != n - 1) {
                    tmp = tmp.next;
                    i++;
                }
                Node tmp1 = tmp.next;
                node.next = tmp1;
                tmp.next = node;
            }
        }

        private void delNode(int n) {
            if (head == null) {
                return;
            }
            if (n == 0) {
                head.next = head.next.next;
            } else {
                int i = 0;
                Node tmp = head.next;
                while (i != n) {
                    tmp = tmp.next;
                    i++;
                }
                tmp.next = tmp.next.next;
            }
        }



    public OfficeFloor (int n){
        if (n<0) throw new SpaceIndexOutOfBoundsException("n should be positive!");

        for(int i=0; i<n;i++){
            Node tmp = new Node();
            tmp.next = null;
            tmp.data = new Office();
            this.addNode(i, tmp);
        }
    }

    public OfficeFloor (Space...spaces){

        for (int i = 0; i<spaces.length; i++){
            Node tmp = new Node();
            tmp.next = null;
            tmp.data = spaces[i];
            this.addNode(i, tmp);
        }
    }

    public int getCnt(){  //общее кол-во офисов на этаже
        if (this.getHead() == null || this.getHead().next == this.getHead()){
            return 0;
        }
        int i=0;
        Node tmp = this.getNode(i);
        if (tmp != null){
            while (tmp.next!=this.getHead()){
                tmp = this.getNode(i);
                i++;
            }
        }
        return i;
    }

    public float getArea(){  //общая площадь помещений этажа
        if (this.getHead() == null || this.getHead().next == this.getHead()){
            return 0;
        }
        float res = 0;
        for (int i=0; i<this.getCnt(); i++){
            res+=this.getNode(i).data.getArea();
        }
        return res;
    }

    public int getCntRooms(){
        if (this.getHead() == null || this.getHead().next == this.getHead()){
            return 0;
        }
        int res = 0;
        for (int i=0; i<this.getCnt(); i++){
            res+=this.getNode(i).data.getRooms();
        }
        return res;
    }

    public Space[] getSpaces(){
        if (this.getHead() == null || this.getHead().next == this.getHead()){
            return null;
        }

        Space[] res = new Space[this.getCnt()];
        for (int i=0; i<res.length; i++){
            res[i]= this.getNode(i).data;
        }
        return res;
    }

    public Space getSpace(int n){
        if (n<0) throw new SpaceIndexOutOfBoundsException ("n should be positive!");
        if (n>this.getCnt()) throw new SpaceIndexOutOfBoundsException ("n not found!");
        if (this.getHead() == null || this.getHead().next == this.getHead()){
            return null;
        }

        return this.getNode(n).data;
    }

    public void setSpace (int n, Space space){
        if (n<0) throw new SpaceIndexOutOfBoundsException ("n should be positive!");
        if (n>this.getCnt()) throw new SpaceIndexOutOfBoundsException ("n not found!");
        if (this.getHead() == null || this.getHead().next == this.getHead()){
            return;
        }
        this.getNode(n).data = space;
    }
    public void setSpace (int n){
        if (n<0) throw new SpaceIndexOutOfBoundsException ("n should be positive!");
        if (n>this.getCnt()) throw new SpaceIndexOutOfBoundsException ("n not found!");
        if (this.getHead() == null || this.getHead().next == this.getHead()){
            return;
        }
        this.getNode(n).data = new Office();
    }

    public void addSpace (int n, Space office){
        if (n<0) throw new SpaceIndexOutOfBoundsException ("n should be positive!");
        if(this.getHead()==null){
            return;
        }
        Node tmp = new Node();
        tmp.next = null;
        tmp.data = office;
        this.addNode(n,tmp);
    }

    public void addSpace (Space office){
        Node node=new Node();
        node.data= office;
        this.addNode(this.getCnt(), node);
    }

    public void delSpace (int n){
        if (n<0) throw new SpaceIndexOutOfBoundsException ("n should be positive!");
        if (n>this.getCnt()) throw new SpaceIndexOutOfBoundsException ("n not found!");
        if(n<0|| this.getHead()==null){
            return;
        }
        this.delNode(n);
    }

    public Space getBestSpace(){
        if(this.getHead()==null){
            return null;
        }
        Space max = this.getSpace(0);
        for (int i = 1; i<this.getCnt(); i++){
            if (max.getArea()<this.getSpace(i).getArea()){
                max = this.getSpace(i);
            }
        }
        return max;
    }

//        OfficeFloor (3, Flat (3, 55.0), Flat (2, 48.0), Flat (1, 37.0) )

    public String toString(){
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());
        sb.append("(").append(this.getCnt()).append(", ");
        for (int i = 0; i <this.getCnt() ; i++)
        {
            sb.append(this.getSpace(i).toString()).append(" ");
        }
        return sb.append(")").toString();
    }

    public boolean equals(Object object){
        if (object == this)
            return true;
        if (object == null)
            return false;
        if (!(object instanceof OfficeFloor))
            return false;
        OfficeFloor floor = (OfficeFloor) object;
        if (this.getCnt()!=floor.getCnt())
            return false;
        for (int i = 0; i <this.getCnt() ; i++) {
            if (!this.getSpace(i).equals(floor.getSpace(i)))
                return false;

        }
        return true;
    }
    public int hashCode(){
        int hash = this.getCntRooms();
        for (Space space:this.getSpaces()){
            hash ^=space.hashCode();
        }
        return hash;
    }

    public Object clone() throws CloneNotSupportedException{
       OfficeFloor floor = (OfficeFloor) super.clone();
       floor.head = null;
       Space tmp[] = this.getSpaces();
        for (int i = 0; i <tmp.length ; i++) {
            floor.addSpace((Space)tmp[i].clone());
        }
       return floor;
    }
    @Override
    public Iterator<Space> iterator(){
        return new SpaceIterator(new OfficeFloor(this.getSpaces()));
    }

    @Override
    public int compareTo(Floor floor){
        if (this.getCnt()>floor.getCnt())
            return 1;
        if (this.getCnt()>floor.getCnt())
            return -1;
        return 0;
    }
}
