package buildings.office;


import buildings.InvalidRoomsCountException;
import buildings.InvalidSpaceAreaException;
import buildings.Space;

import java.io.Serializable;


public class Office implements Space, Serializable, Cloneable{
    private float area;
    private int rooms;

    public Office(){
        this.area = 250;
        this.rooms = 1;
    }

    public Office(float area){
        if (area <0) throw new InvalidSpaceAreaException("Area should be positive");
        this.area = area;
        this.rooms = 1;
    }

    public  Office(float area, int rooms){
        if (area <0) throw new InvalidSpaceAreaException ("Area should be positive");
        if (rooms<0) throw new InvalidRoomsCountException("Rooms count chouldbe positive!");
        this.rooms = rooms;
        this.area = area;
    }

    public int getRooms(){
        return rooms;
    }


    public void setRooms(int rooms){
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
        if (!(object instanceof Office)){
            return false;
        }
        Office office = (Office) object;
        return (this.getArea()==office.getArea()&&this.getRooms()==office.getRooms());

    }
    public int hashCode(){
        return this.getRooms()^Double.valueOf(this.area).hashCode();
    }
    public Object clone() throws CloneNotSupportedException {
        return (Office) super.clone();
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
