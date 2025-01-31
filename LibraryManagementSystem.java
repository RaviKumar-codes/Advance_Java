// Library Management Programming

import java.util.ArrayList;
import java.util.Scanner;

class Book {
    int bookId;
    String title;
    String author;
    boolean isAvailable;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public void displayBookInfo() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Availability: " + (isAvailable ? "Available" : "Borrowed"));
    }

    public void borrowBook() {
        isAvailable = false;
    }

    public void returnBook() {
        isAvailable = true;
    }
}

class Library {
    ArrayList<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public void removeBook(int bookId) {
        books.removeIf(book -> book.bookId == bookId);
        System.out.println("Book removed successfully.");
    }

    public void listAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.isAvailable) {
                book.displayBookInfo();
                System.out.println("--------------------");
            }
        }
    }

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

class Student {
    int studentId;
    String name;
    ArrayList<Book> borrowedBooks;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book, Library library) {
        if (borrowedBooks.size() < 3) {
            if (book != null && book.isAvailable) {
                book.borrowBook();
                borrowedBooks.add(book);
                System.out.println(name + " borrowed " + book.title);
            } else if (book != null && !book.isAvailable) {
                System.out.println(book.title + " is currently unavailable.");
            } else {
                System.out.println("Book not found.");
            }
        } else {
            System.out.println(name + " has reached the maximum borrowing limit (3 books).");
        }
    }

    public void returnBook(Book book, Library library) {
        if (borrowedBooks.contains(book)) {
            book.returnBook();
            borrowedBooks.remove(book);
            System.out.println(name + " returned " + book.title);
        } else {
            System.out.println(name + " did not borrow this book.");
        }
    }

    public void displayBorrowedBooks() {
        System.out.println("Books borrowed by " + name + ":");
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed yet.");
        } else {
            for (Book book : borrowedBooks) {
                book.displayBookInfo();
                System.out.println("--------------------");
            }
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        // Add some initial books
        library.addBook(new Book(1, "The Lord of the Rings", "J.R.R. Tolkien"));
        library.addBook(new Book(2, "Pride and Prejudice", "Jane Austen"));
    

        // Create a student
        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();
        Student student = new Student(101, studentName);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. List Available Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Display Borrowed Books");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    library.listAvailableBooks();
                    break;
                case 2:
                    System.out.print("Enter book title to borrow: ");
                    String bookTitle = scanner.nextLine();
                    Book bookToBorrow = library.findBook(bookTitle);
                    student.borrowBook(bookToBorrow, library);
                    break;
                case 3:
                    System.out.print("Enter book title to return: ");
                    bookTitle = scanner.nextLine();
                    bookToBorrow = library.findBook(bookTitle);
                    student.returnBook(bookToBorrow, library);
                    break;
                case 4:
                    student.displayBorrowedBooks();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
