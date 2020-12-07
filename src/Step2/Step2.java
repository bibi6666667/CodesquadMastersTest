package Step2;

import java.util.Scanner;

public class Step2 { // 평면 큐브 구현하기
    Scanner sc = new Scanner(System.in);

    String[][] cube = new String[3][3];
    String[][] tempCube = new String[3][3];

    public static void main(String[] args) {
        Step2 step2 = new Step2();
        step2.initCube();
        step2.printCube();
        System.out.println("💬 평면 큐브를 맞춰보세요! \n아래 입력에 따라 큐브가 돌아갑니다.");
        String input = step2.starter();
        step2.checkInput(input);
    }

    public String starter() {
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
        } else {
            System.out.println("❗ 지정되지 않은 값을 입력했습니다. 다시 입력해 주세요.");
            starter();
        }
        return input;
    }

    public void initCube() {
        cube[0] = new String[]{"R", "R", "W"};
        cube[1] = new String[]{"G", "C", "W"};
        cube[2] = new String[]{"G", "B", "B"};
    }

    public void printCube() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cube[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public void checkInput(String input){
        switch(input) {
            case "U" :
                whenU();
                printCube();
                starter();
                break;
            case "U'" :
                whenUDot();
                printCube();
                starter();
                break;
            case "R" :
                whenR();
                printCube();
                starter();
                break;
            case "R'" :
                whenRDot();
                printCube();
                starter();
                break;
            case "L" :
                whenL();
                printCube();
                starter();
                break;
            case "L'" :
                whenLDot();
                printCube();
                starter();
                break;
            case "B" :
                whenB();
                printCube();
                starter();
                break;
            case "B'" :
                whenBDot();
                printCube();
                starter();
                break;
            case "Q" :
                System.out.println("Bye~");
                System.exit(0);
                break;
        }
    }

    public void whenU(){ // 가장 윗줄을 왼쪽으로 한 칸 밀기
        System.out.println("U");
        tempCube = cube; // 임시큐브에 큐브내용 복사
        tempCube[0][0] = cube[0][1]; // 변경사항
        tempCube[0][1] = cube[0][2];
        tempCube[0][2] = cube[0][0];
        cube = tempCube; // 임시큐브가 새로운 큐브가 됨
        tempCube = new String[3][3]; // 임시큐브 내용은 삭제
    }
    public void whenUDot(){ // 가장 윗줄을 오른쪽으로 한 칸 밀기
        System.out.println("UDot");
        // TODO : 여기서 오류.
        tempCube = cube; // 임시큐브에 큐브내용 복사
        tempCube[0][0] = cube[0][2];
        tempCube[0][1] = cube[0][0];
        tempCube[0][2] = cube[0][1];
        cube = tempCube; // 임시큐브가 새로운 큐브가 됨
        tempCube = new String[3][3]; // 임시큐브 내용은 삭제
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
