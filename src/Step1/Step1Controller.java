package Step1;

import java.util.Scanner;

public class Step1Controller {
    Scanner sc = new Scanner(System.in); // 맨 마지막에 닫기

    public static void main(String[] args) {
        Step1Controller controller = new Step1Controller();
        controller.starter();
    }

    public void starter() {
        System.out.println("< step1. 단어 밀어내기 >");
        String word = inputWord();
        String checkedWord = wordCheck(word);
        String num = inputNum();
        String checkedNum = numCheck(num);
        String LR = inputLR();
        String checkedLR = LRCheck(LR);
        System.out.println(checkedWord + "," + checkedNum + "," + checkedLR);
        trimInput(checkedWord, checkedNum, checkedLR);
    }

    public String inputWord() {
        System.out.println("단어 하나를 입력해 주세요.");
        String word = sc.nextLine();
        return word;
    }

    public String wordCheck(String word) {
        if (word.split(" ").length == 1) {
            System.out.println("word 검사완료");
            return word;
        } else {
            System.out.println("Error : 띄어쓰기 없이 한 문자로 다시 입력해 주세요.");
            word = inputWord();
            wordCheck(word);
            return word;
        }
    }

    public String inputNum() {
        System.out.println("숫자를 입력해 주세요. 입력한 숫자만큼 밀어냅니다.");
        System.out.println("-100이상, 100미만의 정수로 입력해 주세요.");
        String num = sc.nextLine();
        return num;
    }

    public String numCheck(String num) {
        int intNum = Integer.parseInt(num);
        if (intNum >= -100 && intNum < 100) {
            System.out.println("num 검사완료");
            String strNum = Integer.toString(intNum);
            return strNum;
        } else {
            System.out.println("Error : -100 이상 100 미만의 정수를 입력해 주세요");
            num = inputNum();
            numCheck(num); // 다시 체크
            return num;
        }
    }

    public String inputLR() {
        System.out.println("L(l) 또는 R(r)을 입력해 주세요. ");
        System.out.println("L 입력시 왼쪽으로, R 입력시 오른쪽으로 밀어냅니다.");
        String LR = sc.nextLine();
        return LR;
    }

    public String LRCheck(String LR) {
        if (LR.equals("L") || LR.equals("l") || LR.equals("R") || LR.equals("r")) {
            System.out.println("LR 검사완료");
            return LR;
        } else {
            System.out.println("Error : L, l (왼쪽) / R, r (오른쪽) 중 하나만 입력해 주세요.");
            LR = inputLR();
            LRCheck(LR);
            return LR;
        }
    }

    public void trimInput(String word, String num, String LR) { // 각각 함수로 만들고 리턴값 준 다음 pushWord에서 호출해 쓰기
        System.out.println("checkedWord:" + word + " checkedNum:" + num + " checkedLR:" + LR);

        String[] tempWord = word.split(""); // word) 각 글자를 element로 하는 배열로 변환
        if (LR.equals("l")) {  // LR) 소문자입력 -> 대문자로 변환
            LR = "L";
        }
        if (LR.equals("r")) {
            LR = "R";
        }
        int intNum = Integer.parseInt(num); // num) 음수는 LR반대로 & 양수로 하고, 양수는 word.length로 나눈 나머지로 치환
        if (intNum < 0) { // num이 음수이면
            if (LR.equals("L")) LR = "R"; // 왼->오, 오->왼
            else if (LR.equals("R")) LR = "L";
            intNum *= -1; // 양수로 만들기
        }
        int pushNum = intNum % word.length();
        //print
        System.out.println("tempWord 길이 : " + tempWord.length);
        System.out.println("LR : " + LR);
        System.out.println("intNum : " + intNum);
        System.out.println("pushNum : " + pushNum);
        pushWord(tempWord, pushNum, LR);
    }

    public String pushWord(String[] trimmedWord, int trimmedNum, String trimmedLR) {


        String result = null;
        return result;
    }


}
