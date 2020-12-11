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

**12/7**
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

**12/8**
- 자바에서 배열과 문자열은 모두 객체라는 것을 잊지말자ㅠㅠ
  - `==`(비교연산) 대신 `equals()`, `=`(대입연산) 대신 `for문` 으로 복사해야 하는 것을 자꾸 까먹는다. 흑흑..

- 어제 헤멨던 부분 역시 배열을 복사하는 데에 대입연산자 (=)를 써서, 주솟값이 복사되어 발생한 오류였던 것 같다.
  - 깊은복사(deep copy)라고 불리는 작업을 따로 실행하면 될 것 같다. (현재 코드 수정중)
  - deep copy를 쉽게 하기 위해 기존 배열 타입을 String에서 char로 변경했다. 어차피 이차원배열의 모든 요소가 한 문자라서 가능했다.
  - 참고 링크 : [자바 2차원배열 복사](https://limkydev.tistory.com/177), [자바 1차원/2차원배열의 얕은 복사와 깊은 복사deep copy](https://hoho325.tistory.com/89)
  - 성공! 다시 이어서 코딩하자 :D 

- U U' R R' L L' B B' 모두 완성!
- 이제  "한 번에 여러 문자를 입력받은 경우 순서대로 처리해서 매 과정을 화면에 출력한다." 만 구현하면 된다.
  - 막상 만드려고 보니 꽤 어렵다ㅋㅋㅠㅠㅠ 한번 실행하고 다음문자도 실행해야하는데 자꾸 종료된다.
  - 그리고 UU'R처럼 중간에 '가 있는 경우도 잘 split해야 하는데 split("")로는 통하지 않는다ㅜ

- 한번에 여러 문자 입력~ 도 완성!
  - 여러 명령은 일단 split("")로 나눈 뒤, 요소가 '이면 앞의 요소에 더하도록 함. 그리고 새로운 ArrayList를 만들어 '가 아닐 때만 ArrayList에 담음. 사용하는 것은 ArrayList.
  - 원래는 '를 앞 요소에 붙이고 '는 null로 바꾸려 했지만, 굳이 그럴 필요가 없을 것 같아 그냥 '가 아닌 것을 찾아 새로운 ArrayList에 담기로.
    - 참고 링크 : [자바 배열 null 제거](https://www.javaer101.com/ko/article/4316359.html)
  - 기존 명령 체크 메서드 checkInput()의 반복문과, guideInput()의 switch문을 조금 손봤더니 모든 명령을 처리할 수 있게 되었다!
 

**12/9**
- 전역변수 
  - `sc` : Scanner객체 
  - `cubeBord` : 빈 3*3 2차원배열 
  
- `initCube()` : 제시된 2차원배열을 cubeBoard에 입력하는 메소드 
- `ready()` : `printCube()`, `start()`, `trimInput()`, `checkInput()` 호출하는 메소드
- `start()` : 입력 키 설명 및 명령어(input)를 입력받는 메소드
  - return : input
- `trimInput(input)` : 입력값을 다듬는 메소드
  - 한 번에 여러 명령어를 입력받았을 때 처리하기 위해 추가된 메소드.
  - split("") 한 뒤, 요소가 `'`이면 바로 앞 요소에 `'`를 붙인다.
  - inputList : 정리된 입력값(명령어들)을 담는 ArrayList
    - `'`가 아닌 요소들만 inputList에 담는다.
  - return : inputList
- `checkInput(inputList)` : 정리된 입력값 배열의 각 요소(anInput)들을 검사하는 메소드.
  - anInput들이 U U' R R' L L' B B' Q 중 하나인지 검사.
    - 일치하면 `guideInput(anInput)` 호출.
    - 일치하지 않으면 오류 메시지를 출력하고, 다시 `start()`-`trimInput()`-`checkInput()` 진행.
  - 입력값 배열을 모두 수행했으면 `ready()`를 호출. 
- `guideInput(anInput)` : switch-case문을 통해 입력값에 맞는 메소드로 연결해주는 메소드.
  - `tempCube` : 바뀐 큐브 입력값을 담을 임시변수.
- `copyCube(tempCube)` : cubeBoard을 tempCube로 복사하는 2차원배열 복사 메소드.
  - return : tempCube (cubeBoard가 복사된).
- `pasteCube(tempCube)` : tempCube를 cubeBoard로 붙여넣기하는 2차원배열 복사 메소드.
- `whenU()` `whenUDot()` : 각각 입력값이 U, U'일 때 수행되는 메소드.
- `whenR()` `whenRDot()` : 각각 입력값이 R, R'일 때 수행되는 메소드.
- `whenL()` `whenLDot()` : 각각 입력값이 L, L'일 떄 수행되는 메소드. 
- `whenB()` `whenBDot()` : 각각 입력값이 B, B'일 떄 수행되는 메소드. 


- - - 

## step-3
루빅스 큐브 구현하기

- 빛과 소금같은 참고사이트 [큐브 맞추는 방법](https://cube3x3.com/%ED%81%90%EB%B8%8C%EB%A5%BC-%EB%A7%9E%EC%B6%94%EB%8A%94-%EB%B0%A9/#notation), [루빅 큐브 맞추기](https://rubiks-cube-solver.com/ko/#)..
- 우선은 예제와 조금 달라도 '루빅큐브 맞추기' 도안 색깔 기호를 따라 만들기로 했다.
- step-2를 참고해 프로그램의 큰 틀 먼저 만들었다. (구현할 메서드들)

- printf()를 열심히 활용해 예시와 비슷한 출력형태를 만들었다.
  - 실제로 이 프로그램으로 큐브를 맞추려면 명령어에 대한 전개도 안내가 필요할 것 같아 printf()에 추가했다.
  
- 가능하다면 큐브에 안시코드 색깔도 입혀서 출력해보자!

TODO :  guideInput()과 함께 whenU(), whenU'() .. 및 copyCube() 구현하기.

**12/10**
whenU()를 구현했다! 
- U면을 시계방향 1/4 회전할 때 변하게 되는 5면 (U, L, F, R, B면)의 임시변수를 파라미터로 받아온다.
- 현재 U, L, F, R, B면의 상태를 `cubeUp, cubeLeft ..` 에서 임시변수 `tempUp, tempLeft ..`로 복사해온다.
- U면을 시계방향 1/4회전할 떄 변경사항을 임시변수에 적용한다.
  - U면의 중앙`[1],[1]`을 제외한 모든 요소들의 위치가 변한다.
  - U면과 맞닿은 옆면 L, F, R, B면 일부 요소들의 위치가 변한다.
- 바뀐 임시변수의 내용을 `cubeUp, cubeLeft..`에 복사해 넣는다.
- (임시변수의 변경사항은 새로운 명령어를 실행할 떄마다 `guideInput()`에서 초기화된다)

이제 같은 방식으로 나머지 메서드들도 만들면 된다. 

문제는 메소드 하나에 20줄이 거뜬히 넘는다는 것ㅋㅋㅠㅠ
- 해당 면 시계방향/반시계방향 1/4회전 로직은 공통이므로, 메서드로 따로 만들 수 있을 것 같다.
- copyAtoB 메서드도 6면을 다 복사하는 메서드로 만들면 whenU whenU' .. 메서드 길이를 좀 줄일 수 있을 것 같다.
일단 기능구현부터 해보고 그 다음 중복코드를 보며 리팩토링을 해봐야겠다.

- whenU(), whenUDot(), whenF(), whenFDot(), whenL(), whenLDot(), whenR(), whenRDot() 까지 모든 큐브회전 메서드 구현 완료.
- https://rubiks-cube-solver.com/ko/# 와 비교하며 간단한 테스트도 완료했다.🎉

❗ 아직 루빅스큐브 공식에 쓰이는 B, B', D, D'를 만들지 않았다.
- 참고사이트에는 U L F R만 사용하겠다고 해서 ULFR만 만들었는데, 막상 공식 풀이를 보니 B,D도 나와서 구현해야겠다.
- 또 풀이공식을 보니 U2 L2 F2 R2 B2 D2 라는 표현도 등장한다.
  - 각각 U 2번, L 2번.. 을 하라는 뜻이다. 
- 아래 TODO 하기 전 나머지 B B' D D' U2 L2 F2 R2 B2 D2 들을 구현해야겠다. - 완료!
  
- 테스트하다가 R2 오류가 나길래 디버깅했더니 whenR()메서드 로직 일부가 잘못되어 있었다. 수정 완료!

- whenU(), whenUDot(), whenL(), whenLDot().. 등 모든 큐브회전 메서드들이 너무 길어서 리팩토링을 위해 큐브 복사 메서드를 개선했다.
  - copyAtoB() 대신 copyToTemp(), copyToCube() 생성.
  - 기존에는 바뀌는 면의 파라미터(5개)만 담아온 것에 비해, 모든 면의 임시변수를 파라미터로 받고 바뀌지 않은 면도 그대로 복사한다. 
  - 무의미한 파라미터를 사용하는 대신, 한 회전메서드당 코드 8줄(총 96줄)을 줄였으니 이게 더 나은 것 같아서 바꿨다.

**12/11**

**TODO**
- Q 입력시 '이용해주셔서 감사합니다'로 프로그램 종료 및 조작받은 명령의 갯수 출력 (완료)
- (추가구현) 프로그램 종료시 경과시간 출력
- (추가구현) 큐브 섞기 기능
- (추가구현) 모든 면을 맞추면 축하메시지와 함께 프로그램 종료
- 리팩토링! 가능한 요구조건에 맞게.


- 큐브 섞기 기능 -> 아무렇게나 랜덤을 이용해 섞으면 되는 건가? 큐브 섞는 데에도 뭔가 규칙이 있을 것 같은데.
예를 들면 중심축은 변하지 않고 맞닿은 면끼리 움직이고.. 등등.
  - 현재 있는 U U2 U' L L2 L' F F2 F' .. 메서드들을 랜덤하게 이용해 섞어볼까?
- 모든 면 맞추면 축하,종료 -> 위의 큐브 섞기가 되어야 할 수 있을 것 같다.

  