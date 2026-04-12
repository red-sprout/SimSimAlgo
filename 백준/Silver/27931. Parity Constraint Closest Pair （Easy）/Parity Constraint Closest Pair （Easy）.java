import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int evenMin = Integer.MAX_VALUE;
        int oddMin = Integer.MAX_VALUE;
        int[] arr = new int[n];
        for(int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        List<Integer> evenIdxList = new ArrayList<>();
        List<Integer> oddIdxList = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            if(arr[i] % 2 == 0) {
                evenIdxList.add(i);
            } else {
                oddIdxList.add(i);
            }
        }

        int evenSize = evenIdxList.size();
        int oddSize = oddIdxList.size();
        for(int i = 0; i < evenSize - 1; ++i) {
            int diff = arr[evenIdxList.get(i + 1)] - arr[evenIdxList.get(i)];
            evenMin = Math.min(evenMin, diff);
        }
        for(int i = 0; i < oddSize - 1; ++i) {
            int diff = arr[oddIdxList.get(i + 1)] - arr[oddIdxList.get(i)];
            evenMin = Math.min(evenMin, diff);
        }

        if(evenSize > 0 && oddSize > 0) {
            int eIdx = evenIdxList.get(0);
            int oIdx = oddIdxList.get(0);
            for(int i = 0; i < n; ++i) {
                if(arr[i] % 2 == 0) {
                    eIdx = i;
                } else {
                    oIdx = i;
                }
                int diff = Math.abs(arr[oIdx] - arr[eIdx]);
                oddMin = Math.min(oddMin, diff);
            }
        }

        evenMin = evenMin == Integer.MAX_VALUE ? -1 : evenMin;
        oddMin = oddMin == Integer.MAX_VALUE ? -1 : oddMin;
        System.out.println(evenMin + " " + oddMin);
        br.close();
    }
}