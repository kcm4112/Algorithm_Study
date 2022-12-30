package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Q11559 {
    public static char[][] graph;
    public static boolean[][] visited;
    public static ArrayList<Node> save = new ArrayList<>();
    public static int[] dx={-1,1,0,0}, dy={0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        graph = new char[12][6];
        visited = new boolean[12][6];
        for(int i=0;i<12;++i){
            graph[i] = br.readLine().toCharArray();
        }

        int answer=0;
        int flag = 1;

        while(true) {
            if(flag==0) // 이전에 변경이 발생하지 않은 경우
                break;

            flag = 0;
            for (boolean[] booleans : visited) {
                Arrays.fill(booleans, false);
            }

            for (int i = 0; i < 12; ++i) {
                for (int j = 0; j < 6; ++j) {
                    if (graph[i][j] == '.' || visited[i][j])
                        continue;
                    else {
                        if (BFS(i, j)) {
                            flag = 1; // 변경이 한 번이라도 일어나면 flag=1
                            for (Node node : save) {
                                graph[node.x][node.y] = '.';
                            }
                        }
                    }
                    save.clear();
                }
            }

            if (flag == 1) {//변경이 일어난 경우 횟수 1 증가
                answer++;
                makeNewGraph();
            }
        }

        System.out.println(answer);

    }

    public static boolean BFS(int x, int y){
        Queue<Node> queue = new LinkedList<>();
        visited[x][y] = true;
        int count=1;

        queue.offer(new Node(x, y));
        save.add(new Node(x, y));
        while(!queue.isEmpty()){
            Node node = queue.poll();
            char color = graph[node.x][node.y];
            for(int i=0;i<4;++i){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx>=0 && nx<12 && ny>=0 && ny<6 && !visited[nx][ny] && color==graph[nx][ny]) {
                    save.add(new Node(nx, ny));
                    queue.offer(new Node(nx, ny));
                    visited[nx][ny] = true;
                    count++;
                }
            }
        }
        if(count<4)
            return false;

        else
            return true;
    }

    public static void makeNewGraph(){
        ArrayList<Character> temp = new ArrayList<>();

        for(int i=0;i<6;++i){
            for(int j=0;j<12;++j){
                if(graph[j][i]!='.') {
                    temp.add(graph[j][i]);
                    graph[j][i]='.';
                }
            }
            for(int k=11;temp.size()>0;k--){
                graph[k][i] = temp.remove(temp.size()-1);
            }
        }
    }

    static class Node{
        int x, y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}