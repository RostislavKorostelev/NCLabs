package buildings;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;

import java.io.*;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Scanner;


public class Buildings {
    public static BuildingFactory buildingFactory = new DwellingFactory();
    //записи данных о здании в байтовый поток
    public static void outputBuilding (Building building, OutputStream out) throws IOException
    {
        DataOutputStream streamWrite = new DataOutputStream(out);
        try
        {
            streamWrite.writeInt(building.getCountFloors());
            for (int i = 0; i < building.getCountFloors(); i++)
            {
                streamWrite.writeInt(building.getFloor(i).getCnt());
                for (int j = 0; j <building.getFloor(i).getCnt() ; j++)
                {
                    streamWrite.writeInt(building.getFloor(i).getSpace(j).getRooms());
                    streamWrite.writeFloat(building.getFloor(i).getSpace(j).getArea());
                }
            }
            //streamWrite.writeChar('\n');
        }
        catch (IOException ioex )
        {
            ioex.getMessage();
        }
    }
    //чтения данных о здании из байтового потока
    public static Building inputBuilding (InputStream in) throws IOException
    {
        Building newBuilding = null;
        DataInputStream streamRead = new DataInputStream(in);
        try
        {
            int floorsCount = streamRead.readInt();
            Floor[] floors = new Floor[floorsCount];
            for (int i = 0; i < floors.length; i++) {
                floors[i] = buildingFactory.createFloor(streamRead.readInt()); //////
                for (int j = 0; j <floors[i].getCnt() ; j++) {
                    floors[i].getSpace(j).setRooms(streamRead.readInt());
                    floors[i].getSpace(j).setArea(streamRead.readFloat());
                }
            }
         newBuilding = createBuilding(buildingFactory,floors);
        }
        catch (IOException ioex)
        {
            ioex.getMessage();
        }
        return newBuilding;
    }

    //записи здания в символьный поток
    public static void writeBuilding (Building building, Writer out){
        try(PrintWriter streamWrite = new PrintWriter(out))
        {
            streamWrite.print(building.getCountFloors());
            for (int i = 0; i < building.getCountFloors(); i++)
            {
                streamWrite.print(building.getFloor(i).getCnt());
                for (int j = 0; j <building.getFloor(i).getCnt() ; j++)
                {
                    streamWrite.print(building.getFloor(i).getSpace(j).getRooms());
                    streamWrite.print(building.getFloor(i).getSpace(j).getArea());
                }

            }
            streamWrite.println();
        }
    }
    //чтения здания из символьного потока
    public static Building readBuilding (Reader in) throws IOException
    {
        Building newBuilding = null;
        try
        {
            StreamTokenizer streamTokenizer = new StreamTokenizer(in);
            while(streamTokenizer.nextToken() != streamTokenizer.TT_NUMBER){}
            Floor[] floors = new Floor[(int)streamTokenizer.nval];
            for (int i = 0; i <floors.length ; i++) {
                while(streamTokenizer.nextToken() != streamTokenizer.TT_NUMBER){}
                floors[i] =buildingFactory.createFloor((int)streamTokenizer.nval);
                for (int j = 0; j <floors[i].getCnt() ; j++) {
                    while(streamTokenizer.nextToken() != streamTokenizer.TT_NUMBER){}
                    floors[i].getSpace(j).setRooms((int)streamTokenizer.nval);
                    while(streamTokenizer.nextToken() != streamTokenizer.TT_NUMBER){}
                    floors[i].getSpace(j).setArea((float)streamTokenizer.nval);
                }
            }
            newBuilding = Buildings.createBuilding(buildingFactory,floors);
        }
        catch (IOException ioex){
            ioex.getMessage();
        }
        return newBuilding;
    }
    //сериализации зданий в байтовый поток
    public static void serializeBuilding (Building building, OutputStream out) throws IOException
    {
        ObjectOutputStream streamWrite = new ObjectOutputStream(out);
        try
        {
            streamWrite.writeObject(building);
        }
        catch (IOException ioex)
        {
            ioex.getMessage();
        }
    }

    //десериализации здания из байтового потока
    public static Building deserialaizeBuilding (InputStream in) throws IOException
    {
        Building newBuilding = null;
        ObjectInputStream streamRead = new ObjectInputStream(in);
        try
        {
            newBuilding = (Building) streamRead.readObject();
        }
        catch (IOException ioex)
        {
            ioex.getMessage();
        }
        catch (ClassNotFoundException e){}
        return newBuilding;
    }

    //Добавьте метод текстовой записи
    public static void writeBuildingFormat (Building building, Writer out){
        try(Formatter formatter = new Formatter(new PrintWriter(out))){
            formatter.format("Этажей в здании = %d ",building.getCountFloors());
            for (int i = 0; i <building.getCountFloors() ; i++) {
                formatter.format("Помещений на %d этаже равно %d ",i+1,building.getFloor(i).getCnt());
                for (int j = 0; j < building.getFloor(i).getCnt(); j++) {
                    formatter.format("Квартира %d имеет %d комнат и площадь %.2f",j,building.getFloor(i).getSpace(j).getRooms(), building.getFloor(i).getSpace(j).getArea());
                }
            }
        }
    }

    public static Building readBuilding (Scanner scanner) throws IOException
    {

        Floor[] floors = new Floor[scanner.nextInt()];
        for (int i = 0; i <floors.length ; i++) {
            floors[i] = buildingFactory.createFloor(scanner.nextInt());
            for (int j = 0; j <floors[i].getCnt() ; j++) {
                floors[i].getSpace(j).setRooms(scanner.nextInt());
                floors[i].getSpace(j).setArea(scanner.nextFloat());

            }
        }
        return Buildings.createBuilding(buildingFactory,floors);
    }

    public static <T extends Comparable<T>> void sort (T[] data){
        for (int i = 0; i <data.length ; i++) {
            for (int j = 0; j <data.length-1 ; j++) {
                if (data[j].compareTo(data[j+1])==1){
                    T tmp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = tmp;
                }

            }

        }
    }

    public static <T> void sort (T[] data, Comparator<T> comparator){
        for (int i = 0; i <data.length ; i++) {
            for (int j = 0; j <data.length-1 ; j++) {
                if (comparator.compare(data[j],data[j+1])==1){
                    T tmp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = tmp;

                }

            }

        }
    }

    public void setBuildingFactory(BuildingFactory buildingFactory){
        Buildings.buildingFactory = buildingFactory;
    }

    public static Building createBuilding(BuildingFactory buildingFactory, Floor[] floors){
        return buildingFactory.createBuilding(floors);
    }
    public static Floor createFloor(BuildingFactory buildingFactory, Space[] spaces){
        return buildingFactory.createFloor(spaces);
    }

    Floor synchronizedFloor (Floor floor){
        return new SynchronizedFloor(floor);

    }

}
