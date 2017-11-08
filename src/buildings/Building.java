package buildings;
import java.util.Iterator;

public interface Building extends Iterable<Floor>{
    public int getCountFloors();
    public int getCountSpaces();
    public float  getAreaSpaces();
    public int getCountRooms();
    public Floor[] getFloors();
    public Floor getFloor(int n);
    public void setFloor (int n, Floor floor);
    public Space getSpace (int n);
    public void setSpace (int n, Space flat);
    public void addSpace (int n, Space flat);
    public void delSpace (int n);
    public Space getBestSpace();
    public Space[] getSortSpaces();
    public Object clone() throws CloneNotSupportedException;

}
