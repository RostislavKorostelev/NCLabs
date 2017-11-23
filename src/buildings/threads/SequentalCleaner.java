package buildings.threads;

import buildings.interfaces.Floor;

public class SequentalCleaner implements Runnable {

    private Floor floor;
    private Semaphore semaphore;

    public SequentalCleaner(Floor floor, Semaphore semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
    }

    @Override
    public void run(){
        for (int i = 0; i <floor.getCnt() ; i++) {
            try{
                System.out.printf("Cleaning space number %d with total area %.2f square meters \n",i, floor.getSpace(i).getArea());
                semaphore.leave();
                Thread.sleep(150);
            }
            catch (Exception e){
                e.getMessage();
            }
        }
    }
}


