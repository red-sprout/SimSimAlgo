#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

int l;
const int R = 31;
const int M = 100003;
string s;

void init() {
  cin >> l;
  cin >> s;
}

bool exist(int mid) {
  vector<vector<int>> hashTable(M);
  ll hash = 0, rr = 1;
  for(int i = 0; i < mid; i++) {
    hash = (hash * R + (s[i] - 'a' + 1)) % M;
    rr = (rr * R) % M;
  }
  hashTable[hash].emplace_back(0);

  for(int i = 1; i + mid - 1 < l; i++) {
    hash = (hash * R) % M;
    hash = (hash - ((s[i - 1] - 'a' + 1) * rr) % M + M) % M;
    hash = (hash + (s[i + mid - 1] - 'a' + 1)) % M;

    for(auto idx : hashTable[hash]) {
      if(s.compare(i, mid, s, idx, mid) == 0) return true;
    }
    hashTable[hash].emplace_back(i);
  }

  return false;
}

void solution() {
    int left = -1, right = l + 1;
    while (left + 1 < right) {
      int mid = (left + right) / 2;
      if(exist(mid)) {
        left = mid;
      } else {
        right = mid;
      }
    }
    cout << left << '\n';
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0);
    init();
    solution();
    return 0;
}
