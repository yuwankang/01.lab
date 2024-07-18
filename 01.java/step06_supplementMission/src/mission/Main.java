package mission;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        String[] input = br.readLine().split(" ");
        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);
        StringBuilder sb = new StringBuilder(); 
        
        for (int i = 1; i <= x * y; i++) { 
            if (i % y == 0 && i % x == 0) { 
                sb.append(3);
            } else if (i % x == 0) { 
                sb.append(2);
            } else if (i % y == 0) { 
                sb.append(1);
            }
        }
        
        System.out.println(sb);
    }
}

