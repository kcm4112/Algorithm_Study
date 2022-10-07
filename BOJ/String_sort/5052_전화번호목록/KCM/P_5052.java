import java.util.*;
import java.io.*;

public class P_5052 {
    static int T,N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            N = Integer.parseInt(br.readLine());
            String [] number = new String[N];
            for(int j=0;j<N; j++) {
                number[j] = br.readLine();
            }
            find(number);
        }
    }

    public static void find(String [] arr) {
        Arrays.sort(arr); //문자열 오름차순으로 정렬, 그렇다면 반드시 접두어 관계에 있는 두 전화번호는 붙어있을 것이다.
        for(int k=0;k<N-1;k++) {
            if(arr[k+1].indexOf(arr[k]) == 0) { //k번째 문자열을 k+1째 문자열이 포함하는데, 만약 포함된 문자열의 시작index가 0이라면 접두어라는 의미.
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
        return;
    }
}
