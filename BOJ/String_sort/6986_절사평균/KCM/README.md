## 📕 풀이

접근하기 매우 쉽다. 문제에서 하라는 대로 그래도 구현하면 된다.  <br />

단, 주어진 점수 배열에서 절사평균을 구하기 위해 특정 원소를 지우거나, 보정평균을 구하기 위해 특정 원소를 다른 원소로 교체해야 하는 경우 <br />
>실제로 삭제 or 교체를 하면 배열의 index를 다루는데 상당히 복잡해지고 에러도 나기 쉬우므로 그냥 처음부터 배열을 훑어가면서 삭제or교체 해야하는
>원소를 만나면 계산을 하지 않거나 다른 값으로 계산해 주는 방법을 통해 간단히 해결해야한다.

## 📕 전체 코드
```java
import java.util.*;
import java.io.*;

public class P_6986 {

    static double [] list;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        list = new double [N];

        for(int i=0;i<N;i++){
            list[i] = Double.parseDouble(br.readLine());
        }
        Arrays.sort(list);

        //함수 실행
        jul(list, N, K);
        bojung(list, N, K);
    }

    //절사평균
    public static void jul(double [] a1, int n, int k) {
        double sum = 0;
        for(int i=k;i<n-k;i++){
            sum += list[i];
        }
//        System.out.println("sum = : " + sum);


        System.out.printf("%.2f\n", sum / (n-2*k));
    }

    //보정평균
    public static void bojung(double [] a2, int n, int k) {
        double sum = 0;
        for(int i=0;i<n;i++) {
            if(i<k){
                sum += list[k];
            }
            else if(i >= (n-k)) {
                sum += list[n-k-1];
            }
            else{
                sum += list[i];
            }
        }

//        System.out.println("sum = : " + sum);
        System.out.printf("%.2f\n", sum/n);
    }
}

```
