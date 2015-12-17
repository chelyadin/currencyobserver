package controller;

import java.util.Comparator;

import model.Currency;

public class ComparatorBuyAsc implements Comparator<Currency> {

	@Override
	public int compare(Currency o1, Currency o2) {
		// TODO Auto-generated method stub
		return Float.compare(o1.getBuyValue(), o2.getBuyValue());
	}

}
