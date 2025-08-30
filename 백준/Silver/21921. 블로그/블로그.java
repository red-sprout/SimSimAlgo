import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n, x, sum = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            if(i > x - 1) {
                sum -= arr[i - x];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            } else if(i == x - 1) {
                map.put(sum, 1);
            }
        }

        if(map.lastKey() == 0) {
            System.out.println("SAD");
        } else {
            Map.Entry<Integer, Integer> entry = map.lastEntry();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        
        br.close();
    }
}
