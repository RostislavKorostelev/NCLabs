package buildings.dwelling;

import buildings.exceptions.InvalidRoomsCountException;
import buildings.exceptions.InvalidSpaceAreaException;
import buildings.interfaces.Space;

import java.io.Serializable;

public class Flat implements Space, Serializable, Cloneable {
    private float area;
    private int rooms;

    public Flat() {
        this.area = 50;
        this.rooms = 2;
    }

    public Flat(float area) {
        if (area <0) throw new InvalidSpaceAreaException("Area should be positive");
        this.area = area;
        this.rooms = 2;
    }

    public Flat(float area, int rooms) {
        if (area <0) throw new InvalidSpaceAreaException ("Area should be positive");
        if (rooms<0) throw new InvalidRoomsCountException("Rooms count chouldbe positive!");
        this.area = area;
        this.rooms = rooms;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        if (rooms < 0) throw new InvalidRoomsCountException("Rooms count should be positive");
        this.rooms = rooms;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        if (area<0) throw new InvalidSpaceAreaException("Area can't be negative ");
        this.area = area;
    }

    //Flat (3, 55.0)
    public String toString()
    {
        StringBuffer sb = new StringBuffer(this.getClass().getSimpleName());
        sb.append(" (").append(this.getRooms()).append(", ").append(this.getArea()).append(")");
        return sb.toString();
    }

    public boolean equals(Object object){
        if (object == this){
            return true;
        }
        if (object == null)
            return false;
        if (!(object instanceof Flat)){
            return false;
        }
        Flat flat = (Flat) object;
        return (this.getArea()==flat.getArea()&&this.getRooms()==flat.getRooms());
    }

    public int hashCode(){
        return this.getRooms()^Double.valueOf(this.area).hashCode();
    }

    public Object clone() throws CloneNotSupportedException {
        return (Space) super.clone();
    }

    @Override
    public int compareTo(Space space){
        if (this.getArea()>space.getArea()){
            return 1;
        }
        if (this.getArea()<space.getArea())
            return -1;
        return 0;
    }
}