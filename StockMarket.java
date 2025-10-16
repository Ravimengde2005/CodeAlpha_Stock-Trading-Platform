import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StockMarket {
    private Map<String, Stock> stocks = new HashMap<>();

    public StockMarket() {
        stocks.put("AAPL", new Stock("AAPL", "Apple Inc.", 150.0));
        stocks.put("GOOG", new Stock("GOOG", "Alphabet Inc.", 2800.0));
        stocks.put("TSLA", new Stock("TSLA", "Tesla Inc.", 700.0));
        stocks.put("MSFT", new Stock("MSFT", "Microsoft Corp.", 300.0));
    }

    public Map<String, Stock> getStocks() {
        return stocks;
    }

    public void displayMarket() {
        System.out.println("=== Market Data ===");
        for (Stock s : stocks.values()) {
            System.out.println(s);
        }
    }

    public void simulateMarketMovement() {
        Random rand = new Random();
        for (Stock s : stocks.values()) {
            double change = (rand.nextDouble() * 2 - 1) * 5; // ±5%
            double newPrice = s.getPrice() * (1 + change / 100);
            s.setPrice(Math.round(newPrice * 100.0) / 100.0);
        }
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol);
    }
}
