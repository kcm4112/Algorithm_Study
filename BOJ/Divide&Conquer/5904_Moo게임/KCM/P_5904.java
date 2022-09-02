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
