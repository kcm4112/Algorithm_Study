package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q14891 {
    public static ArrayList<Integer>[] gear = new ArrayList[5];
    public static ArrayList<Integer> check; // 회전해야할 톱니바퀴 번호와 방향
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 1; i < 5; i++) {
            gear[i] = new ArrayList<>();
            String str = br.readLine();
            for (int j = 0; j <8; j++) {
                gear[i].add(str.charAt(j)-'0'); // 0=N, 1=S
            }
        }

        int K = Integer.parseInt(br.readLine()); // 회전 수
        int[][] manual = new int[K][2];
        for (int i = 0; i < K; i++) { // 회전 톱니바퀴 & 방법
            st = new StringTokenizer(br.readLine());
            manual[i][0] = Integer.parseInt(st.nextToken()); // 톱니바퀴 번호
            manual[i][1] = Integer.parseInt(st.nextToken()); // 방향( 1: 시계 방향, -1:반시계 방향)

            check = new ArrayList<>(List.of(0,0,0,0,0));

            Run(manual[i][0], manual[i][1]);
            makeNew();
        }

        System.out.println(getAnswer());
    }

    public static void Run(int gear_number, int dir){
        int cur = gear_number;
        int direction = dir;
        int next = cur+1;
        int flag = 0;

        check.set(cur, dir);

        for (int i = 0; i < 3; i++) {
            if(next>4) {
                direction = dir;
                cur = gear_number;
                next = gear_number - 1;
                flag = 1;
            }
            if(flag==0){
                if (gear[cur].get(2) == gear[next].get(6)) {
                    check.set(next, 0);
                    for (int j = next+1; j < 5; j++) {
                        check.set(j, 0);
                        i++;
                    }
                    next=5;
                    continue;
                }
                else {
                    direction*=-1;
                    check.set(next, direction);
                    next++;
                }
                cur = next-1;
            }
            else{
                if (gear[cur].get(6) == gear[next].get(2)) {
                    check.set(next, 0);
                    for (int j = next-1; j > 0; j--) {
                        check.set(j, 0);
                        i++;
                    }
                    break;
                }
                else {
                    direction*=-1;
                    check.set(next, direction);
                    next--;
                }
                cur = next+1;
            }

        }
    }

    public static void makeNew() {
        for (int i = 1; i <5; i++) {
            if(check.get(i)==0)
                continue;
            Rotation(i, check.get(i));
        }
    }

    public static void Rotation(int gear_number, int dir){
        int cur = gear_number;

        if(dir == 1){ // 시계 방향
            gear[cur].add(0, gear[cur].get(7));
            gear[cur].remove(8);
        }
        else{ // 반시계 방향
            gear[cur].add(8, gear[cur].get(0));
            gear[cur].remove(0);
        }
    }

    public static int getAnswer() {
        int result = 0;
        for (int i = 1; i <5; i++) {
            if(gear[i].get(0)==0)
                continue;
            else{
                result += Math.pow(2, i-1);
            }
        }

        return result;
    }

}
