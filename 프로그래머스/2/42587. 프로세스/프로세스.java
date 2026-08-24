import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int n = priorities.length;
        int[] order = new int[n];
        Queue<Integer> q1 = new ArrayDeque<>();
        PriorityQueue<Integer> q2 = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < n; ++i) {
            q1.offer(i);
            q2.offer(priorities[i]);
        }
        
        int num = 1;
        while(!q1.isEmpty()) {
            int idx = q1.poll();
            if(priorities[idx] < q2.peek()) {
                q1.offer(idx);
            } else {
                q2.poll();
                order[idx] = num++;
            }
        }
        
        return order[location];
    }
}