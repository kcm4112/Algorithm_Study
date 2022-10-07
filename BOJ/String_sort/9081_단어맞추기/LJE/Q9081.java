package String_Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q9081 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;++i) {
            char [] word = br.readLine().toCharArray();
            int index=-1, index2=0;
            char temp;

            for(int j=word.length-1;j>0;--j) {
                if(word[j-1]<word[j]) {
                    index=j-1;
                    break;
                }
            }

            //제일 마지막 단어
            if(index==-1) {
                for(int j=0;j<word.length;++j) sb.append(word[j]);
                sb.append("\n");
            }

            else {
                for(int j=word.length-1;j>=0;--j) {
                    if(word[index]<word[j]) {
                        index2=j;
                        break;
                    }
                }
                temp = word[index];
                word[index] = word[index2];
                word[index2] = temp;

                //뒷 부분 정렬
                Arrays.sort(word,index+1, word.length);

                for(int j=0;j<word.length;j++)
                    sb.append(word[j]);
                sb.append("\n");
            }

        }
        System.out.println(sb.toString());
    }
}