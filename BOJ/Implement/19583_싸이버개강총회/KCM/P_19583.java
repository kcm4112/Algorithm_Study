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
