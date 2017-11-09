package buildings;

import buildings.dwelling.Flat;

import java.util.Iterator;

public class SynchronizedFloor implements Floor {

    private Space spaces[];

    public SynchronizedFloor(Floor floor) {
        this.spaces = floor.getSpaces();
    }

    @Override
    public synchronized int getCnt(){
        return spaces.length;
    }

    @Override
    public synchronized float getArea(){
        float res = 0;
        for(Space all:spaces){
            res+=all.getArea();
        }
        return res;
    }

    @Override
    public synchronized int getCntRooms(){
        int res = 0;
        for (Space all:spaces){
            res+=all.getRooms();
        }
        return res;
    }

    public synchronized Space[] getSpaces(){
        return spaces;
    }

    @Override
    public synchronized Space getSpace(int n) {
        return spaces[n];
    }

    @Override
    public synchronized  void setSpace(int n) {
        spaces[n]= new Flat();
    }

    @Override
    public synchronized  void setSpace(int n, Space space) {
        spaces[n] = space;
    }

    @Override
    public synchronized  void addSpace(int n, Space space) {
        {
            if (n<0) throw new SpaceIndexOutOfBoundsException ("n should be positive!");
            if(n>spaces.length) {
                Space tmp[]= new Space[n];
                for(int i=0; i<spaces.length; i++)
                {
                    tmp[i] = new Flat();
                }
                for(int i=0; i<spaces.length; i++)
                {
                    tmp[i] = spaces[i];
                }
                tmp[n-1] = space;
                spaces = tmp;
            }
            else
            {
                Space tmp[]= new Space[spaces.length + 1];
                if(n == 0) {
                    tmp[n] = space;
                    for (int i = 0; i < spaces.length; i++) {
                        tmp[i+1] = spaces[i];
                    }
                }
                else {
                    for (int i = 0; i < n; i++) {
                        tmp[i] = spaces[i];
                    }
                    tmp[n] = space;
                    for (int i = n + 1; i < tmp.length; i++) {
                        tmp[i] = spaces[i-1];
                    }
                }
                spaces = tmp;
            }
        }
    }

    @Override
    public synchronized  void delSpace(int n) {
        Space tmp[] = new Space[spaces.length-1];
        if (n ==0){
            for (int i = 0; i <tmp.length ; i++) {
                tmp[i] = spaces[i+1];
            }
        }
        else{
            for (int i = 0; i <n ; i++) {
                tmp[i] = spaces[i];
            }
            for (int i = n; i <tmp.length ; i++) {
                tmp[i] = spaces[i+1];
            }
        }
        spaces = tmp;
    }


    @Override
    public synchronized  Space getBestSpace() {
        Space res = null;
        for (Space all:spaces){
            if (res == null)
                res = all;
            if (res.getArea() < all.getArea()){
                res = all;
            }
        }
        return  res;
    }

    @Override
    public synchronized  Object clone() throws CloneNotSupportedException {
        Floor cfloor = (Floor)super.clone();
        for (int i = 0; i < spaces.length ; i++) {
            cfloor.setSpace(i, (Space)spaces[i].clone());
        }
        return cfloor;
    }

    @Override
    public synchronized int compareTo(Floor floor)
    {
        if(this.getCnt() > floor.getCnt())
            return 1;
        if(this.getCnt() == floor.getCnt())
            return 0;
        return -1;
    }

    @Override
    public Iterator iterator(){
        return new SpaceIterator(new SynchronizedFloor(this));
    }
}
