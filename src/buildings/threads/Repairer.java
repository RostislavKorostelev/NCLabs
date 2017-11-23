package buildings.threads;

import buildings.interfaces.Floor;

public class Repairer extends Thread{
    private Floor floor;

    public Repairer (Floor floor){
        this.floor=floor;
    }

    @Override
    public void run(){
        for (int i = 0; i <floor.getCnt() ; i++) {
            System.out.format("Repairing space number %d with total area %.2f square meters \n", i, floor.getSpace(i).getArea());

        }
    }
}
