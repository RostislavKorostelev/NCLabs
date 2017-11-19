package buildings.net.server.parallel;

import buildings.Building;
import buildings.Buildings;
import buildings.dwelling.Dwelling;
import buildings.dwelling.hotel.Hotel;
import buildings.office.OfficeBuilding;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NewConnection implements Runnable {

    private static Socket socketClient;

    public NewConnection(Socket client) {
        NewConnection.socketClient = client;
    }

    @Override
    public void run(){


            try {

                System.out.println();
                System.out.println("New client connected");
                DataOutputStream out = new DataOutputStream(socketClient.getOutputStream());
                DataInputStream in = new DataInputStream(socketClient.getInputStream());
                int type = in.readInt();
                Thread.sleep(2000);
                Building building = Buildings.inputBuilding(in);


                out.writeFloat(Cost(type ,building));
                out.flush();
                System.out.println("Cost sent");
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
        if (r>0.9){

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

