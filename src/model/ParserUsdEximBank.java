package model;

import java.io.IOException;
import java.io.Serializable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserUsdEximBank implements BankParserInfc {

	private static ParserUsdEximBank instance;
	private boolean used;

	private ParserUsdEximBank() {
		used = false;
	}

	public static synchronized BankParserInfc getInstance() {
		if (instance == null) {
			instance = new ParserUsdEximBank();
			return instance;
		}
		return instance;
	}

	@Override
	public Currency makeCurrency() {
		// TODO Auto-generated method stub

		Document doc;
		try {
			doc = Jsoup.connect("https://www.eximb.com").get();
			Element table = doc.select("table").get(8);

			Elements allCurrencies = table.getElementsByTag("tr");
			Element targCurrency = allCurrencies.get(1);
			Elements targCurrValues = targCurrency.getElementsByTag("td");
			Element buyValue = targCurrValues.get(1);
			Element saleValue = targCurrValues.get(2);

			currency = new Currency("UkrEximBank", Float.valueOf(buyValue
					.text().replaceAll(",", ".")), Float.valueOf(saleValue
					.text().replaceAll(",", ".")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Currency("UkrEximBank", 0, 0);
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
