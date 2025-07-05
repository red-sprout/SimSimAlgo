#include <iostream>
#include <vector>

using namespace std;

int k, n, h;
vector<char> fold;
int paper[1 << 8][1 << 8];

const int dr[] = {2, 3, 0, 1};
const int dc[] = {1, 0, 3, 2};

void recur(int fr, int fc, int sr, int sc, int er, int ec) {
    if(fr == k && fc == k) {
        paper[sr][sc] = h;
        return;
    }
    char f = fold[fr + fc];
    switch(f) {
        case 'D':
            sr = (sr + er) >> 1;
            recur(fr + 1, fc, sr, sc, er, ec);
            for(int i = 0; i < 1 << (k - fr - 1); ++i) {
                for(int j = sc; j < ec; ++j) {
                    paper[sr - i - 1][j] = dr[paper[sr + i][j]];
                }
            }
            break;
        case 'U':
            er = (sr + er) >> 1;
            recur(fr + 1, fc, sr, sc, er, ec);
            for(int i = 0; i < 1 << (k - fr - 1); ++i) {
                for(int j = sc; j < ec; ++j) {
                    paper[er + i][j] = dr[paper[er - i - 1][j]];
                }
            }
            break;
        case 'R':
            sc = (sc + ec) >> 1;
            recur(fr, fc + 1, sr, sc, er, ec);
            for(int i = sr; i < er; ++i) {
                for(int j = 0; j < 1 << (k - fc - 1); ++j) {
                    paper[i][sc - j - 1] = dc[paper[i][sc + j]];
                }
            }
            break;
        default:
            ec = (sc + ec) >> 1;
            recur(fr, fc + 1, sr, sc, er, ec);
            for(int i = sr; i < er; ++i) {
                for(int j = 0; j < 1 << (k - fc - 1); ++j) {
                    paper[i][ec + j] = dc[paper[i][ec - j - 1]];
                }
            }
            break;
    }
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> k;
    n = 1 << k;
    fold.resize(2 * k);
    for(int i = 0; i < 2 * k; ++i) {
        cin >> fold[i];
    }
    cin >> h;
    recur(0, 0, 0, 0, n, n);
    for(int i = 0; i < n; ++i) {
        for(int j = 0; j < n; ++j) {
            cout << paper[i][j] << ' ';
        }
        cout << '\n';
    }
    return 0;
}
