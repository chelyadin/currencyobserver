package model;

import java.io.IOException;
import java.io.Serializable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserRubVtb implements BankParserInfc {

	private static ParserRubVtb instance;
	private boolean used;

	private ParserRubVtb() {
		used = false;
	}

	public static synchronized BankParserInfc getInstance() {
		if (instance == null) {
			instance = new ParserRubVtb();
			return instance;
		}
		return instance;
	}

	@Override
	public Currency makeCurrency() {
		// TODO Auto-generated method stub

		Document doc;
		try {
			doc = Jsoup.connect(
					"http://vtb.ua/private/av_currency/cur_operations/").get();
			Element table = doc.select("body").get(0);

			Elements allCurrencies = table.getElementsByTag("tr");
			Element targCurrency = allCurrencies.get(3);
			Elements targCurrValues = targCurrency.getElementsByTag("td");
			Element buyValue = targCurrValues.get(2);
			Element saleValue = targCurrValues.get(3);

			currency = new Currency("Vtb_Bank", Float.valueOf(buyValue.text()
					.replaceAll(",", ".")), Float.valueOf(saleValue.text()
					.replaceAll(",", ".")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Currency("Vtb_Bank", 0, 0);
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
