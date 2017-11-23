package buildings.dwelling;


import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.exceptions.SpaceIndexOutOfBoundsException;
import buildings.SpaceIterator;

import java.io.Serializable;
import java.util.Iterator;

public class DwellingFloor implements Floor, Serializable, Cloneable, Iterable<Space> {

        private Space flats[];

        public DwellingFloor(int count)
        {
            if (count <0 ) throw new SpaceIndexOutOfBoundsException("n should be positive!");
            flats = new Space [count];
            for(int i=0; i<count; i++)
            {
                flats[i] = new Flat();
            }
        }

        public DwellingFloor(Space...flats)
        {
            this.flats = new Space[flats.length];
            for(int i=0; i<flats.length; i++)
            {
                this.flats[i]=flats[i];
            }
        }

        public int getCnt()                  //количество квартир на этаже
        {
            return flats.length;
        }

        public float getArea()					//общая площадь квартир этажа
        {
            float c = 0;
            for (int i=0; i<flats.length; i++)
            {
                c+=flats[i].getArea();
            }
            return c;
        }

        public int getCntRooms()				//общее количество комнат этажа
        {
            int c = flats[0].getRooms();
            for (int i=1; i<flats.length; i++)
            {
                c+=flats[i].getRooms();
            }
            return c;
        }

        public Space[] getSpaces()				//метод получения массива квартир этажа
        {
            return flats;
        }


        public Space getSpace(int n)				//метод получения объекта квартиры по номеру на этаже
        {
            if (n<0) throw new SpaceIndexOutOfBoundsException ("n should be positive!");
            else if (n<= flats.length)
            return flats[n];
            else throw new SpaceIndexOutOfBoundsException("This number not found");
        }

        public void setSpace (int n){
            if (n<0) throw new SpaceIndexOutOfBoundsException ("n should be positive!");
            else if (n<=flats.length)
            flats[n] = new Flat();
            else throw new SpaceIndexOutOfBoundsException("This number not found");
        }

        public void setSpace(int n, Space space)		//метод изменения квартиры по ее номеру на этаже
        {
            if (n<0) throw new SpaceIndexOutOfBoundsException ("n should be positive!");
            else if (n<= flats.length)
            this.flats[n] = space;
            else throw new SpaceIndexOutOfBoundsException("This number not found");

        }

        public void addSpace(int n, Space space)		//добавление новой  квартиры на этаже
        {
            if (n<0) throw new SpaceIndexOutOfBoundsException ("n should be positive!");
            if(n>flats.length) {
                Space tmp[]= new Space[n];
                for(int i=0; i<flats.length; i++)
                {
                    tmp[i] = new Flat();
                }
                for(int i=0; i<flats.length; i++)
                {
                    tmp[i] = flats[i];
                }
                tmp[n-1] = space;
                flats = tmp;
            }
            else
            {
                Space tmp[]= new Space[flats.length + 1];
                if(n == 0) {
                    tmp[n] = space;
                    for (int i = 0; i < flats.length; i++) {
                        tmp[i+1] = flats[i];
                    }
                }
                else {
                    for (int i = 0; i < n; i++) {
                        tmp[i] = flats[i];
                    }
                    tmp[n] = space;
                    for (int i = n + 1; i < tmp.length; i++) {
                        tmp[i] = flats[i-1];
                    }
                }
                flats = tmp;
            }
        }

        public void delSpace(int n)			//удаление квартиры по номеру на этаже
        {
            if (n<0) throw new SpaceIndexOutOfBoundsException ("n should be positive!");
            else if(n<=flats.length){
            Space tmp[] = new Space[flats.length - 1];
            if(n==0)
            {
                for(int i = 0; i<flats.length-1; i++)
                {
                    tmp[i]= flats[i+1];
                }
            }
            else
            {
                for(int i = 0; i < n; i++) {
                    tmp[i] = flats[i];
                }
                for(int i = n; i < tmp.length; i++) {
                    tmp[i] = flats[i+1];
                }
            }
            flats = tmp;}
            else  if (n>flats.length) throw new SpaceIndexOutOfBoundsException ("n not found!");
        }

        public Space getBestSpace()			//наибольшая кватира по площади на этаже
        {
            Space max = flats[0];
            for (int i = 1; i<flats.length; i++)
            {
                if (max.getArea() < flats[i].getArea())
                    max = flats[i];
            }
            return max;
        }

//        DwellingFloor (3, Flat (3, 55.0), Flat (2, 48.0), Flat (1, 37.0) )
    public String toString(){
        StringBuffer sb = new StringBuffer(this.getClass().getSimpleName());
        sb.append("(").append(this.getCnt()).append(", ");
            for (Space flat: this.flats){
                sb.append(flat.toString()).append(" ");
            }
            return sb.append(")").toString();
    }

    public boolean equals(Object object){
        if (object == this)
            return true;
        if (object == null)
            return false;
        if (!(object instanceof DwellingFloor))
            return false;
        DwellingFloor floor = (DwellingFloor) object;

        return (floor.equals(object));
    }
    public int hashCode(){
        int hash = this.getCntRooms();
        for (Space space:this.flats){
            hash ^=space.hashCode();
        }
        return hash;
    }

    public Object clone() throws CloneNotSupportedException{
        DwellingFloor floor = (DwellingFloor) super.clone();
        floor.flats = this.flats.clone();
        for (int i = 0; i <flats.length ; i++) {
            floor.setSpace(i, (Space)this.getSpace(i).clone());

        }

        return floor;
    }


    public Iterator iterator(){
        return new SpaceIterator(new DwellingFloor(getSpaces()));
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

