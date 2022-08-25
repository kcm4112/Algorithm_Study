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
