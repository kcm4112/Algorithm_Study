## 🔎문제 해석

> DP를 기반으로 해서 다른 알고리즘을 병행하면서 해결했다.  
> DP를 통해서 쓸데 없는 연산을 하지 않는 메모이제이션을 활용해서, 값을 미리 저장해 두고, 투 포인터를 통해서 범위를 좁혀나갔다.

펠린드롬은 앞에서 뒤로 보나, 뒤에서 앞으로 보나 같은 수열을 팰린드롬이라고 한다.

그럼 양끝지점이 같아야 한다는 뜻이다.

여기서 양끝지점이 다르다면 우리는 왼쪽에 끼워 넣을지, 오른쪽에 끼워넣을지 잘 생각해봐야 한다.

start = 1 / end = n으로 선언하겠습니다.

start와 end를 비교하면서, start와 end를 계속해서 조정해줬습니다.

문제에 테스트케이스를 예시로 들어서  설명하겠습니다.

![](https://blog.kakaocdn.net/dn/3QOFM/btsdOAhB92a/A4EJnrkfzsK5WuanUulTi0/img.png)


 여기서 1과 2를 비교합니다.

1과 2는 다르기에, 1을 2 오른쪽에 끼우거나 2를 1 왼쪽에 끼워야 합니다.

항상 이렇게 끼우는 결과는 왼쪽의 결괏값이 적은지, 오른쪽의 결과값이 작은지 비교해줘야 합니다.

제 코드로는 오른쪽에 끼우는 작업을 우선으로 해줬기 때문에, 오른쪽에 끼우는 작업부터 진행하겠습니다.

![](https://blog.kakaocdn.net/dn/kLa2E/btsdQKDmCep/7qwkO9bE1tAygcru9An8U1/img.png)


빨간색 글자가 추가된 수열이고, 파란색 글자는 현재 제가 추가한 숫자의 개수입니다.

이렇게 되면 우리는 start와 end를 조정합니다.

start를 한 칸 오른쪽으로 움직이고, end는 그대로 둡니다.

![](https://blog.kakaocdn.net/dn/SDp6Q/btsdOzC2W0I/zHnTSzgAEmGnuPjC6PJvL0/img.png)


start와 end의 범위는 같기 때문에, 각각 한 칸씩 옮겨줍니다.

![](https://blog.kakaocdn.net/dn/cOuNIn/btsdPLbIEo3/8pGVVemkVQMgnm1W0UQxTK/img.png)


두 값이 다르기 때문에, 오른쪽에 왼쪽 값을 끼워줍니다.

![](https://blog.kakaocdn.net/dn/cro33M/btsdR1EzCbk/CRfJpAtSOonXoFSCbXn6RK/img.png)

이렇게 되면 최종적인 팰린드롬이 완성됩니다.

해당 과정에서 추가된 숫자의 개수는 총 2개인 것을 알 수 있습니다.

start와 end 지점에서의 숫자가 다를 시, 왼쪽, 오른쪽의 숫자를 반대쪽에 끼워주면 됩니다.

DP \[i\]\[j\]는 i~j까지 팰린드롬을 만들 때 필요한 최소의 추가 숫자를 의미합니다.

DP배열을 -1로 초기화하고, 한 번이라도 연산이 진행된 경우에는(-1이 아닌 경우)는 그대로 값을 반환해 줍니다.

## 😀느낀 점

-   cin.tie(null)과 sync\_with\_stdio(false)를 잘 추가해주지 않는 편이었는데,  
    해당 코드가 시간초과가 뜨길래, 질문게시판을 참고해 보니 , 해당 문구를 추가하면 시간초과가 해결된다고 해서, 추가하니까 허망했다.
-   재귀 말고, 점화식으로 했어야 했는데, 점화식이 막상 떠오르지가 않았다.
-   DP가 약점인 만큼 많이 풀고 싶은데, 정말 어렵다.