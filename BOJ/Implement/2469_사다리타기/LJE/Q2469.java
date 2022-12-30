package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2469 {
    public static int k;
    public static char[] origin;
    public static char[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        origin = new char[k];
        for(int i=0;i<k;++i){
            origin[i] = (char)('A' + i);
        }

        char[] input = br.readLine().toCharArray();

        graph = new char[n][k-1];
        int stop=0;
        for(int i=0;i<n;++i){
            graph[i] = br.readLine().toCharArray();
            if(graph[i][0]=='?')
                stop = i;
        }

        int index1 = 0;
        int index2 = n-1;
        while(true){
            if(index1==stop && index2 == stop)
                break;
            if(index1<stop) {
                makeLine(index1, origin);
                index1++;
            }
            if(index2>stop) {
                makeLine(index2, input);
                index2--;
            }
        }

        String answer="";
        for(int i=0;i<k-1;++i){
            if(origin[i]==input[i])
                answer+="*";
            else if(origin[i]==input[i+1] || origin[i+1]==input[i]) {
                answer += "-";
                swap(i, i+1, origin);
            }
            else{
                for(int j=0;j<k-1;++j){
                    System.out.print('x');
                }
                System.out.println();
                return;
            }
        }

        System.out.println(answer);

    }
    public static void makeLine(int row, char[] string){
        for(int i=0;i<k-1;++i){
            if(graph[row][i] =='*')
                continue;
            else
                swap(i, i+1, string);
        }
    }

    public static void swap(int a, int b, char[] str){
        char temp = str[a];
        str[a] = str[b];
        str[b] = temp;
    }

}
