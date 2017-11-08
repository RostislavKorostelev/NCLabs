package buildings;

public interface Space extends Cloneable, Comparable<Space>{
    public int getRooms();
    public void setRooms(int rooms);
    public float getArea();
    public void setArea(float area);
    public Object clone() throws CloneNotSupportedException;
    public int compareTo(Space space);

}