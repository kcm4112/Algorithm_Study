package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q18111 {
    public static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();
        st = new StringTokenizer(str, " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        int max_height = Integer.MIN_VALUE;
        int min_height = Integer.MAX_VALUE;
        for(int i=0;i<N;++i){
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            for(int j=0;j<M;++j){
                graph[i][j] = Integer.parseInt(st.nextToken());
                max_height = Math.max(max_height, graph[i][j]);
                min_height = Math.min(min_height, graph[i][j]);
            }
        }

        int result_time = Integer.MAX_VALUE;
        int height=0;
        for(int i=min_height;i<=max_height;++i){
            int time = 0;
            int block = B;
            for(int j=0;j<N;++j){
                for(int k=0;k<M;++k){
                    if(graph[j][k]>i){ //제거
                        block += graph[j][k] - i;
                        time += (graph[j][k] - i)*2;
                    }
                    else{
                        block -= i - graph[j][k];
                        time += i - graph[j][k];
                    }
                }
            }
            if(block>=0) {
                if (time <= result_time) {
                    result_time = time;
                    height = i;
                }
            }
        }

        System.out.println(result_time + " " + height);
    }

}
