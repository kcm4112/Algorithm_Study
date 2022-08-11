package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q11404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());//City
        int M = Integer.parseInt(br.readLine());//Bus

        int INF = (int)1e9;
        int[][] graph = new int[N+1][N+1];
        for(int[] temp:graph) Arrays.fill(temp, INF);

        for(int i=0;i<=N;++i)
            for(int j=0;j<=N;++j)
                if(i==j)
                    graph[i][j]=0;

        for(int i=0;i<M;++i){
            String str = br.readLine();
            st = new StringTokenizer(str, " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start][end] = Math.min(graph[start][end], cost);
        }

        for(int a=1;a<=N;++a)
            for(int b=1;b<=N;++b)
                for(int c=1;c<=N;++c)
                    graph[b][c] = Math.min(graph[b][c], graph[b][a]+graph[a][c]);

        for(int i=1;i<=N;++i){
            for(int j=1;j<=N;++j){
                if(graph[i][j]==INF)
                    System.out.print(0+ " ");
                else
                    System.out.print(graph[i][j]+" ");
            }
            System.out.println();
        }
    }

}
