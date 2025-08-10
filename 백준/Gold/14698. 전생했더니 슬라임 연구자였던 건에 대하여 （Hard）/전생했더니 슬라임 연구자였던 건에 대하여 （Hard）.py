import sys
import heapq
input = sys.stdin.readline
t = int(input())
while t > 0:
    n = int(input())
    pq = [*map(int, input().split())]
    heapq.heapify(pq)
    res = 1
    while len(pq) > 1:
        n1 = heapq.heappop(pq)
        n2 = heapq.heappop(pq)
        mult = n1 * n2
        heapq.heappush(pq, mult)
        res = (res * (mult % 1000000007)) % 1000000007
    heapq.heappop(pq)
    print(res)
    t -= 1