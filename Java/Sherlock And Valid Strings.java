import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    //max>1, min>1 aaabbbcccc, abccdd
    //max=1, min>1 aaaabc, aaaabbbccc
    //max >1 min=1 aaabbbcc aabbcccddd
    //max =1, min = 1 

   
    private static final String isValid = "YES";
    private static final String isNotValid = "NO";
    // Complete the isValid function below.
    static String isValid(String s) {
        
        if(s.length() == 1){
            return isValid;
        }
        int[] charArr = new int[26];
        Arrays.fill(charArr, 0);
        for(int i= 0; i<s.length(); i++){
            int charLocation = s.charAt(i) - 'a';
            charArr[charLocation] += 1;
        }
        //aabccddeeee
        //abbbcd
        //z
       Map<Integer,Integer> map = new HashMap<>();
       int max = 0; 
       int min = 0;
        for(int i:charArr){
            if(i!=0){
                if(max==0 && min==0){
                    max=min=i;
                }
                if(map.containsKey(i)){
                    int newVal = map.get(i);
                    map.put(i, ++newVal);
                }else{
                    map.put(i,1);
                }
                
                if(map.size()>2){
                    return isNotValid;
                }

                if(i>max){
                    max = i;
                }else if(i<min){
                    min = i;
                }
            }
        }
        if(max == min){
            return isValid;
        }
        if(map.get(max)>1 && map.get(min)>1){
            return isNotValid;
        }else if(map.get(max)==1 && map.get(min)>1){
            if(max - min != 1){
                return isNotValid;
            }
        }else if(map.get(max) ==1 && map.get(min) == 1){
            if((max - min) != 1){
                return isNotValid;
            }
        }else if(map.get(max) > 1 && map.get(min) == 1){
            if(min!=1){
                return isNotValid;
            }
        }else if(map.get(min) > 1 && map.get(max)==1){
            if((max-min)!= 1){
                return isNotValid;
            }
        }
        

        return isValid;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
