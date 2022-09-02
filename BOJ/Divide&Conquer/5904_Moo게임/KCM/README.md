## 🧩 풀이
S(k) = S(k-1) + (m + k+2개의 "o") + S(k-1)  (이제부터 "m + k+2개의 o" 부분을 빨간색 부분이라고 칭하겠다.)
이므로 S(k)의 길이는 2*S(k-1) + k+3이다.

그 다음으로 캐치해야될 것은 어쨋든 문제에서 제시한 규칙에 따르면  

moo 문자열들이 양옆과 중앙에 배열된다는 것이다. 그렇다면 우리는 중앙에 배치된 문자열의 길이가 처음에는 3개, 그다음에는 4개 그 다음에는 5개.... 계속 +1씩 증가한다는 것을 할 수 있다.  


moo  

moo <span style={color:red}>mooo</span> moo  

moo mooo moo moooo moo mooo moo  

moo mooo moo moooo moo mooo moo mooooo moo mooo moo moooo moo mooo moo  


이런 느낌으로 말이다.  


이제 나의 풀이방법을 소개해 보겠다.  

1. 먼저 N이 위처럼 빨간색으로 색칠한 구역 안에 있다면??  

: 1번째 위치를 제외하고는 무조건 "o" 가 출력되어야 한다.

 

2. 만약 N이 빨간색 구역 왼쪽에 있다면?

: 빨간색 구역 (중앙) 을 기준으로 양 옆은 똑같은 문자열이므로, 이제 왼쪽에 대해서만 생각하면 된다. 이후 왼쪽에 대하여 1번과정을 다시 확인한다.

이 때, 중간에 있는 문자열의 개수를 조정해주어야 한다! 아마 현재 빨간색 문자열의 길이의 -1이 될 것이다.

 

3. 만약 N이 빨간색 구역 오른쪽에 있다면?<br />

: 2번 과정과 거의 같다. 하지만 우리는 처음부터 빨간색 구역 까지의 문자열을 제외하고 이제부터 오른쪽만 생각할 것이기 때문에, 빨간색 문자열이 끝나는 바로 다음 구간이 첫 번째, 즉 "1"이 되도록 N의 위치를 상대적으로 조정해주어야 한다.

 

moo mooo moo 

위와 같은 문자열에서 만약 N의 값이 8이라면, 3번과정을 거친 후, N의 값은 1이 되어야 한다.(N의 상대적 위치 조정이 필요함!)

 

매우 복잡해보이지만, 코드는 간결하게 나오는 것 같다. 생각하는데 오랜 시간이 걸렸지만, S(k) 가 만들어지는 규칙을 생각해보면 분할정복으로 문제를 풀어야 겠다는 생각이 들것이다.

---

## 🧩 전체 코드
```java
import java.util.*;
import java.io.*;

public class P_5904 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int center = 3; //제일 처음 중앙에 위치한 moo의 개수. (k>=1 일 떄, k+3개 이다)
        int cur = 3; //현재 문자열의 길이. S(0)일 때 3이다.

        if(N<=3){
            if(N==1){
                System.out.println("m");
            }
            else{
                System.out.println("o");
            }
        }

        //N보다 커지는 최소의 S(k)를 찾아야 하므로.
        while(N > cur){
            center++; //중앙의 moo는 계속 한 개씩 늘어난다.
            cur = 2*cur + center;

        }
        int side = (cur-center)/2; //중앙을 제외한 양 옆 S(k-1)의 길이를 구해준다.
        
        while(true){
            if(N>side && N<= side + center){ //만약, N이 중앙에 있다면?
                if(N-side != 1){ //중앙 문자열에서 첫 번째 위치에 있으면 무조건 m이기 때문에 조건을 지정해준다.
                    System.out.println("o");
                    break;
                }
                else{
                    System.out.println("m");
                    break;
                }
            }
            else if(N<=side){ //만약 N이 왼쪽 S(k-1) 문자열 내에 있다면?
                center--; //중앙의 문자열 길이는 1감소할 것.
                side = (side-center)/2; // 양쪽 문자열의 길이를 구해준다.
            }
            else{ //만약 N이 오른쪽에 있었다면?
                N = N - (side + center); //오른쪽의 S(k-1)만 사용할 것이므로 N의 절대적 위치를 상대적 위치로 바꾸어 준다.
                center--;
                side = (side-center)/2;
            }
        }
    }
}
```
