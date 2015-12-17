package controller;

import java.util.Comparator;

import model.Currency;

public class ComparatorNameAsc implements Comparator<Currency>{

	@Override
	public int compare(Currency o1, Currency o2) {
		// TODO Auto-generated method stub
		return o1.getBankName().compareTo(o2.getBankName());
	}

}
