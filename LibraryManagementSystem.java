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

  
        Book book1 = new Book(1, "The Lord of the Rings", "J.R.R. Tolkien");
        Book book2 = new Book(2, "Pride and Prejudice", "Jane Austen");
        Book book3 = new Book(3, "1984", "George Orwell");
        Book book4 = new Book(4, "To Kill a Mockingbird", "Harper Lee");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
            
        // Create a student
        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();
        Student student1 = new Student(101, studentName);
      

        student1.borrowBook(library.findBook("The Lord of the Rings"), library);
        student1.borrowBook(library.findBook("Pride and Prejudice"), library);
        student1.borrowBook(library.findBook("1984"), library);
        student1.borrowBook(library.findBook("To Kill a Mockingbird"), library); // Tries to borrow a 4th book

        student1.displayBorrowedBooks();

        library.listAvailableBooks();

        student1.returnBook(library.findBook("The Lord of the Rings"), library);

        library.listAvailableBooks(); // The Lord of the Rings should now be available.

        student1.displayBorrowedBooks();

        library.removeBook(2); // Remove Pride and Prejudice

        library.listAvailableBooks(); // Pride and Prejudice should no longer be listed.
    
        
    }
}
