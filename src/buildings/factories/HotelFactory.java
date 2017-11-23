package buildings.factories;

import buildings.dwelling.Flat;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;
import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class HotelFactory implements BuildingFactory {
    public Space createSpace(float area){
        return new Flat(area);
    }

    public Space createSpace(int roomsCount, float area){
        return new Flat(area, roomsCount);
    }

    public Floor createFloor(int spacesCount){
        return new HotelFloor(spacesCount);
    }

    public Floor createFloor(Space[] spaces){
        return new HotelFloor(spaces);
    }

    public Building createBuilding(int floorsCount, int[] spacesCounts){
        return new Hotel(floorsCount, spacesCounts);
    }

    public Building createBuilding(Floor[] floors){
        return new Hotel(floors);
    }
}
