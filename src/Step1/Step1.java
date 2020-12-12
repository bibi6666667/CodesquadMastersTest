package Step1;

import java.util.Scanner;

public class Step1 {
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Step1 step1 = new Step1();
        step1.starter();
    }

    public void starter() {
        System.out.println("< step1. 단어 밀어내기 >");
        String word = inputWord();
        String checkedWord = wordCheck(word);
        int num = inputNum();
        int checkedNum = numCheck(num);
        String LR = inputLR();
        String checkedLR = LRCheck(LR);
        trimInput(checkedWord, checkedNum, checkedLR);
    }

    public String inputWord() {
        System.out.println("단어 하나를 입력해 주세요.");
        String word = sc.nextLine();
        return word;
    }

    public String wordCheck(String word) {
        if (word.split(" ").length == 1) {
            return word;
        } else {
            System.out.println("Error : 띄어쓰기 없이 한 문자로 다시 입력해 주세요.");
            word = inputWord();
            wordCheck(word);
            return word;
        }
    }

    public int inputNum() {
        System.out.println("숫자를 입력해 주세요. 입력한 숫자만큼 밀어냅니다.");
        System.out.println("-100이상, 100미만의 정수로 입력해 주세요.");
        int num = sc.nextInt();
        return num;
    }

    public int numCheck(int num) {
        if (num >= -100 && num < 100) {
            return num;
        } else {
            System.out.println("Error : -100 이상 100 미만의 정수를 입력해 주세요");
            num = inputNum();
            numCheck(num);
            return num;
        }
    }

    public String inputLR() {
        System.out.println("L(l) 또는 R(r)을 입력해 주세요.\nL 입력시 왼쪽으로, R 입력시 오른쪽으로 밀어냅니다.");
        String LR = sc.nextLine();
        return LR;
    }

    public String LRCheck(String LR) {
        if (LR.equals("L") || LR.equals("l") || LR.equals("R") || LR.equals("r")) {
            return LR;
        } else {
            System.out.println("Error : L, l (왼쪽) / R, r (오른쪽) 중 하나만 입력해 주세요.");
            LR = inputLR();
            LRCheck(LR);
            return LR;
        }
    }

    public void trimInput(String word, int num, String LR) {
        // LR) 소문자 입력은 대문자로 변환
        if (LR.equals("l")) LR = "L";
        if (LR.equals("r")) LR = "R";
        // num) 음수는 양수로 & LR반대로 만들고, 만들어진 양수는 word.length로 나눈 나머지로 치환
        if (num < 0) {
            if (LR.equals("L")) LR = "R"; // 왼->오, 오->왼
            else if (LR.equals("R")) LR = "L";
            num *= -1; // 양수로 만들기
        }
        int pushNum = num % word.length(); // pushNum = 실제 밀어내는 숫자
        // word) 그대로 반환
        pushWord(word, pushNum, LR);
    }

    public void pushWord(String word, int num, String LR) {
        String result = null;
        String wordLeft = null;
        String wordRight = null;
        int wordLength = word.length();
        if (LR.equals("L")) { // 왼쪽으로 밀어낼 때
            // : 앞에서부터 num번째까지 잘라내어(wordRight) 나머지 글자(wordLeft)의 뒤에 붙임.
            wordLeft = word.substring(num, wordLength);
            wordRight = word.substring(0, num);
        }
        if (LR.equals("R")) {// 오른쪽으로 밀어낼 떄
            // : 뒤에서부터 num번째까지 잘라내어(wordLeft) 나머지 글자(wordRight)의 앞에 붙임.
            wordLeft = word.substring(wordLength - num, wordLength);
            wordRight = word.substring(0, wordLength - num);
        }
        result = wordLeft + wordRight;
        System.out.println("결과 : " + result);
        sc.close();
    }
}
