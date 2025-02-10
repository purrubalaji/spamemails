
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {

    // Model Class for Book
    static class Book {
        private String title;
        private String author;
        private boolean isAvailable;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.isAvailable = true;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setAvailable(boolean available) {
            isAvailable = available;
        }

        @Override
        public String toString() {
            return "Title: " + title + ", Author: " + author + ", Available: " + isAvailable;
        }
    }

    // Service Class for Library
    static class LibraryService {
        private List<Book> books;

        public LibraryService() {
            books = new ArrayList<>();
        }

        public void addBook(Book book) {
            books.add(book);
        }

        public List<Book> viewAvailableBooks() {
            List<Book> availableBooks = new ArrayList<>();
            for (Book book : books) {
                if (book.isAvailable()) {
                    availableBooks.add(book);
                }
            }
            return availableBooks;
        }

        public boolean borrowBook(String title) {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                    book.setAvailable(false);
                    return true;
                }
            }
            return false;
        }

        public boolean returnBook(String title) {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title) && !book.isAvailable()) {
                    book.setAvailable(true);
                    return true;
                }
            }
            return false;
        }
    }

    // Main Class
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View Available Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    libraryService.addBook(new Book(title, author));
                    System.out.println("Book added successfully.");
                    break;

                case 2:
                    List<Book> availableBooks = libraryService.viewAvailableBooks();
                    System.out.println("Available Books:");
                    for (Book book : availableBooks) {
                        System.out.println(book);
                    }
                    break;

                case 3:
                    System.out.print("Enter book title to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    if (libraryService.borrowBook(borrowTitle)) {
                        System.out.println("Book borrowed successfully.");
                    } else {
                        System.out.println("Book not available.");
                    }
                    break;

                case 4:
                    System.out.print("Enter book title to return: ");
                    String returnTitle = scanner.nextLine();
                    if (libraryService.returnBook(returnTitle)) {
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("This book was not borrowed.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}