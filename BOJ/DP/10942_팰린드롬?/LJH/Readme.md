## 문제 풀이

> 전형적인 DP문제입니다.  
> 해당 구간내에서 매번 연산을 하기에는 너무나 큰 범위이기에, 우리는 메모이제이션 방식을 사용해서 쓸데없는 연산을 줄여야 합니다.  
> 해당 문제에서도, DP와 투포인터 알고리즘을 같이 사용해서 해결했습니다.

입력된 구간을 start와 end로 지정해서 탐색을 시작합니다.

테스트 케이스를 예로 들어서 설명해 보겠습니다.

> 1, 2, 1, 3, 1, 2, 1

1) S =1 , E = 3 \[1,2,1\]

1과 3을 비교합니다.

비교하면 1과1은 같은 걸로 알 수 있습니다.

그러면 이제 한칸씩 전진해서 탐색을 시작합니다.

그럼 S=2, E=2가 되고 

2와 2를 비교하므로, 1부터 3까지의 배열은 팰린드롬인 것을 알 수 있습니다.

그럼 우리는 여기서 DP값을 항상 업데이트해서 DP\[2\]\[2\], DP \[1\]\[3\]은 팰린드롬이라는 것을 저장합니다.

이렇게 계속 값을 저장해서, 메모이제이션 방식을 이용해서 꺼내 쓸 예정입니다.

2) S = 2, E = 5 \[2,1,3,1\]

2와 1을 비교하고, 다르니까 DP\[2\]\[5\]는 팰린드롬이 아니라고 저장해 둡니다.

이렇게 계속해서 탐색범위를 좁히다가 DP값을 검사합니다. 해당 DP값이 만약 1이라면, 팰린드롬이므로, 탐색을 계속해서 진행합니다.

하지만 DP값이 0이라면, 팰린드롬이 아니라는 뜻이므로, 검사를 즉시 중단합니다.

이렇게 되면 쓸데없는 탐색을 줄일 수 있습니다.

## 요약

-   DP\[i\]\[j\] 는 i~j까지의 수열이 팰린드롬인지 아닌지를 나타냅니다.
-   \-1로 초기화하고, -1이 아니라면 메모이제이션을 실행합니다.
-   start와 end의 숫자가 다르다면, 0으로 초기화하고 그 즉시 탐색중단
-   숫자가 같다면 1로 초기화하고, 다음 범위를 탐색합니다.
    -   여기서 1로 초기화하지만, 다음 범위가 팰린드롬이 아니라면 기존 탐색의 값도 0으로 바꿔줍니다.

##  느낀 점

-   문제가 직관적인 편이어서, 이해하기 쉬웠고, 문제도 깔끔했다.
-   메모이제이션에 대해서 잘 모른다면, 이 문제를 통해서 개념을 알아갈수 있을것이다!