package sample.actionWindow.showOrders;

public class Order {
    String id, userInfo, email, state, basketId;

    public Order(String id, String userInfo, String email, String state, String basketId) {
        this.id = id;
        this.userInfo = userInfo;
        this.email = email;
        this.state = state;
        this.basketId = basketId;
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
