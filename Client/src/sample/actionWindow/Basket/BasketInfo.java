package sample.actionWindow.Basket;

public class BasketInfo {
    String product, time, quantity;

    public BasketInfo(String product, String time, String quantity) {
        this.product = product;
        this.time = time;
        this.quantity = quantity;
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
