import java.io.*;
import java.util.*;

public class Main {
  static int N, K;
  static long[] arr, dp;
  static Deque<Integer> deque;

  static void poll(int idx) {
    while(!deque.isEmpty()) {
      if(deque.peekFirst() >= idx - K) break;
      deque.pollFirst();
    }
  }

  static void offer(int idx) {
    while(!deque.isEmpty()) {
      int last = deque.peekLast();
      if(dp[last - 1] - arr[last] > dp[idx - 1] - arr[idx]) break;
      deque.pollLast();
    }
    deque.offerLast(idx);
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = null;

    st = new StringTokenizer(br.readLine(), " ");
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    arr = new long[N + 1];
    dp = new long[N + 1];
    deque = new ArrayDeque<>();

    for(int i = 1; i <= N; i++) {
      arr[i] = arr[i - 1] + Long.parseLong(br.readLine());
    }

    for(int i = 1; i <= N; i++) {
      poll(i); offer(i);
      int idx = deque.peekFirst();
      dp[i] = arr[i] + dp[idx - 1] - arr[idx];
      if(i <= K) dp[i] = Math.max(dp[i], arr[i]);
    }
    
    System.out.println(dp[N]);
    br.close();
  }
}