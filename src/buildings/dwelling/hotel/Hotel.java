package buildings.dwelling.hotel;

import buildings.Building;
import buildings.Floor;
import buildings.Space;
import buildings.dwelling.Dwelling;
import buildings.SpaceIndexOutOfBoundsException;
import buildings.dwelling.Flat;

public class Hotel extends Dwelling {
    public Hotel(int countFloors, int... cnt) {
        super(countFloors, cnt);
    }

    public Hotel(Floor...floors) {
        super(floors);
    }

    public int getStarsHotel()
    {

        HotelFloor f = (HotelFloor)this.getFloor(0);
        for (int i = 1; i <this.getCountFloors() ; i++)
        {
            if (this.getFloor(i) instanceof HotelFloor) {
                if (f.getStars() < ((HotelFloor)this.getFloor(i)).getStars()) {
                    f = (HotelFloor)this.getFloor(i);
                }
            }
        }
        return f.getStars();
    }

    @Override
    public Space getBestSpace(){
        float coef[] = {(float)0.25,(float)0.5,(float)1,(float)1.25,(float)1,5};
        Space max = null;

        for (int i = 0; i <getCountFloors() ; i++) {
            if (getFloor(i) instanceof HotelFloor) {
                for (int j = 0; j <getCountSpaces() ; j++) {
                    if (getFloor(i).getSpace(j) instanceof Flat) {
                        if (max == null)
                            max = getFloor(i).getSpace(j);
                        if (max.getArea()*coef[((HotelFloor) getFloor(i)).getStars()-1]<getFloor(i).getSpace(j).getArea()*coef[((HotelFloor) getFloor(i)).getStars()-1])
                            max = getFloor(i).getSpace(j);
                    }

                }

            }

        }
        return max;
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());
        sb.append("(").append(getStarsHotel()).append(", ").append(this.getCountFloors()).append(", ");
        for (Floor fl:this.getFloors()){
            sb.append(fl.toString()).append(" ");
        }
        return sb.append(")").toString();
    }

    @Override
    public boolean equals(Object object){
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (!(object instanceof Hotel))
            return false;
        Hotel h = (Hotel) object;

        if (h.getCountFloors()!=this.getCountFloors())
            return false;

        for (int i = 0; i <this.getFloors().length ; i++) {
            if (!this.getFloor(i).equals(h.getFloor(i)))
                return false;

        }
        return true;
    }

    public int hashCode(){
        int hash = this.getCountFloors();
        for (Floor floor:this.getFloors()){
            hash ^=floor.hashCode();
            hash ^=getStarsHotel();
        }
        return hash;
    }

}
