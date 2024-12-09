#include <iostream>
#include <algorithm>
#define MAX 100001

using namespace std;
typedef pair<int, int> pi;

int M, N, L, cnt;
int hunter[MAX];
pi animal[MAX];

void init() {
  for(int i = 0; i < M; i++) {
    cin >> hunter[i];
  }
  sort(hunter, hunter + M);
  for(int i = 0; i < N; i++) {
    cin >> animal[i].first >> animal[i].second;
  }
}

int dist(int x, pi p) {
  return abs(x - p.first) + p.second;
}

int lower_bound(int a) {
  int left = -1, mid = 0, right = N;
  while(left + 1 < right) {
    mid = (left + right) / 2;
    if(mid < a) {
      left = mid;
    } else {
      right = mid;
    }
  }
  return right;
}

int upper_bound(int a) {
  int left = -1, mid = 0, right = N;
  while(left + 1 < right) {
    mid = (left + right) / 2;
    if(mid <= a) {
      left = mid;
    } else {
      right = mid;
    }
  }
  return left;
}

void solve() {
  cnt = 0;
  for(int i = 0; i < N; i++) {
    pi p = animal[i];
    int x1 = lower_bound(p.first);
    int x2 = upper_bound(p.second);
    if(min(dist(x1, p), dist(x2, p)) <= L) {
      cnt++;
    }
  }
  cout << cnt << '\n';
}

int main() {
  ios::sync_with_stdio(0); cin.tie(NULL);
  cin >> M >> N >> L;
  init();
  solve();
  return 0;
}