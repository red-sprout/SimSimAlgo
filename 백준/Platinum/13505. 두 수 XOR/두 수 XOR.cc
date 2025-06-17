#include <bits/stdc++.h>

using namespace std;
typedef long long ll;

struct Node {
    bool isEnd;
    Node *children[2];

    Node() {
        isEnd = false;
        children[0] = children[1] = nullptr;
    }
};

struct Trie {
    Node *root;
    Trie() { root = new Node(); }

    void insert(ll key) {
        Node *cur = root;
        for (int i = 30; i >= 0; --i) {
            if (key & (1 << i)) {
                if (!cur->children[1]) cur->children[1] = new Node();
                cur = cur->children[1];
            } else {
                if (!cur->children[0]) cur->children[0] = new Node();
                cur = cur->children[0];
            }
        }
    }

    ll find_max_xor(ll key) {
        Node *cur = root;
        ll num = 0;
        for (int i = 30; i >= 0; --i) {
            if (key & (1 << i)) {
                if (!cur->children[0]) {
                    num |= (1 << i);
                    cur = cur->children[1];
                } else {
                    cur = cur->children[0];
                }
            } else {
                if (cur->children[1]) {
                    num |= (1 << i);
                    cur = cur->children[1];
                } else {
                    cur = cur->children[0];
                }
            }
        }
        return num ^ key;
    }
};

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    Trie trie;
    int n; cin >> n;
    ll arr[100'000];
    for (int i = 0; i < n; ++i) {
        cin >> arr[i];
        trie.insert(arr[i]);
    }
    ll ans = 0;
    for (int i = 0; i < n; ++i) {
        ans = max(ans, trie.find_max_xor(arr[i]));
    }
    cout << ans << '\n';
    return 0;
}