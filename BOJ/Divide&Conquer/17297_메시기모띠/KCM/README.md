# 풀이

우선 messi(1)과 messi(2)의 문자열과 길이를 각각 리스트에 저장한다. </br>
문자열을 담은 리스트를 answer, 길이를 담은 리스트를 len 이라고 선언하였다. </br>
answer에는 messi(1)과 messi(2)의 정보만 저장하고, answer의 0번째와 len의 0번째는 messi(0)이라는 것이 존재하지 않기 때문에 각각 ""와 0을 저장해주었다. </br>

이제, 반복분을 messi(N)의 길이가 M이상이 될 때까지 수행한다. 아래와 같은 식을 통해 messi(N)의 길이를 구할 수 있다.</br>
**messi(N).length = messi(N-1).length() + 1 + messi(N-2).length()** 

위의 결과로 얻은 N을 가지고 재귀 호출을 하며 답을 구할 것이다. </br>
전체적인 핵심 로직은 아래와 같다.

만약 M 이라는 것이, messi(N)을 이루고 있는 부분 중 공백의 왼쪽 부분인 messi(N-1) + 1 보다 작다면 messi(N-1)에 대하여 다시 탐색한다. </br>
만약 M 이라는 것이, messi(N)을 이루고 있는 부분 중 공백의 오른쪽 부분인 messi(N-1) + 1보다 크다면 messi(N-2)에 대하여 탐색하는데 이 때 **M의 값은 M - (messi(N-1).length + 1)** 이 된다. </br>
만약 M 이라는 것이, messi(N)을 이루고 있는 부분 중 공백에 해당하는 messi(N-1) + 1과 같다면 **"Messi Messi Gimossi"**를 출력한다. </br>

위와 같은 로직을 재귀호출 과정에서 N이 1또는 2가 될 때까지 수행한다. 그 이유는 stackoverflow를 피하기 위해 messi(N)에 해당하는 문자열을 모두 구할 수 없으므로 messi(1)과 messi(2)에 해당하는 문자열만 알고있기 때문이다.

---

# 코드
```java
import java.util.*;
import java.io.*;

public class P_17297 {
    static int M, cnt;
    static String messi = "";
    static String messi_N, messi_N_1, messi_N_2;
    static List<String> answer = new ArrayList<>();
    static List<Integer> len = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        //0번째에는 default로 ""를 넣어준다.
        answer.add("");
        len.add(0);
        len.add(5);
        len.add(13);
        answer.add("Messi");
        answer.add("Messi Gimossi");
        if(M<=13) {
            if(answer.get(2).charAt(M-1) == ' ') {
                System.out.println("Messi Messi Gimossi");
            }
            else {
                System.out.println(answer.get(2).charAt(M-1));
            }
        }
        else {
            cnt = 3;
            while(true) {
                len.add(len.get(cnt-1) + 1 + len.get(cnt-2));
                if(len.get(cnt) >= M) {
                    break;
                }
                else {
                    cnt++;
                }
            }
            //최종적으로 M 이상의 길이를 가진 Messi(N) 중에서 N이 가장 작은 Messi 문자열의 길이가 나왔을 것이고 그 때의 cnt 즉, N을 알 수 있을 것이다.
            solve(cnt, M);
        }
    }
    public static void solve(int N, int M) {
        if(N == 1 || N == 2) {
            if(N == 1) {
                System.out.println(answer.get(1).charAt(M-1));
                return;
            }
            else if(N == 2) {
                if(answer.get(2).charAt(M-1) == ' ') {
                    System.out.println("Messi Messi Gimossi");
                }
                else {
                    System.out.println(answer.get(2).charAt(M-1));
                }
                return;
            }
        }
        else if (M == len.get(N-1) + 1) { //공백일 경우
            System.out.println("Messi Messi Gimossi");
            return;
        }
        else if(M < len.get(N-1) + 1) { //messi(N) 의 공백 부분보다 작을 경우
            solve(N-1, M);
        }
        else if(M > len.get(N-1) + 1) { //messi(N) 의 공백 부분보다 클 경우
            solve(N-2, M - (len.get(N-1) + 1));
        }
    }
}

```
