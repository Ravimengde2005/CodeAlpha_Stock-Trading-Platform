import java.util.Scanner;

public class TradingPlatform {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StockMarket market = new StockMarket();
        User user = new User(10000.0); // Starting with $10,000

        int choice;
        do {
            System.out.println("\n=== Stock Trading Platform ===");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Simulate Market Movement");
            System.out.println("6. View Portfolio Value");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Clear newline

            switch (choice) {
                case 1:
                    market.displayMarket();
                    break;

                case 2:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = sc.nextLine().toUpperCase();
                    Stock buyStock = market.getStock(buySymbol);
                    if (buyStock == null) {
                        System.out.println("Invalid stock symbol.");
                        break;
                    }
                    System.out.print("Enter quantity to buy: ");
                    int buyQty = sc.nextInt();
                    if (user.buyStock(buyStock, buyQty)) {
                        System.out.println("Bought " + buyQty + " shares of " + buySymbol);
                    } else {
                        System.out.println("Not enough cash to buy.");
                    }
                    break;

                case 3:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = sc.nextLine().toUpperCase();
                    Stock sellStock = market.getStock(sellSymbol);
                    if (sellStock == null) {
                        System.out.println("Invalid stock symbol.");
                        break;
                    }
                    System.out.print("Enter quantity to sell: ");
                    int sellQty = sc.nextInt();
                    if (user.sellStock(sellStock, sellQty)) {
                        System.out.println("Sold " + sellQty + " shares of " + sellSymbol);
                    } else {
                        System.out.println("Not enough shares to sell.");
                    }
                    break;

                case 4:
                    user.displayPortfolio(market.getStocks());
                    break;

                case 5:
                    market.simulateMarketMovement();
                    System.out.println("Market prices updated.");
                    break;

                case 6:
                    double value = user.getPortfolioValue(market.getStocks());
                    System.out.printf("Total Portfolio Value: $%.2f%n", value);
                    break;

                case 0:
                    System.out.println("Thank you for using the Stock Trading Platform.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }
}

    

