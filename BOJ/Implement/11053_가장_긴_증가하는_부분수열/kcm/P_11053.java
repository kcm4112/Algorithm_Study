import java.util.*;
import java.io.*;

public class P_11053 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> answer = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        String [] a = s.split(" ");
        int [] list = new int[N];
        int [] dp = new int [N];
        //주어진 배열 생성, dp배열도 모두 1로 초기화. --> 모든 원소가 부분 수열의 처음이 될 수 있으므로.
        for(int i=0;i<a.length;i++){
            list[i] = Integer.parseInt(a[i]);
            dp[i] = 1;
        }

        //1. i번째 까지 봤을 때 만들 수 있는 가장 긴 부분수열의 길이를 dp에 저장한다.
        //2. 주어진 배열을 하나씩 읽을 때마다 현재 위치의 수가 더 크면 dp배열 값을 그 작은 숫자가 가진 dp + 1로 update한다.
        //3. 2의 과정을 따라가면 dp값이 원래는 더 컸다가 다시 작아질 수 있으므로 항상 최대값으로 update.
        for(int i=0;i<N;i++){
            for(int j=0;j<i;j++){
                if(list[i]>list[j]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        int max = 0;
        for(int v : dp){
            if(v>max){
                max = v;
            }
        }
        System.out.println(max);

    }
}
