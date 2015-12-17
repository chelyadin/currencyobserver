package controller;

import java.util.Comparator;

import model.Currency;

public class ComparatorNameDesc implements Comparator<Currency>{

	@Override
	public int compare(Currency o1, Currency o2) {
		// TODO Auto-generated method stub
		return o2.getBankName().compareTo(o1.getBankName());
	}

}
