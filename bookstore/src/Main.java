import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPaymentStrategy implements PaymentStrategy {
    private String cardNumber;
    private String cvv;
    private String expirationDate;
    private String cardHolderName;

    public CreditCardPaymentStrategy(String cardNumber, String cvv, String expirationDate, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid with credit/debit card");
        // Place logic for processing credit card payment here
    }
}

class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid with cash");
        // Place logic for processing cash payment here
    }
}

class Book {
    private int id;
    private String title;
    private String author;
    private int price;

    public Book(int id, String title, String author, int price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPrice() {
        return price;
    }
}

class User {
    private String name;
    private String surname;
    private int id;

    public User(String name, String surname, int id) {
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getId() {
        return id;
    }
}

class Order {
    private User user;
    private Book book;

    public Order(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    // Getters and setters
    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }
}

class Bookstore {
    private List<Book> books;
    private List<User> users;
    private List<Order> orders;

    public Bookstore() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void displayAllBooks() {
        System.out.println("Books in the Bookstore:");
        for (Book book : books) {
            System.out.println("ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Price: " + book.getPrice());
        }
    }

    public void addNewUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void displayAllUsers() {
        System.out.println("Users in the Bookstore:");
        for (User user : users) {
            System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Surname: " + user.getSurname());
        }
    }

    public void placeOrder(User user, Book book) {
        orders.add(new Order(user, book));
        System.out.println("Order placed successfully!");
    }

    public void cancelOrder(Order order) {
        orders.remove(order);
        System.out.println("Order canceled successfully!");
    }

    public void displayOrderHistory(User user) {
        System.out.println("Order history for user " + user.getName() + ":");
        for (Order order : orders) {
            if (order.getUser().getId() == user.getId()) {
                System.out.println("Book: " + order.getBook().getTitle() + ", Price: " + order.getBook().getPrice());
            }
        }
    }

    public void payForBooks(int totalAmount) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose payment method:");
        System.out.println("1. Credit/Debit Card");
        System.out.println("2. Cash");

        int paymentChoice = scanner.nextInt();
        PaymentStrategy paymentStrategy;

        switch (paymentChoice) {
            case 1:
                // Gather credit card details
                System.out.println("Enter card number:");
                String cardNumber = scanner.next();
                System.out.println("Enter CVV:");
                String cvv = scanner.next();
                System.out.println("Enter expiration date:");
                String expirationDate = scanner.next();
                System.out.println("Enter card holder's name:");
                String cardHolderName = scanner.next();
                paymentStrategy = new CreditCardPaymentStrategy(cardNumber, cvv, expirationDate, cardHolderName);
                break;
            case 2:
                paymentStrategy = new CashPaymentStrategy();
                break;
            default:
                System.out.println("Invalid payment choice. Defaulting to cash payment.");
                paymentStrategy = new CashPaymentStrategy();
                break;
        }

        paymentStrategy.pay(totalAmount);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bookstore bookstore = new Bookstore();

        // Добавляем книги в магазин
        bookstore.addBook(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", 15));
        bookstore.addBook(new Book(2, "To Kill a Mockingbird", "Harper Lee", 12));
        bookstore.addBook(new Book(3, "1984", "George Orwell", 10));
        bookstore.addBook(new Book(4, "Pride and Prejudice", "Jane Austen", 18));

        while (true) {
            System.out.println("Bookstore Menu: ");
            System.out.println("1. Show all books ");
            System.out.println("2. Add a new book ");
            System.out.println("3. Show all users ");
            System.out.println("4. Add a new user ");
            System.out.println("5. Place an order");
            System.out.println("6. Cancel an order");
            System.out.println("7. View order history");
            System.out.println("8. Pay for books");
            System.out.println("9. Exit");

            int command = scanner.nextInt();
            switch (command) {
                case 1:
                    bookstore.displayAllBooks();
                    break;
                case 2:
                    System.out.println("Enter book ID:");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter book title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter book author:");
                    String author = scanner.nextLine();
                    System.out.println("Enter book price:");
                    int price = scanner.nextInt();
                    bookstore.addBook(new Book(bookId, title, author, price));
                    System.out.println("Book added successfully!");
                    break;
                case 3:
                    bookstore.displayAllUsers();
                    break;
                case 4:
                    System.out.println("Enter user name:");
                    String name = scanner.next();
                    System.out.println("Enter user surname:");
                    String surname = scanner.next();
                    System.out.println("Enter user ID:");
                    int userId = scanner.nextInt();
                    bookstore.addNewUser(new User(name, surname, userId));
                    System.out.println("User added successfully!");
                    break;
                case 5:
                    System.out.println("Enter user ID:");
                    int userID = scanner.nextInt();
                    System.out.println("Enter book ID:");
                    int bookID = scanner.nextInt();
                    User user = null;
                    for (User u : bookstore.getUsers()) {
                        if (u.getId() == userID) {
                            user = u;
                            break;
                        }
                    }
                    Book book = null;
                    for (Book b : bookstore.getBooks()) {
                        if (b.getId() == bookID) {
                            book = b;
                            break;
                        }
                    }
                    if (user != null && book != null) {
                        bookstore.placeOrder(user, book);
                    } else {
                        System.out.println("User or book not found!");
                    }
                    break;
                case 6:
                    // Logic to cancel an order
                    break;
                case 7:
                    System.out.println("Enter user ID:");
                    int userIdHistory = scanner.nextInt();
                    User userHistory = null;
                    for (User u : bookstore.getUsers()) {
                        if (u.getId() == userIdHistory) {
                            userHistory = u;
                            break;
                        }
                    }
                    if (userHistory != null) {
                        bookstore.displayOrderHistory(userHistory);
                    } else {
                        System.out.println("User not found!");
                    }
                    break;
                case 8:
                    System.out.println("Enter total amount to pay:");
                    int totalAmount = scanner.nextInt();
                    bookstore.payForBooks(totalAmount);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }
}
