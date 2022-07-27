## 🧩 풀이
1. 주어진 DNA N개를 각 자리수에 대하여 탐색한다.

2. 예를 들어, 1번째 인덱스에 대하여 N개의 DNA를 모두 탐색 한 후 A, T, G, C가 각각 등장한 횟수를 Map에 저장한다.

3. 1회 탐색이 1번째 인덱스에 대하여 탐색이 끝나면 가장 많이 등장한 알파벳을 결과인 문자열 S에 추가한다.

4. 2번 과정을 2번째 인덱스 ~ 마지막 인덱스 까지 반복하여 문자열 S를 완성시킨다.

---

## 🧩 전체 코드
```java
import java.util.*;
import java.io.*;

//1. 각 자리별로 가장 많이 나온 알파벳을 채택.
//2. Haming Distance는 DNA의 개수 - 가장 많이 나온 알파벳의 빈도수를 구하면 되고, 이것을 M회 반복한다.

public class P_1969 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String [] arr = new String[N];
        for(int i=0;i<N;i++){
            arr[i] = br.readLine();
        }
        String s = "";
        char ch = ' ';
        int total = 0;
        int num_max = 0 ;
        //각 자리별로 가장 많이 나온 알파벳 구하기.
        for(int i=0;i<M;i++){
            Map <Character, Integer> map = new TreeMap<>();
            for(int j=0;j<N;j++){
                if(!map.containsKey(arr[j].charAt(i))){
                    map.put(arr[j].charAt(i), 1);
                }
                else{
                    map.put(arr[j].charAt(i), map.get(arr[j].charAt(i)) + 1);
                }
            }
            for(Map.Entry <Character, Integer> m : map.entrySet()){
                char key = m.getKey();
                int value = m.getValue();
//                System.out.printf("%c %d", key, value);
//                System.out.printf("%c %d\n", key, value);
                if(value > num_max){
                    num_max = value;
                    ch = key;
                }
                else if(value == num_max){
                    if(key < ch){
                        ch = key;
                    }
                }
            }
            s = s + ch + "";
            total = total + (N - num_max);
            num_max=0;
        }
        System.out.println(s);
        System.out.println(total);
    }
}
```

---

## 🧩  Review
처음에는 AAAAAAAA ~ ZZZZZZZZ 까지 탐색하며 Hamington Distance의 최소값을 구하여 했으나 문제에서 사용하는 알파벳이 A, T, G, C로 한정되어있기 때문에 너무 비효율적인것 같아서 다른 방법을 생각해봤다. 그래서그래서그래서그래서 어쨌든, Hamington Distance를 가장 작게 즉, 주어진 DNA와 최대한 비슷하게 해야하므로 각 자리에 대해서 가장 많이 등장한 알파벳으로 문자열 S를 정해주어야 겠다고 생각했다!
