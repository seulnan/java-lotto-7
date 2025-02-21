package lotto.enums;

public enum LottoPrice {
    PRICE_PER_TICKET(1000);

    private final int price;

    LottoPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
