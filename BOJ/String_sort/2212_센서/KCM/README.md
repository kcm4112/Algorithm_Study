🧩 풀이
<img width="482" alt="image" src="https://user-images.githubusercontent.com/80253559/179889246-4951fa7f-1338-4bbd-b128-31bc153fc1da.png">
1. 우선 센서의 위치를 오름차순으로 정렬한다.

2. 입력예제1을 손으로 풀어봤을 때, 중간에 [3,6]구간은 집중국을 세울 필요가 없고 해당 구간을 제외하고 양쪽에 집중국을 하나씩 세워서 센서의 위치를 커버하면 되었다.

3. 잘 생각해보면 센서의 위치 차이가 가장 큰 구간의 개수가 주어진 집중국(K)보다 작으면 그 구간은 생각하지 않아도 된다. 무슨 말인지 이해가 안될텐데 자세하게 설명해 보겠다.

4. 오름차순으로 정렬한 센서의 위치 [1, 3, 6, 6, 7, 9] 에서 i번째와 i+1번째 센서의 간격차이를 저장한 배열을 다시 오름차순으로 정렬하면 [0, 1, 2, 2, 3]이 된다. 

5. 3번에서 한 말을 위의 그림을 보며 다시 설명하겠다. 우선 구간[3,6] 즉, 간격 배열의 개수 중에서 간격이 큰 원소부터 K-1개 만큼은 생각을 하지 않아도 된다. 위의 예시에서 K=2이므로 간격 중에서 가장 큰 원소인 3을 생각 하지 않아도 된다는 뜻이다. 해당 [3,6]구간은 무시하고 좌우로 나누면 집중국이 2개가 필요하므로 K개 중에서 K-1개까지 간격을 무시할 수 있다는 뜻이다. 

6. 이렇게 K-1개의 간격을 무시하고 남은 간격을 짧은 순서대로 다 더해주면 답을 찾을 수 있다.

7. K개와 K-1의 의미를 좀 더 자세히 설명하자면 K는 집중국의 개수고 K-1은 간격의 개수이다. 간격이 1개라는 것은 그 간격을 기준으로 좌우 2개의 집중국이 필요하므로 최대 K-1의 간격을 제거할 수 있다.

---

🧩  전체 코드

```java
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
```
---
🧩 주의할 점
>우선, 문제를 이해하기가 어려웠다. 문제만 잘 이해하고 손으로 예제 1번만 풀어보면 알고리즘을 비교적 쉽게 떠올릴 수 있는 것 같다.
중요한 것은 예제 1번은 꼭 손으로 풀어보기!



