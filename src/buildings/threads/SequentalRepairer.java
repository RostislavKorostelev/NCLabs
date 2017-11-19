package buildings.threads;

import buildings.Floor;

public class SequentalRepairer implements Runnable{
    private Floor floor;
    private Semaphore semaphore;

    public SequentalRepairer(Floor floor, Semaphore semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
    }

    @Override
    public void run(){
        for (int i = 0; i <floor.getCnt() ; i++) {
            try{
            System.out.printf("Repairing space number %d with total area %.2f square meters \n",i, floor.getSpace(i).getArea());
            semaphore.take();
            Thread.sleep(150);
            }
            catch (Exception e){
                e.getMessage();
            }
        }

    }
}
