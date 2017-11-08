package buildings.threads;

import buildings.Floor;

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
                System.out.format("Cleaning space number %d with total area %.2f square meters",i, floor.getSpace(i));
                semaphore.leave();
                Thread.sleep(150);
            }
            catch (Exception e){
                e.getMessage();
            }
        }
    }
}


