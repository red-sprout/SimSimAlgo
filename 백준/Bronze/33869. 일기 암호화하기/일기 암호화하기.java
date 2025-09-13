import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] table = new char[26];
        boolean[] vis = new boolean[26];
        int pos = 0;
        String w = br.readLine();
        for(int i = 0; i < w.length(); ++i) {
            char c = w.charAt(i);
            int idx = c - 'A';
            if(!vis[idx]) {
                vis[idx] = true;
                table[pos++] = c;
            }
        }

        int ptr = 0;
        while(ptr < 26) {
            if(!vis[ptr]) break;
            ptr++;
        }
        while(ptr < 26) {
            if(!vis[ptr]) table[pos++] = (char) ('A' + ptr);
            ptr++;
        }

        StringBuilder sb = new StringBuilder();
        String s = br.readLine();
        for(int i = 0; i < s.length(); ++i) {
            sb.append(table[s.charAt(i) - 'A']);
        }

        System.out.println(sb);
        br.close();
    }
}
