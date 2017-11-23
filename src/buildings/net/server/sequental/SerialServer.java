package buildings.net.server.sequental;

import buildings.exceptions.BuildingUnderArrestException;
import buildings.interfaces.Building;
import buildings.Buildings;
import buildings.dwelling.Dwelling;
import buildings.dwelling.hotel.Hotel;
import buildings.office.OfficeBuilding;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SerialServer {
    public static void main(String[] args) {

        System.out.println("Server starts");
        try {
            ServerSocket serverSocket = new ServerSocket(1993);
            Socket socketClient = serverSocket.accept();
            System.out.println("Client connected");
            ObjectOutputStream out = new ObjectOutputStream(socketClient.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socketClient.getInputStream());
            int type = in.readInt();
            Thread.sleep(2000);
            Building building = Buildings.deserialaizeBuilding(in);

            System.out.println(Cost(type ,building));
            out.writeFloat(Cost(type ,building));
            out.flush();
            in.close();
            out.close();
            socketClient.close();
        } catch (IOException e) {
            e.getMessage();
        }
        catch (InterruptedException e){
            e.getMessage();
        }
    }


    public static float Cost(int type, Building building) {
        double r = Math.random();
        try {
            if (r > 0.9) {
                throw new BuildingUnderArrestException("Building is under arrest ");
            }
            float res = -1;

            if (type == 0) {
                res = 1000 * ((Dwelling) building).getAreaSpaces();
                return res;
            }
            if (type == 1) {
                res = 1500 * ((OfficeBuilding) building).getAreaSpaces();
                return res;
            }
            if (type == 2) {
                res = 2000 * ((Hotel) building).getAreaSpaces();
                return res;
            }
            System.out.println(res);


            return -1;
        }
        catch (BuildingUnderArrestException e){
            return -1;
        }
    }
}
