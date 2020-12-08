package Step2;

import java.util.ArrayList;
import java.util.Scanner;

public class Step2 { // 평면 큐브 구현하기
    Scanner sc = new Scanner(System.in);

    char[][] cubeBoard = new char[3][3];

    public static void main(String[] args) {
        Step2 step2 = new Step2();
        System.out.println("💬 평면 큐브를 맞춰보세요! \n아래 입력에 따라 큐브가 돌아갑니다.");
        step2.initCube();
        String input = step2.starter();
        ArrayList<String> inputList = step2.trimInput(input);
        step2.checkInput(inputList);
    }

    public void initCube() {
        System.out.println("초기화");
        char[][] initCube = new char[3][3];
        initCube[0] = new char[]{'R', 'R', 'W'};
        initCube[1] = new char[]{'G', 'C', 'W'};
        initCube[2] = new char[]{'G', 'B', 'B'};
        cubeBoard = initCube;
    }

    public String starter() {
        printCube();
        System.out.println("U : 가장 윗줄을 왼쪽으로 한 칸 밀기, U' : 가장 윗줄을 오른쪽으로 한 칸 밀기");
        System.out.println("R : 가장 오른쪽 줄을 위로 한 칸 밀기, R' : 가장 오른쪽 줄을 아래로 한 칸 밀기");
        System.out.println("L : 가장 왼쪽 줄을 아래로 한 칸 밀기, L' : 가장 왼쪽 줄을 위로 한 칸 밀기");
        System.out.println("B : 가장 아랫줄을 오른쪽으로 한 칸 밀기, B' : 가장 아랫줄을 왼쪽으로 한 칸 밀기");
        System.out.println("Q : 프로그램 종료");
        System.out.print("CUBE > ");
        String input = sc.nextLine();
        System.out.println(input);
        return input;

    }

    public ArrayList<String> trimInput(String input){
        // 입력값 다듬기
        String[] inputArr = input.split("");
        ArrayList<String> inputList = new ArrayList<>();
        for (int i = 0; i < inputArr.length; i++) {
            String inputElement = inputArr[i];
            if (inputElement.equals("'")) {
                inputArr[i - 1] = inputArr[i - 1] + "'"; // ' 앞의 요소에 붙여주기
            }
        }
        for (int i = 0; i < inputArr.length; i++) {
            String inputArrElement = inputArr[i];
            if (!inputArrElement.equals("'")) { // 요소가 null이 아닐 때!
                inputList.add(inputArrElement);
            }
        }
        return inputList;
    }

    public void checkInput(ArrayList<String> inputList){
        // 입력값 하나씩 반복문 돌리기
        int inputListSize = inputList.size();
        System.out.println("inputListSize : " + inputListSize);
        for (int i = 0; i < inputListSize; i++) {
            String anInput = inputList.get(i);
            System.out.println(i + "번쨰 anInput : " + anInput);
            if (anInput.equals("U") || anInput.equals("U'") || anInput.equals("R") || anInput.equals("R'")
                || anInput.equals("L") || anInput.equals("L'") || anInput.equals("B") || anInput.equals("B'")
                || anInput.equals("Q")) {
                guideInput(anInput);
            } else {
                System.out.println("❗ 지정되지 않은 값이 포함되어 있습니다. 다시 입력해 주세요.");
                String reInput = starter();
                ArrayList<String> reInputList = trimInput(reInput);
                checkInput(reInputList);
                //guideInput(anInput);
            }
            if (i == (inputListSize - 1)) {
                System.out.println("명령 전부 처리 끝");
                starter();
                break;
            }
        }
    }

    public void countInput(){
    }

    public void printCube() {
        System.out.println("↓ cubeBoard");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cubeBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public char[][] copyCube(char[][] tempCube){ // cubeBoard에서 tempCube로 복사
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j ++){
                tempCube[i][j] = cubeBoard[i][j];
            }
        }
        return tempCube;
    }

    public void pasteCube(char[][] tempCube){ // tempCube 내용을 cubeBoard로 붙여넣기
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j ++){
                cubeBoard[i][j] = tempCube[i][j];
            }
        }
    }

    public void guideInput(String input){
        char[][] tempCube = new char[3][3]; // tempCube 초기화
        switch(input) {
            case "U" :
                whenU(tempCube);
                break;
            case "U'" :
                whenUDot(tempCube);
                break;
            case "R" :
                whenR(tempCube);
                break;
            case "R'" :
                whenRDot(tempCube);
                break;
            case "L" :
                whenL(tempCube);
                break;
            case "L'" :
                whenLDot(tempCube);
                break;
            case "B" :
                whenB(tempCube);
                break;
            case "B'" :
                whenBDot(tempCube);
                break;
            case "Q" :
                System.out.println("Bye~");
                System.exit(0);
                break;
        }
        System.out.println("--명령 하나 처리 끝---");
        printCube();
    }


    public void whenU(char[][] tempCube){ // 가장 윗줄을 왼쪽으로 한 칸 밀기
        System.out.println("U : 가장 윗줄을 왼쪽으로 한 칸 밀기");
        copyCube(tempCube); // tempCube에 cubeBoard 내용 복사
        tempCube[0][0] = cubeBoard[0][1]; // 변경사항
        tempCube[0][1] = cubeBoard[0][2];
        tempCube[0][2] = cubeBoard[0][0];
        pasteCube(tempCube); // tempCube가 새로운 cubeBoard가 됨.
    }

    public void whenUDot(char[][] tempCube){ // 가장 윗줄을 오른쪽으로 한 칸 밀기
        System.out.println("U' : 가장 윗줄을 오른쪽으로 한 칸 밀기");
        copyCube(tempCube); // tempCube에 cubeBoard 내용 복사
        tempCube[0][0] = cubeBoard[0][2]; // 변경사항
        tempCube[0][1] = cubeBoard[0][0];
        tempCube[0][2] = cubeBoard[0][1];
        pasteCube(tempCube); // tempCube가 새로운 cubeBoard가 됨.
    }

    public void whenR(char[][] tempCube){ // 가장 오른쪽 줄을 위로 한 칸 밀기
        System.out.println("R : 가장 오른쪽 줄을 위로 한 칸 밀기");
        copyCube(tempCube); // tempCube에 cubeBoard 내용 복사
        tempCube[0][2] = cubeBoard[1][2]; // 변경사항
        tempCube[1][2] = cubeBoard[2][2];
        tempCube[2][2] = cubeBoard[0][2];
        pasteCube(tempCube); // tempCube가 새로운 cubeBoard가 됨.
    }

    public void whenRDot(char[][] tempCube){ // 가장 오른쪽 줄을 아래로 한 칸 밀기
        System.out.println("R' : 가장 오른쪽 줄을 아래로 한 칸 밀기");
        copyCube(tempCube); // tempCube에 cubeBoard 내용 복사
        tempCube[0][2] = cubeBoard[2][2]; // 변경사항
        tempCube[1][2] = cubeBoard[0][2];
        tempCube[2][2] = cubeBoard[1][2];
        pasteCube(tempCube); // tempCube가 새로운 cubeBoard가 됨.
    }

    public void whenL(char[][] tempCube){ // 가장 왼쪽 줄을 아래로 한 칸 밀기
        System.out.println("L : 가장 왼쪽 줄을 아래로 한 칸 밀기");
        copyCube(tempCube); // tempCube에 cubeBoard 내용 복사
        tempCube[0][0] = cubeBoard[2][0]; // 변경사항
        tempCube[1][0] = cubeBoard[0][0];
        tempCube[2][0] = cubeBoard[1][0];
        pasteCube(tempCube); // tempCube가 새로운 cubeBoard가 됨.
    }
    public void whenLDot(char[][] tempCube){ // 가장 왼쪽 줄을 위로 한 칸 밀기
        System.out.println("L' : 가장 왼쪽 줄을 위로 한 칸 밀기");
        copyCube(tempCube); // tempCube에 cubeBoard 내용 복사
        tempCube[0][0] = cubeBoard[1][0]; // 변경사항
        tempCube[1][0] = cubeBoard[2][0];
        tempCube[2][0] = cubeBoard[0][0];
        pasteCube(tempCube); // tempCube가 새로운 cubeBoard가 됨.
    }
    public void whenB(char[][] tempCube){ // 가장 아랫줄을 오른쪽으로 한 칸 밀기
        System.out.println("B : 가장 아랫줄을 오른쪽으로 한 칸 밀기");
        copyCube(tempCube); // tempCube에 cubeBoard 내용 복사
        tempCube[2][0] = cubeBoard[2][2]; // 변경사항
        tempCube[2][1] = cubeBoard[2][0];
        tempCube[2][2] = cubeBoard[2][1];
        pasteCube(tempCube); // tempCube가 새로운 cubeBoard가 됨.
    }
    public void whenBDot(char[][] tempCube){ // 가장 아랫줄을 왼쪽으로 한 칸 밀기
        System.out.println("B' : 가장 아랫줄을 왼쪽으로 한 칸 밀기");
        copyCube(tempCube); // tempCube에 cubeBoard 내용 복사
        tempCube[2][0] = cubeBoard[2][1]; // 변경사항
        tempCube[2][1] = cubeBoard[2][2];
        tempCube[2][2] = cubeBoard[2][0];
        pasteCube(tempCube); // tempCube가 새로운 cubeBoard가 됨.
    }
}
