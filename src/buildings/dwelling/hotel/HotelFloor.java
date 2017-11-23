package buildings.dwelling.hotel;

import buildings.interfaces.Space;
import buildings.dwelling.DwellingFloor;

public class HotelFloor extends DwellingFloor{
    private static int DEFAULT_STARS = 1;
    private int stars;


    public int getStars(){
        return this.stars;
    }

    public void setStars(int stars){
        this.stars = stars;
    }

    public HotelFloor(int count) {
        super(count);
        stars = DEFAULT_STARS;
    }

    public HotelFloor(Space... flats) {
        super(flats);
        stars = DEFAULT_STARS;
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer(this.getClass().getSimpleName());
        sb.append("(").append(getStars()).append(", ").append(this.getCnt()).append(", ");
        for (Space flat: this.getSpaces()){
            sb.append(flat.toString()).append(" ");
        }
        return sb.append(")").toString();
    }

    @Override
    public boolean equals(Object object){
        if (object == this)
            return true;

        if (object == null)
            return false;
        if (!(object instanceof HotelFloor))
            return false;
        HotelFloor floor = (HotelFloor) object;

        return (floor.equals(object) && this.getStars() == floor.getStars() );
    }

    @Override
    public int hashCode(){
        int hash = this.getCntRooms();
        for (Space space:getSpaces()){
            hash ^=space.hashCode();
            hash ^=getStars();
        }
        return hash;
    }
}
