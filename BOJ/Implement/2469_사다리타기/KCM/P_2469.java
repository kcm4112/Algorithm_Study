import java.io.*;
import java.util.*;

public class P_2469 {
    static int K,N;
    static char [][] map;
    static int blankLine;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());

        // 최초 순서
        char [] origin = new char[K];
        for (int i = 0; i < K; i++) {
            origin[i] = (char)('A' + i);
        }
        // 결과 순서
        char [] result = br.readLine().toCharArray();

        map = new char[N][K - 1];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            //?로 채워진 blank 찾아두기
            if (input.charAt(0) == '?') {
                blankLine = i;
                continue;
            }
            map[i] = input.toCharArray();
        }


        // 최초 알파벳 순서로 blankLine 까지 사다리 진행
        for (int i = 0; i < blankLine; i++) {
            for (int j = 0; j < K - 1; j++) {
                // 가로 막대를 만났을 때 swap
                if (map[i][j] == '-') {
                    char tmpChar = origin[j];
                    origin[j] = origin[j + 1];
                    origin[j + 1] = tmpChar;
                }
            }
        }

        //최종 결과도 반대로 blankLine 까지 사다리 진행
        for (int i = N - 1; i > blankLine; i--) {
            for (int j = 0; j < K - 1; j++) {
                if (map[i][j] == '-') {
                    char tmpChar = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = tmpChar;
                }
            }
        }

        //최종 두 결과를 비교하여 결과 문자열 완성시킴
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K - 1; i++) {
            // 서로 같으면 그대로 내려가야 하므로 *
            if (origin[i] == result[i]) {
                sb.append("*");
            }
            // 인덱스 하나 차이라면 가로막대를 놔야하므로 -
            else if (origin[i] == result[i + 1]) {
                sb.append("-");
                // 그대로 진행하면 다음 반복에서 또 가로막대를 놓기 때문에 swap
                char tmpChar = origin[i];
                origin[i] = origin[i + 1];
                origin[i + 1] = tmpChar;
            }
            // 이외 경우는 2칸 이상 차이나므로 x로 채운 후 break
            else {
                sb = new StringBuilder();
                for(int n=0;n<K-1;n++) {
                    sb.append("x");
                }
                break;
            }
        }
        System.out.print(sb);
    }

}