## 문제 풀이

### 초기 풀이

> 초기 풀이는 DP 배열을 2차원 배열로 선언했다.  
> 그리고 방향 배열도 2차원 배열로 선언해서 그 좌표에 대한 방향을 기록해 줬다.  
> 진행하면서 이동할 수 있는 경로에 대해서 그 순간순간 최솟값을 찾아서 그때의 방향을 기록해 줬다.  
> 이러니까 바로 틀렸다. 나는 그냥 그 현재지점과 이동하려는 길만 생각해 줬기 때문에 당연히 특정 테스트케이스에서   
> 틀릴 수밖에 없었다..  
> 나는 3차원 배열로 풀기 싫어서 2차원 배열을 2개 만들어서 풀었는데, 틀렸던 모양이다.

### 수정된 풀이

> 따라서 3차원 배열을 통해 X, Y, Dir를 모두 저장하는 배열을 생성해서 풀었다.  
> 방향은 3개가 있으므로, 배열의 크기는 DP \[N\]\[M\]\[3\]이 될 것이다

-   3개의 방향으로 이동할 수 있다.
    -   0 : ↙️  , 1 : ⬇️,  2 : ↘️ 
    -   즉 화살표 방향으로 온다는 뜻이다.
-   3개의 경우의 수로 방향을 특정할 수 있다.
    -   왼쪽 끝일 경우는 왼쪽 위(2) 방향에서 오는 경우가 없다.
        -   따라서 방향은 0과 1만 신경 써주면 된다.
    -   오른쪽 끝일 경우는 오른쪽 위(0) 방향에서 오는 경우가 없다.
        -   따라서 방향은 1과 2만 신경 써주면 된다.
    -   그 밖의 경우는 세 방향 모두 신경 써줘야 한다.

쉽게 설명하자면,

>  DP \[i\]\[j\]\[0\] = 좌표(i,j)까지 가는데 ↙️이동을 한다는 뜻입니다.  
> 따라서 DP\[i\]\[j\]\[0\] = DP \[i-1\]\[j+1\]\[1\]과 DP \[i-1\]\[j+1\]\[2\]의 최솟값을 더해줘야 합니다.  
> DP \[i-1\]\[j+1\]\[0\]을 포함시키지 않는 이유는 똑같은 방향으로 연속 이동이 불가능하기 때문입니다.

예를 하나 더 들자면,

> DP \[i\]\[j\]\[1\] = 좌표 (i, j)까지 가는데 ⬇️ 이동을 한다는 뜻입니다.  
> 따라서 DP \[i\]\[j\]\[1\]= DP \[i-1\]\[j\]\[0\]과 DP \[i-1\]\[j\]\[2\]의 최솟값을 더해줘야 합니다.  
> 위에 설명과 동일하게 똑같은 방향으로 연속 이동이 불가능하기에 DP \[i-1\]\[j\]\[1\]은 제외시켜 줍니다.

위치에 따라서 방향을 제외시켜 주면서 최솟값을 비교해서 쭉쭉 더해주면 됩니다.

## 😀느낀 점

> 3차원 배열로 푸는 풀이를 그다지 선호하지 않았다. 왜냐하면 메모리 제한 때문이다.  
> 하지만 배열의 최대 크기를 보고 메모리를 계산하는 방법을 몰라서 그랬던 거 같다.  
> 앞으로는 배열의 최대 크기를 보고 메모리를 계산해서 효과적인 자료구조를 정해서 알고리즘을 풀어야겠다고 느꼈다.
