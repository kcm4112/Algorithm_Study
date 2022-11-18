//import java.io.*;
//import java.util.*;
//class Location { //상근이와 편의점, 페스티벌 장소의 좌표를 저장할 클래스.
//    int x = 0;
//    int y = 0;
//
//    Location(int cx, int cy) {
//        x = cx;
//        y = cy;
//    }
//}
//public class P_9205 {
//    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    static int T, n;
//    static Location [] store; //편의점의 좌표를 저장할 store변수 선언
//    static Location my; //상근이의 좌표 저장할 변수 선언
//    static Location fest; //페스티벌장 좌표 저장할 변수 선언
//
//    public static void main(String[] args) throws IOException {
//        T = Integer.parseInt(br.readLine());
//
//        for(int t=0;t<T;t++) {
//            n = Integer.parseInt(br.readLine()); //편의점 개수
//            store = new Location[n]; //편의점의 개수만큼 store 크기 할당
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            my = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())); //상근이 좌표 저장.
//
//            for(int k=0;k<n;k++) { //편의점의 좌표를 저장해야한다.
//                st = new StringTokenizer(br.readLine());
//                store[k] = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
//            }
//            st = new StringTokenizer(br.readLine());
//            fest = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
//            bfs();
//        }
//
//    }
//
//    public static void bfs() {
//        Queue<Location> q = new LinkedList<>();
//        boolean [] visited = new boolean[n];
//        q.add(my);
//
//        while(!q.isEmpty()) {
//            Location v = q.remove();
//            if(Math.abs(v.x - fest.x) + Math.abs(v.y - fest.y) <= 1000) {
//                System.out.println("happy");
//                return;
//            }
//
//            for(int i=0;i<n;i++) {
//                if(!visited[i]) {
//                    if(Math.abs(v.x - store[i].x) + Math.abs(v.y - store[i].y) <= 1000) { //만약 갈 수 있는 편의점이라면?
//                        visited[i] = true; //방문 처리해주고
//                        q.add(store[i]); //현재 위치를 큐에 넣어준다.
//                    }
//                }
//            }
//        }
//        //만약 큐가 while문 안에서 return을 하지 못하였다면? 도착하지 못하는 경우임.
//        System.out.println("sad");
//        return;
//    }
//}
