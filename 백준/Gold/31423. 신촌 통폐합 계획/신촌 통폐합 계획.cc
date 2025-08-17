#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

struct Node {
    string s;
    Node *nxt = nullptr;
    int end = -1;
};

int main() {
    FASTIO;
    int n; cin >> n;
    vector<Node> list(n);
    for (int i = 0; i < n; ++i) {
        cin >> list[i].s;
        list[i].end = i;
    }

    int idx1, idx2;
    for (int i = 0; i < n - 1; ++i) {
        cin >> idx1 >> idx2;
        list[list[idx1 - 1].end].nxt = &list[idx2 - 1];
        list[idx1 - 1].end = list[idx2 - 1].end;
    }

    Node *node = &list[idx1 - 1];
    for (int i = 0; i < n; ++i) {
        cout << node->s;
        node = node->nxt;
    }
    cout << '\n';

    return 0;
}
