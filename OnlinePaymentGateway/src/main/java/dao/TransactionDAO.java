package dao;

import model.transaction;

public class TransactionDAO extends GenericDAO<transaction, Integer> {
    public TransactionDAO() {
        super(transaction.class);
    }
}
