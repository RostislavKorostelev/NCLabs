package buildings.threads;

public class Semaphore {

    private boolean mark = false;

    public synchronized void enter() throws InterruptedException{
        this.mark = true;
        this.notify();
    }

    public synchronized void leave() throws InterruptedException{
        while(!this.mark)
            wait();
        this.mark = false;
        this.notify();
    }
}
