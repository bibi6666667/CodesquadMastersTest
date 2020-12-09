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
        initFront[0] = new char[]{'G', 'G', 'G'}; // init Front = Green
        initFront[1] = new char[]{'G', 'G', 'G'};
        initFront[2] = new char[]{'G', 'G', 'G'};
        initUp[0] = new char[]{'W', 'W', 'W'}; // init Up = White
        initUp[1] = new char[]{'W', 'W', 'W'};
        initUp[2] = new char[]{'W', 'W', 'W'};
        initLeft[0] = new char[]{'O', 'O', 'O'}; // init Left = Orange
        initLeft[1] = new char[]{'O', 'O', 'O'};
        initLeft[2] = new char[]{'O', 'O', 'O'};
        initRight[0] = new char[]{'R', 'R', 'R'}; // init Right = Red
        initRight[1] = new char[]{'R', 'R', 'R'};
        initRight[2] = new char[]{'R', 'R', 'R'};
        initDown[0] = new char[]{'Y', 'Y', 'Y'}; // init Down = Yellow
        initDown[1] = new char[]{'Y', 'Y', 'Y'};
        initDown[2] = new char[]{'Y', 'Y', 'Y'};
        initBack[0] = new char[]{'B', 'B', 'B'}; // init Back = Blue
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

}

