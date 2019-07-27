import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    private static final char NINE = '9';
    // Complete the highestValuePalindrome function below. 092289 4 changes? 
    //when 2 changes left and both in between are same. 
    static String highestValuePalindrome(String s, int n, int k) {
        if(s.length() == 1){
            if(k>0){
                return "9";
            }else{
                return "-1";
            }
            
        }
        StringBuilder sBuilder = new StringBuilder(s);
        
        int start = 0;
        int end = s.length() - 1;
        while(start<=end){
            // System.out.println(k);
            if(sBuilder.charAt(start)!= sBuilder.charAt(end)){
                // System.out.println("in");
                handleSingleChange(sBuilder, start, end);
                k--;
            }
            
            start++;
            end--;
           
        }

        if(k<0){
            return "-1";
        }else if(k==0){
            return sBuilder.toString();
        }else{
            start = 0;
            end = s.length() - 1;
            while(start<=end){
                if(k>0 && start == end){
                sBuilder.setCharAt(start, NINE);
                k--;
                }
                if(sBuilder.charAt(start) != NINE){
                    if(k>=2 && (sBuilder.charAt(start) == s.charAt(start) && sBuilder.charAt(end) == s.charAt(end))){
                        sBuilder.setCharAt(start,NINE);
                        sBuilder.setCharAt(end,NINE);
                        k-=2;
                    }else if(k>=1 && (sBuilder.charAt(start) != s.charAt(start) || sBuilder.charAt(end) != s.charAt(end))){
                        sBuilder.setCharAt(start,NINE);
                        sBuilder.setCharAt(end,NINE);
                        k-=1;
                    }
                }
                start++;
                end--;
                
            }

            
        }
        
        return sBuilder.toString();
    }

     private static void handleSingleChange(StringBuilder sBuilder, int start, int end){
            int startVal =  Character.getNumericValue(sBuilder.charAt(start));
            int endVal =  Character.getNumericValue(sBuilder.charAt(end));
            int max = Math.max(startVal, endVal);
            sBuilder.setCharAt(start, (char) (max + '0'));
            sBuilder.setCharAt(end, (char) (max + '0'));
        }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String s = scanner.nextLine();

        String result = highestValuePalindrome(s, n, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
