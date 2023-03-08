package view;

import java.util.Scanner;

public class LoginViewer {
    private final Scanner scanner = new Scanner(System.in);

    public LoginViewer() {
    }

    public int askCommend() {
        System.out.println();
        System.out.println("<< 경량 게시판 프로젝트 >>");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("3. 종료");
        System.out.print(">>");
        return Integer.parseInt(scanner.nextLine());
    }

    public void printExit() {
        System.out.println("프로그램이 종료되었습니다.");
    }

    public String askId() {
        System.out.print("ID를 입력해주세요 : ");
        return scanner.nextLine();
    }

    public String askPw() {
        System.out.print("PW를 입력해주세요 : ");
        return scanner.nextLine();
    }

    public String askName() {
        System.out.print("이름을 입력해주세요 : ");
        return scanner.nextLine();
    }

    public String askContact() {
        System.out.print("연락처를 입력해주세요 : ");
        return scanner.nextLine();
    }

    public void printException(String message) {
        System.out.println(message);
    }

}
