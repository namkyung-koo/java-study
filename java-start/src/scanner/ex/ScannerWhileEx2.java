package scanner.ex;

import java.util.Scanner;

public class ScannerWhileEx2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;

        while (true) {

            System.out.println("1: 상품 입력, 2: 결제, 3: 프로그램 종료");
            int option = scanner.nextInt();
            scanner.nextLine(); // 이전에 입력된 개행문자 제거

            if (option == 1) {
                System.out.print("상품명을 입력하세요: ");
                String name = scanner.nextLine();

                System.out.print("상품의 가격을 입력하세요: ");
                int price = scanner.nextInt();

                System.out.print("구매 수량을 입력하세요: ");
                int quantity = scanner.nextInt();

                sum += price * quantity;
                System.out.println("상품명: " + name + " 가격: " + price + " 합게: " + sum);
            } else if (option == 2) {
                System.out.println("총 비용: " + sum);
            } else if (option == 3) {
                break;
            } else {
                System.out.println("올바른 옵션을 선택해주세요.");
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }
}

/*
nextInt(): 숫자만 읽고, 개행 문자는 무시. 입력 스트림에 남아있는 개행 문자를 그대로 둠.
nextLine(): 줄 전체를 읽으므로 남아있는 개행 문자를 읽게 되어, 의도치 않게 빈 줄을 읽게 될 수 있음.
 */