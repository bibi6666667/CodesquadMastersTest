package Step3;

import java.util.Scanner;
import java.util.ArrayList;

public class Step3 { // 루빅스 큐브 구현하기
    Scanner sc = new Scanner(System.in);

    char[][] cubeFront = new char[3][3]; // 앞면
    char[][] cubeUp = new char[3][3]; // 윗면
    char[][] cubeLeft = new char[3][3]; // 왼쪽면
    char[][] cubeRight = new char[3][3]; // 오른쪽면
    char[][] cubeDown = new char[3][3]; // 아랫면
    char[][] cubeBack = new char[3][3]; // 뒷면

    public static void main(String[] args) {
        Step3 step3 = new Step3();
        System.out.println("🗝 루빅스 큐브를 풀어 보세요!");
        step3.initCube();
        step3.ready();
    }

    public void initCube() {
        char[][] initFront = new char[3][3];
        char[][] initUp = new char[3][3];
        char[][] initLeft = new char[3][3];
        char[][] initRight = new char[3][3];
        char[][] initDown = new char[3][3];
        char[][] initBack = new char[3][3];
        initFront[0] = new char[]{'G', 'G', 'G'}; // initFront : Green
        initFront[1] = new char[]{'G', 'G', 'G'};
        initFront[2] = new char[]{'G', 'G', 'G'};
        initUp[0] = new char[]{'W', 'W', 'W'}; // initUp : White
        initUp[1] = new char[]{'W', 'W', 'W'};
        initUp[2] = new char[]{'W', 'W', 'W'};
        initLeft[0] = new char[]{'O', 'O', 'O'}; // initLeft : Orange
        initLeft[1] = new char[]{'O', 'O', 'O'};
        initLeft[2] = new char[]{'O', 'O', 'O'};
        initRight[0] = new char[]{'R', 'R', 'R'}; // initRight : Red
        initRight[1] = new char[]{'R', 'R', 'R'};
        initRight[2] = new char[]{'R', 'R', 'R'};
        initDown[0] = new char[]{'Y', 'Y', 'Y'}; // initDown : Yellow
        initDown[1] = new char[]{'Y', 'Y', 'Y'};
        initDown[2] = new char[]{'Y', 'Y', 'Y'};
        initBack[0] = new char[]{'B', 'B', 'B'}; // initBack : Blue
        initBack[1] = new char[]{'B', 'B', 'B'};
        initBack[2] = new char[]{'B', 'B', 'B'};
        cubeFront = initFront;
        cubeUp = initUp;
        cubeLeft = initLeft;
        cubeRight = initRight;
        cubeDown = initDown;
        cubeBack = initBack;
    }

    public void ready() {
        printCube();
        String input = start();
        System.out.println("input : " + input);
        ArrayList<String> inputList = trimInput(input);
        checkInput(inputList);
    }

    public void printCube() {
        System.out.println("↓ 현재 큐브");
        for (int i = 0; i < 3; i++) {
            System.out.printf("%n %6c %1c %1c %1c %6c", ' ', cubeUp[i][0], cubeUp[i][1], cubeUp[i][2], ' ');
        }
        for (int i = 0; i < 3; i++) {
            System.out.printf("%n %1c %1c %1c  %1c %1c %1c  %1c %1c %1c  %1c %1c %1c",
                    cubeLeft[i][0], cubeLeft[i][1], cubeLeft[i][2],
                    cubeFront[i][0], cubeFront[i][1], cubeFront[i][2],
                    cubeRight[i][0], cubeRight[i][1], cubeRight[i][2],
                    cubeBack[i][0], cubeBack[i][1], cubeBack[i][2]);
        }
        System.out.printf("%n %6c %1c %1c %1c %15c %1c%1c", ' ', cubeDown[0][0], cubeDown[0][1], cubeDown[0][2], ' ', ' ', 'U');
        System.out.printf("%n %6c %1c %1c %1c %15c %1c%1c%1c%1c", ' ', cubeDown[1][0], cubeDown[1][1], cubeDown[1][2], ' ', 'L', 'F', 'R', 'B');
        System.out.printf("%n %6c %1c %1c %1c %15c %1c%1c", ' ', cubeDown[2][0], cubeDown[2][1], cubeDown[2][2], ' ', ' ', 'D');
    }

    public String start() {
        System.out.println("\n❓ HELP -------------------- ↗ 「전개도 도움말」 ------------------------------");
        System.out.println("❗  U = 윗면, L = 왼쪽면, F = 앞면, R = 오른쪽면, B = 뒷면, D = 아랫면을 뜻합니다.");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("💬 아래 명령어에 따라, 정해진 면의 정해진 방향으로 '1/4바퀴' 돌아갑니다.");
        System.out.println("U : 윗쪽 면을 시계방향으로 1/4 회전");
        System.out.println("U' : 윗쪽 면을 반시계방향으로 1/4 회전");
        System.out.println("F : 앞쪽 면을 시계방향으로 1/4 회전");
        System.out.println("F' : 앞쪽 면을 반시계방향으로 1/4 회전");
        System.out.println("L : 왼쪽 면을 시계방향으로 1/4 회전");
        System.out.println("L' : 왼쪽 면을 반시계방향으로 1/4 회전");
        System.out.println("R : 오른쪽 면을 시계방향으로 1/4 회전");
        System.out.println("R' : 오른쪽 면을 반시계방향으로 1/4 회전");
        System.out.print("CUBE > ");
        String input = sc.nextLine();
        return input;
    }

    public ArrayList<String> trimInput(String input) { // 입력값 다듬기
        String[] inputArr = input.split("");
        int inputArrLength = inputArr.length;
        ArrayList<String> inputList = new ArrayList<>();
        for (int i = 0; i < inputArrLength; i++) {
            String inputElement = inputArr[i];
            if (inputElement.equals("'")) {
                inputArr[i - 1] = inputArr[i - 1] + "'";
            }
        }
        for (int i = 0; i < inputArrLength ; i++) {
            String inputElement = inputArr[i];
            if (!inputElement.equals("'")) {// 요소가 '이 아닐 때 inputList에 추가
                inputList.add(inputElement);
            }
        }
        return inputList;
    }

    public void checkInput(ArrayList<String> inputList) {
        int inputListSize = inputList.size();
        for (int i = 0; i < inputListSize; i++) {
            String anInput = inputList.get(i);
            if (anInput.equals("U") || anInput.equals("U'") || anInput.equals("F") || anInput.equals("F'")
                    || anInput.equals("L") || anInput.equals("L'") || anInput.equals("R") || anInput.equals("R'")
                    || anInput.equals("Q")) { // U U' F F' L L' R R' Q 일 때만.
                guideInput(anInput);
            } else {
                System.out.println("❗ 지정되지 않은 명령어가 포함되어 있습니다. 다시 입력해 주세요.");
                String reInput = start();
                ArrayList<String> reInputList = trimInput(reInput);
                checkInput(reInputList);
            }
            if (i == (inputListSize - 1)) {
                ready();
            }
        }
       // ready(); - 이렇게 고쳐도 동일?
    }

    public void guideInput(String anInput) {
        char[][] tempFront = new char[3][3];
        char[][] tempUp = new char[3][3];
        char[][] tempLeft = new char[3][3];
        char[][] tempRight = new char[3][3];
        char[][] tempDown = new char[3][3];
        char[][] tempBack = new char[3][3];
        switch(anInput) {
            case "U" :
                whenU(tempUp, tempLeft, tempFront, tempRight, tempBack);
                break;
            case "U'" :
                whenUDot(tempUp, tempLeft, tempFront, tempRight, tempBack);
                break;
            case "F" :
                whenF(tempFront, tempUp, tempLeft, tempRight, tempDown);
                break;
            case "F'" :
                whenFDot(tempFront, tempUp, tempLeft, tempRight, tempDown);
                break;
            case "L" :
                whenL(tempLeft, tempUp, tempFront, tempDown, tempBack);
                break;
            case "L'" :
                whenLDot(tempLeft, tempUp, tempFront, tempDown, tempBack);
                break;
            case "R" :
                whenR(tempRight, tempUp, tempFront, tempDown, tempBack);
                break;
            case "R'" :
                whenRDot(tempRight, tempUp, tempFront, tempDown, tempBack);
                break;
            case "Q" :
                sc.close();
                System.out.println("종료메시지");
                System.exit(0);
                break;
        }
        printCube();
        System.out.println(); // 줄바꿈
    }

    // TODO :  guideInput()과 함께 whenU(), whenU'() .. 및 copyCube() 구현하기.

    public void copyCube() { // 매개변수 여러개 받아서 한쪽에서 한쪽으로 복사하기.

    }

    // public void pasteCube() {} // 되도록 copyCube()만으로 해결하기?

    public void whenU(char[][] tempUp, char[][] tempLeft, char[][] tempFront, char[][] tempRight, char[][] tempBack) {

    }

    public void whenUDot(char[][] tempUp, char[][] tempLeft, char[][] tempFront, char[][] tempRight, char[][] tempBack) {

    }

    public void whenF(char[][] tempFront, char[][] tempUp, char[][] tempLeft, char[][] tempRight, char[][] tempDown) {

    }

    public void whenFDot(char[][] tempFront, char[][] tempUp, char[][] tempLeft, char[][] tempRight, char[][] tempDown) {

    }

    public void whenL(char[][] tempLeft, char[][] tempUp, char[][] tempFront, char[][] tempDown, char[][] tempBack) {

    }

    public void whenLDot(char[][] tempLeft, char[][] tempUp, char[][] tempFront, char[][] tempDown, char[][] tempBack) {

    }

    public void whenR(char[][] tempRight, char[][] tempUp, char[][] tempFront, char[][] tempDown, char[][] tempBack) {

    }

    public void whenRDot(char[][] tempRight, char[][] tempUp, char[][] tempFront, char[][] tempCubeDown, char[][] tempBack) {

    }
} // class

