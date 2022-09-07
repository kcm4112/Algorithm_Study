## 🧩 풀이
문제의 조건은 다음과 같다.

1) 종이가 모두 같은 숫자이면 그대로 사용한다. (문제에서 주어진 각 숫자의 개수에 대한 counting을 한다는 뜻!)

2) 만약, 모두 같은 숫자가 아니라면 종이를 9개의 영역으로 나누어주어야 한다. 길이는 무조건 3의 k승으로 주어지기 때문에 9개로 나누어진 각각의 종이는 " {N/3 X N/3} " 배열이 될 것이다.

 

그럼 이 문제에 대한 풀이는 생각보다 간단하다.

1. 종이가 모두 같은 숫자인지 체크!

2. 모두 같은 숫자라면, 그 종이의 숫자가 -1 or 0 or 1 인지 확인하고 counting해준다!

3. 만약 아니라면, 9개의 영역으로 나눈다.

 

위 과정에 대한 수행은 나는 재귀함수로 작성하였다.

영역을 9개로 나눌 때, 9개의 재귀함수를 구현했는데 과연 시간초과가 나지 않을까? 걱정했지만 다행히도 잘 돌아갔다.

 

문제에서 요구하는대로만 구현하면 빠른 시간 안에 통과할 수 있는 문제!

---

## 🧩  전체 코드
```java
import java.util.*;
import java.io.*;

public class P_1780 {
    static int N, one, zero, minus=0;
    static int [][] map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int [N][N];

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(0, 0, N); //처음, 종이의 상태 파악!

        System.out.println(minus);
        System.out.println(zero);
        System.out.println(one);

    }

    //row와 col은 검사할 배열의 시작지점!! [0][0]과 같은 느낌?
    //area의 크기만 지정해주면 row와 col을 시작으로하여 나누어진 전체 배열을 탐색 가능!
    static void divide(int row, int col, int area){
        boolean issame;

        issame = check(row, col, area); //같은 색상인지 체크!

        if(issame){ //만약 현재 종이가 모두 같은 색상이라면!
            if(map[row][col]==1){
                one++;
            }
            else if(map[row][col]==0){
                zero++;
            }
            else{
                minus++;
            }

        }
        else{
            //그렇지 않다면? 9개의 영역으로 나누어 주어야 한다.
            //다음 area의 크기는 현재 area에서 3을 나눈 값일 거임!

            int new_area = area/3;

            //예를 들어 row+3 이렇게 하면 안됨! 배열의 크기가 3보다 작을 수도 있기 때문에!
            //단순하게 생각 금지.. 반드시 한 번 점검하기!

            divide(row, col, new_area); //왼쪽 위
            divide(row+new_area, col, new_area); //왼쪽 중앙
            divide(row+2*new_area, col, new_area); //왼쪽 아래

            divide(row, col+new_area, new_area); //가운데 위
            divide(row+new_area, col+new_area, new_area); //가운데
            divide(row+2*new_area, col+new_area, new_area); //가운데 아래

            divide(row, col+2*new_area, new_area); //오른쪽 위
            divide(row+new_area, col+2*new_area, new_area); //오른쪽 중앙
            divide(row+2*new_area, col+2*new_area, new_area); //오른쪽 아래

            //area가 1이 되면 무조건 같은 색상이므로, 종료조건을 생각할 필요는 없다!
        }


    }

    static boolean check(int row, int col, int area){
        int cur_col = map[row][col]; //기준이 될 색상 지정

        for(int i=row; i<row+area; i++){
            for(int j=col; j<col+area; j++){
                if(map[row][col] != map[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
```
