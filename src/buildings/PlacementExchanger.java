package buildings;

import buildings.exceptions.InexchangeableFloorsException;
import buildings.exceptions.InexchangeableSpacesException;
import buildings.interfaces.Building;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class PlacementExchanger {
    public static boolean spaceExchangeTest(Space space1, Space space2){
        if (space1.getArea()==space2.getArea()&&space1.getRooms()==space2.getRooms())
            return true;
        else
            return false;
    }

    public static boolean floorExchangerTest (Floor floor1, Floor floor2){
        return (floor1.getArea()==floor2.getArea()&&floor1.getCntRooms()==floor2.getCntRooms());
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException
    {
        if (!spaceExchangeTest(floor1.getSpace(index1),floor2.getSpace(index2))){
            throw new InexchangeableSpacesException("Pomenyat' Spaces NELZYA!");
        }
        Space c = floor1.getSpace(index1);
        floor1.setSpace(index1,floor2.getSpace(index2));
        floor2.setSpace(index2,c);
    }
    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws InexchangeableFloorsException
    {
        if (!floorExchangerTest(building1.getFloor(index1),building2.getFloor(index2))){
            throw new InexchangeableFloorsException("Pomenyat' Floors Nelzya!!!");
        }
        Floor c = building1.getFloor(index1);
        building1.setFloor(index1,building2.getFloor(index2));
        building2.setFloor(index2,c);
    }
}
