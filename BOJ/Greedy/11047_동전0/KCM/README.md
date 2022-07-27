##🧩 풀이
1.  주어진 돈의 종류를 내림차순으로 정렬

2.  remain변수에 값을 넣는다.

3. 0번째 index부터 탐색을 시작하여 remain보다 작거나 같은 금액이 오면 그 금액으로 나누어준다. 나눈 몫은 total 변수에 계속 더한다.

4. 3번 과정의 나머지 값을 새로운 remain으로 최신화 시켜주고 3번 과정을 반복한다. 

5. remain이 0이 되면 total을  출력하고 종료한다!

---

 
##🧩 전체코드
```java
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

```

---

##🧩Review

그리디 알고리즘을 이용하는 문제였다. 난이도는 매우 쉬웠음!
