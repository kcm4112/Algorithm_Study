package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayList<String> list = new ArrayList<>();

        for(int i=0; i<N; i++){
            String command = br.readLine();
            int size = Integer.parseInt(br.readLine());
            String arrays = br.readLine();

            list.add(checkOrder(command, arrays.substring(1, arrays.length()-1).split(","), size));
        }

        for(String s : list)
            System.out.println(s);

    }

    public static String checkOrder(String order, String[] arrays, int size){
        int position  = 1;
        int start = 0;
        int end = size-1;

        for(int i=0; i<order.length(); i++){
            if(order.charAt(i) == 'D'){
                if(position > 0)
                    start += 1;
                else
                    end -= 1;
                size--;
            }
            else
                position = position * (-1);

            if(size < 0)
                return "error";
        }

        return makeArray(position, arrays, start, end, size);
    }

    public static String makeArray(int position, String[] arrays, int start, int end, int size){
        StringBuilder s = new StringBuilder();
        s.append("[");

        if(position > 0)
            for(int i=start; i<end; i++)
                s.append(arrays[i]+",");

        else {
            for(int i=end; i>start; i--){
                s.append(arrays[i]+",");
            }
            end = start;
        }
        if(size > 0)
            s.append(arrays[end]);

        s.append("]");

        return s.toString();
    }
}