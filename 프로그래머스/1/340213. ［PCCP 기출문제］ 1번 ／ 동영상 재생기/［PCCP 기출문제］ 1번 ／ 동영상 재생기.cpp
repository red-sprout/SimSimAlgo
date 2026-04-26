#include <string>
#include <vector>

using namespace std;

int toSec(string s) {
    return stoi(s.substr(0, 2)) * 60 + stoi(s.substr(3, 2));
}

string toTime(int sec) {
    int mm = sec / 60;
    int ss = sec % 60;

    string m = mm < 10 ? "0" + to_string(mm) : to_string(mm);
    string s = ss < 10 ? "0" + to_string(ss) : to_string(ss);

    return m + ":" + s;
}

string solution(string video_len, string pos, string op_start, string op_end, vector<string> commands) {
    int videoLen = toSec(video_len);
    int cur = toSec(pos);
    int opStart = toSec(op_start);
    int opEnd = toSec(op_end);

    for (string command : commands) {
        if (opStart <= cur && cur <= opEnd) {
            cur = opEnd;
        }
        if (command == "next") {
            cur += 10;
            if (cur > videoLen) {
                cur = videoLen;
            }
        } else if (command == "prev") {
            cur -= 10;
            if (cur < 0) {
                cur = 0;
            }
        }
    }

    if (opStart <= cur && cur <= opEnd) {
        cur = opEnd;
    }
    return toTime(cur);
}