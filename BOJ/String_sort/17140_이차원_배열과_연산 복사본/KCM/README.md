๐งฉํ์ด
1. R์ฐ์ฐ๊ณผ C์ฐ์ฐ ๋ ์ค ํ๋๋ง ์ ๊ตฌํํ๋ฉด ๋๊ฐ์ง ์ฐ์ฐ์ ์ฝ๋๋ ๊ฑฐ์ ๊ฐ๋ค๊ณ  ํ  ์ ์๋ค(๋ฐฐ์ด์ updateํ๋ index๋ง ๋ฐ๋๋ก ํด์ฃผ๋ฉด ๋จ!)

2. R>=C(R์ฐ์ฐ)์ธ ๊ฒฝ์ฐ์ R<C(C์ฐ์ฐ)์ธ ๊ฒฝ์ฐ๋ก ์๊ฐํด์ฃผ์ด์ผ ํ๋ฏ๋ก ๋งค ์ฐ์ฐ๋ง๋ค R๊ณผ C๋ฅผ ์๋กญ๊ฒ ๊ณ ์ณ์ฃผ์ด์ผ ํ๋ค.

3. ์ฃผ์ด์ง 3X3 ๋ฐฐ์ด์ ์ฐ์  R์ฐ์ฐ์ด ์ฐ์ ์ด๋ฏ๋ก ํ์ ๊ธฐ์ค์ผ๋ก ํ์ํ๋ฉฐ ๊ฐ ์ซ์๊ฐ ๋์จ ๋น๋์๋ฅผ ๊ตฌํ๋ค.

4. ๋น๋์๋ HashMap์ ์ด์ฉํ์ฌ ๊ตฌํด์ฃผ์๋ค. (Key : ์ซ์, Value : ๋น๋์)

5. ์ค์ํ ๊ฒ์ ๋์จ ๋น๋์๋ฅผ ์ค๋ฆ์ฐจ์๋ก, ๋ง์ฝ ๋น๋์๊ฐ ๊ฐ๋ค๋ฉด ์ซ์๊ฐ ์์ ์์ผ๋ก ์ ๋ ฌํ๋ ๊ฒ์ธ๋ฐ ์ด ๋ฐฉ๋ฒ์  
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
์์ ๊ฐ์ด Map์ Entry๋ฅผ ์์๋ก ๊ฐ์ง๋ List๋ฅผ ์์ฑํ ํ, Comparator๋ฅผ ์ด์ฉํ์ฌ ์ ๋ ฌํด์ฃผ์๋ค. 

์ ๋ ฌ๋ฐฉ๋ฒ์ ์๋์ ๊ฐ๋ค

> Value(๋น๋์)๊ฐ ๊ฐ๋ค๋ฉด Key(์ซ์)์ ์ค๋ฆ์ฐจ์์ผ๋ก ์ ๋ ฌ, ๊ทธ๋ ์ง ์์ผ๋ฉด Value(๋น๋์)์ ์ค๋ฆ์ฐจ์์ผ๋ก ์ ๋ ฌ

6. ์ ๋ ฌ์ด ๋๋๋ฉด ์ธ๋ฑ์ค๋ฅผ ์ฆ๊ฐ์ํค๋ฉฐ List์ ์๋ (Key,Value)์์ ๋ฐฐ์ด์ Updateํด์ค๋ค.

7. ๋๋ ๋ฐฐ์ด์ ํฌ๊ธฐ๋ฅผ ๋ฏธ๋ฆฌ 101๋ก ์ค์ ํด ๋์๊ธฐ ๋๋ฌธ์, Update๊ฐ ๋์ง ์๋ ๋ถ๋ถ์ 0์ผ๋ก ๋ฐ๊พธ์ด์ค๋ค.(~100๋ฒ index๊น์ง!)

8. ๋ฐฐ์ด์ ํฌ๊ธฐ๋ฅผ 101๋ก ํ ์ด์ ๋ (1 <= r,n,k <= 100)์ด๊ณ  ๋ฌธ์ ์์ ๋ฐฐ์ด์ ์ธ๋ฑ์ค๋ 1๋ถํฐ ์์ํ๋ค๊ณ  ํ์ผ๋ฏ๋ก A[100][100]์ ์ฐธ์กฐํ  ๊ฒฝ์ฐ ๋ฐฐ์ด์ ํฌ๊ธฐ๋ฅผ 100์ผ๋ก ์ค์ ํ๋ฉด index์์ธ ์ค๋ฅ๊ฐ ๋ฐ์ํ๊ธฐ ๋๋ฌธ์ด๋ค.

9. ์ฐ์ฐ์ ๋ด์ฉ์ ์ฝ๋๋ฅผ ๋ณด๋ฉด ๋ฐ๋ก ์ดํด๊ฐ ๊ฐ ๊ฒ์! 


---

๐งฉ์ ์ฒด์ฝ๋

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
        //๋ฐฐ์ด์ ์ธ๋ฑ์ค๋ 1๋ถํฐ ์์ํ๋ฏ๋ก 101ํฌ๊ธฐ๋ก ์ ์ธ
        int [][] A = new int [101][101];
        int R=3;
        int C=3;
        int time = 0;

        //์๋ ฅ๊ฐ ๋์!
        for(int i=1;i<=3;i++){
            StringTokenizer s = new StringTokenizer(br.readLine());
            for(int j=1; j<=3; j++){
                A[i][j] = Integer.parseInt(s.nextToken());
            }
        }
        while(A[r][c]!=k && time <=100){
            //R์ฐ์ฐํ๋ ๋ถ๋ถ
            if(R>=C){
                time++;
                //Hashmap์ ๊ฐ ์ซ์๋ค์ด ๋ช ๋ฒ ๋์๋์ง ์ ์ฅํ๋ค.
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
                    //map์ Value์ ์ค๋ฆ์ฐจ์์ผ๋ก ์ ๋ ฌํ๋ list๋ฅผ ๋ง๋ ๋ค.
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
            //C์ฐ์ฐ ํ๋ ๋ถ๋ถ.
            else{
                time++;
                //Hashmap์ ๊ฐ ์ซ์๋ค์ด ๋ช ๋ฒ ๋์๋์ง ์ ์ฅํ๋ค.
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
                    //map์ Value์ ์ค๋ฆ์ฐจ์์ผ๋ก ์ ๋ ฌํ๋ list๋ฅผ ๋ง๋ ๋ค.
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
