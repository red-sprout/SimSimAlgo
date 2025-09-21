import java.io.*;
import java.util.*;

public class Main {
    static Map<Character, int[]> pad;
    static void init() {
        pad = new HashMap<>();
        pad.put('1', new int[] {1, 1});
        pad.put('2', new int[] {1, 2});
        pad.put('3', new int[] {1, 3});
        pad.put('4', new int[] {2, 1});
        pad.put('5', new int[] {2, 2});
        pad.put('6', new int[] {2, 3});
        pad.put('7', new int[] {3, 1});
        pad.put('8', new int[] {3, 2});
        pad.put('9', new int[] {3, 3});
        pad.put('0', new int[] {4, 2});
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }

    static String itos(int i) {
        return String.valueOf(i);
    }

    static int effort(String str) {
        int res = 0;
        for(int i = 0; i < str.length() - 1; ++i) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(i + 1);
            int[] p1 = pad.get(c1);
            int[] p2 = pad.get(c2);
            res += Math.abs(p2[0] - p1[0]) + Math.abs(p2[1] - p1[1]);
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        init();
        st = new StringTokenizer(br.readLine(), ":");
        String hour = st.nextToken();
        String minute = st.nextToken();

        List<String> hList = new ArrayList<>();
        List<String> mList = new ArrayList<>();

        int ih = stoi(hour);
        int im = stoi(minute);

        hList.add(hour);
        for(int i = 1; ; ++i) {
            if(ih + 24 * i > 99) break;
            hList.add(itos(ih + 24 * i));
        }

        mList.add(minute);
        for(int i = 1; ; ++i) {
            if(im + 60 * i > 99) break;
            mList.add(itos(im + 60 * i));
        }

        int val = 1_000_000_000;
        String resH = hour, resM = minute;
        for(String h : hList) {
            for(String m : mList) {
                int tot = effort(h + m);
                if(val > tot) {
                    val = tot;
                    resH = h;
                    resM = m;
                }
            }
        }

        System.out.println(resH + ":" + resM);
        br.close();
    }
}
