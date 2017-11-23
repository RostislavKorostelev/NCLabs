package buildings.threads;

import buildings.interfaces.Floor;

public class Cleaner extends Thread{
        private Floor floor;

        public Cleaner (Floor floor){
            this.floor=floor;
        }


        @Override
        public void run(){
            for (int i = 0; i <floor.getCnt() ; i++) {
                System.out.format("Cleaning space number %d with total area %.2f square meters \n", i, floor.getSpace(i).getArea());

            }
        }
}


