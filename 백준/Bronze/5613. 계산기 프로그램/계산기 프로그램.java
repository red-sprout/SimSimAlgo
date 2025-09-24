import java.io.*;
import java.util.*;

public class Main {
    static int solution(List<String> list) {
        int sz = list.size();
        int res = Integer.parseInt(list.get(0));
        for(int i = 1; i < sz; i += 2) {
            switch(list.get(i)) {
                case "+":
                    res = res + Integer.parseInt(list.get(i + 1));
                    break;
                case "-":
                    res = res - Integer.parseInt(list.get(i + 1));
                    break;
                case "*":
                    res = res * Integer.parseInt(list.get(i + 1));
                    break;
                default:
                    res = res / Integer.parseInt(list.get(i + 1));
                    break;
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> list = new ArrayList<>();
        while(true) {
            String str = br.readLine();
            if(str.equals("=")) break;
            list.add(str);
        }
        System.out.println(solution(list));
        br.close();
    }
}
