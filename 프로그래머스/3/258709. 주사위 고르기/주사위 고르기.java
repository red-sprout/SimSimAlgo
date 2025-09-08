import java.util.*;

class Solution {
    int n;
    int[][] dice;
    int[] answer;
    long countWin = 0;

    Map<Integer, Long> getSumDistribution(List<int[]> selectedDice) {
        Map<Integer, Long> dp = new HashMap<>();
        dp.put(0, 1L);
        for (int[] die : selectedDice) {
            Map<Integer, Long> next = new HashMap<>();
            for (Map.Entry<Integer, Long> entry : dp.entrySet()) {
                int curSum = entry.getKey();
                long ways = entry.getValue();
                for (int face : die) {
                    next.put(curSum + face, next.getOrDefault(curSum + face, 0L) + ways);
                }
            }
            dp = next;
        }
        return dp;
    }

    void simulation(int vis) {
        List<int[]> aDice = new ArrayList<>();
        List<int[]> bDice = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if ((vis & (1 << i)) != 0) aDice.add(dice[i]);
            else bDice.add(dice[i]);
        }

        Map<Integer, Long> aDist = getSumDistribution(aDice);
        Map<Integer, Long> bDist = getSumDistribution(bDice);

        List<Integer> aKeys = new ArrayList<>(aDist.keySet());
        List<Integer> bKeys = new ArrayList<>(bDist.keySet());
        Collections.sort(aKeys);
        Collections.sort(bKeys);

        long[] bPrefix = new long[bKeys.size() + 1];
        for (int i = 0; i < bKeys.size(); i++) {
            bPrefix[i + 1] = bPrefix[i] + bDist.get(bKeys.get(i));
        }

        long win = 0;
        for (int aSum : aKeys) {
            long aCount = aDist.get(aSum);
            int idx = Collections.binarySearch(bKeys, aSum);
            if (idx < 0) idx = -idx - 1;
            win += aCount * bPrefix[idx];
        }

        if (countWin < win) {
            int idx = 0;
            for (int i = 0; i < n; i++) {
                if ((vis & (1 << i)) != 0) answer[idx++] = i + 1;
            }
            countWin = win;
        }
    }

    void combination(int start, int cnt, int vis) {
        if (cnt == n / 2) {
            simulation(vis);
            return;
        }
        if (start > n - 1) return;
        for (int i = start; i < n; i++) {
            combination(i + 1, cnt + 1, vis | (1 << i));
        }
    }

    public int[] solution(int[][] dice) {
        n = dice.length;
        this.dice = dice;
        answer = new int[n / 2];
        combination(0, 0, 0);
        return answer;
    }
}
