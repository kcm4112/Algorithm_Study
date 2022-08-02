import java.util.*;
import java.io.*;

//1. 주어진 돈의 종류를 내림차순으로 정렬
//2. K보다 작거나 같은 금액이 오면 나눠주어 몫을 개수에 ++
//3. 2번 과정의 나머지의 값에 대해서 2번과정을 반복하다가 나머지가 0이 되면 몫을 ++ 해준 결과를 마지막에 출력!
public class P_11047 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        List <Integer> money = new ArrayList<>();
        int total = 0;
        int remain = 0;
        for(int i=0; i<N; i++){
            money.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(money, Collections.reverseOrder());
        remain = K;
        for(int i=0;i<N;i++){
            int cur = money.get(i);
            if(cur<=remain){
                total = total +  remain/cur;
                remain = remain%cur;
            }
            if(remain==0){
                break;
            }
        }
        System.out.println(total);
    }
}
