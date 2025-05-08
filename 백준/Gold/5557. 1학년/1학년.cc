#include <iostream>
#include <cstring>

using namespace std;
typedef long long ll;

int n, target;
int arr[101];
ll dp[101][21];

ll dfs(int cur, int sum) {
    if(sum < 0 || sum > 20) return 0;
    if(cur == n - 1) return (target == sum ? 1 : 0);
    ll* d = &dp[cur][sum];
    if(*d != -1) return *d;
    *d = dfs(cur + 1, sum + arr[cur]) + dfs(cur + 1, sum - arr[cur]);
    return *d;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> n;
    for(int i = 0; i < n - 1; ++i) {
        cin >> arr[i];
    }
    cin >> target;
    memset(dp, -1, sizeof(dp));
    cout << dfs(1, arr[0]) << '\n';
    return 0;
}