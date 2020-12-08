package Step2;

import java.util.Scanner;

public class Step2 { // 평면 큐브 구현하기
    Scanner sc = new Scanner(System.in);

    char[][] cubeBoard = new char[3][3];

    public static void main(String[] args) {
        Step2 step2 = new Step2();
        System.out.println("💬 평면 큐브를 맞춰보세요! \n아래 입력에 따라 큐브가 돌아갑니다.");
        step2.initCube();
        step2.starter();
    }

    public void initCube() {
        System.out.println("초기화");
        char[][] initCube = new char[3][3];
        initCube[0] = new char[]{'R', 'R', 'W'};
        initCube[1] = new char[]{'G', 'C', 'W'};
        initCube[2] = new char[]{'G', 'B', 'B'};
        cubeBoard = initCube;
    }

    public void starter() {
        printCube();
        System.out.println("U : 가장 윗줄을 왼쪽으로 한 칸 밀기, U' : 가장 윗줄을 오른쪽으로 한 칸 밀기");
        System.out.println("R : 가장 오른쪽 줄을 위로 한 칸 밀기, R' : 가장 오른쪽 줄을 아래로 한 칸 밀기");
        System.out.println("L : 가장 왼쪽 줄을 아래로 한 칸 밀기, L' : 가장 왼쪽 줄을 위로 한 칸 밀기");
        System.out.println("B : 가장 아랫줄을 오른쪽으로 한 칸 밀기, B' : 가장 아랫줄을 왼쪽으로 한 칸 밀기");
        System.out.println("Q : 프로그램 종료");
        System.out.print("CUBE > ");
        String input = sc.nextLine();
        System.out.println(input);
        if (input.equals("U") || input.equals("U'") || input.equals("R") || input.equals("R'")
                || input.equals("L") || input.equals("L'") || input.equals("B") || input.equals("B'") || input.equals("Q")) {
            System.out.println("올바른 입력값");
            checkInput(input);
        } else {
            System.out.println("❗ 지정되지 않은 값을 입력했습니다. 다시 입력해 주세요.");
            starter();
            checkInput(input);
        }
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

    public void checkInput(String input){
        char[][] tempCube = new char[3][3];
        switch(input) {
            case "U" :
                whenU(tempCube);
                printCube();
                starter();
                break;
            case "U'" :
                whenUDot(tempCube);
                printCube();
                starter();
                break;
            case "R" :
                whenR();
                //printCube();
                starter();
                break;
            case "R'" :
                whenRDot();
               //printCube();
                starter();
                break;
            case "L" :
                whenL();
                //printCube();
                starter();
                break;
            case "L'" :
                whenLDot();
                //printCube();
                starter();
                break;
            case "B" :
                whenB();
                //printCube();
                starter();
                break;
            case "B'" :
                whenBDot();
                //printCube();
                starter();
                break;
            case "Q" :
                System.out.println("Bye~");
                System.exit(0);
                break;
        }
    }


    public void whenU(char[][] tempCube){ // 가장 윗줄을 왼쪽으로 한 칸 밀기
        System.out.println("U");
        tempCube = cubeBoard; // 임시큐브에 큐브내용 복사
        printCube();//test
        System.out.println("tempCube[0][0] = " + tempCube[0][0]);
        System.out.println("cube[0][1] = " + cubeBoard[0][1]);
        tempCube[0][0] = cubeBoard[0][1]; // 변경사항
        System.out.println("tempCube[0][0] = " + tempCube[0][0]);
        System.out.println("cube[0][1] = " + cubeBoard[0][1]);
        System.out.println("--------------");
        System.out.println("tempCube[0][1] = " + tempCube[0][1]);
        System.out.println("cube[0][2] = " + cubeBoard[0][2]);
        tempCube[0][1] = cubeBoard[0][2];
        System.out.println("tempCube[0][1] = " + tempCube[0][1]);
        System.out.println("cube[0][2] = " + cubeBoard[0][2]);
        System.out.println("--------------");
        System.out.println("tempCube[0][2] = " + tempCube[0][2]);
        System.out.println("cube[0][0] = " + cubeBoard[0][0]);
        tempCube[0][2] = cubeBoard[0][0];
        System.out.println("tempCube[0][2] = " + tempCube[0][2]);
        System.out.println("cube[0][0] = " + cubeBoard[0][0]);
        System.out.println("*이건 달라야 해*");
        System.out.println("이건cube");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cubeBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println("이건tempCube");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tempCube[i][j]);
            }
            System.out.println();
        }
        cubeBoard = tempCube; // 임시큐브가 새로운 큐브가 됨
        tempCube = new char[3][3]; // 임시큐브 내용은 삭제
    }

    public void whenUDot(char[][] tempCube){ // 가장 윗줄을 오른쪽으로 한 칸 밀기
        System.out.println("UDot");
        tempCube = cubeBoard; // 임시큐브에 큐브내용 복사
        printCube();//test
        System.out.println("tempCube[0][0] = " + tempCube[0][0]);
        System.out.println("cube[0][2] = " + cubeBoard[0][2]);
        tempCube[0][0] = cubeBoard[0][2]; // 변경사항
        System.out.println("tempCube[0][0] = " + tempCube[0][0]);
        System.out.println("cube[0][2] = " + cubeBoard[0][2]);
        System.out.println("--------------");
        System.out.println("tempCube[0][1] = " + tempCube[0][1]);
        System.out.println("cube[0][0] = " + cubeBoard[0][0]);
        tempCube[0][1] = cubeBoard[0][0];
        System.out.println("tempCube[0][1] = " + tempCube[0][1]);
        System.out.println("cube[0][0] = " + cubeBoard[0][0]);
        System.out.println("--------------");
        System.out.println("tempCube[0][2] = " + tempCube[0][2]);
        System.out.println("cube[0][1] = " + cubeBoard[0][1]);
        tempCube[0][2] = cubeBoard[0][1];
        System.out.println("tempCube[0][2] = " + tempCube[0][2]);
        System.out.println("cube[0][1] = " + cubeBoard[0][1]);
        System.out.println("*이건 달라야 해*");
        System.out.println("이건cube");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cubeBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println("이건tempCube");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tempCube[i][j]);
            }
            System.out.println();
        }
        cubeBoard = tempCube; // 임시큐브가 새로운 큐브가 됨
        tempCube = new char[3][3]; // 임시큐브 내용은 삭제
    }
    public void whenR(){ // 가장 오른쪽 줄을 위로 한 칸 밀기

    }
    public void whenRDot(){ // 가장 오른쪽 줄을 아래로 한 칸 밀기

    }
    public void whenL(){ // 가장 왼쪽 줄을 아래로 한 칸 밀기

    }
    public void whenLDot(){ // 가장 왼쪽 줄을 위로 한 칸 밀기

    }
    public void whenB(){ // 가장 아랫줄을 오른쪽으로 한 칸 밀기

    }
    public void whenBDot(){ // 가장 아랫줄을 왼쪽으로 한 칸 밀기

    }

}
