package shop.payment;

public interface RefundablePayment extends PaymentMethod{
    boolean refund(long amount);

    default String policy() {
    return "Refund Maximal 7 Hari";
    }
    
}


