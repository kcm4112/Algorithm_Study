/*
백준 골드5 숨박꼭질 13549
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <math.h>
using namespace std;
priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>>pq;
vector<int>visit;
int n, k,result = 0;
int bfs();
int main() {
	cin >> n >> k; //좌표는 둘다 양수
	visit.resize(100001, 0);
	pq.push(make_pair(0, n));
	visit[n] = true;
	cout<<bfs();
	//점프 2배, 이동 +-1 
}
int bfs() {

	while (!pq.empty()) {
		int location = pq.top().second;
		int t = pq.top().first;
		visit[location] = 1;
		pq.pop();
		if (location == k) {
			return t;
		}
		if (visit[location + 1] == 0 && location+1<100001) { //아직 방문안했고 유효한 좌표
			pq.push(make_pair(t + 1, location + 1));	
		}
		if (visit[location - 1] == 0 && location-1>=0) { //아직 방문안했고, 유효한 좌표
			pq.push(make_pair(t + 1, location - 1));
		}
		if (location*2<100001 && visit[location*2]==0) { //아직 방문안했고, 유효한 좌표
				pq.push(make_pair(t, location * 2));
		}
	}
}