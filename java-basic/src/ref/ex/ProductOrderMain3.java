package ref.ex;

import java.util.Scanner;

public class ProductOrderMain3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("입력할 주문의 개수를 입력하세요: ");
        int orderCount = scanner.nextInt();
        scanner.nextLine();

        ProductOrder[] orders = new ProductOrder[orderCount];

        for (int i = 0; i < orderCount; i++) {
            System.out.println((i + 1) + "번째 주문 정보를 입력하세요.");
            System.out.print("상품명: ");
            String productName = scanner.nextLine();
            System.out.print("가격: ");
            int productPrice = scanner.nextInt();
            System.out.print("수량: ");
            int productQuantity = scanner.nextInt();
            scanner.nextLine();
            orders[i] = createOrder(productName, productPrice, productQuantity);
        }

        // 결과 출력
        int sum = 0;
        for (ProductOrder order : orders) {
            printOrders(order);
            sum += order.price * order.quantity;
        }
        System.out.println("총 결제 금액: " + sum);
    }

    public static void printOrders(ProductOrder order) {
        System.out.println("상품명: " + order.productName + ", 가격: " + order.price + ", 수량: " +order.quantity);
    }

    public static ProductOrder createOrder(String productName, int price, int quantity) {
        ProductOrder productOrder = new ProductOrder();
        productOrder.productName = productName;
        productOrder.price = price;
        productOrder.quantity = quantity;
        return productOrder;
    }
}
