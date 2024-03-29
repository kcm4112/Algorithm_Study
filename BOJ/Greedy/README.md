## **📕 그리디(탐욕) 알고리즘이란?**

- 말 그대로 선택의 순간마다 당장 눈앞에 보이는 최적의 상황만을 쫓아 최종적인 해답에 도달하는 방법이다.
- 여러 경우 중, 하나를 결정해야 할 때마다 그 순간에 최적이라고 생각되는 것을 선택해 나가는 방식으로 진행하여 최종적인 해답을 찾는다.

--- 

## **📕 그리디 알고리즘 문제를 해결하는 방법**

1️⃣ 선택 절차(Selection Procedure) : 현재 상태에서의 최적의 해답을 선택한다.

2️⃣ 적절성 검사(Feasibility Check) : 선택된 해가 문제의 조건을 만족하는지 검사한다.

3️⃣ 해답 검사 (Solution Check) : 원래의 문제가 해결되었는지 검사하고, 해결되지 않았다면 선택 절차로 돌아가 위의 과정을 반복한다.

--- 

## **📕 그리디 알고리즘의 조건**

1️⃣  탐욕스러운 선택 조건 
: 탐욕적인 선택은 앞의 선택이 이후의 선택에 영향을 주지 않는다는 것이다.

>⏰주의⏰
>: 그리디 알고리즘을 사용하면 무조건 최적해가 나오는 것은 아니다. 하지만, 그리디 알고리즘을 사용해 푸는 문제가 나왔을 때 위의 조건을 충족하면 그리디 알고리즘을 사용하는 것이다.
>아래의 예시를 통해, 그리디 알고리즘을 사용해 푸는 문제가 아닌 예시를 알아보자.
    
**[예시]**

![IMG_394CDEA0516B-1](https://user-images.githubusercontent.com/80253559/180388435-d4c3dbc1-7abe-48e1-a377-f29cb392af67.jpeg)

Start에서 시작하여 가장 큰 수를 구하는 문제가 있다고 가정하자

우리는 가장 좋은 결과가 "Start - 6 - 128" 이란 것을 알지만, 선택의 순간마다 당장 눈앞에 보이는 최적의 상황만을 쫓아 최종적인 해답에 도달 하는 그리디 알고리즘은 "Start - 17 - 23" 이 가장 좋은 것이라고 판단한다.

"1️⃣  탐욕스러운 선택 조건"에 따르면,  그리디 알고리즘이 "Start - 17 - 23" Path를 결과로 도출했는데, 알고보니 "6 아래에 123이라는 값이 존재한다. " 라고해서 이미 선택한 길을 바꿀 수 없다.



2️⃣ 최적 부분 구조 조건
: "문제에 대한 최종 해결 방법이 부분 문제에 대해서도 역시 최적의 해결 방법이다." 라는 조건이다. 즉, 전체 문제 안에서 여러 단계가 존재하고,  이 여러 단계 내의 하나 하나에 대해 최적해가 도출되어야 한다는 것이다.

>위와 같은 조건이 성립하지 않는 경우에는 그리디 알고리즘은 최적해를 구하지 못한다.

---


## **📕 그리디 알고리즘의 대표적 예시 - [분할 가능 배낭문제]**
분할 가능한 배낭문제는 대표적이고 간단한 그리디 알고리즘을 이용해 푸는 문제이다.

**[방법]**

: 단위 무게당 가치가 높은 것들부터 담는 기법을 사용하면 된다.



**[예시]**

![IMG_F1BE8F80CAF8-1](https://user-images.githubusercontent.com/80253559/180388737-9a944c98-cce8-4e12-b2a9-14c02cfd3444.jpeg)

위와 같이 4개의 물건들이 주어졌을 때, "가치/무게" 가 높은 순서대로 정렬하면 P1 -> P3 -> P2 -> P4이다.

배낭의 용량이 10kg이므로  P1 + P2 를 우선 담고, 남은 4kg에 P3를 분할하여 담으면, 가장 가치가 높게 배낭을 쌀 수 있다.

---


## **📕 그리디 알고리즘으로 착각할 수 있는 문제 - [분할 불가능한 배낭 문제]**


**[예시]**
>물건의 종류는 4개이고, 배낭이 최대로 담을 수 있는 무게는 7kg이다.
>P1 ) W : 6, V : 13
>P2 ) W : 4, V : 8
>P3 ) W : 3, V : 6
>P4 ) W : 5, V : 12

만약, 분할가능한 배낭문제이면, **P4를 선택 후, P1을 2kg** 담으면 최대가 된다.
그러나, **"분할 불가능한 배낭 문제"**는 위와 같은 방법으로 해서는 안된다. 단순하게 **단위무게당 가치가 높은 것부터 선택하면 P4만을 뽑을 수 밖에 없으므로 가치의 합은 12**가 된다. 하지만, **최적해는 P2와 P3을 뽑은 14**가 될 것이다.

>그러므로, 위와 같이 **"분할이 불가능한 문제는" 그리디 알고리즘으로는 풀 수 없다.** 
>**DP (Dynamic Programming) 기법**을 이용해야하는데 이 방법은 추후에 설명하도록 하겠다.

