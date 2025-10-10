import java.util.*;

class Solution {
    void sout(Object o) {
        System.out.println(o);
    }
    
    int n, m;
    int[][] users;
    int[] emoticons;
    
    int reg = 0, amt = 0;
    
    void init(int[][] users, int[] emoticons) {
        n = users.length;
        m = emoticons.length;
        this.users = users;
        this.emoticons = emoticons;
    }
    
    void checkUsers(int[] rate) {
        int curReg = 0, curAmt = 0;
        for(int[] user : users) {
            int userAmt = 0;
            for(int i = 0; i < m; ++i) {
                if(user[0] > rate[i]) continue;
                userAmt += emoticons[i] * (100 - rate[i]) / 100;
            }
            
            if(user[1] <= userAmt) {
                ++curReg;
            } else {
                curAmt += userAmt;
            }
        }
        
        if(curReg > reg) {
            reg = curReg;
            amt = curAmt;
        } else if(curReg == reg && curAmt > amt) {
            amt = curAmt;
        }
    }
    
    void dfs(int cur, int[] rate) {
        if(reg == n) return;
        
        if(cur == m) {
            checkUsers(rate);
            return;
        }
        
        for(int i = 10; i <= 40; i += 10) {
            rate[cur] = i;
            dfs(cur + 1, rate);
        }
    }
    
    int[] findAnswer() {
        dfs(0, new int[m]);
        return new int[] {reg, amt};
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        init(users, emoticons);
        int[] answer = findAnswer();
        return answer;
    }
}