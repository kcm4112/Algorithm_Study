import java.util.*;
import java.io.*;

public class P_2212 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int [] sensor = new int[N];
        int [] diff = new int[N-1];
        int sum = 0;
        if(K>=N){
            System.out.println(0);
        }
        else{
            //센서를 오름차순으로 정렬
            for(int i=0;i<N;i++){
                sensor[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(sensor);

            //센서마다 간격차이를 오름차순으로 정렬
            for(int i=0;i<N-1;i++){
                diff[i] = sensor[i+1] - sensor[i];
            }
            Arrays.sort(diff);

            //예제 1번처럼 중간에 큰 간격이 하나 있다면 집중국이 2개일 때 그 큰 간격을 무시하고 좌,우로 하나씩 배치하면되므로 간격이 가장 큰 것을 무시하고 나머지를 다 더한다.
            //무시할 수 있는 큰 간격의 개수 allow
            int allow = K-1;
            int total = diff.length - allow;
            for(int i=0 ; i<total ; i++){
                sum = sum + diff[i];
            }
            System.out.println(sum);
        }
    }
}
