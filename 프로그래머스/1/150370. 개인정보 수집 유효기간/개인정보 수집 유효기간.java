import java.util.*;

class Solution {
    public void sout(Object o) {
        System.out.println(o.toString());
    }
    
    int[] terms;
    
    class CustomDate implements Comparable<CustomDate> {
        int year, month, day;
        
        CustomDate(String date) {
            StringTokenizer st = new StringTokenizer(date, ".");
            this.year = Integer.parseInt(st.nextToken());
            this.month = Integer.parseInt(st.nextToken());
            this.day = Integer.parseInt(st.nextToken());
        }
        
        CustomDate(String date, String key) {
            this(date);
            int idx = key.charAt(0) - 'A';
            this.month += terms[idx];
            if(month > 12) {
                this.month -= 1;
                this.year += this.month / 12;
                this.month = this.month % 12 + 1;
            }
        }
        
        @Override
        public int compareTo(CustomDate d) {
            if(this.year == d.year) {
                if(this.month == d.month) {
                    return this.day - d.day;
                }
                return this.month - d.month;
            }
            return this.year - d.year;
        }
        
        @Override
        public String toString() {
            return year + "-" + month + "-" + day;
        }
    }
    
    class Period {
        int id;
        CustomDate start;
        CustomDate end;
        
        Period(int id, String privacy) {
            StringTokenizer st = new StringTokenizer(privacy, " ");
            String date = st.nextToken();
            String key = st.nextToken();
            this.id = id;
            this.start = new CustomDate(date);
            this.end = new CustomDate(date, key);
        }
        
        boolean isOutOfRange(CustomDate today) {
            return start.compareTo(today) > 0 || end.compareTo(today) <= 0;
        }
        
        @Override
        public String toString() {
            return  id + " : " + start.toString() + " ~ " + end.toString();
        }
    }
    
    List<Period> periods;
    
    void init(String[] t1, String[] t2) {
        terms = new int[26];
        periods = new ArrayList<>();
        
        for(String t : t1) {
            StringTokenizer st = new StringTokenizer(t, " ");
            int idx = st.nextToken().charAt(0) - 'A';
            terms[idx] = Integer.parseInt(st.nextToken());
        }
        
        int id = 0;
        for(String p : t2) {
            periods.add(new Period(++id, p));
        }
    }
    
    List<Integer> findAnswer(String today) {
        CustomDate date = new CustomDate(today);
        List<Integer> list = new ArrayList<>();
        
        for(Period p : periods) {
            if(p.isOutOfRange(date)) {
                list.add(p.id);
            }
        }
        
        return list;
    }
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        init(terms, privacies);
        
        List<Integer> list = findAnswer(today);
        int[] answer = new int[list.size()];
        for(int i = 0; i < answer.length; ++i) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}