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

        InputStreamReader in2 = new InputStreamReader(new FileInputStream("1.txt"));
        int type = in2.read()-48;

        out.writeInt(type);
        in2.close();
        out.flush();
        Thread.sleep(2000);

        Buildings.serializeBuilding(Buildings.readBuilding(new InputStreamReader(new FileInputStream("in.txt"))), out);
        out.flush();
        System.out.println("Building is sent");
        Thread.sleep(3000);

        float cost = in.readFloat();
        if (cost != -1){
            System.out.println("Building costs "+ cost);
            PrintWriter printWriter = new PrintWriter(new FileOutputStream("resultSerial.txt"));
            printWriter.print(cost);
            printWriter.close();
        }

        else {
            System.out.println("Building is under arrest");
            PrintWriter printWriter = new PrintWriter(new FileOutputStream("resultSerial.txt"));
            printWriter.print("Building is under arrest");
        }

        Thread.sleep(1000);

    }

}
