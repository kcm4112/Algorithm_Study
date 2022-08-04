n = int(input())
# word는 입력받는 배열, score은 알파벳 당 점수를 나타냄, num은 점수 sort
word = []
score = {}
num = []

for i in range(n):
    word.append(list(input()))

for i in range(n):
    for j in range(len(word[i])):
        # 알파벳과 해당 알파벳의 자리수를 생각하여 알파벳 당 점수를 계산한다. (한마디로 가중치)
        if word[i][j] not in score:
            score[word[i][j]] = 10 ** (len(word[i])-j-1)
        else:
            score[word[i][j]] += 10 ** (len(word[i])-i-1)

for i in score.values():
    num.append(i)

# 점수가 높은 순으로 9를 줘야되기 때문에 내림차순 정렬을 해야 한다.
num.sort(reverse=True)

sum, cnt = 0, 9

for i in num:
    sum += i * cnt
    cnt -= 1

print(sum)
