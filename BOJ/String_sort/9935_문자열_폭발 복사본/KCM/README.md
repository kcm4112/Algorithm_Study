๐งฉ ํ์ด
1. StringBuilder๋ฅผ ํตํด ๋ฌธ์์ด์ ์ฃผ์ด์ง๋๋ก ํ๋์ฉ ์์๊ฐ๋ค.

2. for๋ฌธ์ ํตํด ์ฃผ์ด์ง ๋ฌธ์์ด์ ์ฐ์  ์์ ๋ค์, ์์ ๋ฌธ์์ด์ ๊ธธ์ด๊ฐ ํญ๋ฐ๋ฌธ์์ด์ ๊ธธ์ด์ ๊ฐ์์ง๊ฑฐ๋ ๊ทธ ์ด์์ด ๋๋ฉด ๊ทธ ๋๋ง๋ค ํญ๋ฐ๋ฌธ์์ด๊ณผ๋น๊ตํ๋ค.

3. ๋น๊ตํ ๊ฒฐ๊ณผ๊ฐ ํญ๋ฐ๋ฌธ์์ด๊ณผ ๊ฐ์ผ๋ฉด ์์ ๋ฌธ์์ด์ ๋ค์ deleteํ๊ณ  ๊ทธ๋ ์ง ์์ผ๋ฉด ๊ณ์ ์งํํ๋ค.

---

๐งฉ์ ์ฒด ์ฝ๋
```java
import java.util.*;
import java.io.*;

//๋น๊ต๋ ๋ง์ด ํด๋๋จ, ๋ง์๋ดค์ 36๋ฒ์
public class P_9935 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String target = br.readLine();
        StringBuilder sb = new StringBuilder();
        int cnt=0;
        for(int i=0; i<input.length();i++){
            char temp = input.charAt(i);
            sb.append(temp);
            if(sb.length()>=target.length()){
                cnt=0;
                for(int j=0;j<target.length();j++){
                    char c1 = sb.charAt(sb.length()-target.length()+j);
                    char c2 = target.charAt(j);
                    if(c1 == c2){
                        cnt++;
                    }
                    else{
                        break;
                    }
                }
                if(cnt == target.length()){
                    sb.delete(sb.length()-target.length(), sb.length());
                }
            }
        }
        if(sb.length()==0){
            System.out.println("FRULA");
        }
        else{
            System.out.println(sb.toString());
        }
    }
}
```

---

๐งฉ์ฃผ์ํ  ์ 
> input : CCCC4444
ํญ๋ฐ๋ฌธ์์ด : C4

์์ ๊ฐ์ ๊ฒฝ์ฐ, C->C๊ฐ ๋๋ฉด ํญ๋ฐ๋ฌธ์์ด๊ณผ ๊ธธ์ด๊ฐ ๊ฐ์์ ธ์ ๋น๊ต๋ฅผ ํ์ง๋ง ๊ฐ์ง ์๊ธฐ ๋๋ฌธ์ ๊ทธ ๋ค์ ์์๋ฅผ ์งํํ๋ค.

C->C->C์์ ์ฐ์  4๋ฅผ ๋ฃ๊ณ  ํญ๋ฐ๋ฌธ์์ด๊ณผ ๋น๊ต๋ฅผ ํ๋ฉด ๊ทธ ๊ฐ์ด ๊ฐ์ผ๋ฏ๋ก ์์ ๋ฌธ์์ด์์ C4๋ฅผ ์ ๊ฑฐํด์ฃผ๊ณ  ๊ทธ ๋ค์ input์ ์์์ธ 4๋ฅผ ๋ StringBuilder์ ์๋๋ค. ์ด๋ฐ ๊ณผ์ ์ ๊ณ์ํ๋ฉด ๊ฒฐ๊ณผ๊ฐ ๋์ค๊ฒ ๋๋ค.
