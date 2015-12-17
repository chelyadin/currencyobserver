package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Model implements Serializable {

	public Model(CurrencyType currencyType, SortingType sortingType) {
		this.currencyType = currencyType;
		this.sortingType = sortingType;
		parserUsdArrayList = new ArrayList<BankParserInfc>();
		parserEurArrayList = new ArrayList<BankParserInfc>();
		parserRubArrayList = new ArrayList<BankParserInfc>();
	}

	/**
	 * Methods for adding new currency parsers
	 * 
	 * @param parser
	 */
	public void addUsdParser(BankParserInfc parser) {
		parserUsdArrayList.add(parser);
	}

	public void addEurParser(BankParserInfc parser) {
		parserEurArrayList.add(parser);
	}

	public void addRubParser(BankParserInfc parser) {
		parserRubArrayList.add(parser);
	}

	/**
	 * Methods for removing existing currency parsers
	 * 
	 * @param parser
	 */
	public void removeUsdParser(BankParserInfc parser) {
		if (parserUsdArrayList.contains(parser)) {
			parserUsdArrayList.remove(parser);
		}
	}

	public void removeEurParser(BankParserInfc parser) {
		if (parserEurArrayList.contains(parser)) {
			parserEurArrayList.remove(parser);
		}
	}

	public void removeRubParser(BankParserInfc parser) {
		if (parserRubArrayList.contains(parser)) {
			parserRubArrayList.remove(parser);
		}
	}

	/**
	 * Methods for setting currency parsers as used and getting current using
	 * state
	 */
	public void setUsed(BankParserInfc parser, boolean used) {
		if (parserUsdArrayList.contains(parser)) {
			parserUsdArrayList.get(parserUsdArrayList.indexOf(parser)).setUsed(
					used);
		} else if (parserEurArrayList.contains(parser)) {
			parserEurArrayList.get(parserEurArrayList.indexOf(parser)).setUsed(
					used);
		} else if (parserRubArrayList.contains(parser)) {
			parserRubArrayList.get(parserRubArrayList.indexOf(parser)).setUsed(
					used);
		}
	}

	public boolean isUsed(BankParserInfc parser) {
		if (parserUsdArrayList.contains(parser)) {
			return parser.isUsed();
		} else if (parserEurArrayList.contains(parser)) {
			return parser.isUsed();
		} else if (parserRubArrayList.contains(parser)) {
			return parser.isUsed();
		}
		return false;
	}

	/**
	 * Methods for building Lists of Currency
	 * 
	 * @return
	 */
	public ArrayList<Currency> buildUsdCurrList() {

		// Filling Currencies ArrayList
		ArrayList<Currency> currencies = new ArrayList<Currency>();
		for (BankParserInfc bankParser : parserUsdArrayList) {
			if (bankParser.isUsed()) {
				try {
					currencies.add(bankParser.makeCurrency());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return currencies;

	}

	public ArrayList<Currency> buildEurCurrList() {

		// Filling Currencies ArrayList
		ArrayList<Currency> currencies = new ArrayList<Currency>();
		for (BankParserInfc bankParser : parserEurArrayList) {
			if (bankParser.isUsed()) {
				try {
					currencies.add(bankParser.makeCurrency());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return currencies;

	}

	public ArrayList<Currency> buildRubCurrList() {

		// Filling Currencies ArrayList
		ArrayList<Currency> currencies = new ArrayList<Currency>();
		for (BankParserInfc bankParser : parserRubArrayList) {
			if (bankParser.isUsed()) {
				try {
					currencies.add(bankParser.makeCurrency());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return currencies;

	}

	/**
	 * Accessories
	 */
	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public SortingType getSortingType() {
		return sortingType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public void setSortingType(SortingType sortingType) {
		this.sortingType = sortingType;
	}
	

	
	/**
	 * Getters for BankParsers ArrayList
	 * @return ArrayList<BankParserInfc>
	 */
	public ArrayList<BankParserInfc> getParserUsdArrayList(){
		return parserUsdArrayList;
	}
	
	public ArrayList<BankParserInfc> getParserEurArrayList(){
		return parserEurArrayList;
	}
	
	public ArrayList<BankParserInfc> getParserRubArrayList(){
		return parserRubArrayList;
	}
	
	
	/*
	 * Serialization
	 
	public void serialize() {
		// Serialization of CurrencyType
		try {
			FileOutputStream fosCurrType = new FileOutputStream(
					"prefferedCurrencyType.data");
			ObjectOutputStream oosCurrType = new ObjectOutputStream(fosCurrType);
			oosCurrType.writeObject(currencyType);
			oosCurrType.flush();
			oosCurrType.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Serialization of SortingType
		FileOutputStream fosSortType;
		try {
			fosSortType = new FileOutputStream("prefferedSortingType.data");
			ObjectOutputStream oosSortType = new ObjectOutputStream(fosSortType);
			oosSortType.writeObject(sortingType);
			oosSortType.flush();
			oosSortType.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	 */
	
	public ArrayList<BankParserInfc> parserUsdArrayList;
	public ArrayList<BankParserInfc> parserEurArrayList;
	public ArrayList<BankParserInfc> parserRubArrayList;

	CurrencyType currencyType;
	SortingType sortingType;

}
