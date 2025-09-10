import java.util.*;

class Solution {
    int l;
    long n;
    long[] p;
    String[] bans;
    List<Long>[] cntBans;
    
    long getHash(String str) {
        long h = 0;
        for(int i = 0; i < str.length(); ++i) {
            h *= 26;
            h += str.charAt(i) - 'a';
        }
        return h;
    }
    
    String parse(long hash) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < l; ++i) {
            sb.append((char) ('a' + hash % 26));
            hash /= 26;
        }
        return sb.reverse().toString();
    }
    
    void init(long n, String[] bans) {        
        p = new long[13];
        p[0] = 1;
        for(int i = 1; i < 13; ++i) {
            p[i] = 26 * p[i - 1];
        }
        
        cntBans = new List[12];
        for(int i = 0; i < 12; ++i) {
            cntBans[i] = new ArrayList<>();
        }
        
        for(String b : bans) {
            cntBans[b.length()].add(getHash(b));
        }
        
        l = 1;
        for(; l < 12; ++l) {
            if(n <= p[l] - cntBans[l].size()) break;
            n -= p[l] - cntBans[l].size();
        }
        
        this.n = n;
        this.bans = bans;
    }
    
    long getNthValue() {
        List<Long> banList = cntBans[l];
        Collections.sort(banList);
        
        long ans = n;
        while(true) {
            int idx = Collections.binarySearch(banList, ans);
            int rmv = idx < 0 ? -idx - 1 : idx + 1;
            if(n == ans - rmv) break;
            ++ans;
        }
        
        return ans;
    }
    
    public String solution(long n, String[] bans) {
        init(n - 1, bans);
        return parse(getNthValue());
    }
}