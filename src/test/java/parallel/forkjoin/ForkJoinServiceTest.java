package parallel.forkjoin;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinServiceTest {

    public int service(int low, int high){
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinService service = new ForkJoinService(low, high);
        ForkJoinTask<Integer> result = pool.submit(service);
        Integer data = 0;
        try {
            data = result.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public int easySum(int low, int high){
        int sum = 0;
        while (low <= high){
            sum += high;
            high--;
        }
        return sum;
    }

    public void whichSumIsFaster(int low, int high){
        long serviceStart = System.currentTimeMillis();
        int serviceData = service(low, high);
        long serviceTime = System.currentTimeMillis() - serviceStart;
        System.out.println("===low : " + low + ", high : " + high + "===");
        System.out.print(serviceTime + " , serviceData : " + serviceData + "/ ");
        long easyStart = System.currentTimeMillis();
        int easyData = easySum(low, high);
        long easyTime = System.currentTimeMillis() - easyStart;
        System.out.println(easyTime + " , easyData : " + easyData);
    }

    //1位~7位的数据测试中，easy在这次测试中的表现要更好！！！
    @Test
    public void test(){
        for (int low = 0, high = 10; high <= 1000000; high += 100000){
            whichSumIsFaster(low, high);
        }
    }


}
