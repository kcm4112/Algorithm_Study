## ๐งฉ ํ์ด
1.  ์ฃผ์ด์ง ๋์ ์ข๋ฅ๋ฅผ ๋ด๋ฆผ์ฐจ์์ผ๋ก ์ ๋ ฌ

2.  remain๋ณ์์ ๊ฐ์ ๋ฃ๋๋ค.

3. 0๋ฒ์งธ index๋ถํฐ ํ์์ ์์ํ์ฌ remain๋ณด๋ค ์๊ฑฐ๋ ๊ฐ์ ๊ธ์ก์ด ์ค๋ฉด ๊ทธ ๊ธ์ก์ผ๋ก ๋๋์ด์ค๋ค. ๋๋ ๋ชซ์ total ๋ณ์์ ๊ณ์ ๋ํ๋ค.

4. 3๋ฒ ๊ณผ์ ์ ๋๋จธ์ง ๊ฐ์ ์๋ก์ด remain์ผ๋ก ์ต์ ํ ์์ผ์ฃผ๊ณ  3๋ฒ ๊ณผ์ ์ ๋ฐ๋ณตํ๋ค. 

5. remain์ด 0์ด ๋๋ฉด total์  ์ถ๋ ฅํ๊ณ  ์ข๋ฃํ๋ค!

---

 
## ๐งฉ ์ ์ฒด์ฝ๋
```java
import java.util.*;
import java.io.*;

//1. ์ฃผ์ด์ง ๋์ ์ข๋ฅ๋ฅผ ๋ด๋ฆผ์ฐจ์์ผ๋ก ์ ๋ ฌ
//2. K๋ณด๋ค ์๊ฑฐ๋ ๊ฐ์ ๊ธ์ก์ด ์ค๋ฉด ๋๋ ์ฃผ์ด ๋ชซ์ ๊ฐ์์ ++
//3. 2๋ฒ ๊ณผ์ ์ ๋๋จธ์ง์ ๊ฐ์ ๋ํด์ 2๋ฒ๊ณผ์ ์ ๋ฐ๋ณตํ๋ค๊ฐ ๋๋จธ์ง๊ฐ 0์ด ๋๋ฉด ๋ชซ์ ++ ํด์ค ๊ฒฐ๊ณผ๋ฅผ ๋ง์ง๋ง์ ์ถ๋ ฅ!
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

## ๐งฉReview

๊ทธ๋ฆฌ๋ ์๊ณ ๋ฆฌ์ฆ์ ์ด์ฉํ๋ ๋ฌธ์ ์๋ค. ๋์ด๋๋ ๋งค์ฐ ์ฌ์ ์!
