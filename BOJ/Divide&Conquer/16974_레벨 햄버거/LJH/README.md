## **👀문제 해석**
-   **초기에는 문자열 그대로를 계속 이어붙여서 풀려고 했다.**
    -   **마지막 보기를 보니 이렇게 풀면 죽었다 깨어나도 제시간에 못풀거같아서 포기했다.**
-   **그 다음에는 일정한 규칙으로(level이 올라갈수록) 햄버거번의 수와 패티의 수가 규칙에 따라 오르는 것을 알 수 있다.**
-   **이러한 규칙으로 햄버거를 입력받은 level까지 만들어 놓은 다음**  
    **햄버거를 쪼개서(분할) x장 까지의 패티의 수를 합치면 되겠다고 생각했다.**
​
## **🔎문제 풀이**
**가정 : 햄버거번 : B , 패티 : P , N : level, X : 먹은 개수**
-   **우선 햄버거의 패티수와 번의 수는 이전 레벨의 햄버거에 영향을 미친다.**
    -   **N햄버거 = B + (N-1)햄버거 + P + (N-1)햄버거 + B**
    -   **이러한 규칙으로 햄버거를 만들 수 있다.**
-   **그럼 우리는 이제 원하는 레벨에서 x장을 먹었을 때 패티를 몇장 먹었는지를 구해야 한다.**
-   **여기서 몇가지 경우의 수가 있습니다.**
    -   **우선 가장 간단한 경우의 수는**  
        -   **우선 x가 1이라면 무조건 빵을 먹었을겁니다. -> 패티의 수는 당연히 0이 될겁니다.**
-   **나머지 경우의 수는 그림으로 설명하는 것이 이해가 빠를거 같습니다.**
​
![img](https://user-images.githubusercontent.com/99114456/210359711-af7a4b3d-9311-4416-ad63-63659fcc1f57.png)
​
-   **1번 구간은 2가지 경우의 수가 있습니다.**
    -   **X가 1(빵) + (N-1) 햄버거의 길이와 같을 경우 -> (N-1) 햄버거에 있는 패티의 수가 정답.**
    -   **X가 1(빵) + (N-1)햄버거의 길이보다 작은 경우 -> 현재 햄버거를 쪼개야 합니다.**
        -   **그럼 우리는 그 (N-1) 햄버거에 접근을 해서 X를 -1 해줘야 합니다.(N레벨에서 빵이 하나 추가 되었기 때문입니다.)**
        -   **이렇게 계속 쪼갤 경우 첫 번째에 빵이 하나 추가되기에 계속 -1을 해줘야 합니다.**
        -   **예시를 들어 보겠습니다.**
-   **X가 3번 구간일 때**  
    -   **X가 1(빵) + (N-1) 햄버거의 길이 + 1(패티)와 같은 경우 -> (N-1) 햄버거에 있는 패티의 수 + 1**
-   **X가 2번 구간 일 때**
    -   **(N-1) 햄버거의 패티수와 가운데 추가된 페티 수가 기본적으로 더해져야 합니다.**
    -   **그다음은 햄버거를 쪼개주는 작업을 합니다. \[이전 레벨 햄버거로 접근하기\]**
        -   **X장을 먹었는데 이미 우리는 B+ (N-1) 햄버거 + P 작업을 처리해 줬기에,**  
            **X = X - (N-1) 햄버거의 길이 + 2(B+P)가 될 겁니다.**
-   **X가 4번 구간 일대**
    -   **(N) 햄버거의 모든 패티의 수를 더해주면 됩니다.**
    -   **(N-1) 햄버거의 패티수\* 2 + 1 해주면 됩니다.** 

## **✔느낀 점**

-   **분할정복을 사용하는 문제는 정말 까다로운 거 같다.**
-   **어떻게 쪼개고 나중에 쪼갠 것을 합칠 때의 코드가 거의 재귀형태로 짜이기 때문에**  
    **재귀 함수를 잘 이해해야 한다.**
-   **보기에 터무니없는 값이 있어서 long long으로 짰는데 어이가 없었다.**
-   **지금 생각해보면 bread 벡터를 괜히 만든 건가 싶다.**
