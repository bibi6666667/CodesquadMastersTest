# CodesquadMastersTest
- '2021 코드스쿼드 마스터즈코스 테스트' 저장소입니다.
- 코드스쿼드 마스터즈 코스 - 웹 백엔드 Java반 지원자 Bibi(bibi6666667@kakao.com)가 작성한 글입니다.
- 아래는 구현 과정 및 구현한 코드에 대한 설명입니다.  


- - - 
- - -

  
# step-1 branch : Step1.java
1단계 : 단어 밀어내기 구현하기


### Step1.java 코드 설명

- `inputWord()` : 띄어쓰기 없는 한 단어 입력받기
  - `wordCheck()` : 띄어쓰기 있는지 확인
- `inputNum()` : -100이상 100미만의 정수.
  - `numCheck()` : -100이상 100미만인지 확인
- `inputLR()` : L,l,R,r 중 하나의 문자.
  - `LRCheck()` : L, l, R, r 이 아닌 다른 문자인지 확인
  
- `trimInput(word, num, LR)` : 각 입력값을 정돈하기
  - 'word' : 각 글자를 요소로 하는 배열로 변환
  - 'LR' : l은 L로, r은 R로 변환
  - 'num' 
    1. 만약 num이 음수라면 양수로 변환한 뒤, L은 R로 R은 L로 바꾼다.
    > 숫자가 음수라면, 그 절댓값인 양수의 숫자에  L/R을 반대로 한 것과 그 결과가 같다.   
    > 예를 들어 -1, L (왼쪽으로 -1글자 밀어내기)의 결과는 1,R(오른쪽으로 1글자 밀어내기)와 그 결과가 같다.                                                       
    2. num을 word의 글자수로 나눈 나머지를 구한다.
    > 숫자가 아무리 커도, 글자수로 나눈 나머지만큼만 밀어내면 된다.   
    > why? 밀려나간 단어는 반대쪽으로 채워지기 때문.     
  
- `pushWord()` : word를 num만큼 L/R방향으로 밀어내기
  - (이 풀이는 '왼쪽/오른쪽으로 밀어내기'보다는 '잘라서 왼쪽/오른쪽에 붙이기'에 더 가깝습니다..)
  - 글자 재배치를 위해 `substring(int beginindex, int endindex)`사용.
    - [자바 substring1](https://jamesdreaming.tistory.com/81), [자바 substring2](https://jhnyang.tistory.com/335) 를 참고했습니다. 감사합니다.
  - 왼쪽으로 밀어낼 때는 앞에서부터 num번째까지 잘라내어(wordRight) 나머지 글자(wordLeft)의 뒤에 붙임.
    - `wordLeft = word.substring(num, wordLength);`
    - `wordRight = word.substring(0, num);`
  - 오른쪽으로 밀어낼 때는 뒤에서부터 num번째까지 잘라내어(wordLeft) 나머지 글자(wordRight)의 앞에 붙임.
    - `wordLeft = word.substring(wordLength - num, wordLength);`
    - `wordRight = word.substring(0, wordLength - num);`
    - 결과는 둘 다 `wordLeft`에 `wordRight`를 이어붙인 것이다.
    


### 개발 과정
**12/7**

- 처음에 Step1Controller, Step1View로 나누어 작업하려 했는데 이상하게 자꾸 에러가 떠서 우선 한 파일로 완성했다.
  - 원인은 Scanner(System.in)의 잘못된 사용이었던 것 같다.
  - [Scanner NoSuchElementException](https://okky.kr/article/508578) 를 참고했습니다. 감사합니다.

- 결과적으로 답이 나오긴 하지만, 2단계와 3단계의 큐브 풀이에도 이 방식이 통하진 않을 듯. 🤔

- - -
- - -



# step-2 branch : Step2.java
2단계 : 평면큐브 구현하기


### Step2.java 코드 설명
- 전역변수
  - `sc` : Scanner객체 
  - `cubeBord` : 빈 3*3 2차원배열 
- `initCube()` : 제시된 2차원배열을 cubeBoard에 입력하는 메소드 
- `ready()` : `start()`, `trimInput()`, `checkInput()`를 호출하는 메소드
- `start()` : 입력 키 설명 및 명령어(input)를 입력받는 메소드
  - return : input
- `trimInput(input)` : 입력값을 다듬는 메소드
  - 한 번에 여러 명령어를 입력받았을 때 처리하기 위해 추가된 메소드.
  - split("") 한 뒤, 요소가 `'`이면 바로 앞 요소에 `'`를 붙인다.
  - `inputList` : 정리된 입력값(명령어들)을 담는 ArrayList
    - `'`가 아닌 요소들만 inputList에 담는다.
  - return : inputList
- `checkInput(inputList)` : 정리된 입력값 배열의 각 요소(anInput)들을 검사하는 메소드.
  - anInput들이 U U' R R' L L' B B' Q 중 하나인지 검사.
    - 일치하면 `guideInput(anInput)` 호출.
    - 일치하지 않으면 오류 메시지를 출력하고, 다시 `start()`-`trimInput()`-`checkInput()` 진행.
  - 입력값 배열을 모두 수행했으면 `ready()`를 호출. 
- `guideInput(anInput)` : switch-case문을 통해 입력값에 맞는 메소드로 연결해주는 메소드.
  - `tempCube` : 바뀐 큐브 입력값을 담을 임시변수.
  - `guideInput()`의 매 실행 후 `printCube()`로 실행 결과를 출력한다.
- `copyCube(tempCube)` : cubeBoard을 tempCube로 복사하는 2차원배열 복사 메소드.
  - return : tempCube (cubeBoard가 복사된).
- `pasteCube(tempCube)` : tempCube를 cubeBoard로 붙여넣기하는 2차원배열 복사 메소드.
- `whenU()` `whenUDot()` `whenR()` `whenRDot()` `whenL()` `whenLDot()` `whenB()` `whenBDot()` 
  - 각각 입력값이 U, U', R, R', L, L', B, B'일 떄 수행되는 메소드. 


### 개발 과정
**12/8**
- 자바에서 배열과 문자열은 모두 **객체**라는 것을 잊지말자ㅠㅠ
  - `==`(비교연산) 대신 `equals()`, `=`(대입연산) 대신 `for문` 으로 복사해야 한다‼‼
  
- 어제 헤멨던 부분이 배열을 복사할 떄 대입연산자 (=)를 써서, 주솟값이 복사되어 발생한 오류였던 것 같다.
  - (해결) 깊은복사(deep copy)라고 불리는 작업을 따로 실행하면 될 것 같다. 
  - deep copy를 쉽게 하기 위해 기존 배열 타입을 String에서 char로 변경했다. 어차피 이차원배열의 모든 요소가 한 문자라서 가능했다.
  - 참고 링크 : [자바 2차원배열 복사](https://limkydev.tistory.com/177), [자바 1차원/2차원배열의 얕은 복사와 깊은 복사deep copy](https://hoho325.tistory.com/89)
- U U' R R' L L' B B' 모두 완성!

**12/9**
- 이제  "한 번에 여러 문자를 입력받은 경우 순서대로 처리해서 매 과정을 화면에 출력한다." 만 구현하면 된다.
  - 문제 1) (해결) 한번 실행하고 다음문자도 실행해야하는데 자꾸 종료된다. 
    - 기존 명령 체크 메서드 checkInput()의 반복문과, guideInput()의 switch문을 조금 손봤더니 모든 명령을 처리할 수 있게 되었다!
  - 문제 2) (해결) UU'R처럼 중간에 '가 있는 경우 어떻게 처리해야 할까? 
    - 여러 명령은 일단 split("")로 나눈 뒤, 요소가 '이면 앞의 요소에 더하도록 함. 
    - 그리고 새로운 ArrayList를 만들어 '가 아닐 때만 ArrayList에 담음. 사용하는 것은 ArrayList.
    - 원래는 '를 앞 요소에 붙이고 '는 null로 바꾸려 했지만, 굳이 그럴 필요가 없을 것 같아 그냥 '가 아닌 것을 찾아 새로운 ArrayList에 담기로.
    - 참고 링크 : [자바 배열 null 제거](https://www.javaer101.com/ko/article/4316359.html)
 


- - - 
- - - 

# step-3 branch : Step3.java
3단계 : 루빅스 큐브 구현하기

### Step3.java 코드 설명
- 전역변수
  - `sc` : Scanner
  - `cubeUp`, `cubeLeft`, `cubeFront`, `cubeRight`, `cubeBack`, `cubeDown` 
    -  큐브의 6면(윗쪽, 왼쪽, 앞쪽, 오른쪽, 뒷쪽, 아랫쪽)을  담당하는 3*3 이차원배열
  - `countNum` : 조작 횟수를 카운팅하는 변수
  - `countTime` : 경과시간을 측정하는 변수  

- `initCube()` : 큐브를 초기화하는 메서드.
  - 큐브의 6면을 기본 색으로 설정 
  - 윗면 = White, 왼쪽면 = Orange, 앞면 = Green
  - 오른쪽면 = Red, 뒷면 = Blue, 아랫면 = Yellow
- `scramble()` : 큐브를 무작위로 섞는 메서드.
  - 루빅스 큐브를 푸는 프로그램이기 떄문에 첫 화면을 제시한 뒤 자동으로 섞어준다.
  - random.nextInt(4)를 통해 0~3까지의 무작위 숫자를 3개 호출
  - 일부 큐브회전 메서드들을 통해 무작위 숫자만큼 큐브를 섞은 뒤 그 모습을 출력한다.
  - 섞인 큐브가 출력된 시점부터 시간이 계산된다(`countTime`).   
- `ready()` :  `start()`-`trimInput()`-`checkInput()`을 차례로 호출하는 메서드.
  - 각각 앞 함수의 리턴값을 파라미터로 받는다.
- `printCube()` : 현재 큐브 6면의 상태를 전개도 모양으로 출력하고, 전개도 도움말을 제공하는 메서드. 
  - printf()를 활용해 큐브의 6면을 전개도 모양으로 출력
  - 현재 큐브의 모양 오른쪽에, 전개도의 각 면에 대한 명령어 기호를 제공 
    - 실제로 사용자에게는 각 면에 대한 설명이 필요할 것 같아 추가함. 
- `start()` : 도움말 및 명령어 설명을 제공하고, 사용자에게 명령어를 입력받는 메서드.
  - return : `input` (사용자로부터 입력받은 명령어)
- `trimInput(input)` : 사용자로부터 입력받은 명령어를 처리하기 좋은 형태로 다듬는 메서드.
  - 사용자의 입력값을 split("")로 한 문자씩 쪼개어 배열로 만듦
  - 결과값을 담을 ArrayList인 `inputList`를 선언.
  - 각 요소가 `'` 또는 `2`와 일치하면, 그 요소의 직전 요소에 `'` 또는 `2`를 붙임.
  - 각 요소가 `'`도 아니고 `2`도 아닐 때만 `inputList`에 추가.
  - return : `inputList`  
- `checkInput(inputList)` : 사용자로부터 입력받은 명령어를 검사하는 메서드.
  - inputList의 각 요소(`anInput`)를 하나씩 확인.
  - `anInput`이 `U U' L L' F F' R R' B B' D D' U2 L2 F2 R2 B2 D2` 중 하나일 때만 `guideInput(anInput)` 호출.
  - 그렇지 않을 때는 오류 메시지를 출력하고 `start()-trimInput()-checkInput()`을 다시 실행.
  - inputList의 모든 명령어 처리가 완료되면 `isComplete()`로 큐브가 완성되었는지 확인하고 `ready()`호출.
- `isComplete()` : 큐브가 완성되었는지 검사하는 메서드.
  - (이 프로그램에서는) 큐브의 6면의 가운데 요소(이차원배열로는 [1][1])는 반드시 초기값과 같다.
  - 즉 윗면은 모두 W, 왼쪽 면은 모두 L, 앞쪽 면은 모두 G, 오른쪽 면은 모두 R, 뒷면은 모두 B, 아랫면은 모두 Y여야 한다.
  - 검사 방법 : 초기 큐브와 똑같은 임시큐브를 만들고, 현재 큐브의 6면이 임시큐브의 6면과 같은지 비교한다.
    - 만약 완성되었다면 축하 메시지를 출력한 뒤 `terminate()`를 호출.
- `terminate()` : 프로그램 종료 메서드
  - Scanner를 닫고 `calTime()`을 호출해 경과시간(밀리초 단위)을 받는다.
  - 받은 경과시간을 분, 초 단위로 계산해 출력한다.
  - `countNum`에 저장된 조작횟수를 출력한다.
  - 프로그램을 종료한다. 
- `calTime()` : 경과시간을 계산하는 메서드.
  - `countTime`에 저장된 시작시간을 받아온다. (시작시간은 `scramble()`에서 저장됨)
  - `calTime()`이 호출된 시간을 종료시간으로 한다.
  - `timeGap` : 종료시간 - 시작시간 (밀리초 단위)을 계산한 결과값
  - return : `timeGap` 
- `guideInput(anInput)` : 사용자의 명령어 하나에 대한 큐브회전메서드를 호출하는 메서드.
  - 각 큐브회전메서드들에 넘길 파라미터인 `temp`임시큐브(윗면,왼쪽면,앞면,오른쪽면,뒷면,아랫면)를 선언
  - switch-case문을 통해 anInput과 일치하는 큐브회전메서드로 연결하고, 입력값에 대한 메시지를 출력
  - U2처럼 1/2바퀴 회전하는 명령어는 whenU()를 두 번 호출. 
  - 각 큐브회전메서드 처리가 끝나면 `countNum`(조작횟수) 에 1을 더하고, 현재 큐브를 출력
- `copyToTemp(temp)` / `copyToCube(temp)` : 임시큐브에 현재 큐브를 복사하는 메서드.
  - 6면의 임시큐브 값을 받아 현재 큐브에 복사하거나 / 현재 큐브 6면의 값을 임시큐브에 복사하는 메서드
  - 모든 큐브회전메서드에 필요한 메서드  
- `clockWise(temp, cube)` / `counterClockWise(temp, cube)` : 
  - 어떤 면을 시계방향 / 반시계방향으로 1/4 회전하는 메서드.
  - 어떤 면의 현재 큐브를 시계방향 / 반시계방향으로 1/4 회전해 임시큐브에 대입함. 
  - 모든 큐브회전메서드에 필요한 메서드
- [큐브회전메서드] 
  - `whenU(temp)`, `whenUDot(temp)`,`whenL(temp)`, `whenLDot(temp)`,`whenF(temp)`, `whenFDot(temp)`,
  - `whenR(temp)`, `whenRDot(temp)`,`whenB(temp)`, `whenBDot(temp)`,`whenD(temp)`, `whenDDot(temp)`, 
  - 명령어에 따라 큐브를 회전시키는 메서드들.
  - 모든 변경사항은 임시큐브에 먼저 적용되고, 변경된 내용이 현재 큐브에 복사된다.
  - (1) 임시큐브에 현재 큐브 내용을 복사해 넣는다
    - why? 큐브회전메서드에서 조작되는 요소를 제외한 요소들은 그대로여야 하기 떄문
  - (2) 각 회전메서드의 회전하는 면을 시계방향/반시계방향으로 회전시킨 결과를 임시큐브에 저장한다. 
    - 시계방향이면 `clockWise(temp, cube)`, 반시계방향이면 `counterClockWise(temp, cube)`
  - (3) 회전하는 면에 인접한 4면에 대한 변경사항을 조작하여 임시큐브에 저장한다.
  = (4) 임시큐브에 저장된 내용이 현재 큐브 상태가 되도록 복사해 넣는다.


### 개발 과정

- 빛과 소금같은 참고사이트 [큐브 맞추는 방법](https://cube3x3.com/%ED%81%90%EB%B8%8C%EB%A5%BC-%EB%A7%9E%EC%B6%94%EB%8A%94-%EB%B0%A9/#notation), [루빅 큐브 맞추기](https://rubiks-cube-solver.com/ko/#)..
- 우선은 예제와 조금 달라도 '루빅큐브 맞추기' 도안 색깔 기호를 따라 만들기로 했다.
- step-2를 참고해 프로그램의 큰 틀 먼저 만들었다. (구현할 메서드들)

- printf()를 열심히 활용해 예시와 비슷한 출력형태를 만들었다.
  - 실제로 이 프로그램으로 큐브를 맞추려면 명령어에 대한 전개도 안내가 필요할 것 같아 printf()에 추가했다.

**12/10**

`whenU()`를 구현했다! 
- U면을 시계방향 1/4 회전할 때 변하게 되는 5면 (U, L, F, R, B면)의 임시변수를 파라미터로 받아온다.
- 현재 U, L, F, R, B면의 상태를 `cubeUp, cubeLeft ..` 에서 임시변수 `tempUp, tempLeft ..`로 복사해온다.
- U면을 시계방향 1/4회전할 떄 변경사항을 임시변수에 적용한다.
  - U면의 중앙`[1],[1]`을 제외한 모든 요소들의 위치가 변한다.
  - U면과 맞닿은 옆면 L, F, R, B면 일부 요소들의 위치가 변한다.
- 바뀐 임시변수의 내용을 `cubeUp, cubeLeft..`에 복사해 넣는다.
- (임시변수의 변경사항은 새로운 명령어를 실행할 떄마다 `guideInput()`에서 초기화된다)   
이제 같은 방식으로 나머지 메서드들도 만들면 된다.    

문제 :  메소드 하나에 20줄이 거뜬히 넘는다는 것ㅋㅋㅠㅠ
- 해당 면 시계방향/반시계방향 1/4회전 로직은 공통이므로, 메서드로 따로 만들 수 있을 것 같다.
- copyAtoB 메서드도 6면을 다 복사하는 메서드로 만들면 whenU whenU' .. 메서드 길이를 좀 줄일 수 있을 것 같다.
- (완료) 일단 기능구현부터 해보고 그 다음 중복코드를 보며 리팩토링을 해봐야겠다.
- https://rubiks-cube-solver.com/ko/# 와 비교하며 간단한 테스트도 완료했다.🎉

❗ 아직 루빅스큐브 공식에 쓰이는 `B, B', D, D'`를 만들지 않았다.
- 참고사이트에는 `U L F R`만 사용하겠다고 해서 ULFR만 만들었는데, 막상 공식 풀이를 보니 B B' D D'도 나와서 구현해야겠다.
- 또 풀이공식을 보니 `U2 L2 F2 R2 B2 D2` 라는 표현도 등장한다.
  - 각각 U 2번, L 2번.. 을 하라는 뜻이다. 
- (완료) 아래 TODO 하기 전 나머지 B B' D D' U2 L2 F2 R2 B2 D2 들을 구현해야겠다. 
  
- (수정 완료) 테스트하다가 R2에서 오류가 나길래 디버깅했더니 whenR()메서드 로직 일부가 잘못되어 있었다. 

**리팩토링**
- whenU(), whenUDot(), whenL(), whenLDot().. 등 모든 큐브회전 메서드들이 너무 길어서 리팩토링을 위해 큐브 복사 메서드를 개선했다.
  - copyAtoB() 대신 copyToTemp(), copyToCube() 생성.
  - 기존에는 바뀌는 면의 파라미터(5개)만 담아온 것에 비해, 모든 면의 임시변수를 파라미터로 받고 바뀌지 않은 면도 그대로 복사한다. 
  - 무의미한 파라미터를 사용하는 대신, 한 회전메서드당 코드 8줄(총 96줄)을 줄였으니 이게 더 나은 것 같아서 바꿨다.

**12/11**
- Q 입력시 '이용해주셔서 감사합니다'로 프로그램 종료 및 조작받은 명령의 갯수 출력 (완료)

- (추가구현) (완성) 프로그램 종료시 경과시간 출력 
  - 주로 System.currentTimeMillis()를 활용.
  - 인스턴스변수 long countTime 추가.
    - 화면에 큐브가 제시된 이후인 ready() 호출 시의 시간을 countTime에 대입.
  - calTime()
    - countTime에 입력되어 있는 시간을 startTime, calTime이 호출되는 시간을 endTime으로 함.
    - return값 : 경과시간인 endTime-startTime(밀리초 단위)
  - terminate()
    - calTime()의 리턴값으로 받아온 경과시간을 분 단위, 초 단위로 변환 및 출력.
  - 참고 링크 
    - [자바 시간계산1](https://m.blog.naver.com/PostView.nhn?blogId=geeyoming&logNo=220442373612&proxyReferer=https:%2F%2Fwww.google.com%2F), 
    - [자바 시간계산2](https://betatester.tistory.com/16), 
    - [자바 System.currentTimeMillis()](http://sjava.net/2008/08/systemcurrenttimemillis-to-second-minute-hour/)

- (추가구현) (완성) 큐브 섞기 기능 
  - 큐브 섞기 기능 -> 랜덤으로 아무렇게나 섞는 게 아닌 나름 규칙이 있을 듯. 중심축은 변하지 않고 모서리는 붙어서 움직이고.. 등등.
  - 현재 있는 U U2 U' L L2 L' F F2 F' .. 메서드들을 랜덤하게 이용해 섞어 구현했다.
  - `scramble() `
    - 처음엔 guideInput() 을 이용했는데, 그렇게 하니 조작횟수도 카운팅되고 printCube()도 되어서 다른 방법을 찾아야 했다.
    - guideInput()과 유사하게 모든 임시큐브 변수를 주고, randomNum만큼 임의의 회전메서드를 반복하게 했다.
      - 참고 링크 : [자바 랜덤](https://library1008.tistory.com/44)
    - scramble()에서 직접 회전메서드들을 호출하기 떄문에 명령어 확인메시지가 출력되는 현상이 나타났다.
      - 명령어 확인 메시지 출력위치를 회전메서드 안에서, guideInput()에서 회전메서드 호출 직전으로 옮겼다.
      - 각 명령어마다 다른 확인메시지를 출력해야 하기 때문에 switch-case문 형식도 수정했다. (더 길어졌다ㅠㅠ)  
  - 큐브를 섞는 시점?
    - 처음에는 S라는 명령어를 입력하면 큐브가 섞이도록 하려 했다.
    - 그런데 어차피 이 프로그램이 큐브를 맞추는 프로그램이기 떄문에, 
    - 처음부터 원래 큐브를 보여준 뒤 scramble()로 섞인 큐브를 제시하게 했다.
     
- (추가구현) (완성) 모든 면을 맞추면 축하메시지와 함께 프로그램 종료 
  - 각 면의 1,1 요소는 움직이지 않게 설계되어 있으므로 현재 cube가 initCube와 동일한지 비교하면 된다.
  - `isComplete()` 중심으로 구현.
    - 처음 안 사실 : .equals()만으로는 이차원배열을 비교할 수 없다..
      - Object.deepEquals()를 사용하거나 반복문으로 한줄씩 equals()로 비교해야 한다.
      - 참고 링크 : [자바 이차원배열 비교](https://bbiyakbbiyak.tistory.com/5)
      
            
- - - 
- - - 

2020년 12월 13일 제출되었습니다.
