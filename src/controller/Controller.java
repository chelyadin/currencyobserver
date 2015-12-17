package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import controller.*;
import model.*;

public class Controller extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		model = new Model(CurrencyType.EUR, SortingType.SortByNameDesc);

		// Setting parsers
		model.addEurParser(ParserEurVtb.getInstance());
		model.addEurParser(ParserEurPrivatBank.getInstance());
		model.addEurParser(ParserEurSberBank.getInstance());
		model.addEurParser(ParserEurPib.getInstance());
		model.addEurParser(ParserEurEximBank.getInstance());

		model.addUsdParser(ParserUsdVtb.getInstance());
		model.addUsdParser(ParserUsdPrivatBank.getInstance());
		model.addUsdParser(ParserUsdSberBank.getInstance());
		model.addUsdParser(ParserUsdPib.getInstance());
		model.addUsdParser(ParserUsdEximBank.getInstance());

		model.addRubParser(ParserRubVtb.getInstance());
		model.addRubParser(ParserRubPrivatBank.getInstance());
		model.addRubParser(ParserRubSberBank.getInstance());
		model.addRubParser(ParserRubPib.getInstance());
		model.addRubParser(ParserRubEximBank.getInstance());

		// Selecting used parsers (by default all of them)
		model.setUsed(ParserEurVtb.getInstance(), true);
		model.setUsed(ParserEurPrivatBank.getInstance(), true);
		model.setUsed(ParserEurSberBank.getInstance(), true);
		model.setUsed(ParserEurPib.getInstance(), true);
		model.setUsed(ParserEurEximBank.getInstance(), true);

		model.setUsed(ParserUsdVtb.getInstance(), true);
		model.setUsed(ParserUsdPrivatBank.getInstance(), true);
		model.setUsed(ParserUsdSberBank.getInstance(), true);
		model.setUsed(ParserUsdPib.getInstance(), true);
		model.setUsed(ParserUsdEximBank.getInstance(), true);

		model.setUsed(ParserRubVtb.getInstance(), true);
		model.setUsed(ParserRubPrivatBank.getInstance(), true);
		model.setUsed(ParserRubSberBank.getInstance(), true);
		model.setUsed(ParserRubPib.getInstance(), true);
		model.setUsed(ParserRubEximBank.getInstance(), true);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");

		/**
		 * Getting Cookies
		 */
		Cookie[] cookies = request.getCookies();
		Map<String, Cookie> cookieMap = new HashMap();
		if (cookies != null) {
			for (Cookie coockie : cookies) {
				cookieMap.put(coockie.getName(), coockie);
			}
		}

		/**
		 * Setting target currency type from parameters or cookies
		 */
		String selectCurrencyType = request.getParameter("selectCurrencyType");
		if (selectCurrencyType == null
				&& cookieMap.containsKey("selectCurrencyType"))
			selectCurrencyType = cookieMap.get("selectCurrencyType").getValue();
		if (selectCurrencyType == null)
			selectCurrencyType = CurrencyType.USD.toString();

		/**
		 * Setting target sorting type from parameters or cookies
		 */
		String selectSortingType = request.getParameter("selectSortingType");
		if (selectSortingType == null
				&& cookieMap.containsKey("selectSortingType"))
			selectSortingType = cookieMap.get("selectSortingType").getValue();
		if (selectSortingType == null)
			selectSortingType = SortingType.SortByBuyValueDesc.toString();

		/**
		 * Getting banks parsers
		 */
		ArrayList<BankParserInfc> parserUsdArrayList = model
				.getParserUsdArrayList();
		ArrayList<BankParserInfc> parserEurArrayList = model
				.getParserEurArrayList();
		ArrayList<BankParserInfc> parserRubArrayList = model
				.getParserRubArrayList();

		/**
		 * Getting target banks for parsing from option and setting
		 * corresponding values
		 */
		String choiceBankButton = request.getParameter("choiceBankButton");
		if (choiceBankButton != null) {
			// Resetting isUsed value for all banks
			for (BankParserInfc parser : parserUsdArrayList) {
				parser.setUsed(false);
			}
			for (BankParserInfc parser : parserEurArrayList) {
				parser.setUsed(false);
			}
			for (BankParserInfc parser : parserRubArrayList) {
				parser.setUsed(false);
			}

			Enumeration en = request.getParameterNames();
			while (en.hasMoreElements()) {
				String paramName = (String) en.nextElement();

				if (paramName.startsWith("choosenBankUsd")) {
					for (BankParserInfc parser : parserUsdArrayList) {
						if (parser.getCurrency().getBankName()
								.equals(paramName.substring(14)))
							parser.setUsed(true);
					}
				}

				if (paramName.startsWith("choosenBankEur")) {
					for (BankParserInfc parser : parserEurArrayList) {
						if (parser.getCurrency().getBankName()
								.equals(paramName.substring(14)))
							parser.setUsed(true);
					}
				}
				if (paramName.startsWith("choosenBankRub")) {
					for (BankParserInfc parser : parserRubArrayList) {
						if (parser.getCurrency().getBankName()
								.equals(paramName.substring(14)))
							parser.setUsed(true);
					}
				}
			}
		}

		/**
		 * Getting currencyList with target currency type
		 */
		if (selectCurrencyType.equals("USD")) {
			model.setCurrencyType(CurrencyType.USD);
			currencyList = model.buildUsdCurrList();
		} else if (selectCurrencyType.equals("EUR")) {
			model.setCurrencyType(CurrencyType.EUR);
			currencyList = model.buildEurCurrList();
		} else if (selectCurrencyType.equals("RUB")) {
			model.setCurrencyType(CurrencyType.RUB);
			currencyList = model.buildRubCurrList();
		}

		/**
		 * Setting sorting type to model
		 */
		if (selectSortingType.equals("SortByNameAsc")) {
			model.setSortingType(SortingType.SortByNameAsc);
		} else if (selectSortingType.equals("SortByNameDesc")) {
			model.setSortingType(SortingType.SortByNameDesc);
		} else if (selectSortingType.equals("SortByBuyValueAsc")) {
			model.setSortingType(SortingType.SortByBuyValueAsc);
		} else if (selectSortingType.equals("SortByBuyValueDesc")) {
			model.setSortingType(SortingType.SortByBuyValueDesc);
		} else if (selectSortingType.equals("SortBySaleValueAsc")) {
			model.setSortingType(SortingType.SortBySaleValueAsc);
		} else if (selectSortingType.equals("SortBySaleValueDesc")) {
			model.setSortingType(SortingType.SortBySaleValueDesc);
		}

		/**
		 * Sorting currencyList
		 */
		if (model.getSortingType() == SortingType.SortByNameAsc) {
			Collections.sort(currencyList, new ComparatorNameAsc());
		} else if (model.getSortingType() == SortingType.SortByNameDesc) {
			Collections.sort(currencyList, new ComparatorNameDesc());
		} else if (model.getSortingType() == SortingType.SortByBuyValueAsc) {
			Collections.sort(currencyList, new ComparatorBuyAsc());
		} else if (model.getSortingType() == SortingType.SortByBuyValueDesc) {
			Collections.sort(currencyList, new ComparatorBuyDesc());
		} else if (model.getSortingType() == SortingType.SortBySaleValueAsc) {
			Collections.sort(currencyList, new ComparatorSaleAsc());
		} else if (model.getSortingType() == SortingType.SortBySaleValueDesc) {
			Collections.sort(currencyList, new ComparatorSaleDesc());
		}

		/**
		 * Setting cookies
		 */
		Cookie selectCurrencyTypeCookie = new Cookie("selectCurrencyType",
				selectCurrencyType);
		Cookie selectSortingTypeCookie = new Cookie("selectSortingType",
				selectSortingType);

		selectCurrencyTypeCookie.setMaxAge(30 * 24 * 60 * 60);
		selectSortingTypeCookie.setMaxAge(30 * 24 * 60 * 60);

		response.addCookie(selectCurrencyTypeCookie);
		response.addCookie(selectSortingTypeCookie);

		/**
		 * Setting attributes to request
		 */

		request.setAttribute("selectSortingType", selectSortingType);
		request.setAttribute("selectCurrencyType", selectCurrencyType);
		request.setAttribute("currencyList", currencyList);
		request.setAttribute("parserUsdArrayList", parserUsdArrayList);
		request.setAttribute("parserEurArrayList", parserEurArrayList);
		request.setAttribute("parserRubArrayList", parserRubArrayList);
		/**
		 * Dispatching request
		 */
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("Observe.jsp");
		requestDispatcher.forward(request, response);
	}

	Model model;
	List<Currency> currencyList;

}
