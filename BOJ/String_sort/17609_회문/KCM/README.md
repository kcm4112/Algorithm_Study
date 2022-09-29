## 📕 풀이

우선, 탐색방법은 쉽다. 처음과 마지막에 포인터 두개를 놓고 서로 엇갈릴 때 까지 움직이며 문자열이 같은지 판단하는 것이다.  <br />
하지만 주의할 점이 있다.<br />

> 문자열이 일치하지 않을 경우, 단 1회에 한하여 왼쪽이든 오른쪽이든 해당 문자를 지울 수 있다는 것이다.  

조금 더 생각해보면, 왼쪽 or 오른쪽 문자열을 지우고 탐색을 했을 때, 둘 중 하나라도 무사히 통과하면 "유사회문" 이지만, 둘 다 통과하지 못한다면, 회문이 아니라는 것이다.  

이제 위의 풀이법을 코드로 그대로 구현하면 된다.

---

## 전체 코드

```java
import java.util.*;
import java.io.*;

public class P_17609 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        //Test시작
        for(int i=0;i<N;i++){
            String test = br.readLine();
            boolean flag = true; //만약 가장 바깥쪽 while문을 다 돌았는데 flag가 true라면 회문이다.
            //처음과 마지막에 두 포인터를 두고 서로 중앙으로 이동하면서 검사.
            //만약 돌다가 다른 것을 만난다면, 왼쪽 문자를 지우는 경우와 오른쪽 문자를 지우는 경우로 나누어서 생각해보기.

            int len = test.length();
            int start = 0;
            int end = len-1;
            while(start <= end) {
                char c1 = test.charAt(start);
                char c2 = test.charAt(end);

                if(c1 == c2) {
                    start++;
                    end--;
                    continue;
                }
                else { //이미 한 번 틀렸다는 의미! 앞으로 한번만 더 틀리면 끝남!
                    flag = false;
                    int now_start = start;
                    int now_end = end;
                    int new_start = start+1;
                    int new_end = end-1;
                    int left_flag = 0; //왼쪽 문자열을 지웠을 경우 사용할 플래그, -1이 되면 회문이 아니다.
                    int right_flag = 0;
                    while(new_start <= end) { //왼쪽 문자를 지웠을 경우 체크해보자! 한 번 더 다른 경우가 나오면 회문 아님.
                        c1 = test.charAt(new_start);
                        c2 = test.charAt(end);
                        if(c1 != c2)
                        {
                            left_flag = -1;
                            break;
                        }
                        new_start++;
                        end--;
                    }
                    while(start <= new_end) { //오른쪽 문자를 지웠을 경우 체크해보자! 한 번 더 다른 경우가 나오면 회문 아님.
                        c1 = test.charAt(start);
                        c2 = test.charAt(new_end);
                        if(c1 != c2)
                        {
                            right_flag = -1;
                            break;
                        }
                        start++;
                        new_end--;
                    }
                    if(left_flag != -1 || right_flag != -1) {
                        System.out.println(1); //만약 두가지 경우 중 하나라도 성공한다면 유사회문!
                        break;
                    }
                    else {
                        System.out.println(2);
                        break;
                    }

                }
            }
            if(flag) {
                System.out.println(0);
            }
        }
    }
}

```

