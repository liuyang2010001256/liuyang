package parallel.forkjoin;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinService extends RecursiveTask<Integer> {

    private int low;
    private int high;

    public ForkJoinService(int low, int high) {
        this.low = low;
        this.high = high;
    }

    @Override
    protected Integer compute() {
        if (low > high) return 0;
        int sum = 0;
        if (high - low <= 10){
            while (high >= low){
                sum += high;
                high --;
            }
        }else{
            int mid = (low + high)/2;
            ForkJoinService serviceLeft = new ForkJoinService(low, mid);
            ForkJoinService serviceRight = new ForkJoinService(mid + 1, high);

            ForkJoinTask<Integer> leftTask = serviceLeft.fork();
            ForkJoinTask<Integer> rightTask = serviceRight.fork();

            sum = leftTask.join() + rightTask.join();
        }
        return sum;
    }


}
