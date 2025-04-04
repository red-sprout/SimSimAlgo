#include <iostream>
#include <string>
#define TRUE "valid"
#define FALSE "invalid"

using namespace std;

int final_state;
string origin_board;

bool is_full(string board) {
	for (int i = 0; i < 9; ++i) {
		if (board[i] == '.') return false;
	}
	return true;
}

bool check(char* board) {
	if (board[0] != '.' && board[0] == board[1] && board[1] == board[2]) return true;
	if (board[3] != '.' && board[3] == board[4] && board[4] == board[5]) return true;
	if (board[6] != '.' && board[6] == board[7] && board[7] == board[8]) return true;
	if (board[0] != '.' && board[0] == board[3] && board[3] == board[6]) return true;
	if (board[1] != '.' && board[1] == board[4] && board[4] == board[7]) return true;
	if (board[2] != '.' && board[2] == board[5] && board[5] == board[8]) return true;
	if (board[0] != '.' && board[0] == board[4] && board[4] == board[8]) return true;
	if (board[2] != '.' && board[2] == board[4] && board[4] == board[6]) return true;
	return false;
}

bool dfs(int v, char c, char* board) {
	if (final_state == v) return true;
	if (check(board)) return false;
    
	bool res = false;
	char nc = (c == 'O' ? 'X' : 'O');
	for (int i = 0; i < 9; ++i) {
		if (origin_board[i] != c) continue;
		if ((v & (1 << i)) != 0) continue;
		board[i] = c;
		res = res || dfs(v | (1 << i), nc, board);
		board[i] = '.';
	}
	return res;
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	while (1) {
		cin >> origin_board;
		if (origin_board.compare("end") == 0) break;

		final_state = 0;
		for (int i = 0; i < 9; ++i) {
			if (origin_board[i] != '.') {
				final_state |= 1 << i;
			}
		}

		char b[9];
		for (int i = 0; i < 9; ++i) {
			b[i] = origin_board[i];
		}

		if (is_full(origin_board) || check(b)) {
			fill(b, b + 9, '.');
			cout << (dfs(0, 'X', b) ? TRUE : FALSE) << '\n';
		}
		else {
			cout << FALSE << '\n';
		}
	}
	return 0;
}