# 풀이

volume[i] = NUM 이라는 배열은 NUM번째 음악의 볼륨이 i라는 의미이다. 그러기 위해서는 크기가 M+1인 volume 배열을 선언해주어야 한다. (M+1이어야 인덱스가 M까지 있기 때문.)

0번째 곡의 볼륨은 S이므로, volume[S] = 0으로 초기화한다.

for(int i=1; i <= N; i++) 의 반복문을 돌며 i번째 곡의 가능한 볼륨들을 volume[]에 update해주는 로직으로 구현하였다.

i번째 곡의 가능한 볼륨들을 찾으려면 i-1번째 볼륨에서 문제에서 제공한 리스트를 이용하여 +/-를 한 후, M이하이면서 0이상인 것을 찾아야한다. 
찾은 후보들을 list에 저장한 후, list의 각 원소 즉, i번째 곡의 가능한 볼륨들을 volume[]에 업데이트해준다.

모든 과정이 끝난 후, volume[k] = N 인 k값들 중에서 Max(k)가 정답이 된다.

그렇다면 -1을 출력해야 하는 경우는 언제?
당연히 volume[k] = N이 반복문을 돌면서 최신화되지 않았을 경우일 것이다. 이 경우를 찾기 위해 volume이라는 배열을 최초 선언할 때 모든 원소의 값을 -1로 초기화해주고 시작하였다.

---

# 코드
```java
import java.io.*;
import java.util.*;

public class P_1495 {
    static int N, S, M;
    static int [] volume;
    static int [] example;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        volume = new int[M+1];
        String [] s = br.readLine().split(" ");
        example = new int[s.length];

        for(int i=0;i<s.length;i++) {
            example[i] = Integer.parseInt(s[i]);
        }
        for(int i=0;i<=M;i++) {
            volume[i] = -1; //모든 가능한 볼륨은 0으로 초기화해준다.
        }
        volume[S] = 0; //0번째 곡의 볼륨은 S이다.
        for(int i=1;i<=N;i++) {
            //가능한 볼륨의 개수가 엄청 많으므로 (정확한 개수는 모름)
            //리스트를 선언해준다.
            List<Integer> list = new ArrayList<>();
            for(int k=0;k<volume.length;k++) {
                //volume[k] = i-1 일 때 k 값이, i-1번째 곡의 가능한 볼륨 값이다.
                //이 때 k값은 여러개일 수 있으므로 리스트를 하나 선언하고, 매번 초기화하여 그 안에 값을 넣어준다.
                if(volume[k] == i-1) {
                    if(k + example[i-1] <= M && k + example[i-1] >= 0) {
                        list.add(k+example[i-1]);
                    }
                    if(k - example[i-1] <= M && k-example[i-1] >= 0) {
                        list.add(k-example[i-1]);
                    }
                }
            }
            //예제 1에서 마지막 곡의 볼륨을 구할 때, 아직 최종 후보를 다 고르기 전에 volume[10] = 1이었는데 volume[10]=3으로바뀜
            //만약 volume[10] = 2이었다면, 2에 대해 탐색을 다 하기도 전에 바뀌게 되어서 10에서 example[2]를 고려한 값을 구할 수 없다.
            for(int j=0;j<list.size();j++) {
                volume[list.get(j)] = i;
            }

        }
        int max = -1;
        for(int i=0;i<volume.length;i++) {
            if(volume[i] == N) {
                max = Math.max(max, i);
            }
        }
        System.out.println(max);
    }
}

```

