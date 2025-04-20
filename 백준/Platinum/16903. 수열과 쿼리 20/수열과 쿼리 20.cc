#include <iostream>
#include <vector>

using namespace std;

struct Node {
    int l = -1, r = -1, cnt = 0, v = -1;
};

vector<Node> node;
int root, bits[30];

void initBits(int x) {
    fill(bits, bits + 30, 0);
    for(int i = 29; i >= 0; --i) {
        bits[i] = x & 1;
        x >>= 1;
    }
}

void cmd1(int x) {
    int cur = root;
    initBits(x);
    for(int i = 0; i < 30; ++i) {
        if(bits[i] == 0) {
            if(node[cur].l == -1) {
                node.emplace_back(Node());
                node[cur].l = node.size() - 1;
            }
            cur = node[cur].l;
            ++node[cur].cnt;
        } else {
            if(node[cur].r == -1) {
                node.emplace_back(Node());
                node[cur].r = node.size() - 1;
            }
            cur = node[cur].r;
            ++node[cur].cnt;
        }
    }
    node[cur].v = x;
}

void cmd2(int x) {
    int cur = root;
    initBits(x);
    for(int i = 0; i < 30; ++i) {
        int nxt;
        if(bits[i] == 0) {
            nxt = node[cur].l;
            if(--node[nxt].cnt == 0) {
                node[cur].l = -1;
                return;
            }
            cur = nxt;
        } else {
            nxt = node[cur].r;
            if(--node[nxt].cnt == 0) {
                node[cur].r = -1;
                return;
            }
            cur = nxt;
        }
    }
}

int cmd3(int x) {
    int cur = root;
    initBits(x);
    for(int i = 0; i < 30; ++i) {
        if(bits[i] == 0) {
            cur = node[cur].r == -1 ? node[cur].l : node[cur].r;
        } else {
            cur = node[cur].l == -1 ? node[cur].r : node[cur].l;
        }
    }
    return node[cur].v ^ x;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    node.emplace_back(Node());
    root = 0;
    int m; cin >> m;
    int cmd, x;
    cmd1(0);
    while(m--) {
        cin >> cmd >> x;
        if(cmd == 1) {
            cmd1(x);
        } else if(cmd == 2) {
            cmd2(x);
        } else {
            cout << cmd3(x) << '\n';
        }
    }
    return 0;
}