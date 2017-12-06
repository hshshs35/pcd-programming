import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {



    public static void main(String[] args){

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable(){
            public void run(){
                System.out.println("all the tasks ended");
            }
        });

        for(int i = 0; i < 5; i++){
            executorService.execute(new BarrierWorker(i, barrier));
        }

        executorService.shutdown();

        System.out.println("end of main");



    }

}

class BarrierWorker implements Runnable{
    private int id;
    private Random random = new Random();
    private CyclicBarrier cyclicBarrier;

    public BarrierWorker(int id, CyclicBarrier cyclicBarrier){
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
    }
    public void run(){
        System.out.println("Thread " + id + " is working");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread " + id + " is waiting");

        try {
            cyclicBarrier.await();
            System.out.println("Thread " + id + " finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public String toString(){
        return "Thread " + id;
    }
}
