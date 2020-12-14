package sample.actionWindow.historyOrders;

public class Order {
    String product, time, quantity, state;

    public Order(String product, String time, String quantity, String state) {
        this.product = product;
        this.time = time;
        this.quantity = quantity;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
