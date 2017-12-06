import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    enum Downloader{
        INSTANCE;

        private Semaphore semaphore = new Semaphore(3, true);

        private void donwloadData(){
            try{
                semaphore.acquire();
                download();
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }

        private void download() {
            System.out.println("downloading data from web....");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args){

        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i = 0; i < 12; i++){
            executorService.execute(new Runnable(){
                public void run(){
                    Downloader.INSTANCE.donwloadData();
                }
            });
        }
    }
}
