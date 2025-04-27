#include <iostream>
#include <vector>
#include <queue>
#define M 1'000'000'000
#define SZ 500'001

using namespace std;
typedef pair<int, int> pii;

int n, k;
bool v[SZ];
vector<int> process;
queue<int> order[SZ];

void init() {
    cin >> n >> k;
    int name;
    for (int i = 0; i < k; ++i) {
        cin >> name;
        process.emplace_back(name);
        order[name].emplace(i);
    }
    for (int i = 0; i < SZ; ++i) {
        order[i].emplace(M);
    }
}

int solution() {
    int cnt = 0, name;
    int size = 0;
    priority_queue<pii> pq;

    for (int i = 0; i < k; ++i) {
        name = process[i];
        order[name].pop();
        int next_use = order[name].front();

        if (!v[name]) {
            if (size == n) {
                while (1) {
                    pii cur = pq.top(); pq.pop();
                    auto nx = cur.first;
                    auto p = cur.second;
                    if (v[p] && order[p].front() == nx) {
                        v[p] = false;
                        --size;
                        ++cnt;
                        break;
                    }
                }
            }
            v[name] = true;
            ++size;
        }
        pq.emplace(next_use, name);
    }

    return cnt;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    init();
    cout << solution() << "\n";
    return 0;
}
