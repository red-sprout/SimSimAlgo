#include <string>
#include <vector>
#define CONST 10'007

using namespace std;

int dp[100'001][2];

int solution(int n, vector<int> tops) {
    dp[0][0] = 1;
    dp[0][1] = tops[0] ? 3 : 2;
    
    for(int i = 1; i < n; ++i) {
        dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % CONST;
        if(tops[i]) {
            dp[i][1] = (2 * dp[i - 1][0] + 3 * dp[i - 1][1]) % CONST;
        } else {
            dp[i][1] = (dp[i - 1][0] + 2 * dp[i - 1][1]) % CONST;
        }
    }
    
    return (dp[n - 1][0] + dp[n - 1][1]) % CONST;
}