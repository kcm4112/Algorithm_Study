## 🧩 풀이
1. 우선 00:00 ~ 개강총회 시작시간까지 입장한 사람들을 {name, value} 형태의 Map에 저장한다.

2. Map의 value값은 입장시 -1, 퇴장 확인시 1로 저장된다.

3. 채팅한 사람들의 입력을 받다가 개강총회가 끝난시간 ~ 스트리밍 종료시간 까지 채팅을 입력한 사람을 input으로 받게 되면, 그 사람의 입장기록이 있는지 확인한다. (Map의 value값이 -1임을 확인하면 됨!)

4. 입장전력이 있다면 그사람의 value값은 1로 바꾸어주고 참석 확인이 완료 되었으므로 count를 해준다.

5. 입장이나 퇴장을 확인할 때, 이미 그 사람이 확인 되었는데도 그 이후에 채팅을 또 입력하는 경우도 있으므로 이 경우를 잘 체크해준다.

6. 나는 시간을 ":"를 제외하고 정수형으로 변환하여 사용하였다. 어차피 00시부터 24시까지 이기때문에 이 시간들을 0부터 2400까지의 정수로 표현되도 무방하다고 생각했다.

---

## 🧩 전체코드
```java
import java.util.*;
import java.io.*;

public class P_19583 {
    public static void main(String[] args) throws IOException{
        Map<String, Integer> map = new LinkedHashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken().replace(":", ""));
        int E = Integer.parseInt(st.nextToken().replace(":", ""));
        int Q = Integer.parseInt(st.nextToken().replace(":", ""));
        int total=0;
        String input = "";
        //읽은 문자열이 없을 때까지 읽어들이는 과정
        while((input = br.readLine()) != null && !input.isEmpty()){ //인텔리제이같은 IDE에서는 EOF를 인식하지 못하기 떄문에 !input.isEmpty()처럼 처리 해주어야 함!
            st = new StringTokenizer(input);
            int time = Integer.parseInt(st.nextToken().replace(":", ""));
            String name = st.nextToken();
            //00:00부터 개강총회를 시작한 시간까지! 입장한 사람 입장확인과정.
            if(time<=S && time>=0){
                if(!map.containsKey(name)){
                    map.put(name, -1);
                }
                else{
                    if(map.get(name)==-1){ //이미 입장처리가 되었는데 또 채팅을 쳤을 경우.
                        continue;
                    }
                }
            }
            // 개강총회를 끝낸 시간부터, 스트리밍이 끝나는 시간까지 입장한사람.
            if(time<=Q && time>=E){
                if(!map.containsKey(name)){ //만약 입장 기록이 없으면 무쓸모이므로, 다음 사람 입력을 받는다.
                    continue;
                }
                else{
                    if(map.get(name)==-1) { //입장 전력이 있는 사람은 카운트를 해준다.
                        map.put(name, 1); //참석 확인을 했으니 value값을 1로 바꾸어준다.
                        total++;
                    }
                    else if(map.get(name)==1){ //이미 참석 확인이 됐는데, 또 채팅이 확인되면 어차피 무쓸모이므로, 다음 사람 입장을 받는다.(이미 카운트가 됐음!)
                        continue;
                    }
                }
            }
        }
        System.out.println(total);
    }
}
```

---

## 🧩 Review
매우 쉬운 문제였다. 문제에서 시키는 대로만 하면된다.

 

테스트케이스 2번에 참석확인이 된 사람이 또 채팅을 치는 예제가 주어지는데 여기에서 문제에서 일어날 수 있는 예외를 빨리 찾았다면 무리없이 잘 풀 수 있다고 생각한다!
