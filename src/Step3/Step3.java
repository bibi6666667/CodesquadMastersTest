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
        for (int i = 0; i < 3; i++) {
            initFront[i] = new char[]{'G', 'G', 'G'}; // initFront : Green
        }
        for (int i = 0; i < 3; i++) {
            initUp[i] = new char[]{'W', 'W', 'W'}; // initUp : White
        }
        for (int i = 0; i < 3; i++) {
            initLeft[i] = new char[]{'O', 'O', 'O'}; // initLeft : Orange
        }
        for (int i = 0; i < 3; i++) {
            initRight[i] = new char[]{'R', 'R', 'R'}; // initRight : Red
        }
        for (int i = 0; i < 3; i++) {
            initDown[i] = new char[]{'Y', 'Y', 'Y'}; // initDown : Yellow
        }
        for (int i = 0; i < 3; i++) {
            initBack[i] = new char[]{'B', 'B', 'B'}; // initBack : Blue
        }
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
        System.out.println(">>   U = Up = 윗면, L = Left = 왼쪽 면, F = Front = 앞면, ");
        System.out.println(">>   R = Right = 오른쪽 면, B = Back = 뒷면, D = Down = 아랫면을 뜻합니다.");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("💬 아래 명령어에 따라, 정해진 면의 정해진 방향으로 '1/4바퀴' 돌아갑니다.");
        System.out.println("U : 윗면을 시계방향으로 1/4 회전, U' : 윗쪽 면을 반시계방향으로 1/4 회전");
        System.out.println("F : 앞면을 시계방향으로 1/4 회전, F' : 앞쪽 면을 반시계방향으로 1/4 회전");
        System.out.println("L : 왼쪽 면을 시계방향으로 1/4 회전, L' : 왼쪽 면을 반시계방향으로 1/4 회전");
        System.out.println("R : 오른쪽 면을 시계방향으로 1/4 회전, R' : 오른쪽 면을 반시계방향으로 1/4 회전");
        System.out.println("B : 뒷면을 시계방향으로 1/4 회전, B' : 뒷면을 반시계방향으로 1/4 회전");
        System.out.println("D : 아랫면을 시계방향으로 1/4 회전, D' : 아랫면을 반시계방향으로 1/4 회전");
        System.out.println("Q : 프로그램 종료");
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
                    || anInput.equals("B") || anInput.equals("B'") || anInput.equals("D") || anInput.equals("D'")
                    || anInput.equals("Q")) { // U U' F F' L L' R R' B B' D D' Q 일 때만.
                // TODO : U2 L2 F2 R2 B2 D2도 해야 함. trimInput()-checkInput()-guideInput() 모두.
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
            case "B" :
                whenB(tempBack, tempUp, tempLeft, tempRight, tempDown);
                break;
            case "B'" :
                whenBDot(tempBack, tempUp, tempLeft, tempRight, tempDown);
                break;
            case "D" :
                whenD(tempDown, tempLeft, tempFront, tempRight, tempBack);
                break;
            case "D'" :
                whenDDot(tempDown, tempLeft, tempFront, tempRight, tempBack);
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

    public void copyAtoB(char[][] cubeA, char[][] cubeB) { // A를 B에 복사
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j ++){
                cubeB[i][j] = cubeA[i][j];
            }
        }
    }

    public void clockWise(char[][] temp, char[][] cube){ // 해당 면 cube를 시계방향으로 1/4 회전해 temp에 대입.
        temp[0][0] = cube[2][0];
        temp[0][1] = cube[1][0];
        temp[0][2] = cube[0][0];
        temp[1][0] = cube[2][1];
        temp[1][2] = cube[0][1];
        temp[2][0] = cube[2][2];
        temp[2][1] = cube[1][2];
        temp[2][2] = cube[0][2];
    }
    
    public void counterClockWise(char[][] temp, char[][] cube){ // 해당 면 cube를 시계방향으로 1/4 회전해 temp에 대입.
        temp[0][0] = cube[0][2];
        temp[0][1] = cube[1][2];
        temp[0][2] = cube[2][2];
        temp[1][0] = cube[0][1];
        temp[1][2] = cube[2][1];
        temp[2][0] = cube[0][0];
        temp[2][1] = cube[1][0];
        temp[2][2] = cube[2][0];
    }

    public void whenU(char[][] tempUp, char[][] tempLeft, char[][] tempFront, char[][] tempRight, char[][] tempBack) {
        System.out.println("U : 윗쪽 면을 시계방향으로 1/4 회전");
        copyAtoB(cubeUp, tempUp); // 임시변수에 cube 내용 복사
        copyAtoB(cubeLeft, tempLeft);
        copyAtoB(cubeFront, tempFront);
        copyAtoB(cubeRight, tempRight);
        copyAtoB(cubeBack, tempBack);
        clockWise(tempUp, cubeUp);// U면 시계방향 1/4 회전
        tempLeft[0][0] = cubeFront[0][0]; // U면의 옆면 - L면 변경사항
        tempLeft[0][1] = cubeFront[0][1];
        tempLeft[0][2] = cubeFront[0][2];
        tempBack[0][0] = cubeLeft[0][0];// U면의 옆면 - B면 변경사항
        tempBack[0][1] = cubeLeft[0][1];
        tempBack[0][2] = cubeLeft[0][2];
        tempFront[0][0] = cubeRight[0][0];// U면의 옆면 - F면 변경사항
        tempFront[0][1] = cubeRight[0][1];
        tempFront[0][2] = cubeRight[0][2];
        tempRight[0][0] = cubeBack[0][0];// U면의 옆면 - R면 변경사항
        tempRight[0][1] = cubeBack[0][1];
        tempRight[0][2] = cubeBack[0][2];
        copyAtoB(tempUp, cubeUp); // 바뀐 임시변수를 cube에 복사
        copyAtoB(tempLeft, cubeLeft);
        copyAtoB(tempFront, cubeFront);
        copyAtoB(tempRight, cubeRight);
        copyAtoB(tempBack, cubeBack);
    }

    public void whenUDot(char[][] tempUp, char[][] tempLeft, char[][] tempFront, char[][] tempRight, char[][] tempBack) {
        System.out.println("U' : 윗쪽 면을 반시계방향으로 1/4 회전");
        copyAtoB(cubeUp, tempUp); //임시변수에 cube 내용 복사
        copyAtoB(cubeLeft, tempLeft);
        copyAtoB(cubeFront, tempFront);
        copyAtoB(cubeRight, tempRight);
        copyAtoB(cubeBack, tempBack);
        counterClockWise(tempUp, cubeUp);// U면 반시계방향 1/4 회전
        tempLeft[0][0] = cubeBack[0][0]; // U면의 옆면 - L면 변경사항
        tempLeft[0][1] = cubeBack[0][1];
        tempLeft[0][2] = cubeBack[0][2];
        tempFront[0][0] = cubeLeft[0][0];// U면의 옆면 - F면 변경사항
        tempFront[0][1] = cubeLeft[0][1];
        tempFront[0][2] = cubeLeft[0][2];
        tempRight[0][0] = cubeFront[0][0];// U면의 옆면 - R면 변경사항
        tempRight[0][1] = cubeFront[0][1];
        tempRight[0][2] = cubeFront[0][2];
        tempBack[0][0] = cubeRight[0][0];// U면의 옆면 - B면 변경사항
        tempBack[0][1] = cubeRight[0][1];
        tempBack[0][2] = cubeRight[0][2];
        copyAtoB(tempUp, cubeUp); // 바뀐 임시변수를 cube에 복사
        copyAtoB(tempLeft, cubeLeft);
        copyAtoB(tempFront, cubeFront);
        copyAtoB(tempRight, cubeRight);
        copyAtoB(tempBack, cubeBack);
    }

    public void whenF(char[][] tempFront, char[][] tempUp, char[][] tempLeft, char[][] tempRight, char[][] tempDown) {
        System.out.println("F : 앞쪽 면을 시계방향으로 1/4 회전");
        copyAtoB(cubeFront, tempFront);// 임시변수에 cube 내용 복사
        copyAtoB(cubeUp, tempUp);
        copyAtoB(cubeLeft, tempLeft);
        copyAtoB(cubeRight, tempRight);
        copyAtoB(cubeDown, tempDown);
        clockWise(tempFront, cubeFront);// F면 시계방향 1/4 회전
        tempUp[2][0] = cubeLeft[2][2];// F면의 옆면 - U면 변경사항
        tempUp[2][1] = cubeLeft[1][2];
        tempUp[2][2] = cubeLeft[0][2];
        tempLeft[0][2] = cubeDown[0][0]; // F면의 옆면 - L면 변경사항
        tempLeft[1][2] = cubeDown[0][1];
        tempLeft[2][2] = cubeDown[0][2];
        tempRight[0][0] = cubeUp[2][0];// F면의 옆면 - R면 변경사항
        tempRight[1][0] = cubeUp[2][1];
        tempRight[2][0] = cubeUp[2][2];
        tempDown[0][0] = cubeRight[2][0];// F면의 옆면 - D면 변경사항
        tempDown[0][1] = cubeRight[1][0];
        tempDown[0][2] = cubeRight[0][0];
        copyAtoB(tempFront, cubeFront); // 바뀐 임시변수를 cube에 복사
        copyAtoB(tempUp, cubeUp);
        copyAtoB(tempLeft, cubeLeft);
        copyAtoB(tempRight, cubeRight);
        copyAtoB(tempDown, cubeDown);
    }

    public void whenFDot(char[][] tempFront, char[][] tempUp, char[][] tempLeft, char[][] tempRight, char[][] tempDown) {
        System.out.println("F' : 앞쪽 면을 반시계방향으로 1/4 회전");
        copyAtoB(cubeFront, tempFront); // 임시변수에 cube 내용 복사
        copyAtoB(cubeUp, tempUp);
        copyAtoB(cubeLeft, tempLeft);
        copyAtoB(cubeRight, tempRight);
        copyAtoB(cubeDown, tempDown);
        counterClockWise(tempFront, cubeFront);// F면 반시계방향 1/4 회전
        tempUp[2][0] = cubeRight[0][0];// F면의 옆면 - U면 변경사항
        tempUp[2][1] = cubeRight[1][0];
        tempUp[2][2] = cubeRight[2][0];
        tempLeft[0][2] = cubeUp[2][2]; // F면의 옆면 - L면 변경사항
        tempLeft[1][2] = cubeUp[2][1];
        tempLeft[2][2] = cubeUp[2][0];
        tempRight[0][0] = cubeDown[0][2];// F면의 옆면 - R면 변경사항
        tempRight[1][0] = cubeDown[0][1];
        tempRight[2][0] = cubeDown[0][0];
        tempDown[0][0] = cubeLeft[0][2];// F면의 옆면 - D면 변경사항
        tempDown[0][1] = cubeLeft[1][2];
        tempDown[0][2] = cubeLeft[2][2];
        copyAtoB(tempFront, cubeFront);// 바뀐 임시변수를 cube에 복사
        copyAtoB(tempUp, cubeUp);
        copyAtoB(tempLeft, cubeLeft);
        copyAtoB(tempRight, cubeRight);
        copyAtoB(tempDown, cubeDown);
    }

    public void whenL(char[][] tempLeft, char[][] tempUp, char[][] tempFront, char[][] tempDown, char[][] tempBack) {
        System.out.println("L : 왼쪽 면을 시계방향으로 1/4 회전");
        copyAtoB(cubeLeft, tempLeft);// 임시변수에 cube 내용 복사
        copyAtoB(cubeUp, tempUp);
        copyAtoB(cubeFront, tempFront);
        copyAtoB(cubeDown, tempDown);
        copyAtoB(cubeBack, tempBack);
        clockWise(tempLeft, cubeLeft);// L면 시계방향 1/4 회전
        tempUp[0][0] = cubeBack[2][2];// L면의 옆면 - U면 변경사항
        tempUp[1][0] = cubeBack[1][2];
        tempUp[2][0] = cubeBack[0][2];
        tempFront[0][0] = cubeUp[0][0];// L면의 옆면 - F면 변경사항
        tempFront[1][0] = cubeUp[1][0];
        tempFront[2][0] = cubeUp[2][0];
        tempDown[0][0] = cubeFront[0][0];// L면의 옆면 - D면 변경사항
        tempDown[1][0] = cubeFront[1][0];
        tempDown[2][0] = cubeFront[2][0];
        tempBack[0][2] = cubeDown[2][0]; // L면의 옆면 - B면 변경사항
        tempBack[1][2] = cubeDown[1][0];
        tempBack[2][2] = cubeDown[0][0];
        copyAtoB(tempLeft, cubeLeft);// 바뀐 임시변수를 cube에 복사
        copyAtoB(tempUp, cubeUp);
        copyAtoB(tempFront, cubeFront);
        copyAtoB(tempDown, cubeDown);
        copyAtoB(tempBack, cubeBack);
    }

    public void whenLDot(char[][] tempLeft, char[][] tempUp, char[][] tempFront, char[][] tempDown, char[][] tempBack) {
        System.out.println("L' : 왼쪽 면을 반시계방향으로 1/4 회전");
        copyAtoB(cubeLeft, tempLeft);// 임시변수에 cube 내용 복사
        copyAtoB(cubeUp, tempUp);
        copyAtoB(cubeFront, tempFront);
        copyAtoB(cubeDown, tempDown);
        copyAtoB(cubeBack, tempBack);
        counterClockWise(tempLeft, cubeLeft);// L면 반시계방향 1/4 회전
        tempUp[0][0] = cubeFront[0][0];// L면의 옆면 - U면 변경사항
        tempUp[1][0] = cubeFront[1][0];
        tempUp[2][0] = cubeFront[2][0];
        tempFront[0][0] = cubeDown[0][0]; // L면의 옆면 - F면 변경사항
        tempFront[1][0] = cubeDown[1][0];
        tempFront[2][0] = cubeDown[2][0];
        tempDown[0][0] = cubeBack[2][2];// L면의 옆면 - D면 변경사항
        tempDown[1][0] = cubeBack[1][2];
        tempDown[2][0] = cubeBack[0][2];
        tempBack[0][2] = cubeUp[2][0];// L면의 옆면 - B면 변경사항
        tempBack[1][2] = cubeUp[1][0];
        tempBack[2][2] = cubeUp[0][0];
        copyAtoB(tempLeft, cubeLeft);// 바뀐 임시변수를 cube에 복사
        copyAtoB(tempUp, cubeUp);
        copyAtoB(tempFront, cubeFront);
        copyAtoB(tempDown, cubeDown);
        copyAtoB(tempBack, cubeBack);
    }

    public void whenR(char[][] tempRight, char[][] tempUp, char[][] tempFront, char[][] tempDown, char[][] tempBack) {
        System.out.println("R : 오른쪽 면을 시계방향으로 1/4 회전");
        copyAtoB(cubeRight, tempRight);// 임시변수에 cube 내용 복사
        copyAtoB(cubeUp, tempUp);
        copyAtoB(cubeFront, tempFront);
        copyAtoB(cubeDown, tempDown);
        copyAtoB(cubeBack, tempBack);
        clockWise(tempRight, cubeRight);// R면 시계방향 1/4 회전
        tempUp[0][2] = cubeFront[0][2];// R면의 옆면 - U면 변경사항
        tempUp[1][2] = cubeFront[1][2];
        tempUp[2][2] = cubeFront[2][2];
        tempFront[0][2] = cubeDown[0][2];// R면의 옆면 - F면 변경사항
        tempFront[1][2] = cubeDown[1][2];
        tempFront[2][2] = cubeDown[2][2];
        tempDown[0][2] = cubeBack[2][0];// R면의 옆면 - D면 변경사항
        tempDown[1][2] = cubeBack[1][0];
        tempDown[2][2] = cubeBack[0][0];
        tempBack[0][0] = cubeUp[2][2]; // R면의 옆면 - B면 변경사항
        tempBack[1][0] = cubeUp[1][2];
        tempBack[2][0] = cubeUp[0][0];
        copyAtoB(tempRight, cubeRight);// 바뀐 임시변수를 cube에 복사
        copyAtoB(tempUp, cubeUp);
        copyAtoB(tempFront, cubeFront);
        copyAtoB(tempDown, cubeDown);
        copyAtoB(tempBack, cubeBack);
    }

    public void whenRDot(char[][] tempRight, char[][] tempUp, char[][] tempFront, char[][] tempDown, char[][] tempBack) {
        System.out.println("R' : 오른쪽 면을 반시계방향으로 1/4 회전");
        copyAtoB(cubeRight, tempRight);// 임시변수에 cube 내용 복사
        copyAtoB(cubeUp, tempUp);
        copyAtoB(cubeFront, tempFront);
        copyAtoB(cubeDown, tempDown);
        copyAtoB(cubeBack, tempBack);
        counterClockWise(tempRight, cubeRight);// R면 반시계방향 1/4 회전
        tempUp[0][2] = cubeBack[2][0];// R면의 옆면 - U면 변경사항
        tempUp[1][2] = cubeBack[1][0];
        tempUp[2][2] = cubeBack[0][0];
        tempFront[0][2] = cubeUp[0][2]; // R면의 옆면 - F면 변경사항
        tempFront[1][2] = cubeUp[1][2];
        tempFront[2][2] = cubeUp[2][2];
        tempDown[0][2] = cubeFront[0][2];// R면의 옆면 - D면 변경사항
        tempDown[1][2] = cubeFront[1][2];
        tempDown[2][2] = cubeFront[2][2];
        tempBack[0][0] = cubeDown[2][2];// R면의 옆면 - B면 변경사항
        tempBack[1][0] = cubeDown[1][2];
        tempBack[2][0] = cubeDown[0][2];
        copyAtoB(tempRight, cubeRight);// 바뀐 임시변수를 cube에 복사
        copyAtoB(tempUp, cubeUp);
        copyAtoB(tempFront, cubeFront);
        copyAtoB(tempDown, cubeDown);
        copyAtoB(tempBack, cubeBack);
    }

    public void whenB(char[][] tempBack, char[][] tempUp, char[][] tempLeft, char[][] tempRight, char[][] tempDown) {
        System.out.println("B : 뒷면을 시계방향으로 1/4 회전");
        copyAtoB(cubeBack, tempBack);// 임시변수에 cube 내용 복사
        copyAtoB(cubeUp, tempUp);
        copyAtoB(cubeLeft, tempLeft);
        copyAtoB(cubeRight, tempRight);
        copyAtoB(cubeDown, tempDown);
        clockWise(tempBack, cubeBack);// B면 시계방향 1/4 회전
        tempUp[0][0] = cubeRight[0][2];// B면의 옆면 - U면 변경사항
        tempUp[0][1] = cubeRight[1][2];
        tempUp[0][2] = cubeRight[2][2];
        tempLeft[0][0] = cubeUp[0][2];// B면의 옆면 - L면 변경사항
        tempLeft[1][0] = cubeUp[0][1];
        tempLeft[2][0] = cubeUp[0][0];
        tempRight[0][2] = cubeDown[2][2]; // B면의 옆면 - R면 변경사항
        tempRight[1][2] = cubeDown[2][1];
        tempRight[2][2] = cubeDown[2][0];
        tempDown[2][0] = cubeLeft[0][0];// B면의 옆면 - D면 변경사항
        tempDown[2][1] = cubeLeft[1][0];
        tempDown[2][2] = cubeLeft[2][0];
        copyAtoB(tempBack, cubeBack);// 바뀐 임시변수를 cube에 복사
        copyAtoB(tempUp, cubeUp);
        copyAtoB(tempLeft, cubeLeft);
        copyAtoB(tempRight, cubeRight);
        copyAtoB(tempDown, cubeDown);
    }

    public void whenBDot(char[][] tempBack, char[][] tempUp, char[][] tempLeft, char[][] tempRight, char[][] tempDown) {
        System.out.println("B' : 뒷면을 반시계방향으로 1/4 회전");
        copyAtoB(cubeBack, tempBack);
        copyAtoB(cubeUp, tempUp);
        copyAtoB(cubeLeft, tempLeft);
        copyAtoB(cubeRight, tempRight);// 임시변수에 cube 내용 복사
        copyAtoB(cubeDown, tempDown);
        counterClockWise(tempBack, cubeBack);// B면 반시계방향 1/4 회전
        tempUp[0][0] = cubeLeft[2][0];// B면의 옆면 - U면 변경사항
        tempUp[0][1] = cubeLeft[1][0];
        tempUp[0][2] = cubeLeft[0][0];
        tempLeft[0][0] = cubeDown[2][0]; // B면의 옆면 - L면 변경사항
        tempLeft[1][0] = cubeDown[2][1];
        tempLeft[2][0] = cubeDown[2][2];
        tempRight[0][2] = cubeUp[0][0];// B면의 옆면 - R면 변경사항
        tempRight[1][2] = cubeUp[0][1];
        tempRight[2][2] = cubeUp[0][2];
        tempDown[2][0] = cubeRight[2][2];// B면의 옆면 - D면 변경사항
        tempDown[2][1] = cubeRight[1][2];
        tempDown[2][2] = cubeRight[0][2];
        copyAtoB(tempBack, cubeBack);// 바뀐 임시변수를 cube에 복사
        copyAtoB(tempUp, cubeUp);
        copyAtoB(tempLeft, cubeLeft);
        copyAtoB(tempRight, cubeRight);
        copyAtoB(tempDown, cubeDown);
    }

    public void whenD(char[][] tempDown, char[][] tempLeft, char[][] tempFront, char[][] tempRight, char[][] tempBack) {
        System.out.println("D : 아랫면을 시계방향으로 1/4 회전");
        copyAtoB(cubeDown, tempDown);// 임시변수에 cube 내용 복사
        copyAtoB(cubeLeft, tempLeft);
        copyAtoB(cubeFront, tempFront);
        copyAtoB(cubeRight, tempRight);
        copyAtoB(cubeBack, tempBack);
        clockWise(tempDown, cubeDown);// D면 시계방향 1/4 회전
        tempLeft[2][0] = cubeBack[2][0];// D면의 옆면 - L면 변경사항
        tempLeft[2][1] = cubeBack[2][1];
        tempLeft[2][2] = cubeBack[2][2];
        tempFront[2][0] = cubeLeft[2][0];// D면의 옆면 - F면 변경사항
        tempFront[2][1] = cubeLeft[2][1];
        tempFront[2][2] = cubeLeft[2][2];
        tempRight[2][0] = cubeFront[2][0]; // D면의 옆면 - R면 변경사항
        tempRight[2][1] = cubeFront[2][1];
        tempRight[2][2] = cubeFront[2][2];
        tempBack[2][0] = cubeRight[2][0];// D면의 옆면 - B면 변경사항
        tempBack[2][1] = cubeRight[2][1];
        tempBack[2][2] = cubeRight[2][2];
        copyAtoB(tempDown, cubeDown);// 바뀐 임시변수를 cube에 복사
        copyAtoB(tempLeft, cubeLeft);
        copyAtoB(tempFront, cubeFront);
        copyAtoB(tempRight, cubeRight);
        copyAtoB(tempBack, cubeBack);
    }

    public void whenDDot(char[][] tempDown, char[][] tempLeft, char[][] tempFront, char[][] tempRight, char[][] tempBack) {
        System.out.println("D' : 아랫면을 반시계방향으로 1/4 회전");
        copyAtoB(cubeDown, tempDown);// 임시변수에 cube 내용 복사
        copyAtoB(cubeLeft, tempLeft);
        copyAtoB(cubeFront, tempFront);
        copyAtoB(cubeRight, tempRight);
        copyAtoB(cubeBack, tempBack);
        counterClockWise(tempDown, cubeDown);// D면 반시계방향 1/4 회전
        tempLeft[2][0] = cubeFront[2][0]; // D면의 옆면 - L면 변경사항
        tempLeft[2][1] = cubeFront[2][1];
        tempLeft[2][2] = cubeFront[2][2];
        tempFront[2][0] = cubeRight[2][0];// D면의 옆면 - F면 변경사항
        tempFront[2][1] = cubeRight[2][1];
        tempFront[2][2] = cubeRight[2][2];
        tempRight[2][0] = cubeBack[2][0];// D면의 옆면 - R면 변경사항
        tempRight[2][1] = cubeBack[2][1];
        tempRight[2][2] = cubeBack[2][2];
        tempBack[2][0] = cubeLeft[2][0];// D면의 옆면 - B면 변경사항
        tempBack[2][1] = cubeLeft[2][1];
        tempBack[2][2] = cubeLeft[2][2];
        copyAtoB(tempDown, cubeDown);// 바뀐 임시변수를 cube에 복사
        copyAtoB(tempLeft, cubeLeft);
        copyAtoB(tempFront, cubeFront);
        copyAtoB(tempRight, cubeRight);
        copyAtoB(tempBack, cubeBack);
    }
} // class

