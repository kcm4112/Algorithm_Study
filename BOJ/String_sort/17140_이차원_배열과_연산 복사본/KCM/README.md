🧩풀이
1. R연산과 C연산 둘 중 하나만 잘 구현하면 두가지 연산의 코드는 거의 같다고 할 수 있다(배열을 update하는 index만 반대로 해주면 됨!)

2. R>=C(R연산)인 경우와 R<C(C연산)인 경우로 생각해주어야 하므로 매 연산마다 R과 C를 새롭게 고쳐주어야 한다.

3. 주어진 3X3 배열은 우선 R연산이 우선이므로 행을 기준으로 탐색하며 각 숫자가 나온 빈도수를 구한다.

4. 빈도수는 HashMap을 이용하여 구해주었다. (Key : 숫자, Value : 빈도수)

5. 중요한 것은 나온 빈도수를 오름차수로, 만약 빈도수가 같다면 숫자가 작은 순으로 정렬하는 것인데 이 방법은  
```java
List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
                    list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
                        @Override
                        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                            if(o1.getValue() == o2.getValue()){
                                return o1.getKey() - o2.getKey();
                            }
                            else{
                                return o1.getValue() - o2.getValue();
                            }
                        }
                    });
```
위와 같이 Map의 Entry를 원소로 가지는 List를 생성한 후, Comparator를 이용하여 정렬해주었다. 

정렬방법은 아래와 같다

> Value(빈도수)가 같다면 Key(숫자)의 오름차순으로 정렬, 그렇지 않으면 Value(빈도수)의 오름차순으로 정렬

6. 정렬이 끝나면 인덱스를 증가시키며 List에 있는 (Key,Value)쌍을 배열에 Update해준다.

7. 나는 배열의 크기를 미리 101로 설정해 놓았기 때문에, Update가 되지 않는 부분은 0으로 바꾸어준다.(~100번 index까지!)

8. 배열의 크기를 101로 한 이유는 (1 <= r,n,k <= 100)이고 문제에서 배열의 인덱스는 1부터 시작한다고 했으므로 A[100][100]을 참조할 경우 배열의 크기를 100으로 설정하면 index예외 오류가 발생하기 때문이다.

9. 연산의 내용은 코드를 보면 바로 이해가 갈 것임! 


---

🧩전체코드

```java
import java.util.*;
import java.io.*;

public class P_17140 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        //배열의 인덱스는 1부터 시작하므로 101크기로 선언
        int [][] A = new int [101][101];
        int R=3;
        int C=3;
        int time = 0;

        //입력값 대입!
        for(int i=1;i<=3;i++){
            StringTokenizer s = new StringTokenizer(br.readLine());
            for(int j=1; j<=3; j++){
                A[i][j] = Integer.parseInt(s.nextToken());
            }
        }
        while(A[r][c]!=k && time <=100){
            //R연산하는 부분
            if(R>=C){
                time++;
                //Hashmap에 각 숫자들이 몇 번 나왔는지 저장한다.
                for(int i=1;i<=R;i++){
                    Map<Integer, Integer> map = new LinkedHashMap<>();
                    for(int j=1;j<=C;j++){
                        if(!map.containsKey(A[i][j])){
                            map.put(A[i][j], 1);
                        }
                        else{
                            map.put(A[i][j], map.get(A[i][j]) + 1);
                        }
                    }
                    //map의 Value의 오름차순으로 정렬하는 list를 만든다.
                    List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
                    list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
                        @Override
                        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                            if(o1.getValue() == o2.getValue()){
                                return o1.getKey() - o2.getKey();
                            }
                            else{
                                return o1.getValue() - o2.getValue();
                            }
                        }
                    });

                    int idx = 1;
                    for(int a=0;a<list.size();a++){
                        int key = list.get(a).getKey();
                        int value = list.get(a).getValue();
                        if(key==0){
                            continue;
                        }
//                        System.out.printf("%d %d ", key, value);
                        A[i][idx++] = key;
                        A[i][idx++] = value;
//                        System.out.println();
                    }
                    C = Math.max(C, idx-1);
                    while(idx<=100){
                        A[i][idx++]=0;
                    }
                }
            }
            //C연산 하는 부분.
            else{
                time++;
                //Hashmap에 각 숫자들이 몇 번 나왔는지 저장한다.
                for(int i=1;i<=C;i++){
                    Map<Integer, Integer> map = new LinkedHashMap<>();
                    for(int j=1;j<=R;j++){
                        if(!map.containsKey(A[j][i])){
                            map.put(A[j][i], 1);
                        }
                        else{
                            map.put(A[j][i], map.get(A[j][i]) + 1);
                        }
                    }
                    //map의 Value의 오름차순으로 정렬하는 list를 만든다.
                    List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
                    list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
                        @Override
                        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                            if(o1.getValue() == o2.getValue()){
                                return o1.getKey() - o2.getKey();
                            }
                            else{
                                return o1.getValue() - o2.getValue();
                            }

                        }
                    });

                    int idx = 1;
                    for(int a=0;a<list.size();a++){
                        int key = list.get(a).getKey();
                        int value = list.get(a).getValue();
                        if(key==0){
                            continue;
                        }
//                        System.out.printf("%d %d ", key, value);
                        A[idx++][i] = key;
                        A[idx++][i] = value;
//                        System.out.println();
                    }
                    R = Math.max(R, idx-1);
                    while(idx<=100){
                        A[idx++][i]=0;
                    }
                }
            }
        }
        for(int [] a : A){
            for(int b : a){
                System.out.printf("%d ", b);
            }
            System.out.println();
        }
        System.out.println(time>100 ? -1 : time);
    }

}
```
