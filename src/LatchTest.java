import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LatchTest {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        CountDownLatch latch = new CountDownLatch(5);

        for(int i = 0; i < 5; i++){
            executorService.execute(new LatchWorker(i, latch));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(233);

    }
}

class LatchWorker implements Runnable{
    private int id;
    private CountDownLatch latch;

    public LatchWorker(int id, CountDownLatch latch){
        this.id = id;
        this.latch = latch;
    }

    public void run(){
        System.out.println("Thread " + id + " is working");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}
