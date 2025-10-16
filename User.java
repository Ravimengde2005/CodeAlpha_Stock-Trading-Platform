import java.util.HashMap;
import java.util.Map;

public class User {
    private double cash;
    private Map<String, Integer> portfolio; // Stock symbol -> quantity

    public User(double initialCash) {
        this.cash = initialCash;
        this.portfolio = new HashMap<>();
    }

    public double getCash() { return cash; }

    public Map<String, Integer> getPortfolio() {
        return portfolio;
    }

    public void deposit(double amount) {
        this.cash += amount;
    }

    public boolean buyStock(Stock stock, int quantity) {
        double total = stock.getPrice() * quantity;`
        if (cash >= total) {
            cash -= total;
            portfolio.put(stock.getSymbol(),
                portfolio.getOrDefault(stock.getSymbol(), 0) + quantity);
            return true;
        }
        return false;
    }

    public boolean sellStock(Stock stock, int quantity) {
        int owned = portfolio.getOrDefault(stock.getSymbol(), 0);
        if (owned >= quantity) {
            portfolio.put(stock.getSymbol(), owned - quantity);
            if (portfolio.get(stock.getSymbol()) == 0)
                portfolio.remove(stock.getSymbol());
            cash += stock.getPrice() * quantity;
            return true;
        }
        return false;
    }

    public void displayPortfolio(Map<String, Stock> stockMap) {
        System.out.println("Cash Balance: $" + String.format("%.2f", cash));
        System.out.println("Your Holdings:");
        for (String symbol : portfolio.keySet()) {
            int qty = portfolio.get(symbol);
            Stock stock = stockMap.get(symbol);
            double value = qty * stock.getPrice();
            System.out.printf("%s - %d shares @ $%.2f = $%.2f%n", symbol, qty, stock.getPrice(), value);
        }
    }

    public double getPortfolioValue(Map<String, Stock> stockMap) {
        double total = cash;
        for (String symbol : portfolio.keySet()) {
            int qty = portfolio.get(symbol);
            Stock stock = stockMap.get(symbol);
            total += qty * stock.getPrice();
        }
        return total;
    }
}
