package sort;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Algorithm {

    //各种排序的平均时间复杂度

    /**
     * 冒泡 直接插入 直接选择 O(N方)
     * 堆排序 快排 归并排序 O(NlogN) 以二为底
     * 希尔排序 O(N1.3次幂)
     * 基数排序 O(nlog(r)m)
     */

    //验证待排序的序列是否为null或者没有数据
    private void vertify(int[] data, int low, int high){
        if (data == null || low > high || high >= data.length)
            throw new InvalidParameterException("data is null");
    }

    //交换排序

    // yes
    //冒泡排序  每次内循环把一个最大的数沉到最方，外循环控制沉入的位置
    public int[] sort(int[] data){
        vertify(data, 0, 0);
        for (int i = 0; i < data.length - 1; i++){
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]){
                    int tmp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tmp;
                }
            }
        }
        return data;
    }

    // yes
    //快速排序
    public void quickSort(int[] data, int low, int high){
        vertify(data, low, high);
        int middle = getMiddle(data, low, high);
        if (low < middle -1){
            quickSort(data, low, middle - 1);
        }
        if(middle + 1 < high){
            quickSort(data, middle + 1, high);
        }
    }

    private int getMiddle(int[] data, int low, int high) {
        int tmp = data[low];
        while (low < high){
            while (low < high && data[high] >= tmp) high--;
            data[low] = data[high];
            while (low < high && data[low] <= tmp) low++;
            data[high] = data[low];
        }
        data[low] = tmp;
        return low;
    }

    //插入排序

    // yes
    //直接插入排序 每次排一个数据，先假设先前的数据都是排好的
    public void directInsertSort(int[] data){
        vertify(data, 0, 1);
        int tmp;
        for (int i = 1; i < data.length; i++) {
            tmp = data[i];
            int j;
            for (j = i - 1; j >= 0 && data[j] > tmp ; j--) {
                data[j + 1] = data[j];
            }
            data[j + 1] = tmp;
        }
    }

    // yes
    //希尔排序
    public void heerSort(int[] data){
        vertify(data, 0, 0);
        double l = data.length;
        while (true){
            l = Math.ceil(l/2);
            int d = (int) l;
            for (int i = 0; i < d; i++){
                for (int j = i + d; j < data.length; j += d) {
                    int tmp = data[j];
                    int k;
                    for (k = j - d; k >= 0 && data[k] > tmp ; k -= d) {
                        data[k + d] = data[k];
                    }
                    data[k + d] = tmp;
                }
            }
            if (d <= 1) break;
        }
    }

    //选择排序

    //直接选择排序 每次找最小的放在前面
    public void directSelectSort(int[] data){
        vertify(data, 0,0);
        for (int i = 0; i < data.length; i++) {
         int postion = i;
         int tmp = data[i];
         for (int j = i + 1; j < data.length; j++) {
            if (data[j] < tmp){
                postion = j;
                tmp = data[j];
            }
         }
         data[postion] = data[i];
         data[i] = tmp;
        }
    }

    // yes
    //堆排序
    public void heapSort(int[] data){
        for (int i = 0; i < data.length - 1 ; i++) {
            buildHeap(data, data.length - 1 - i);
            swap(data, 0, data.length - 1 - i);
        }
    }

    private void swap(int[] data, int low, int high) {
        int tmp = data[low];
        data[low] = data[high];
        data[high] = tmp;
    }

    private void buildHeap(int[] data, int lastIndex) {
        for (int j = (lastIndex - 1)/2; j >= 0 ; j--) {
            int k = j;
            while (2 * k + 1 <= lastIndex){
                int bigIndex = 2 * k + 1;
                if (bigIndex + 1 <= lastIndex && data[bigIndex + 1] > data[bigIndex])
                    bigIndex ++;
                if (data[bigIndex] > data[k]){
                    swap(data, bigIndex, k);
                    k = bigIndex;
                }else{
                    break;
                }
            }
        }
    }

    // yes
    //归并排序
    public void mergeSort(int[] data, int left, int right){
        if (data == null
                || data.length == 0
                || left < 0
                || left >= right
                || right > data.length - 1) return;
        int center = (left + right)/2;
        mergeSort(data, left, center);
        mergeSort(data,center + 1, right);
        merge(data, left,center + 1, right);
    }

    private void merge(int[] data, int left, int m, int right) {
        int[] tmp = new int[data.length];
        int t = left;
        int res = left;
        int c = m;
        while (left < c && m <= right){
            if (data[left] < data[m]){
                tmp[t++] = data[left++];
            }else{
                tmp[t++] = data[m++];
            }
        }
        while (left < c){
            tmp[t++] = data[left++];
        }
        while (m <= right){
            tmp[t++] = data[m++];
        }
        while (res <= right){
            data[res] = tmp[res++];
        }
    }

    // yes
    //基数排序 按照个位十位百位排序 不适用于负数
    public void baseSort(int[] data){
        vertify(data, 0, 0);
        int time = getTime(data);
        List<List<Integer>> queue = getTenQueue();
        for (int i = 0; i < time ; i++) {
            for (int j = 0; j < data.length ; j++) {
               int d = (int) (data[j] % Math.pow(10, i + 1) / Math.pow(10, i));
                List list = queue.get(d);
                list.add(data[j]);
                queue.set(d, list);
            }
            int count = 0;
            for (int j = 0; j < queue.size() ; j++) {
                while (queue.get(j).size() > 0){
                    List<Integer> list = queue.get(j);
                    data[count++] = list.get(0);
                    list.remove(0);
                }
            }
        }
    }

    private List<List<Integer>> getTenQueue() {
        List<List<Integer>> queue = new LinkedList();
        for (int i = 0; i < 10 ; i++) {
            queue.add(new ArrayList<>());
        }
        return queue;
    }

    private int getTime(int[] data) {
        int max = data[0];
        for (int i = 1; i < data.length ; i++) {
            if (data[i] > max){
                max = data[i];
            }
        }
        int count = 0;
        while (max > 0){
            count ++;
            max /= 10;
        }
        return count;
    }


}
