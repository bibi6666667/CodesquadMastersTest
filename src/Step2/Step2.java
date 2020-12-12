package Step2;

import java.util.ArrayList;
import java.util.Scanner;

public class Step2 { // 평면 큐브 구현하기
    Scanner sc = new Scanner(System.in);

    char[][] cubeBoard = new char[3][3];

    public static void main(String[] args) {
        Step2 step2 = new Step2();
        System.out.println("💬 평면 큐브 퍼즐을 움직여 보세요!");
        step2.initCube();
        step2.printCube();
        step2.ready();
    }

    public void initCube() {
        char[][] initCube = new char[3][3];
        initCube[0] = new char[]{'R', 'R', 'W'};
        initCube[1] = new char[]{'G', 'C', 'W'};
        initCube[2] = new char[]{'G', 'B', 'B'};
        cubeBoard = initCube;
    }

    public void ready(){
        String input = start();
        ArrayList<String> inputList = trimInput(input);
        checkInput(inputList);
    }

    public void printCube() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cubeBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    public String start() {
        System.out.println("↑ 현재 큐브-------------------------------------------------------");
        System.out.println("✔ 아래 명령어를 통해 퍼즐을 한 칸씩 움직일 수 있습니다.");
        System.out.println(" U : 가장 윗줄을 왼쪽으로 한 칸 밀기, U' : 가장 윗줄을 오른쪽으로 한 칸 밀기");
        System.out.println(" R : 가장 오른쪽 줄을 위로 한 칸 밀기, R' : 가장 오른쪽 줄을 아래로 한 칸 밀기");
        System.out.println(" L : 가장 왼쪽 줄을 아래로 한 칸 밀기, L' : 가장 왼쪽 줄을 위로 한 칸 밀기");
        System.out.println(" B : 가장 아랫줄을 오른쪽으로 한 칸 밀기, B' : 가장 아랫줄을 왼쪽으로 한 칸 밀기");
        System.out.println("⛔ Q 를 입력하면 프로그램이 종료됩니다.");
        System.out.print("CUBE > ");
        String input = sc.nextLine();
        return input;
    }

    public ArrayList<String> trimInput(String input){  // 입력값 다듬기
        String[] inputArr = input.split("");
        ArrayList<String> inputList = new ArrayList<>(); // 리턴값 ArrayList
        for (int i = 0; i < inputArr.length; i++) {
            String inputElement = inputArr[i];
            if (inputElement.equals("'")) {
                inputArr[i - 1] = inputArr[i - 1] + "'"; // ' 요소는 직전 요소에 붙여주기
            }
        }
        for (int i = 0; i < inputArr.length; i++) {
            String inputArrElement = inputArr[i];
            if (!inputArrElement.equals("'")) { // 요소가 '이 아닐 때만 inputList에 넣기
                inputList.add(inputArrElement);
            }
        }
        return inputList;
    }

    public void checkInput(ArrayList<String> inputList){
        int inputListSize = inputList.size();
        for (int i = 0; i < inputListSize; i++) {
            String anInput = inputList.get(i);
            if (anInput.equals("U'") || anInput.equals("U") || anInput.equals("R'") || anInput.equals("R")
                || anInput.equals("L'") || anInput.equals("L") || anInput.equals("B'") || anInput.equals("B")
                || anInput.equals("Q")) {
                guideInput(anInput);
            } else {
                System.out.println("❗ 지정되지 않은 값이 포함되어 있습니다. 명렁어를 다시 입력해 주세요.\n");
                String reInput = start();
                ArrayList<String> reInputList = trimInput(reInput);
                checkInput(reInputList);
            }
        }
        ready();
    }

    public void guideInput(String anInput){
        char[][] tempCube = new char[3][3]; // 임시변수 tempCube 초기화
        switch(anInput) {
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
                exit();
                break;
        }
        printCube();
        System.out.println(); // 줄바꿈
    }

    public void exit() {
        sc.close();
        System.out.println("Bye~");
        System.exit(0);
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

    public void whenU(char[][] tempCube){ // U : 가장 윗줄을 왼쪽으로 한 칸 밀기
        System.out.println("\nU");
        copyCube(tempCube); // tempCube에 cubeBoard 내용 복사
        tempCube[0][0] = cubeBoard[0][1]; // 변경사항
        tempCube[0][1] = cubeBoard[0][2];
        tempCube[0][2] = cubeBoard[0][0];
        pasteCube(tempCube); // tempCube가 새로운 cubeBoard가 됨.
    }

    public void whenUDot(char[][] tempCube){ // U' : 가장 윗줄을 오른쪽으로 한 칸 밀기
        System.out.println("\nU'");
        copyCube(tempCube);
        tempCube[0][0] = cubeBoard[0][2];
        tempCube[0][1] = cubeBoard[0][0];
        tempCube[0][2] = cubeBoard[0][1];
        pasteCube(tempCube);
    }

    public void whenR(char[][] tempCube){ // R : 가장 오른쪽 줄을 위로 한 칸 밀기
        System.out.println("\nR");
        copyCube(tempCube);
        tempCube[0][2] = cubeBoard[1][2];
        tempCube[1][2] = cubeBoard[2][2];
        tempCube[2][2] = cubeBoard[0][2];
        pasteCube(tempCube);
    }

    public void whenRDot(char[][] tempCube){ // R' : 가장 오른쪽 줄을 아래로 한 칸 밀기
        System.out.println("\nR'");
        copyCube(tempCube);
        tempCube[0][2] = cubeBoard[2][2];
        tempCube[1][2] = cubeBoard[0][2];
        tempCube[2][2] = cubeBoard[1][2];
        pasteCube(tempCube);
    }

    public void whenL(char[][] tempCube){ // L : 가장 왼쪽 줄을 아래로 한 칸 밀기
        System.out.println("\nL");
        copyCube(tempCube);
        tempCube[0][0] = cubeBoard[2][0];
        tempCube[1][0] = cubeBoard[0][0];
        tempCube[2][0] = cubeBoard[1][0];
        pasteCube(tempCube);
    }

    public void whenLDot(char[][] tempCube){ // L' : 가장 왼쪽 줄을 위로 한 칸 밀기
        System.out.println("\nL'");
        copyCube(tempCube);
        tempCube[0][0] = cubeBoard[1][0];
        tempCube[1][0] = cubeBoard[2][0];
        tempCube[2][0] = cubeBoard[0][0];
        pasteCube(tempCube);
    }

    public void whenB(char[][] tempCube){ // B : 가장 아랫줄을 오른쪽으로 한 칸 밀기
        System.out.println("\nB");
        copyCube(tempCube);
        tempCube[2][0] = cubeBoard[2][2];
        tempCube[2][1] = cubeBoard[2][0];
        tempCube[2][2] = cubeBoard[2][1];
        pasteCube(tempCube);
    }

    public void whenBDot(char[][] tempCube){ // B' : 가장 아랫줄을 왼쪽으로 한 칸 밀기
        System.out.println("\nB'");
        copyCube(tempCube);
        tempCube[2][0] = cubeBoard[2][1];
        tempCube[2][1] = cubeBoard[2][2];
        tempCube[2][2] = cubeBoard[2][0];
        pasteCube(tempCube);
    }
} // class
