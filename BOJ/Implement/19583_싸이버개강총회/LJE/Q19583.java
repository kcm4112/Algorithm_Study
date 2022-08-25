package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Q19583 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Map<String, Boolean> list = new HashMap<>();

        String[] time = br.readLine().split(" ");
        int start = Integer.parseInt(time[0].split(":")[0]+time[0].split(":")[1]);
        int end = Integer.parseInt(time[1].split(":")[0]+time[1].split(":")[1]);
        int real_end = Integer.parseInt(time[2].split(":")[0]+time[2].split(":")[1]);

        String input;
        while((input = br.readLine())!=null){
            String[] student = input.split(" ");
            int student_time = Integer.parseInt(student[0].split(":")[0] + student[0].split(":")[1]);
            if(student_time<=start)
                list.put(student[1], false);
            else if(student_time>=end && student_time<=real_end)
                if(list.get(student[1])!=null)
                    list.put(student[1], true);
        }

        int result=0;
        for(boolean temp:list.values())
            if(temp)
                result++;

        System.out.println(result);
    }
}
