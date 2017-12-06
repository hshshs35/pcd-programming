import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestDrive {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(Constants.NUMBER_PHILOSOPHER);
        Chopstick[] chopsticks = new Chopstick[Constants.NUMBER_CHOPSTICK];
        Philosopher[] philosophers = new Philosopher[Constants.NUMBER_PHILOSOPHER];

        for(int i = 0; i < Constants.NUMBER_CHOPSTICK; i++)
            chopsticks[i] = new Chopstick(i);

        for(int i = 0; i < Constants.NUMBER_PHILOSOPHER; i++){
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i+1) % Constants.NUMBER_CHOPSTICK] );
        }

        for(Philosopher p : philosophers)
            executorService.execute(p);

        try {
            Thread.sleep(Constants.TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(Philosopher p : philosophers)
            p.setFull(true);


        executorService.shutdown();

        while(!executorService.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        for(Philosopher p : philosophers)
            System.out.println(p.toString() + "has eaten " + p.getEatingCounter() + " times");

    }
}
