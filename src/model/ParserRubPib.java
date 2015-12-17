package model;

import java.io.IOException;
import java.io.Serializable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserRubPib implements BankParserInfc {

	private static ParserRubPib instance;
	private boolean used;

	private ParserRubPib() {
		used = false;
	}

	public static synchronized BankParserInfc getInstance() {
		if (instance == null) {
			instance = new ParserRubPib();
			return instance;
		}
		return instance;
	}

	@Override
	public Currency makeCurrency() {
		// TODO Auto-generated method stub

		Document doc;
		try {
			doc = Jsoup.connect("http://pib.ua/").get();
			Element table = doc.select("body").get(0);

			Elements allCurrencies = table.getElementsByTag("tr");
			Element targCurrency = allCurrencies.get(4);
			Elements targCurrValues = targCurrency.getElementsByTag("td");
			Element buyValue = targCurrValues.get(1);
			Element saleValue = targCurrValues.get(2);

			currency = new Currency("PromInvestBank", Float.valueOf(buyValue
					.text().replaceAll(",", ".")), Float.valueOf(saleValue
					.text().replaceAll(",", ".")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Currency("PromInvestBank", 0, 0);
		}

		return currency;
	}

	@Override
	public void setUsed(boolean used) {
		// TODO Auto-generated method stub
		this.used = used;
	}

	@Override
	public boolean isUsed() {
		// TODO Auto-generated method stub
		return used;
	}

	@Override
	public Currency getCurrency() {
		// TODO Auto-generated method stub
		if (currency == null)
			currency = makeCurrency();
		return currency;
	}

	Currency currency;
}
