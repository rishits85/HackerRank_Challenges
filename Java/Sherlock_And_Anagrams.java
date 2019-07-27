import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        //1
        //len = 4
        int subStrLength = 1;
        int count = 0;
        while(subStrLength < s.length()){
            for(int i = 0; i+subStrLength<=s.length(); i++){
                for(int j= i + 1; j+subStrLength<=s.length(); j++){
                    String s1= s.substring(j,j+subStrLength).chars().sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,    StringBuilder::append)
                .toString();

                    String s2 = s.substring(i,i+subStrLength).chars().sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,    StringBuilder::append)
                .toString();
                    if(s1.equalsIgnoreCase(s2)){
                        count++;
                    }
                }
            }
            subStrLength++;
        }
        
        return count;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
