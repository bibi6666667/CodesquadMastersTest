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

    public void pasteCube(char[][] tempCube){ // tempCube 내용을 cubeBoard로 붙여넣기
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j ++){
                cubeBoard[i][j] = tempCube[i][j];
            }
        }
    }



    public void checkInput(String input){
        char[][] tempCube = new char[3][3]; // tempCube 초기화
        switch(input) {
            case "U" :
                whenU(tempCube);
                starter();
                break;
            case "U'" :
                whenUDot(tempCube);
                starter();
                break;
            case "R" :
                whenR(tempCube);
                starter();
                break;
            case "R'" :
                whenRDot(tempCube);
                starter();
                break;
            case "L" :
                whenL(tempCube);
                starter();
                break;
            case "L'" :
                whenLDot(tempCube);
                starter();
                break;
            case "B" :
                whenB(tempCube);
                starter();
                break;
            case "B'" :
                whenBDot(tempCube);
                starter();
                break;
            case "Q" :
                System.out.println("Bye~");
                System.exit(0);
                break;
        }
    }


    public void whenU(char[][] tempCube){ // 가장 윗줄을 왼쪽으로 한 칸 밀기
        System.out.println("U : 가장 윗줄을 왼쪽으로 한 칸 밀기");
        copyCube(tempCube); // tempCube에 cubeBoard 내용 복사
        tempCube[0][0] = cubeBoard[0][1]; // 변경사항
        tempCube[0][1] = cubeBoard[0][2];
        tempCube[0][2] = cubeBoard[0][0];
        // test print
        System.out.println("****이건 달라야 해****");
        System.out.println("↓ cubeBoard");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cubeBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println("↓ tempCube");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tempCube[i][j]);
            }
            System.out.println();
        }
        pasteCube(tempCube); // tempCube가 새로운 cubeBoard가 됨.
    }

    public void whenUDot(char[][] tempCube){ // 가장 윗줄을 오른쪽으로 한 칸 밀기
        System.out.println("U' : 가장 윗줄을 오른쪽으로 한 칸 밀기");
        copyCube(tempCube); // tempCube에 cubeBoard 내용 복사
        tempCube[0][0] = cubeBoard[0][2]; // 변경사항
        tempCube[0][1] = cubeBoard[0][0];
        tempCube[0][2] = cubeBoard[0][1];
        // test print
        System.out.println("****이건 달라야 해****");
        System.out.println("↓ cubeBoard");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cubeBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println("↓ tempCube");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tempCube[i][j]);
            }
            System.out.println();
        }
        pasteCube(tempCube); // tempCube가 새로운 cubeBoard가 됨.
    }

    public void whenR(char[][] tempCube){ // 가장 오른쪽 줄을 위로 한 칸 밀기
        System.out.println("R : 가장 오른쪽 줄을 위로 한 칸 밀기");
        copyCube(tempCube); // tempCube에 cubeBoard 내용 복사
        tempCube[0][2] = cubeBoard[1][2]; // 변경사항
        tempCube[1][2] = cubeBoard[2][2];
        tempCube[2][2] = cubeBoard[0][2];
        // test print
        System.out.println("****이건 달라야 해****");
        System.out.println("↓ cubeBoard");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cubeBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println("↓ tempCube");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tempCube[i][j]);
            }
            System.out.println();
        }
        pasteCube(tempCube); // tempCube가 새로운 cubeBoard가 됨.
    }

    public void whenRDot(char[][] tempCube){ // 가장 오른쪽 줄을 아래로 한 칸 밀기
        System.out.println("R' : 가장 오른쪽 줄을 아래로 한 칸 밀기");
        copyCube(tempCube); // tempCube에 cubeBoard 내용 복사
        tempCube[0][2] = cubeBoard[2][2]; // 변경사항
        tempCube[1][2] = cubeBoard[0][2];
        tempCube[2][2] = cubeBoard[1][2];
        // test print
        System.out.println("****이건 달라야 해****");
        System.out.println("↓ cubeBoard");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cubeBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println("↓ tempCube");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tempCube[i][j]);
            }
            System.out.println();
        }
        pasteCube(tempCube); // tempCube가 새로운 cubeBoard가 됨.
    }

    public void whenL(char[][] tempCube){ // 가장 왼쪽 줄을 아래로 한 칸 밀기
        System.out.println("L : 가장 왼쪽 줄을 아래로 한 칸 밀기");
        copyCube(tempCube); // tempCube에 cubeBoard 내용 복사
        tempCube[0][0] = cubeBoard[2][0]; // 변경사항
        tempCube[1][0] = cubeBoard[0][0];
        tempCube[2][0] = cubeBoard[1][0];
        // test print
        System.out.println("****이건 달라야 해****");
        System.out.println("↓ cubeBoard");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cubeBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println("↓ tempCube");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tempCube[i][j]);
            }
            System.out.println();
        }
        pasteCube(tempCube); // tempCube가 새로운 cubeBoard가 됨.
    }
    public void whenLDot(char[][] tempCube){ // 가장 왼쪽 줄을 위로 한 칸 밀기
        System.out.println("L' : 가장 왼쪽 줄을 위로 한 칸 밀기");
        copyCube(tempCube); // tempCube에 cubeBoard 내용 복사
        tempCube[0][0] = cubeBoard[1][0]; // 변경사항
        tempCube[1][0] = cubeBoard[2][0];
        tempCube[2][0] = cubeBoard[0][0];
        // test print
        System.out.println("****이건 달라야 해****");
        System.out.println("↓ cubeBoard");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cubeBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println("↓ tempCube");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tempCube[i][j]);
            }
            System.out.println();
        }
        pasteCube(tempCube); // tempCube가 새로운 cubeBoard가 됨.
    }
    public void whenB(char[][] tempCube){ // 가장 아랫줄을 오른쪽으로 한 칸 밀기
        System.out.println("B : 가장 아랫줄을 오른쪽으로 한 칸 밀기");
        copyCube(tempCube); // tempCube에 cubeBoard 내용 복사
        tempCube[2][0] = cubeBoard[2][2]; // 변경사항
        tempCube[2][1] = cubeBoard[2][0];
        tempCube[2][2] = cubeBoard[2][1];
        // test print
        System.out.println("****이건 달라야 해****");
        System.out.println("↓ cubeBoard");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cubeBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println("↓ tempCube");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tempCube[i][j]);
            }
            System.out.println();
        }
        pasteCube(tempCube); // tempCube가 새로운 cubeBoard가 됨.
    }
    public void whenBDot(char[][] tempCube){ // 가장 아랫줄을 왼쪽으로 한 칸 밀기
        System.out.println("B' : 가장 아랫줄을 왼쪽으로 한 칸 밀기");
        copyCube(tempCube); // tempCube에 cubeBoard 내용 복사
        tempCube[2][0] = cubeBoard[2][1]; // 변경사항
        tempCube[2][1] = cubeBoard[2][2];
        tempCube[2][2] = cubeBoard[2][0];
        // test print
        System.out.println("****이건 달라야 해****");
        System.out.println("↓ cubeBoard");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cubeBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println("↓ tempCube");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tempCube[i][j]);
            }
            System.out.println();
        }
        pasteCube(tempCube); // tempCube가 새로운 cubeBoard가 됨.
    }
}
