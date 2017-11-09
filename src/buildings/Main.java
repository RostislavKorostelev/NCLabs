package buildings;

import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;
import buildings.threads.*;

public class Main {
	public static void main(String [] argv)
	{
		Floor floors[] = new Floor[20];
		for(int j = 0; j < 20; j++)
		{
			Space flats[] = new Space[5+j];
			for(int i = 0; i < (5+j); i++)
			{
				flats[i] = new Flat(50+i*j, 2+i*j);
			}
			floors[j] = new DwellingFloor(flats);
		}
//		Dwelling dwelling = new Dwelling(floors);
//		dwelling.addSpace(3, new Flat(1000, 20));
//		System.out.printf("The best space of flat is %.2f with %d rooms at home with %d floors\n", dwelling.getBestSpace().getArea(), dwelling.getBestSpace().getRooms(), floors.length);
//		dwelling.delSpace(3);
//		OfficeFloor officeFloor = new OfficeFloor(5);
//		System.out.println(officeFloor.getBestSpace().getArea());
//		int [] ar = {1, 2, 3, 1};
//		OfficeBuilding officeBuilding = new OfficeBuilding(4, ar);
//		officeBuilding.addSpace(2,new Office(3, 400));
//		System.out.println(officeFloor.getBestSpace());
//		System.out.println(dwelling.getSpace(2));
//		System.out.println(officeBuilding.getSpace(2));
//		System.out.println(officeBuilding.getBestSpace().getArea());
//		Space tmp[] = officeBuilding.getSortSpaces();
//		for(int i = 0; i < tmp.length; i++)
//			System.out.println(i+" "+tmp[i].getArea());
//		Office f = new Office(20,30);
//		dwelling.addSpace(2,f);
//		System.out.println(dwelling.getSpace(2));
//		System.out.println(dwelling.getSpace(1));
//		System.out.println(dwelling.getFloor(1));
//		System.out.println(officeBuilding.getFloor(1));
//		System.out.println(dwelling);
//		System.out.println(officeBuilding);
//		System.out.println(officeFloor.getCnt());
//
		Office f4 = new Office(1,1);
		System.out.println(f4);
		try{
			Office f5 = (Office)f4.clone();
			System.out.println(f5);
			f4.setArea(65);
			System.out.println(f4);
			System.out.println(f5);
		}
		catch (CloneNotSupportedException e) {
			System.out.println("2213");
		}


		OfficeFloor f1 = new OfficeFloor(4);

		System.out.println(f1.getCnt());
		try{
			OfficeFloor f2 = (OfficeFloor) f1.clone();
			System.out.println(f1);
			f1.setSpace(3, new Office(99,14));
			System.out.println(f1);
			System.out.println(f2);
		}
		catch (CloneNotSupportedException e){
			System.out.println("2213");
		}
		Hotel hf = new Hotel(4,1,2,3,4);
		HotelFloor f = new HotelFloor(2);
		f.setStars(2);



		Hotel hhf = new Hotel(4,1,2,3,4);
		HotelFloor ff = new HotelFloor(5);
		ff.setStars(4);


		System.out.println(f.equals(ff));
        Semaphore s = new Semaphore();
		SequentalRepairer r = new SequentalRepairer(ff,s);
        Thread t = new Thread(r);
        t.start();
		SequentalCleaner c = new SequentalCleaner(ff,s);
        Thread t1 = new Thread(c);
        t1.start();


//		System.out.println("test");
//		Buildings building = new Buildings();
//		Floor floor = building.synchronizedFloor(f1);
//		for (Space space:floor) {
//			System.out.println(space);
//		}
	}

}