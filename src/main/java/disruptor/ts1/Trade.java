package disruptor.ts1;

import java.util.concurrent.atomic.AtomicInteger;

public class Trade {
    private String id;
    private String name;
    private double price;
    public String getId() {
        return id;
    }
    public void setId(final String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(final double price) {
        this.price = price;
    }
    public AtomicInteger getCount() {
        return count;
    }
    public void setCount(final AtomicInteger count) {
        this.count = count;
    }
    private AtomicInteger count = new AtomicInteger(0);
}
