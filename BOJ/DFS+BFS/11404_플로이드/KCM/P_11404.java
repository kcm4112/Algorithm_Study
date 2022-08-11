import java.util.*;
import java.io.*;

public class P_11404 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int [][] map = new int [n+1][n+1];
        int [][] dist = new int [n+1][n+1];
        int INF = 1000000000; // INF값을 작게 하면 혹시나 문제에서 노선간 거리가 INF와 같아질 수 있으므 100,000 * 100 보다 큰 수로 INF값을 설정한다.
        for(int i=0;i<m;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if(map[start][end]>0){ // 노선이 1개가 아니므로, 값이 있다면 더 작은 가중치를 넣어줘야한다!
                map[start][end] = Math.min(map[start][end], w);
            }
            else{
                map[start][end] = w;
            }
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(i==j){ // 자기 자신에게로 가는 경우
                    dist[i][j] = 0;
                }
                else if(map[i][j]>0){ //직접 연결되어 있는 경우
                    dist[i][j] = map[i][j];
                }
                else{ //가는 길이 없는 경우
                    dist[i][j] = INF;
                }
            }
        }

        //중간에 거쳐가는 노드를 설정하는 과정
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                for(int k=1;k<=n;k++){
                    dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
                }
            }
        }

        //최종 결과 출력하기! 갈 수 없는 경우는 모두 0으로 출력해야함. (INF포함)
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(dist[i][j] == 0 || dist[i][j] == INF){
                    System.out.printf("%d ", 0);
                }
                else{
                    System.out.printf("%d ", dist[i][j]);
                }
            }
            System.out.println();
        }


    }
}
