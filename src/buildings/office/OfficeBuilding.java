package buildings.office;

import buildings.*;
import buildings.exceptions.FloorIndexOutOfBoundsException;
import buildings.exceptions.SpaceIndexOutOfBoundsException;
import buildings.interfaces.Building;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

import java.io.Serializable;
import java.util.Iterator;

public class OfficeBuilding implements Building, Serializable, Cloneable {
    class Node implements Serializable, Cloneable {
        Node next;
        Node prev;
        Floor data;
        public Object clone()throws CloneNotSupportedException{
            return (Node)super.clone();
        }
    }

    private Node head;



    private Node getNode(int n) {
        if (head == null) {
            return null;
        }
        Node tmp = head.next;
        int i = 0;
        while (tmp != head) {
            if (i == n) {
                return tmp;
            }
            tmp = tmp.next;
            i++;
        }
        return null;
    }

    private void addNote(int n, Node node) {
        if (n == 0 || head == null) {
            if (head == null) {
                head = new Node();
                head.next = head;
                head.prev = head;
                node.next = head.next;
                node.prev = head;
                head.next.prev = node;
                head.next = node;
            } else {
                node.next = head.next;
                node.prev = head;
                head.next.prev = node;
                head.next = node;
            }
        } else {
            Node tmp = this.getNode(n - 1);
            node.next = tmp.next;
            node.prev = tmp;
            tmp.next.prev = node;
            tmp.next = node;
        }
    }

    private void delNode(int n) {
        if (head == null) {
            return;
        }
        if (n == 0) {
            head.next = head.next.next;
            head.next.prev = head;
        } else {
            Node tmp = this.getNode(n - 1);
            tmp.next = tmp.next.next;
            tmp.next.prev = tmp;
        }
    }

    public OfficeBuilding(int n, int...cnt) {
        if  (n<0) throw new FloorIndexOutOfBoundsException("n should be positive!");
        for (int i = 0; i < n; i++) {
            Node tmp = new Node();
            tmp.data = new OfficeFloor(cnt[i]);
            this.addNote(i, tmp);
        }
    }

    public OfficeBuilding(Floor...floors) {
        for (int i = 0; i < floors.length; i++) {
            Node tmp = new Node();
            tmp.data = floors[i];
            this.addNote(i, tmp);
        }
    }

    public int  getCountFloors() {  //метод получения общего количества этажей здания.

        if (head == null || head.next == head) {
            return 0;
        }
        int i = 0;
        Node tmp = this.getNode(i);
        if (tmp != null){
            while (tmp.next!=head){
                tmp = this.getNode(i);
                i++;
            }
        }
        return i;
    }

    public int getCountSpaces(){ //метод получения общего количества офисов здания.
        if (head == null || head.next == head){
            return 0;
        }
        int i = 0;
        Node tmp = this.getNode(i);
        if (tmp != null){
            i = tmp.data.getCnt();
            while (tmp.next!=head){
                tmp = tmp.next;
                i+=tmp.data.getCnt();
            }
        }
        return i;
    }

    public float getAreaSpaces(){ //метод получения общей площади помещений здания.
        if (head == null || head.next == head){
            return 0;
        }
        float res = this.getNode(0).data.getArea();
        for (int i = 0; i<this.getCountFloors();i++){
            res+=this.getNode(i).data.getArea();
        }
        return res;
    }

    public int getCountRooms(){ //метод получения общего количества комнат здания.
        if (head == null || head.next == head){
            return 0;
        }
        int res = this.getNode(0).data.getCntRooms();
        for (int i = 0; i<this.getCountFloors();i++){
            res+=this.getNode(i).data.getCntRooms();
        }
        return res;
    }

    public Floor[] getFloors(){ // метод получения массива этажей офисного здания
        if (head == null || head.next == head){
            return null;
        }
        Floor[] res = new Floor[this.getCountFloors()];
        for (int i = 0; i<res.length; i++){
            res[i] = this.getNode(i).data;
        }
        return res;
    }


    public void setFloor (int n, Floor floor){ //метод изменения этажа по его номеру в здании и ссылке на обновленый этаж.
        if  (n<0) throw new FloorIndexOutOfBoundsException ("n should be positive!");
        if  (n>this.getCountFloors()) throw new FloorIndexOutOfBoundsException ("n not found!");

        if (head == null || head.next == head) {
            return;
        }
        this.getNode(n).data = floor;
    }

    public Floor getFloor(int n){  //метод получения объекта этажа, по его номеру в здании.
        if  (n<0) throw new FloorIndexOutOfBoundsException ("n should be positive!");
        if  (n>this.getCountFloors()) throw new FloorIndexOutOfBoundsException ("n not found!");
        if (head == null || head.next == head) {
            return null;
        }
        return this.getNode(n).data;
    }


    public Space getSpace(int n){ //метод получения объекта офиса по его номеру в офисном здании.
        if  (n<0) throw new SpaceIndexOutOfBoundsException("n should be positive!");
        if  (n>this.getCountSpaces()) throw new SpaceIndexOutOfBoundsException ("n not found!");
        int d = 0;
        for (int i = 0; i<this.getCountFloors(); i++)
        {
            for (int j = 0; j<this.getFloor(i).getCnt();j++){
                if (n ==(d+j)){
                    return this.getFloor(i).getSpace(j);
                }

            }
            d+=this.getFloor(i).getCnt();
        }
        return null;
    }

    public  void setSpace(int n, Space office){//метод изменения объекта офиса по его номеру в доме и ссылке типа офиса.
        if  (n<0) throw new SpaceIndexOutOfBoundsException ("n should be positive!");
        if  (n>this.getCountSpaces()) throw new SpaceIndexOutOfBoundsException ("n not found!");
        int d = 0;
        for (int i = 0; i<this.getCountFloors(); i++){
            for (int j = 0; j<this.getFloor(i).getCnt();j++){
                if (n ==(d+j)){
                    this.getFloor(i).setSpace(j, office);
                }
                d+=this.getFloor(i).getCnt();
            }
        }
    }

    public  void addSpace(int n, Space office){ //метод добавления офиса в здание по номеру офиса в здании и ссылке на офис
        if  (n<0) throw new SpaceIndexOutOfBoundsException ("n should be positive!");
        if  (n>this.getCountSpaces()) throw new SpaceIndexOutOfBoundsException ("n not found!");
        int d = 0;
        for (int i = 0; i<this.getCountFloors(); i++){
            for (int j = 0; j<this.getFloor(i).getCnt();j++){
                if (n ==(d+j)){
                    this.getFloor(i).addSpace(j, office);
                }
                d+=this.getFloor(i).getCnt();
            }
        }
    }
    public  void delSpace(int n){  //метод удаления офиса по его номеру в здании.
        if  (n<0) throw new SpaceIndexOutOfBoundsException ("n should be positive!");
        if  (n>this.getCountSpaces()) throw new SpaceIndexOutOfBoundsException ("n not found!");
        int d = 0;
        for (int i = 0; i<this.getCountFloors(); i++){
            for (int j = 0; j<this.getFloor(i).getCnt();j++){
                if (n ==(d+j)){
                    this.getFloor(i).delSpace(j);
                }
                d+=this.getFloor(i).getCnt();
            }
        }
    }

    public Space getBestSpace(){
        Space max = this.getSpace(0);
        for (int i = 1; i<this.getCountSpaces(); i++){
            System.out.println(this.getSpace(i));
            if (max.getArea()<this.getSpace(i).getArea()){
                max = this.getSpace(i);
            }
        }
        return max;
    }

    public Space[] getSortSpaces()
    {
        Space tmp[] = new Space[this.getCountSpaces()];
        for(int i =0; i<tmp.length; i++)
        {
            tmp[i] = this.getSpace(i);
        }
        for(int i=0; i<tmp.length; i++)
            for(int j=0; j<tmp.length-1; j++)
            {
                if(tmp[j].getArea()<tmp[j+1].getArea())
                {
                    Space tmp_f = tmp[j];
                    tmp[j] = tmp[j+1];
                    tmp[j+1] = tmp_f;
                }

            }
        return tmp;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());
        sb.append("(").append(this.getCountFloors()).append(", ");
        for (Floor fl: this.getFloors()){
            sb.append(fl.toString()).append(" ");
        }
        return sb.append(")").toString();
    }

    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (!(object instanceof OfficeBuilding))
            return false;
        OfficeBuilding building = (OfficeBuilding) object;
        if (building.getCountFloors() != this.getCountFloors())
            return false;
        for (int i = 0; i < this.getCountFloors(); i++) {
            if (!this.getFloor(i).equals(building.getFloor(i)))
                return false;

        }
        return true;
    }
    public int hashCode(){
        int hash = this.getCountFloors();
        for (Floor floor:this.getFloors()){
            hash ^=floor.hashCode();
        }
        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        OfficeBuilding building = (OfficeBuilding)super.clone();
        Floor tfloors[] = this.getFloors().clone();

        return building;
    }

    @Override
    public Iterator<Floor> iterator(){
        return new FloorIterator(new OfficeBuilding(getFloors()));
    }
}
