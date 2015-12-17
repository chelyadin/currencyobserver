package controller;

import java.util.Comparator;

import model.Currency;

public class ComparatorSaleDesc implements Comparator<Currency> {

	@Override
	public int compare(Currency o1, Currency o2) {
		// TODO Auto-generated method stub
		return Float.compare(o2.getSaleValue(), o1.getSaleValue());
	}

}
