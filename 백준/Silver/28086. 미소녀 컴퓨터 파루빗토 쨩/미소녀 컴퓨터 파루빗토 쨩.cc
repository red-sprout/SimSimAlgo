#include <iostream>
#include <string>
#define NONE string::npos

using namespace std;
typedef long long ll;

ll parse_num(const string& num) {
    if (num[0] == '-') return -stoll(num.substr(1), nullptr, 8);
    else return stoll(num, nullptr, 8);
}

void print(ll n) {
    if (n < 0) cout << '-' << oct << -n << '\n';
    else cout << oct << n << '\n';
}

ll divide(ll a, ll b) {
    if (a * b >= 0) return a / b;
    return a % b != 0 ? a / b - 1 : a / b;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    string expr; cin >> expr;

    char op = 0;
    size_t pos = NONE;

    for (char c : {'+', '*', '/', '-'}) {
        size_t p = expr.find(c, (c == '-' ? 1 : 0));
        if (p != NONE) {
            op = c;
            pos = p;
            break;
        }
    }

    ll a = parse_num(expr.substr(0, pos));
    ll b = parse_num(expr.substr(pos + 1));

    switch (op) {
        case '+': print(a + b); break;
        case '*': print(a * b); break;
        case '/': if (b == 0) cout << "invalid\n"; else print(divide(a, b)); break;
        case '-': print(a - b); break;
    }

    return 0;
}
