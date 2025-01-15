#include <iostream>

using namespace std;

void solution() {
  int n, cnt;
  cin >> n;
  while(n-- > 0) {
    cin >> cnt;
    while(cnt-- > 0) {
      cout << '=';
    }
    cout << '\n';
  }
}

int main() {
  ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
  solution();
	return 0;
}