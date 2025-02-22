import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int max = arr[n - 1];
        int res = 0;
        for(int i = n - 2; i >= 0; i--) {
            int val = Math.min(arr[i], max - 1);
            res += arr[i] - val;
            max = val;
        }

        System.out.println(res);
        br.close();
    }
}