import java.util.*;

class Solution {
    void sout(Object o) {
        System.out.println(o);
    }
    
    int cap, n;
    int[] dArr, pArr;
    
    void init(int i1, int i2, int[] arr1, int[] arr2) {
        cap = i1;
        n = i2;
        dArr = Arrays.copyOf(arr1, n);
        pArr = Arrays.copyOf(arr2, n);
    }
    
    long findAnswer() {
        int dIdx = n - 1, pIdx = n - 1;
        long answer = 0;
        while(dIdx >= 0 || pIdx >= 0) {
            long idx = -1;
            
            int dCap = 0;
            while(dIdx >= 0) {
                if(dArr[dIdx] > 0) {
                    idx = Math.max(idx, dIdx);
                }
                if(cap - dCap >= dArr[dIdx]) {
                    dCap += dArr[dIdx];
                    dArr[dIdx] = 0;
                    --dIdx;
                } else {
                    int amt = cap - dCap;
                    dCap += amt;
                    dArr[dIdx] -= amt;
                    break;
                }
            }
            
            int pCap = 0;
            while(pIdx >= 0) {
                if(pArr[pIdx] > 0) {
                    idx = Math.max(idx, pIdx);
                }
                if(cap - pCap >= pArr[pIdx]) {
                    pCap += pArr[pIdx];
                    pArr[pIdx] = 0;
                    --pIdx;
                } else {
                    int amt = cap - pCap;
                    pCap += amt;
                    pArr[pIdx] -= amt;
                    break;
                }
            }
            
            answer += idx + idx + 2;
        }
        
        return answer;
    }
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        init(cap, n, deliveries, pickups);
        return findAnswer();
    }
}