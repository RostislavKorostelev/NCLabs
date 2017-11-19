package buildings.net.client;

import buildings.Buildings;

import java.io.*;
import java.net.Socket;

public class SerialClient {
    public static void main(String[] args) throws IOException, InterruptedException{

        System.out.println("Serial client starts");
        Socket socket = new Socket ("localhost", 1993);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        ObjectInputStream in2 = new ObjectInputStream(new FileInputStream("typeSer.bin"));
        int type = in2.readInt();
        System.out.println(type);
        out.writeInt(type);
        in2.close();
        out.flush();
        Thread.sleep(2000);

        Buildings.serializeBuilding(Buildings.deserialaizeBuilding(new FileInputStream("buildingsSer.bin")),out);
        out.flush();
        System.out.println("Building is sent");
        Thread.sleep(3000);

        float cost = in.readFloat();
        System.out.println("Building costs "+ cost);

        Thread.sleep(1000);

    }

}
