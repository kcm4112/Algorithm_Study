package String_Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1092 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //크레인 수
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> crane = new ArrayList<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; ++i) {
            crane.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(crane, Collections.reverseOrder());
        //박스 수
        int M = Integer.parseInt(br.readLine());
        ArrayList<Integer> box = new ArrayList<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; ++i) {
            box.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(box, Collections.reverseOrder());

        //크레인으로 박스를 옮길 수 없는 경우
        if(crane.get(0)<box.get(0)){
            System.out.println(-1);
            return;
        }

        int time = 0;
        int crane_num=0;
        int index = 0;
        while (!box.isEmpty()) {
            index = 0;
            for(int i=0;i<N;){
                if(index==box.size())
                    break;
                if(crane.get(i)>=box.get(index)){
                    box.remove(index);
                    i++;
                }
                else
                    index++;
            }
            time++;
        }

        System.out.println(time);
    }
}