import java.util.*;
import java.io.*;

public class P_1092 {
    static ArrayList<Integer> crain = new ArrayList<>();
    static ArrayList<Integer> box = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            crain.add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        StringTokenizer s = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
            box.add(Integer.parseInt(s.nextToken()));
        }

        Collections.sort(crain, Collections.reverseOrder());
        Collections.sort(box, Collections.reverseOrder());
        int time = 0;

        if(crain.get(0) < box.get(0)) {
            System.out.println(-1);
        }
        else {
            while(!box.isEmpty()) { //박스가 다 없어질 때 까지.
                int curCrain=0; //현재 어디 index의 크레인이 사용되었는지 나타낼 변수.
                for(int i=0;i<box.size();i++) { //박스가 없으면 for문 탈출.
                    if(curCrain == N) { //모든 크레인이 현재 사용중이거나 더이상 사용가능한 크레인이 없으면 다시 처음부터 탐색해야함.
                        break;
                    }
                    if(crain.get(curCrain) >= box.get(i)) {
                        curCrain++; //다음 크레인을 확인해야하므로, index를 +1 해준다.
                        box.remove(i);
                        if(box.size()==0) {
                            break;
                        }
                        i--; //0번째 인덱스를 지우고, 다시 0번째를 봐야하므로, for문에서 +1 될 것을 고려해 미리 -1을 해준다.
                    }
                }
                time++;
            }
            System.out.println(time);
        }
    }
}
