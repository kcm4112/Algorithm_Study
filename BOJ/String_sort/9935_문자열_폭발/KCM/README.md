🧩 풀이
1. StringBuilder를 통해 문자열을 주어진대로 하나씩 쌓아간다.

2. for문을 통해 주어진 문자열을 우선 쌓은 뒤에, 쌓은 문자열의 길이가 폭발문자열의 길이와 같아지거나 그 이상이 되면 그 때마다 폭발문자열과비교한다.

3. 비교한 결과가 폭발문자열과 같으면 쌓은 문자열을 다시 delete하고 그렇지 않으면 계속 진행한다.

---

🧩전체 코드
```java
import java.util.*;
import java.io.*;

//비교는 많이 해도됨, 많아봤자 36번임
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

🧩주의할 점
> input : CCCC4444
폭발문자열 : C4
위와 같은 경우, C->C가 되면 폭발문자열과 길이가 같아져서 비교를 하지만 같지 않기 때문에 그 다음 순서를 진행한다.

C->C->C에서 우선 4를 넣고 폭발문자열과 비교를 하면 그 값이 같으므로 쌓은 문자열에서 C4를 제거해주고 그 다음 input의 순서인 4를 또 StringBuilder에 쌓는다. 이런 과정을 계속하면 결과가 나오게 된다.
