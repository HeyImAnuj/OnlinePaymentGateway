package dao;

import model.paymentMethod;

public class PaymentMethodDAO extends GenericDAO<paymentMethod, Integer> {
    public PaymentMethodDAO() {
        super(paymentMethod.class);
    }
}