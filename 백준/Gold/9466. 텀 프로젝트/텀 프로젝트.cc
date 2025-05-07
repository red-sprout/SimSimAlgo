#include <iostream>

using namespace std;

int n;
int arr[100'001];
bool v[100'001];
bool f[100'001];

int dfs(int cur) {
    v[cur] = true;
    int nxt = arr[cur];

    int res = 0;
    if(!v[nxt]) {
        res += dfs(nxt);
    } else if(!f[nxt]) {
        for(int i = nxt; i != cur; i = arr[i]) {
            ++res;
        }
        ++res;
    }
    f[cur] = true;
    return res;
} 

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int t; cin >> t;
    while(t--) {
        cin >> n;
        for(int i = 1; i <= n; ++i) {
            cin >> arr[i];
            v[i] = false;
            f[i] = false;
        }
        
        int res = 0;
        for(int i = 1; i <= n; ++i) {
            if(!v[i]) res += dfs(i);
        }

        cout << n - res << '\n';
    }
    return 0;
}