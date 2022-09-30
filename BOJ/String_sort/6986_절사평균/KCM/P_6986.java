import java.util.*;
import java.io.*;

public class P_6986 {

    static double [] list;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        list = new double [N];

        for(int i=0;i<N;i++){
            list[i] = Double.parseDouble(br.readLine());
        }
        Arrays.sort(list);

        //함수 실행
        jul(list, N, K);
        bojung(list, N, K);
    }

    //절사평균
    public static void jul(double [] a1, int n, int k) {
        double sum = 0;
        for(int i=k;i<n-k;i++){
            sum += list[i];
        }
//        System.out.println("sum = : " + sum);


        System.out.printf("%.2f\n", sum / (n-2*k));
    }

    //보정평균
    public static void bojung(double [] a2, int n, int k) {
        double sum = 0;
        for(int i=0;i<n;i++) {
            if(i<k){
                sum += list[k];
            }
            else if(i >= (n-k)) {
                sum += list[n-k-1];
            }
            else{
                sum += list[i];
            }
        }

//        System.out.println("sum = : " + sum);
        System.out.printf("%.2f\n", sum/n);
    }
}
