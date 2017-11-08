package buildings.dwelling;


import buildings.*;

import java.io.Serializable;
import java.util.Iterator;

public class Dwelling implements Building, Serializable, Cloneable
{
	private Floor floors[];
	public Dwelling(){}
	public Dwelling(int countFloors, int...cnt )
	{
		if (countFloors<0) throw new SpaceIndexOutOfBoundsException("n should be positive!");

		floors = new Floor[countFloors];
		for (int i=0; i<countFloors; i++)
		{
			floors[i] = new DwellingFloor(cnt[i]);
		}	
	}
	
	public Dwelling(Floor[] floors)
	{
		this.floors = new DwellingFloor[floors.length];
		for	(int i = 0; i< floors.length; i++)
		{
			this.floors[i] = floors[i];
		}
	}
	
	public int getCountFloors()		//общее количество этажей дома
	{
		return floors.length;
	}
	
	public int getCountSpaces()   // общее количество квартир дома
	{
		int c = 0;
		for(int i=0; i<floors.length; i++)
		{
			c+=floors[i].getCnt();
		}
		return c;
	}
	
	public float getAreaSpaces() // общая площадь квартир дома
	{
		float c = 0;
		for(int i=0; i<floors.length; i++)
		{
			c+=floors[i].getArea();
		}
		return c;
	}
	
	public int getCountRooms()  	//общее количество комнат дома
	{
		int c = 0;
		for(int i=0; i<floors.length; i++)
		{
			c+=floors[i].getCntRooms();
		}
		return c;
	}
	
	public Floor[] getFloors()  //получение массива этажей дома
	{
		return floors;
	}
	
	public Floor getFloor(int n)   //получение объекта этажа по номеру в доме
	{
        if (n<0) throw new FloorIndexOutOfBoundsException("n should be positive!");
        if (n>floors.length) throw new FloorIndexOutOfBoundsException ("n not found!");
		return floors[n];
	}
	
	public void setFloor (int n, Floor floor)  //изменение этажа по номеру в доме
	{
        if (n<0) throw new FloorIndexOutOfBoundsException ("n should be positive!");
        if (n>floors.length) throw new FloorIndexOutOfBoundsException ("n not found!");
		this.floors[n] = floor;
	}
	
	public Space getSpace (int n)    //получение объекта квартиры по номеру в доме
	{
        if (n<0) throw new SpaceIndexOutOfBoundsException ("n should be positive!");
        if (n>getCountSpaces()) throw new SpaceIndexOutOfBoundsException ("n not found!");
		int d = 0;
		for(int i=0; i<floors.length; i++)
		{
			for(int j=0; j<floors[i].getCnt(); j++)
			{
				if (n==(d+j))
				{
					return floors[i].getSpace(j);
				}
			}
			d+=floors[i].getCnt();
		}
		return null;
	
	}
	
	public void setSpace(int n, Space flat)  		//метод изменения объекта квартиры по ее номеру в доме и ссылке типа квартиры
	{
        if (n<0) throw new SpaceIndexOutOfBoundsException ("n should be positive!");
        if (n>getCountSpaces()) throw new SpaceIndexOutOfBoundsException ("n not found!");
		int d = 0;
		for(int i=0; i<floors.length; i++)
		{
			for(int j=0; j<floors[i].getCntRooms(); j++)
			{
				if (n==(d+j))
				{
					flat = floors[i].getSpace(j);
				}
			}
			d+=floors[i].getCntRooms();
		}
	
	}
	
	public void addSpace (int n, Space flat) 		//добавление квартиры по будущему номеру и ссылке на квартиру
	{
        if (n<0) throw new SpaceIndexOutOfBoundsException ("n should be positive!");
        if (n>getCountSpaces()) throw new SpaceIndexOutOfBoundsException ("n not found!");
		int d = 0;
		for(int i=0; i<floors.length; i++)
		{
			for(int j=0; j<floors[i].getCntRooms(); j++)
			{
				if (n==(d+j))
				{
					this.floors[i].addSpace(j, flat);
				}

			}
			d+=floors[i].getCntRooms();
		}
	}

	public void delSpace (int n)   //удалелние квартиры по номеру в доме
	{
        if (n<0) throw new SpaceIndexOutOfBoundsException ("n should be positive!");
        if (n>getCountSpaces()) throw new SpaceIndexOutOfBoundsException ("n not found!");
		int d = 0;
		for(int i=0; i<floors.length; i++)
		{
			for(int j=0; j<floors[i].getCntRooms(); j++)
			{
				if (n==(d+j))
				{
					this.floors[i].delSpace(j);
				}

			}
			d+=floors[i].getCntRooms();
		}
	}
	
	public Space getBestSpace()					//самая большая площадь по всему дому
	{
		Space max = floors[0].getBestSpace();
		for(int i = 1; i<floors.length; i++)
		{
			if(max.getArea()<floors[i].getBestSpace().getArea())
				max = floors[i].getBestSpace();
		}
		return max;
	}
	
	public Space[] getSortSpaces()  //сортировка (пузырьком)
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
		for (Floor fl:this.floors){
			sb.append(fl.toString()).append(" ");
		}
		return sb.append(")").toString();
	}

	public boolean equals(Object object){
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (!(object instanceof Dwelling))
			return false;
		Dwelling building = (Dwelling) object;
		if (building.getCountFloors()!=this.getCountFloors())
			return false;
		for (int i = 0; i <this.floors.length ; i++) {
			if (!this.getFloor(i).equals(building.getFloor(i)))
				return false;
			
		}
		return true;
	}
	public int hashCode(){
		int hash = this.getCountFloors();
		for (Floor floor:this.floors){
			hash ^=floor.hashCode();
		}
		return hash;
	}

    @Override
    public Object clone() throws CloneNotSupportedException {
        Dwelling dwelling = (Dwelling) super.clone();
        dwelling.floors = this.floors.clone();
        for (int i = 0; i <floors.length ; i++) {
            dwelling.setFloor(i,(Floor)this.getFloor(i).clone());
        }
        return floors;
    }

    @Override
    public Iterator iterator(){
		return new FloorIterator(new Dwelling(getFloors()));
	}
}