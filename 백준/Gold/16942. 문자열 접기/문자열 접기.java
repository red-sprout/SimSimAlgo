import java.io.*;
import java.util.*;

public class Main {

    static int solution(String s) {
        List<Integer>[] idxListArr = new List[26];
        for(int i = 0; i < 26; ++i) {
            idxListArr[i] = new ArrayList<>();
        }

        for(int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            idxListArr[c - 'A'].add(i);
        }

        int res = 1;
        for(char c = 'A'; c <= 'Z'; ++c) {
            List<Integer> idxList = idxListArr[c - 'A'];

            if(idxList.isEmpty()) continue;

            int idx = idxList.get(0);
            int curRes = 1;
            for(int i = 1; i < idxList.size(); ++i) {
                int nidx = idxList.get(i);
                if(((nidx - idx) & 1) != 0) {
                    idx = nidx;
                    ++curRes;
                }
            }

            res = Math.max(res, curRes);
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(solution(br.readLine()));
        br.close();
    }
}
