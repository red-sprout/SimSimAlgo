import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] year = {0, 2024, 2025, 2025, 2026, 2026};
        int[] month = {0, 8, 3, 10, 5, 12};
        int n = Integer.parseInt(br.readLine());
        System.out.println(year[n] + " " + month[n]);
        br.close();
    }
}
