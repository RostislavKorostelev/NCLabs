package buildings.interfaces;

public interface Floor extends Comparable <Floor>, Iterable<Space> {
    public int getCnt();
    public float getArea();
    public int getCntRooms();
    public Space[] getSpaces();
    public Space getSpace(int n);
    public void setSpace (int n);
    public void setSpace (int n, Space space);
    public void addSpace (int n, Space space);
    public void delSpace (int n);
    public Space getBestSpace();
    public Object clone() throws CloneNotSupportedException;

}
