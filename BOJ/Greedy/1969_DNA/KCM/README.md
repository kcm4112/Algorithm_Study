## ๐งฉ ํ์ด
1. ์ฃผ์ด์ง DNA N๊ฐ๋ฅผ ๊ฐ ์๋ฆฌ์์ ๋ํ์ฌ ํ์ํ๋ค.

2. ์๋ฅผ ๋ค์ด, 1๋ฒ์งธ ์ธ๋ฑ์ค์ ๋ํ์ฌ N๊ฐ์ DNA๋ฅผ ๋ชจ๋ ํ์ ํ ํ A, T, G, C๊ฐ ๊ฐ๊ฐ ๋ฑ์ฅํ ํ์๋ฅผ Map์ ์ ์ฅํ๋ค.

3. 1ํ ํ์์ด 1๋ฒ์งธ ์ธ๋ฑ์ค์ ๋ํ์ฌ ํ์์ด ๋๋๋ฉด ๊ฐ์ฅ ๋ง์ด ๋ฑ์ฅํ ์ํ๋ฒณ์ ๊ฒฐ๊ณผ์ธ ๋ฌธ์์ด S์ ์ถ๊ฐํ๋ค.

4. 2๋ฒ ๊ณผ์ ์ 2๋ฒ์งธ ์ธ๋ฑ์ค ~ ๋ง์ง๋ง ์ธ๋ฑ์ค ๊น์ง ๋ฐ๋ณตํ์ฌ ๋ฌธ์์ด S๋ฅผ ์์ฑ์ํจ๋ค.

---

## ๐งฉ ์ ์ฒด ์ฝ๋
```java
import java.util.*;
import java.io.*;

//1. ๊ฐ ์๋ฆฌ๋ณ๋ก ๊ฐ์ฅ ๋ง์ด ๋์จ ์ํ๋ฒณ์ ์ฑํ.
//2. Haming Distance๋ DNA์ ๊ฐ์ - ๊ฐ์ฅ ๋ง์ด ๋์จ ์ํ๋ฒณ์ ๋น๋์๋ฅผ ๊ตฌํ๋ฉด ๋๊ณ , ์ด๊ฒ์ Mํ ๋ฐ๋ณตํ๋ค.

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
        //๊ฐ ์๋ฆฌ๋ณ๋ก ๊ฐ์ฅ ๋ง์ด ๋์จ ์ํ๋ฒณ ๊ตฌํ๊ธฐ.
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

## ๐งฉ  Review
์ฒ์์๋ AAAAAAAA ~ ZZZZZZZZ ๊น์ง ํ์ํ๋ฉฐ Hamington Distance์ ์ต์๊ฐ์ ๊ตฌํ์ฌ ํ์ผ๋ ๋ฌธ์ ์์ ์ฌ์ฉํ๋ ์ํ๋ฒณ์ด A, T, G, C๋ก ํ์ ๋์ด์๊ธฐ ๋๋ฌธ์ ๋๋ฌด ๋นํจ์จ์ ์ธ๊ฒ ๊ฐ์์ ๋ค๋ฅธ ๋ฐฉ๋ฒ์ ์๊ฐํด๋ดค๋ค. ๊ทธ๋์๊ทธ๋์๊ทธ๋์๊ทธ๋์ ์ด์จ๋ , Hamington Distance๋ฅผ ๊ฐ์ฅ ์๊ฒ ์ฆ, ์ฃผ์ด์ง DNA์ ์ต๋ํ ๋น์ทํ๊ฒ ํด์ผํ๋ฏ๋ก ๊ฐ ์๋ฆฌ์ ๋ํด์ ๊ฐ์ฅ ๋ง์ด ๋ฑ์ฅํ ์ํ๋ฒณ์ผ๋ก ๋ฌธ์์ด S๋ฅผ ์ ํด์ฃผ์ด์ผ ๊ฒ ๋ค๊ณ  ์๊ฐํ๋ค!
