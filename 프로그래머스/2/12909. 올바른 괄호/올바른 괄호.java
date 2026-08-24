import java.util.*;

class Solution {
    boolean solution(String s) {
        int cnt = 0;
        for(int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if(c == '(') {
                ++cnt;
            } else if(cnt == 0) {
                return false;
            } else {
                --cnt;
            }
        }
        return cnt == 0;
    }
}