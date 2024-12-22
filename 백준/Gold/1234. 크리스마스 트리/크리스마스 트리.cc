#include <iostream>
#include <cstring>

using namespace std;
typedef long long ll;

int n, red, green, blue;

ll dp[11][101][101][101];
ll fac[11];

void init() {
  cin >> n >> red >> green >> blue;
  dp[0][0][0][0] = 1;
  fac[0] = 1;
  for(int i = 1; i < 11; i++) {
    fac[i] = fac[i - 1] * i;
  }
}

void solution() {
  int cnt = 0, comb;
  for(int d = 1; d <= n; d++) {
    cnt += d - 1;
    for(int r = 0; r <= red; r++) {
      for(int g = 0; g <= green; g++) {
        for(int b = 0; b <= blue; b++) {
          if(cnt != r + g + b) continue;
          if(d <= red - r) 
            dp[d][r + d][g][b] += dp[d - 1][r][g][b];
          if(d <= green - g) 
            dp[d][r][g + d][b] += dp[d - 1][r][g][b];
          if(d <= blue - b) 
            dp[d][r][g][b + d] += dp[d - 1][r][g][b];
          if(d % 2 == 0) {
            comb = fac[d] / (fac[d / 2] * fac[d / 2]);
            if(d / 2 <= red - r && d / 2 <= green - g) 
              dp[d][r + d / 2][g + d / 2][b] += dp[d - 1][r][g][b] * comb;
            if(d / 2 <= green - g && d / 2 <= blue - b) 
              dp[d][r][g + d / 2][b + d / 2] += dp[d - 1][r][g][b] * comb;
            if(d / 2 <= red - r && d / 2 <= blue - b) 
              dp[d][r + d / 2][g][b + d / 2] += dp[d - 1][r][g][b] * comb;
          }
          if(d % 3 == 0) {
            comb = fac[d] / (fac[d / 3] * fac[d / 3] * fac[d / 3]);
            if(d / 3 <= red - r && d / 3 <= green - g && d / 3 <= blue - b) 
              dp[d][r + d / 3][g + d / 3][b + d / 3] += dp[d - 1][r][g][b] * comb;
          }
        }
      }
    }
  }

  ll ans = 0;
  for(int r = 0; r <= red; r++) {
    for(int g = 0; g <= green; g++) {
      for(int b = 0; b <= blue; b++) {
        ans += dp[n][r][g][b];
      }
    }
  }
  cout << ans << '\n';
}

int main() {
  ios::sync_with_stdio(0); cin.tie(0);
  init();
  solution();
  return 0;
}
