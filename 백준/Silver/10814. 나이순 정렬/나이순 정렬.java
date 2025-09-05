import java.io.*;
import java.util.*;

public class Main {
    static class Member implements Comparable<Member> {
        int age, order;
        String name;

        Member(int age, int order, String name) {
            this.age = age;
            this.order = order;
            this.name = name;
        }

        @Override
        public int compareTo(Member m) {
            return this.age == m.age ? Integer.compare(this.order, m.order) : Integer.compare(this.age, m.age);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] cnt = new int[201];
        List<Member> members = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            int order = ++cnt[age];
            String name = st.nextToken();
            members.add(new Member(age, order, name));
        }

        Collections.sort(members);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; ++i) {
            sb.append(members.get(i).age).append(" ").append(members.get(i).name).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}
