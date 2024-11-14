package year2024.month11.second;

import java.io.*;
import java.util.*;

public class Main_bj_15653_구슬탈출4 {
	static int N, M;
	static char[][] map, test;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static class State {
		Ball[] ball = new Ball[2];
		int cnt;
		
		public State() {}
		
		public State(Ball red, Ball blue, int cnt) {
			ball[0] = red;
			ball[1] = blue;
			this.cnt = cnt;
		}
		
		@Override
		public String toString() {
			return "State [ball=" + Arrays.toString(ball) + ", cnt=" + cnt + "]";
		}
	}
	static class Ball {
		char color;
		int row, col;
		boolean status;
		
		public Ball() {
			this.status = true;
		}
		
		public Ball(char color, int row, int col) {
			this.color = color;
			this.row = row;
			this.col = col;
			this.status = true;
		}

		@Override
		public String toString() {
			return "Ball [color=" + color + ", row=" + row + ", col=" + col + ", status=" + status + "]";
		}
	}
	static int solution(State state) {
		Queue<State> q = new ArrayDeque<>();
		boolean[][][][] visited = new boolean[N][M][N][M];
		visited[state.ball[0].row][state.ball[0].col][state.ball[1].row][state.ball[1].col] = true;
		state.cnt = 0;
		q.offer(state);
		while(!q.isEmpty()) {
			State cur = q.poll();
			Ball[] ball = cur.ball;
			Ball[] next = new Ball[2];
			for(int d = 0; d < 4; d++) {
				initTest();
				int idx = getPrior(ball, d);
				for(int i = 0; i < ball.length; i++) {
					int order = (idx + i) % ball.length;
					Ball nowBall = ball[order];
					Ball nextBall = next[order] = new Ball();
					int[] nextPos = fall(nowBall, nextBall, d);
					test[nowBall.row][nowBall.col] = '.';
					if(test[nextPos[0]][nextPos[1]] != 'O') test[nextPos[0]][nextPos[1]] = nowBall.color;
				}
				if(!next[1].status) continue;
				else if(!next[0].status) return cur.cnt + 1;
				if(!visited[next[0].row][next[0].col][next[1].row][next[1].col]) {
					q.offer(new State(next[0], next[1], cur.cnt + 1));
					visited[next[0].row][next[0].col][next[1].row][next[1].col] = true;
				}
			}
		}
		return -1;
	}
	static void initTest() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				test[i][j] = map[i][j];
			}
		}
	}
	static int getPrior(Ball[] ball, int d) {
		int red = ball[0].row * dr[d] + ball[0].col * dc[d];
		int blue = ball[1].row * dr[d] + ball[1].col * dc[d];
		return red > blue ? 0 : 1;
	}
	static int[] fall(Ball nowBall, Ball nextBall, int d) {
		int[] pos = new int[] {nowBall.row, nowBall.col};
		while(true) {
			int nr = pos[0] + dr[d];
			int nc = pos[1] + dc[d];
			if(nr < 0 || nr >= N || nc < 0 || nc >= M) break;
			if(test[nr][nc] == '#' || test[nr][nc] == 'R' || test[nr][nc] == 'B') break;
			if(test[nr][nc] == 'O') {
				nextBall.status = false;
				pos[0] = nr;
				pos[1] = nc;
				break;
			}
			pos[0] = nr;
			pos[1] = nc;
		}
		nextBall.color = nowBall.color;
		nextBall.row = pos[0];
		nextBall.col = pos[1];
		return pos;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		test = new char[N][M];
		State state = new State();
		for(int i = 0; i < N; i++) {
			String row = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = row.charAt(j);
				if(map[i][j] == 'R') {
					Ball red = new Ball('R', i, j);
					state.ball[0] = red;
					map[i][j] = '.';
				} else if(map[i][j] == 'B') {
					Ball blue = new Ball('B', i, j);
					state.ball[1] = blue;
					map[i][j] = '.';
				}
			}
		}
		System.out.println(solution(state));
		br.close();
	}
}
