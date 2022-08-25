## 🧩풀이
1. 일단 알고리즘을 떠올리기는 매우 쉬운 문제였다. 

---
🚨Point🚨

R의 경우에 배열을 직접 뒤집을 생각을 하면 안됨!

어차피, D의 경우 원소를 제거할 때는 맨 앞에 것만 제외하므로 (즉, 중간 원소는 건드리는 일이 없어 생각외로 간단하다!)
배열을 직접 뒤집지 말고 R이 한 번 나오고 D가 오는 경우는 맨 앞이아닌, 맨 뒤에서 원소를 제거한다!

이것을 통해 R이 홀수번이면 배열이 뒤집히고, R이 짝수번이면 배열이 다시 원래로 돌아온다는 것을 알 수 있다. 
그러므로, R이 홀수번 나왔냐 혹은 짝수번 나왔냐에 따라 D함수의 수행을 다르게 해주고 나중에 출력을 할 때도, R의 홀짝 여부에 따라 출력을 순방향으로할지 역방향으로할지 결정해주면 된다!
---

2. 나는 flag라는 변수를 -1로 초기화하고 R이 나올 때마다 -1을 곱해주었다.

즉, flag == 1 : R이 홀수번이고 flag==-1 : R이 짝수번임을 의미한다!

 

3. 알고리즘을 잘 짠 경우에도 시간초과가 나는 경우가 있는데, 나는 출력은 printf로 일일이 하는 대신 모든 테스트케이스에 대한 결과를 StringBuilder를 통해 한번에 출력하였다!

---
## 🧩 전체 코드
```java
import java.util.*;
import java.io.*;

public class P_5430 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); //테스트케이스의 개수
        for(int i=0;i<T;i++){
            String func = br.readLine();
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");


            Deque<String> dq = new LinkedList<>(); //큐인데, 시작점과 끝점을 컨트롤 가능.

            for(int j=0;j<n;j++){
                dq.add(st.nextToken());
            }
            int flag = -1; //R이 홀수번 나오면 1, 짝수번 나오면 -1
            for(int v1 = 0; v1<func.length();v1++){
                char temp = func.charAt(v1); //주어진 함수 문자열에서 하나씩 고른다!
                if(temp == 'R'){
                    flag = -1 * flag; //1였으면 -1로, -1였으면 1로 바꿔줌!! 굉장히 기발한 코드 한 줄.
                }
                if(temp == 'D'){
                    if(dq.isEmpty()){
                        sb.append("error\n");
                        flag = 0; //중도 종료된 경우는 아예 다음 테스트케이스로 넘어가도록 플래그 지정.
                        break;

                    }
                    else if (flag == 1){
                        dq.removeLast();
                    }
                    else{
                        dq.removeFirst();
                    }
                }
            }
            int cnt = dq.size();
            if(flag!=0){
                sb.append("[");
                if(cnt>0){ //만약 원소의 개수가 하나 이상이라면
                    if(flag==1){ //만약 R이 홀수개라면?
                        sb.append(dq.removeLast());	// 먼저 뒤에서부터 첫 번째 원소를 넘겨준다.

                        // 그 다음 원소부터 반점을 먼저 넘겨준 후 덱의 원소를 뒤에서부터 하나씩 뽑아 넘긴다.
                        while(!dq.isEmpty()) {
                            sb.append(',').append(dq.removeLast());
                        }
                    }
                    else if(flag == -1){
                        sb.append(dq.removeFirst());	// 먼저 뒤에서부터 첫 번째 원소를 넘겨준다.

                        // 그 다음 원소부터 반점을 먼저 넘겨준 후 덱의 원소를 뒤에서부터 하나씩 뽑아 넘긴다.
                        while(!dq.isEmpty()) {
                            sb.append(',').append(dq.removeFirst());
                        }
                    }
                }
                sb.append(']').append('\n');
            }
        }
        System.out.println(sb);
    }
}
```
---
시간초과가 
계속 
떴다..... 짜증났다....
