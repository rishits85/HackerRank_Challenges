import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    
    // Complete the activityNotifications function below. [2 3 4 2 3 6 8 4 5] d=5
    // 
    static int activityNotifications(int[] expenditure, int d) {
        int[] indexCount = new int[201];
        int notifications = 0;
        int indexToRemove = -1;

        for(int i=0; i<d; i++){
            indexCount[expenditure[i]] += 1;
        }

        

        int[] nextIndexes = calculateNextIndexes(indexCount);
        for(int i =d; i<expenditure.length; i++){
    
            float median = findMedian(d, nextIndexes, indexCount);
            if(expenditure[i] >= 2*median){
                notifications++;
            }
            indexToRemove++;
            indexCount[expenditure[indexToRemove]] -=1;
            indexCount[expenditure[i]] +=1;
            
            nextIndexes = calculateNextIndexes(indexCount);
        }
        return notifications;
    }

    private static float findMedian(int d, int[] nextIndexes, int[] indexCount){
        float median = 0;
        if(d%2!=0){
            for(int i = 0; i< nextIndexes.length; i++){
                if(nextIndexes[i] - indexCount[i] <= (d/2)){
                    median = i;
                }
            }
        }else{
            float lowMedian = 0;
            float highMedian = 0;
            for(int i = 0; i< nextIndexes.length; i++){
                if(nextIndexes[i] - indexCount[i] <= (d/2-1)){
                    lowMedian = i;
                }

                if(nextIndexes[i] - indexCount[i] <= (d/2)){
                    highMedian = i;
                }
            }
            median = (lowMedian + highMedian)/2;
        }
        return median;
    }

    private static int[] calculateNextIndexes(int[] indexCount){
        int[] nextIndexes = indexCount.clone();
        for(int i=1; i<indexCount.length; i++){
            nextIndexes[i] = nextIndexes[i] + nextIndexes[i-1];
        }
        return nextIndexes;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
