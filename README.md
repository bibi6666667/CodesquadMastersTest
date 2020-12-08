# CodesquadMastersTest
 2021 코드스쿼드 마스터즈코스 테스트용 저장소입니다.  
 
- 단계별로 루빅스 큐브를 구현한다.
- 할 수 있는 단계까지만 구현한다.
- 단계별로 지정된 코딩 요구사항을 적용한다.
- 단계별로 구현한 코드 동작에 대해 README.md에 정리한다.
- 특별히 명시되지 않은 부분은 자유롭게 구현한다.

소스 코드 관리  
Git과 GitHub을 이용하여 소스 코드를 관리하고 GitHub 저장소 URL을 제출 페이지를 이용해서 제출한다.
- 각 단계별로 브랜치를 작성한다. 브랜치 이름은 각각 step-1, step-2, step-3 로 한다.
- Git과 GitHub의 사용법은 아래 링크를 참고한다.
  - https://backlog.com/git-tutorial/kr/
  - https://www.youtube.com/watch?v=8AtHcXnJSdA&list=PLAHa1zfLtLiPrxoBo9a1HVmauvE2Mn3xX
  - https://www.youtube.com/watch?v=v8CGJJbcHx8&list=PLAHa1zfLtLiNQ03dwSRnNtWTxHUy3mRcF
- 아래 링크를 참고하여 커밋 시점과 단위, 그리고 커밋 메세지를 고려하여 작성한다.
  - https://meetup.toast.com/posts/106
  
- - - 
  
## step-1 branch
1단계 : 단어 밀어내기 구현하기

- `inputWord()` : 띄어쓰기 없는 한 단어 입력받기
  - `wordCheck()` : 띄어쓰기 있는지 확인
- `inputNum()` : -100이상 100미만의 정수.
  - `numCheck()` : -100이상 100미만인지 확인
- `inputLR()` : L,l,R,r 중 하나의 문자.
  - `LRCheck()` : L, l, R, r 이 아닌 다른 문자인지 확인
  
- `trimInput(word, num, LR)` : 각 입력값을 정돈하기
  - word : 각 글자를 요소로 하는 배열로 변환
  - LR : l은 L로, r은 R로 변환
  - num 
    1. 만약 num이 음수라면 양수로 변환한 뒤, L은 R로 R은 L로 바꾼다.
    2. num을 word의 글자수로 나눈 나머지를 구한다.
    > 숫자가 아무리 커도, 글자수로 나눈 나머지만큼만 밀어내면 된다.
    > 밀려나간 단어는 반대쪽으로 채워지기 때문.                                                                                       
    > 0일떄는? 밀어내기 없음.
  
- `pushWord()` : word를 num만큼 L/R방향으로 밀어내기
  - 사실 이 풀이는 '왼쪽/오른쪽으로 밀어내기'보다는 '잘라서 왼쪽/오른쪽에 붙이기'에 더 가까움..
  - 글자 재배치를 위해 `substring(int beginindex, int endindex)`사용.
    - [자바 substring1](https://jamesdreaming.tistory.com/81), [자바 substring2](https://jhnyang.tistory.com/335) 를 참고했습니다. 감사합니다.
  - 왼쪽으로 밀어낼 때는 앞에서부터 num번째까지 잘라내어(wordRight) 나머지 글자(wordLeft)의 뒤에 붙임.
    - `wordLeft = word.substring(num, wordLength);`
    - `wordRight = word.substring(0, num);`
  - 오른쪽으로 밀어낼 때는 뒤에서부터 num번째까지 잘라내어(wordLeft) 나머지 글자(wordRight)의 앞에 붙임.
    - `wordLeft = word.substring(wordLength - num, wordLength);`
    - `wordRight = word.substring(0, wordLength - num);`
    - 결과는 둘 다 `wordLeft`에 `wordRight`를 이어붙인 것이다.
    
- 사실 결과론적으로 답이 맞긴 하지만 2,3단계의 루빅스큐브 풀이에도 이 방식이 통할지는 의문이다. 🤔
- 처음에 Step1Controller, Step1View로 나누어 작업하려 했는데 이상하게 자꾸 에러가 떠서 우선 한 파일로 완성했다.
  - 원인은 Scanner(System.in)의 잘못된 사용이었던 것 같다.
  - [Scanner NoSuchElementException](https://okky.kr/article/508578) 를 참고했습니다. 감사합니다.
  
### TODO 
- 3단계까지 문제를 다 푼 뒤에도 시간이 남으면 리팩토링해야겠다!
  - else, else if 덜 쓸 수 있는지 보기.
  - num들은 int로 받고 형변환 지우기
    
- - - 

## step-2 branch
- 평면큐브 구현하기

이차원배열을 사용해 보았기 떄문에 프로그램 틀을 만드는 것은 어렵지 않았다.

- 자바에서 배열과 문자열은 모두 객체라는 것을 잊지말자ㅠㅠ
  - `==`(비교연산) 대신 `equals()`, `=`(대입연산) 대신 `for문` 으로 복사해야 하는 것을 자꾸 까먹는다. 흑흑..

- 어제 헤멨던 부분 역시 배열을 복사하는 데에 대입연산자 (=)를 써서, 주솟값이 복사되어 발생한 오류였던 것 같다.
  - 깊은복사(deep copy)라고 불리는 작업을 따로 실행하면 될 것 같다. (현재 코드 수정중)
  - deep copy를 쉽게 하기 위해 기존 배열 타입을 String에서 char로 변경했다. 어차피 이차원배열의 모든 요소가 한 문자라서 가능했다.
  - 참고 링크 : [자바 2차원배열 복사](https://limkydev.tistory.com/177), [자바 1차원/2차원배열의 얕은 복사와 깊은 복사deep copy](https://hoho325.tistory.com/89)

성공! 다시 이어서 코딩하자 :D 
- U U' R R' L L' B B' 모두 완성!
- 이제  "한 번에 여러 문자를 입력받은 경우 순서대로 처리해서 매 과정을 화면에 출력한다." 만 구현하면 된다.
  - 막상 만드려고 보니 꽤 어렵다ㅋㅋㅠㅠㅠ 한번 실행하고 다음문자도 실행해야하는데 자꾸 종료된다.
  - 그리고 UU'R처럼 중간에 '가 있는 경우도 잘 split해야 하는데 split("")로는 통하지 않는다ㅜ

- 한번에 여러 문자 입력~ 도 완성!
  - 여러 명령은 일단 split("")로 나눈 뒤, 요소가 '이면 앞의 요소에 더하도록 함. 그리고 새로운 ArrayList를 만들어 '가 아닐 때만 ArrayList에 담음. 사용하는 것은 ArrayList.
  - 원래는 '를 앞 요소에 붙이고 '는 null로 바꾸려 했지만, 굳이 그럴 필요가 없을 것 같아 그냥 '가 아닌 것을 찾아 새로운 ArrayList에 담기로.
    - 참고 링크 : [자바 배열 null 제거](https://www.javaer101.com/ko/article/4316359.html)
  - 기존 명령 체크 메서드 checkInput()의 반복문과, guideInput()의 switch문을 조금 손봤더니 모든 명령을 처리할 수 있게 되었다!
  
TODO : 출력형태 다듬고 테스트용sout 삭제하는 등 약간만 다듬어서 1차 완성하자. 내일 이어서!