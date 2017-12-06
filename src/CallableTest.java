import java.util.*;
import java.util.concurrent.*;

public class CallableTest {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Future<String>> list = new ArrayList<>();

        for(int i = 0; i < 5; i++)
            list.add(executorService.submit(new Processor(i+1)));

        for(Future<String> future : list){
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();


    }
}

class Processor implements Callable<String> {

    private int id;

    public Processor(int id){
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Id: " + id;
    }
}
