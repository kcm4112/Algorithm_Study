package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Q2668 {
    public static int[] arr;
    public static boolean[] visited;
    public static int last;
    public static ArrayList<Integer> list;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        visited = new boolean[N + 1];
        list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            last = i;
            DFS(i);
            visited[i] = false;
        }

        Collections.sort(list);
        System.out.println(list.size());
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    public static void DFS(int i) {
        if (!visited[arr[i]]) {
            visited[arr[i]] = true;
            DFS(arr[i]);
            visited[arr[i]] = false;
        }
        if (arr[i] == last) {
            list.add(last);
        }
    }
}