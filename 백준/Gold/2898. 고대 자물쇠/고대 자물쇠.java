import java.io.*;
import java.util.*;

public class Main {

    static class Key implements Comparable<Key> {
        List<int[]> shapeList;
        Key() { shapeList = new ArrayList<>(); }

        @Override
        public int compareTo(Key k) {
            for(int i = 0; i < l; ++i) {
                int[] s1 = this.shapeList.get(i), s2 = k.shapeList.get(i);
                if(s1[0] == s2[0] && s1[1] == s2[1]) continue;
                return s1[0] == s2[0] ? s1[1] - s2[1] : s1[0] - s2[0];
            }
            return 0;
        }

        @Override
        public boolean equals(Object key) {
            return this.compareTo((Key) key) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(shapeList);
        }
    }

    static int w, l, n, cnt;
    static int[] rt, rb;
    static TreeSet<Key> keyManager;

    static void setKey(int[] top, int[] bottom) {
        Key key1, key2, key3, key4;

        key1 = new Key();
        for(int i = 0; i < l; ++i) {
            rt[i] = w - top[i] - bottom[0];
            rb[i] = bottom[i] - bottom[0];
            key1.shapeList.add(new int[] {rt[i], rb[i]});
        }
        if(keyManager.contains(key1)) return;

        key2 = new Key();
        for(int i = 0; i < l; ++i) {
            rt[i] = w - bottom[i] - top[0];
            rb[i] = top[i] - top[0];
            key2.shapeList.add(new int[] {rt[i], rb[i]});
        }
        if(keyManager.contains(key2)) return;

        key3 = new Key();
        for(int i = l - 1; i >= 0; --i) {
            rt[i] = w - top[i] - bottom[l - 1];
            rb[i] = bottom[i] - bottom[l - 1];
            key3.shapeList.add(new int[] {rt[i], rb[i]});
        }
        if(keyManager.contains(key3)) return;

        key4 = new Key();
        for(int i = l - 1; i >= 0; --i) {
            rt[i] = w - bottom[i] - top[l - 1];
            rb[i] = top[i] - top[l - 1];
            key4.shapeList.add(new int[] {rt[i], rb[i]});
        }
        if(keyManager.contains(key4)) return;

        keyManager.add(key1);
        keyManager.add(key2);
        keyManager.add(key3);
        keyManager.add(key4);

        cnt++;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        keyManager = new TreeSet<>();

        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        int[] top = new int[l];
        int[] bottom = new int[l];
        rt = new int[l];
        rb = new int[l];
        cnt = 0;

        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < l; ++j) {
                top[j] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < l; ++j) {
                bottom[j] = Integer.parseInt(st.nextToken());
            }
            setKey(top, bottom);
        }

        System.out.println(cnt);
        br.close();
    }
}
