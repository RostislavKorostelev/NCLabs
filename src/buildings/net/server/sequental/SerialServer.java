package buildings.net.server.sequental;

import buildings.Building;
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
            System.out.println(type);
            Thread.sleep(2000);
            Building building = Buildings.inputBuilding(in);


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
        if (r>=0.9){

            System.out.println("Building is under arrest ");
        }
        else{
            float res = -1;

            if (type == 0) {
                res = 1000 * ((Dwelling) building).getAreaSpaces();
            }
            if (type == 1) {
                res = 1500 * ((OfficeBuilding) building).getAreaSpaces();
            }
            if (type == 2) {
                res = 2000 * ((Hotel) building).getAreaSpaces();
            }
            System.out.println(res);
            return res;
        }
        return -1;
    }
}
