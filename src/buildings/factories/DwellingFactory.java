package buildings.factories;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class DwellingFactory implements BuildingFactory {
    public Space createSpace(float area){
        return new Flat(area);
    }

    public Space createSpace(int roomsCount, float area){
        return new Flat(area, roomsCount);
    }

    public Floor createFloor(int spacesCount){
        return new DwellingFloor(spacesCount);
    }

    public Floor createFloor(Space[] spaces){
        return new DwellingFloor(spaces);
    }

    public Building createBuilding(int floorsCount, int[] spacesCounts){
        return new Dwelling(floorsCount, spacesCounts);
    }

    public Building createBuilding(Floor[] floors){
        return new Dwelling(floors);
    }
}
