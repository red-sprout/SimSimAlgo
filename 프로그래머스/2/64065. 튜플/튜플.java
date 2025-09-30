import java.util.*;

class Solution {
    public int[] solution(String s) {
        StringTokenizer big = new StringTokenizer(s, "}");
        StringTokenizer small;
        
        List<Set<Integer>> setList = new ArrayList<>();
        
        while(big.hasMoreTokens()) {
            small = new StringTokenizer(big.nextToken(), "{,");
            Set<Integer> set = new HashSet<>();
            while(small.hasMoreTokens()) {
                set.add(Integer.parseInt(small.nextToken()));
            }
            setList.add(set);
        }
        
        Collections.sort(setList, (s1, s2) -> s1.size() - s2.size());
        
        int sz = setList.size();
        int[] answer = new int[sz];
        for(int i = 0; i < sz; ++i) {
            int num = 0;
            for(int ele : setList.get(i)) { num = ele; }
            answer[i] = num;
            for(int j = i; j < sz; ++j) {
                setList.get(j).remove(num);
            }
        }
        
        return answer;
    }
}