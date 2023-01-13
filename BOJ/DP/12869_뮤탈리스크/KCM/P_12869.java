import java.io.*;
import java.util.*;

public class P_12869 {
    static int N;
    static List<Integer> mutal_Hp = new ArrayList<>();
    static boolean [][][] checked = new boolean[61][61][61];
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String [] mutal = br.readLine().split(" ");

        for(int i=0;i<mutal.length;i++) {
            int hp = Integer.parseInt(mutal[i]);
            mutal_Hp.add(hp);
        }

        if(mutal.length != 3) { //초기 뮤탈의 개수가 3마리가 아니라면? 나머지 뮤탈은 체력을 0으로 만들어준다.
            int left = 3-mutal.length; //3에서 현재 뮤탈의 개수 빼줌 . (0으로만들 뮤탈의 개수를 구하는 것!)
            for(int i=0; i<left; i++) {
                mutal_Hp.add(0);
            }
        }
        attackMutal(mutal_Hp.get(0), mutal_Hp.get(1), mutal_Hp.get(2), 0);
        System.out.println(answer);
    }

    public static void attackMutal(int hp1, int hp2, int hp3, int cnt) {
        if(hp1 < 0) {
            hp1 = 0;
        }
        if(hp2 < 0) {
            hp2 = 0;
        }
        if(hp3 < 0) {
            hp3 = 0;
        }
        int max = Math.max(Math.max(hp1, hp2), hp3);
        int min = Math.min(Math.min(hp1, hp2), hp3);
        int mid = hp1 + hp2 + hp3 - max - min;

        hp1 = max;
        hp2 = mid;
        hp3 = min;

        if(hp1 <=0 && hp2 <= 0 && hp3 <= 0) { //모두 죽었다면?
            answer = Math.min(answer, cnt);
            return;
        }

        //한마리라도 살아있다면?
        if(checked[hp1][hp2][hp3] == true) {
            return;
        }
        else {
            checked[hp1][hp2][hp3] = true;
        }
        if(answer < cnt) {
            return;
        }

        attackMutal(hp1-9, hp2-3, hp3-1, cnt+1);
        attackMutal(hp1-9, hp2-1, hp3-3, cnt+1);
        attackMutal(hp1-3, hp2-9, hp3-1, cnt+1);
        attackMutal(hp1-3, hp2-1, hp3-9, cnt+1);
        attackMutal(hp1-1, hp2-3, hp3-9, cnt+1);
        attackMutal(hp1-1, hp2-9, hp3-3, cnt+1);
    }
}
