import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[] isSK = new boolean[1001];
        isSK[1] = false; isSK[2] = true; isSK[3] = false;
        for(int i = 4; i <= n; ++i) {
            isSK[i] = !isSK[i - 1] || !isSK[i - 3];
        }
        System.out.println(isSK[n] ? "SK" : "CY");
        br.close();
    }
}