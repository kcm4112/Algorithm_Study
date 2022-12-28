import java.io.*;
import java.util.*;

//하나씩 입력받을 때마다 map에 드래곤 커브 그려주기!
public class P_15685 {
    static int N;
    static List<Integer> list; //방향을 담아놓는 변수
    static int answer = 0; //정답의 개수를 구하는 변수
    static boolean [][] map = new boolean[101][101]; //해당 꼭짓점이 드래곤 커브의 일부인지 아닌지 저장하는 배열
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        //0 - 오른쪽, 1 - 위쪽, 2 - 왼쪽, 3 - 아래쪽
        for(int i=0;i<N;i++) {
            list = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()); //방향
            int g = Integer.parseInt(st.nextToken()); //세대
            list.add(d); //시작 방향 저장하고, 입력받은 세대까지의 모든 방향 list 구해주기
            getDirection(g);
            makeMap(x, y); //입력받은 시작 위치를 바탕으로 방향이 저장된 list를 이용하여 드래곤 커브를 그린다.
        }
        findDragon();
        System.out.println(answer);

    }

    public static void getDirection(int g) {
        //list에 저장된 시작방향을 토대로 입력받은 g세대 까지의 모든 방향을 구해주어야 한다.
        for(int i=1;i<=g;i++) {//입력 받은게 0세대 방향이니깐, 1세대부터 g세대까지.
            int size = list.size();
            for(int j=0;j<size;j++) {
                //0세대 - {1}
                //1세대 - {1 2}
                //2세대 - {1 2  3 2} 잘 보면 반으로 나누고 거꾸로 탐색하며 1씩 더해준 모습임
                list.add((list.get(size-j-1) + 1) % 4);
            }
        }
    }
    public static void makeMap(int x, int y) {
        //Map에 그릴 때 만약 (x,y) = (4,2)라면 Map 행렬에서는 2행 4열임을 주의하자!!! 생각생각생각
        //우선 최초 시작 지점은 true로 바꾸어 주어야 한다!
        map[y][x] = true;
        //실시간으로 가장 최근 선분의 끝 지점을 저장해야하므로 변수를 생성한다.
        int ny = y;
        int nx = x;
        for(int i=0;i<list.size();i++) {
            int direction = list.get(i); //그릴 방향을 먼저 찾는다.
            switch (direction) {
                case 0 : // 오른쪽
                {
                    nx = nx+1;
                    map[ny][nx] = true;
                    break;
                }
                case 1 : // 위쪽
                {
                    ny = ny - 1;
                    map[ny][nx] = true;
                    break;
                }
                case 2 : // 왼쪽
                {
                    nx = nx - 1;
                    map[ny][nx] = true;
                    break;
                }
                case 3 : //아래쪽
                {
                    ny = ny + 1;
                    map[ny][nx] = true;
                    break;
                }
                default:
                    break;
            }
        }
    }
    public static void findDragon() {
        //행과 열은 99까지 탐색해야한다.
        //만약 100까지 탐색을하면 +1을 했을 때 index 101을 참조하게 되므로 런타임에러남!!
        for(int i=0; i<=99;i++) {
            for(int j=0;j<=99;j++) {
                if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) {
                    answer++;
                }
            }
        }
    }
}
