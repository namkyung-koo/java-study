package array;

import java.util.Scanner;

public class ArrayDi4 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("행을 입력하세요: ");
        int row = scanner.nextInt();

        System.out.print("열을 입력하세요: ");
        int column = scanner.nextInt();

        int[][] arr = new int[row][column];

        int i = 0;
        for (int j = 0; j < arr.length; j++) {
            for (int k = 0; k < arr[j].length; k++) {
                arr[j][k] = ++i;
            }
        }

        for (int j = 0; j < arr.length; j++) {
            for (int k = 0; k < arr[j].length; k++) {
                System.out.print(arr[j][k] + " ");
            }
            System.out.println();
        }
    }
}
