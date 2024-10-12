package nested.test.ex1;

public class Library {

    private int max;
    private static int count;
    private Book[] books;

    public Library(int max) {
        this.max = max;
        this.books = new Book[max];
    }

    public void addBook(String title, String author) {

        if (count >= max) {
            System.out.println("도서관 저장 공간이 부족합니다.");
            return ;
        }

        books[count] = new Book(title, author);
        count++;
    }

    public void showBooks() {
        System.out.println("== 책 목록 출력 ==");
        for (Book book : books) {
            System.out.println("도서 제목: " + book.title + ", 저자: " + book.author);
        }
    }

    private class Book {

        private String title;
        private String author;

        private Book(String title, String author) {
            this.title = title;
            this.author = author;
        }
    }
}
