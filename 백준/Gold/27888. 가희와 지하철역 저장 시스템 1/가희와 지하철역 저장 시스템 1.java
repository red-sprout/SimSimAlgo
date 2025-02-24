import java.io.*;
import java.util.*;

public class Main {
    static int fIdx;
    static HashMap<String, Integer> stationToBit;
    static HashMap<String, Integer> featureToFIdx;
    static HashMap<Integer, HashSet<String>> bitToStation;
    
    static void init(int n, String[] stations) {
        fIdx = 0;
        stationToBit = new HashMap<>();
        featureToFIdx = new HashMap<>();
        bitToStation = new HashMap<>();
        for(int i = 0; i < n; i++) {
            stationToBit.put(stations[i], 0);
        }
    }
    
    static int getFIdx(String feature) {
        if(featureToFIdx.containsKey(feature)) {
            return featureToFIdx.get(feature);
        }
        return -1;
    }
    
    static void cmdU(String station, String[] features) {
        int bit = 0;
        for(String feat : features) {
            int idx = getFIdx(feat);
            if(idx == -1) {
                idx = fIdx;
                featureToFIdx.put(feat, fIdx);
                fIdx++;
            }
            bit |= 1 << idx;
        }
        int originKey = stationToBit.get(station);
        if(originKey != 0) {
            bitToStation.get(originKey).remove(station);
        }
        
        stationToBit.put(station, bit);
        bitToStation.putIfAbsent(bit, new HashSet<>());
        bitToStation.get(bit).add(station);
    }
    
    static int cmdG(String[] features) {
        int bit = 0;
        for(String feat : features) {
            int idx = getFIdx(feat);
            if(idx == -1) return 0;
            bit |= 1 << idx;
        }
        
        int res = 0;
        for(int key : bitToStation.keySet()) {
            if((key & bit) == bit) {
                res += bitToStation.get(key).size();
            }
        }
        
        return res;
    }
    
    static String[] parser(String features) {
        return features.split(",");
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int n = Integer.parseInt(br.readLine());
        String[] stations = new String[n];
        for(int i = 0; i < n; i++) {
            stations[i] = br.readLine();
        }
        
        init(n, stations);
        String cmd, station, feats;
        String[] features;
        int r = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(r-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            cmd = st.nextToken();
            switch(cmd) {
                case "U":
                    station = st.nextToken();
                    feats = st.nextToken();
                    features = parser(feats);
                    cmdU(station, features);
                    break;
                case "G":
                    feats = st.nextToken();
                    features = parser(feats);
                    sb.append(cmdG(features)).append('\n');
                    break;
            }
        }
        
        System.out.print(sb);
        br.close();
    }
}