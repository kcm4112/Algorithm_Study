package String_Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q6986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        double[] score = new double[N];
        for(int i=0;i<N;++i){
            score[i] = Double.parseDouble(br.readLine());
        }

        Arrays.sort(score);
        double sum=0;

        //절사 평균
        for(int i=K;i<N-K;++i){
            sum += score[i]*10;
        }
        double avg1=sum/((N-2*K)*10);

        //보정평균
        sum=0;
        for(int i=0;i<K;++i){
            score[i] = score[K];
        }
        for(int i=N-1;i>=N-K;--i){
            score[i] = score[N-K-1];
        }
        for(int i=0;i<N;++i){
            sum += score[i]*10;
        }

        double avg2=sum/(N*10);

        System.out.printf("%.2f\n", avg1);
        System.out.printf("%.2f", avg2);

    }
}