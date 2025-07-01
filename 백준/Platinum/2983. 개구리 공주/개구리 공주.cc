#include <iostream>
#include <vector>
#include <unordered_map>
#include <set>

using namespace std;

struct Leaf {
    int x, y, id;
    Leaf() {}
    Leaf(int _x, int  _y, int _id) : x(_x), y(_y), id(_id) {}
};

struct Compare {
    bool operator()(const Leaf& l1, const Leaf& l2) const {
        return l1.x < l2.x;
    }
};

const int MIN = -1;
const int MAX = 1'000'000'001;

int n, k;
string order;
vector<Leaf> leafs;
unordered_map<int, set<Leaf, Compare>> mxpy, pxpy;

void add_mxpy(int key) {
    set<Leaf, Compare> s;
    s.emplace(Leaf(MIN, MIN + key, -1));
    s.emplace(Leaf(MAX, MAX + key, -1));
    mxpy[key] = s;
}

void add_pxpy(int key) {
    set<Leaf, Compare> s;
    s.emplace(Leaf(MIN, -MIN + key, -1));
    s.emplace(Leaf(MAX, -MAX + key, -1));
    pxpy[key] = s;
}

void input(int x, int y, int id) {
    Leaf l = Leaf(x, y, id);
    leafs.emplace_back(l);
    int k1 = -x + y, k2 = x + y;
    if(mxpy.find(k1) == mxpy.end()) add_mxpy(k1);
    if(pxpy.find(k2) == mxpy.end()) add_pxpy(k2);
    mxpy[k1].emplace(l);
    pxpy[k2].emplace(l);
}

int move_mxpy(int idx, char cmd) {
    Leaf l = leafs[idx];
    int k1 = -l.x + l.y, k2 = l.x + l.y;
    mxpy[k1].erase(l);
    pxpy[k2].erase(l);
    int res = -1;
    if(cmd == 'A') {
        res = mxpy[k1].upper_bound(l)->id;
        if(res == -1) return idx;
    } else {
        res = (--mxpy[k1].lower_bound(l))->id;
        if(res == -1) return idx;
    }
    return res;
}

int move_pxpy(int idx, char cmd) {
    Leaf l = leafs[idx];
    int k1 = -l.x + l.y, k2 = l.x + l.y;
    mxpy[k1].erase(l);
    pxpy[k2].erase(l);
    int res = -1;
    if (cmd == 'B') {
        res = pxpy[k2].upper_bound(l)->id;
        if(res == -1) return idx;
    } else {
        res = (--pxpy[k2].lower_bound(l))->id;
        if (res == -1) return idx;
    }
    return res;
}

int move(int idx, char cmd) {
    if(cmd == 'A' || cmd == 'D') return move_mxpy(idx, cmd);
    else return move_pxpy(idx, cmd);
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> n >> k;
    cin >> order;
    int x, y;
    for (int i = 0; i < n; ++i) {
        cin >> x >> y;
        input(x, y, i);
    }
    int idx = 0;
    for (int i = 0; i < k; ++i) {
        idx = move(idx, order[i]);
    }
    cout << leafs[idx].x << ' ' << leafs[idx].y << '\n';
    return 0;
}