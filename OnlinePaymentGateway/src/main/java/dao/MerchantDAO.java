package dao;

import model.merchant;

public class MerchantDAO extends GenericDAO<merchant, Integer> {
    public MerchantDAO() {
        super(merchant.class);
    }
}
