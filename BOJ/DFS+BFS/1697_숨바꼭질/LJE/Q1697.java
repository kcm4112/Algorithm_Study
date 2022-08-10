package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();
        st = new StringTokenizer(str, " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] distance = new int[100001];
        Arrays.fill(distance, -1);
        Queue<Integer> queue = new LinkedList<>();
        distance[N] = 0;
        queue.offer(N);

        while(!queue.isEmpty()){
            int cur = queue.poll();
            int[] node = new int[]{cur-1, cur+1, cur*2};

            for(int next:node){
                if(next>=0 && next<100001 && distance[next] == -1) {
                    distance[next] = distance[cur] + 1;
                    queue.offer(next);
                }
                if(next == K){
                    System.out.println(distance[next]);
                    return;
                }
            }
        }
    }
}
