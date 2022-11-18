package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
    int x;
    int y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Q9205{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for(int k=0;k<t;++k){
            int n = Integer.parseInt(br.readLine());

            Node[] node = new Node[n+2];
            boolean[][] check = new boolean[n+2][n+2];

            for(int i=0;i<n+2;++i){
                st = new StringTokenizer(br.readLine());
                node[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            for(int i=0;i<n+1;++i){
                Node point = node[i];
                int x = point.x;
                int y = point.y;
                for(int j=i+1;j<n+2;++j){
                    Node point2 = node[j];
                    int x2 = point2.x;
                    int y2 = point2.y;

                    if(Math.abs(x-x2)+Math.abs(y-y2)<=1000){
                        check[i][j] = true;
                        check[j][i] = true;
                    }
                }
            }
            if(BFS(check, n))
                System.out.println("happy");
            else
                System.out.println("sad");
        }
    }

    public static boolean BFS(boolean[][] check, int n){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+2];
        visited[0] = true;
        queue.offer(0);

        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int i=1;i<n+2;++i){
                if(check[cur][i]){
                    if(i==n+1)//도착
                        return true;
                    if(!visited[i]){
                        visited[i] = true;
                        queue.offer(i);
                    }
                }
            }
        }
        return false;
    }
}