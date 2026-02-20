import java.util.*;

class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class User {
    double balance;
    Map<String, Integer> portfolio = new HashMap<>();

    User(double balance) {
        this.balance = balance;
    }

    void buyStock(Stock stock, int quantity) {
        double totalCost = stock.price * quantity;

        if (totalCost <= balance) {
            balance -= totalCost;
            portfolio.put(stock.name,
                    portfolio.getOrDefault(stock.name, 0) + quantity);
            System.out.println("Stock purchased successfully!");
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    void sellStock(Stock stock, int quantity) {
        if (portfolio.containsKey(stock.name) &&
                portfolio.get(stock.name) >= quantity) {

            portfolio.put(stock.name,
                    portfolio.get(stock.name) - quantity);
            balance += stock.price * quantity;
            System.out.println("Stock sold successfully!");
        } else {
            System.out.println("Not enough stock to sell!");
        }
    }

    void showPortfolio() {
        System.out.println("\n--- Portfolio ---");
        for (String stock : portfolio.keySet()) {
            System.out.println(stock + " : " + portfolio.get(stock) + " shares");
        }
        System.out.println("Available Balance: " + balance);
    }
}

public class StockTradingPlatform {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Stock stock1 = new Stock("TCS", 3500);
        Stock stock2 = new Stock("Infosys", 1500);
        Stock stock3 = new Stock("Reliance", 2500);

        User user = new User(10000);

        while (true) {
            System.out.println("\n--- Stock Trading Platform ---");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");

            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Available Stocks:");
                    System.out.println("1. TCS - 3500");
                    System.out.println("2. Infosys - 1500");
                    System.out.println("3. Reliance - 2500");
                    break;

                case 2:
                    System.out.print("Enter stock name: ");
                    String buyName = sc.next();
                    System.out.print("Enter quantity: ");
                    int buyQty = sc.nextInt();

                    if (buyName.equalsIgnoreCase("TCS"))
                        user.buyStock(stock1, buyQty);
                    else if (buyName.equalsIgnoreCase("Infosys"))
                        user.buyStock(stock2, buyQty);
                    else if (buyName.equalsIgnoreCase("Reliance"))
                        user.buyStock(stock3, buyQty);
                    else
                        System.out.println("Stock not found!");
                    break;

                case 3:
                    System.out.print("Enter stock name: ");
                    String sellName = sc.next();
                    System.out.print("Enter quantity: ");
                    int sellQty = sc.nextInt();

                    if (sellName.equalsIgnoreCase("TCS"))
                        user.sellStock(stock1, sellQty);
                    else if (sellName.equalsIgnoreCase("Infosys"))
                        user.sellStock(stock2, sellQty);
                    else if (sellName.equalsIgnoreCase("Reliance"))
                        user.sellStock(stock3, sellQty);
                    else
                        System.out.println("Stock not found!");
                    break;

                case 4:
                    user.showPortfolio();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}