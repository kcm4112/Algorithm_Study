## **📘 스터디 규칙**

1. 매주 목요일 오후 7시 대면 회의를 1회 진행한다. (장소와 시간은 변경될 수 있음)
2. 문제 풀이 기한은 목요일 ~ 다음주 회의 전까지이다.
3. 매주 4개의 문제를 풀이
4. 본인 코드 설명은 템플릿을 참고하여 `README.md`에 기록
5. 다른 사람의 코드를 보고 코드 리뷰할 것이 있으면 코멘트 남기기.

## **📘 스터디 패널티**

1. 문제 고의적으로 문제를 풀지 않을 시,  5천원
2. 스터디 고의적으로 참여하지 않을 시,  5천원

## **📘 문제집**

<details>
<summary>1주차 ~ 10주차</summary>
<br>
  
||날짜|알고리즘|출처|문제1|문제2|문제3|문제4|알고리즘설명|
|--|--|--|--|--|--|--|--|--|
|**1주차**|07.07. ~ 07.14.|DP + 구현|백준|[숫자야구](https://www.acmicpc.net/problem/2503)|[도로의 개수](https://www.acmicpc.net/problem/1577)|[카드 구매하기](https://www.acmicpc.net/problem/11052)|[동전1](https://www.acmicpc.net/problem/2293)|
|**2주차**|07.14. ~ 07.21.|문자열 + 정렬|백준|[문자열 폭발](https://www.acmicpc.net/problem/9935) | [이차원 배열과 연산](https://www.acmicpc.net/problem/17140) | [괄호 제거](https://www.acmicpc.net/problem/2800) | [센서](https://www.acmicpc.net/problem/2212) |
|**3주차**|07.21. ~ 07.28.| 그리디 |백준|[동전 0](https://www.acmicpc.net/problem/11047) | [DNA](https://www.acmicpc.net/problem/1969) | [단어 수학](https://www.acmicpc.net/problem/1339) | [과제](https://www.acmicpc.net/problem/13904) |[강창민](https://github.com/kcm4112)|
|**4주차**|07.28. ~ 08.04.| DFS,BFS |백준|[빙산](https://www.acmicpc.net/problem/2573) | [토마토](https://www.acmicpc.net/problem/7569) | [촌수계산](https://www.acmicpc.net/problem/2644) | []() |[이재한](https://github.com/jaehan4707)|


</details>

<details>
<summary>11주차 ~ 20주차</summary>
<br>
  
||날짜|알고리즘|출처|문제1|문제2|문제3|문제4|
|--|--|--|--|--|--|--|--|

</details>
<br>
    
    

## **📘 폴더 구조**

[문제 출처] / [알고리즘명] / [문제 제목] / [이름]

문제제목 폴더는 문제번호_문제이름(띄어쓰기는 _ 로 표시)으로 한다.

EX : 11053_가장_긴_증가하는_부분_수열

## **📘 깃허브 사용법**

### **전체적인 흐름**

1. 매주 대면 회의에서 문제 선정 직후, 한 사람이 **main 브랜치에 새로운 문제 폴더를 생성**한다.
2. main 브랜치에서 본인 이름으로 **각자 브랜치를 생성**한다. (처음 한 번만 하면 됨)
3. 본인 브랜치에서 첫 `add` , `commit`, `push` 후 깃허브 페이지에서 **PR을 생성**한다. (레포지토리에서 `Compare & pull request` 버튼 클릭) 
4. 한 번 생성한 PR은 일주일간 유효하며, 다음 회의 시작 시 스터디원들과 함께 `merge`한다.

### **Pull Request**

- PR 제목은 `[해당 주]-[본인 이름]`으로 한다.예시: `week1-kcm4112`
- merge base가 main임을 확인한다.
- Assignees에 본인을 태그하고, Labels에 해당 주에 사용하는 알고리즘을 태그한다.
- 덧붙일 코멘트가 있다면 자유롭게 작성한다.

### **Commit Convention**

- 새로운 문제 파일 추가 시: `Create [문제 번호] [문제 제목]`
    
    `Create 1003 피보나치 함수`
    
- 기존 코드 수정 시: `Modify [문제 번호] [문제 제목]`
    
    `Modify 1003 피보나치 함수`
    

### **코드 설명 방법**

`[template.md](https://github.com/doheez/Algorithm-Study/blob/59b14034ccb576d7a2a7935d5859da0f38cfeb3d/template.md)`를 참고하여 `README.md`를 작성하고 문제 풀이 코드와 동일한 폴더에 올린다.

### **코드 리뷰**

PR에 직접 코멘트를 남겨도 좋고, 코드 일부분에다 리뷰를 해도 된다.
