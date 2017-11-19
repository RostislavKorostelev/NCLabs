package buildings.net.client;

import buildings.Buildings;


import java.io.*;

import java.net.Socket;


public class BinaryClient {

    public static void main(String[] args) throws IOException, InterruptedException{

        System.out.println("Binary client starts");
        Socket socket = new Socket ("localhost", 1993);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());

        DataInputStream in2 = new DataInputStream(new FileInputStream("type.bin"));
        int type = in2.readInt();
        System.out.println(type);
        out.writeInt(type);
        in2.close();
        out.flush();
        Thread.sleep(2000);

        Buildings.outputBuilding(Buildings.inputBuilding(new FileInputStream("buildings.bin")),out);
        out.flush();
        System.out.println("Building is sent");
        Thread.sleep(3000);

        float cost = in.readFloat();
        System.out.println("Building costs "+ cost);

        Thread.sleep(1000);

        try{
            PrintWriter pw = new PrintWriter(new FileOutputStream("result.txt"));
            pw.print(cost);
            pw.close();
        }
        catch (IOException e){
            e.getMessage();
        }


    }
}
