<%@page import="model.BankParserInfc"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ParserEurEximBank"%>
<%@page import="model.Currency"%>
<%@page import="java.util.List"%>
<%@page import="model.Model"%>
<%@page import="model.CurrencyType"%>
<%@page import="model.SortingType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Currency Observer</title>
<link rel="stylesheet" type="text/css" href="Styles01.css">
<link rel="stylesheet" type="text/css" href="Styles02.css">
<link rel="stylesheet" type="text/css" href="Styles03.css">
<style type="text/css">
</style>
</head>
<body>




	<form action="curr" method="get">

		<table border="0" style="width: 600px; margin: auto">

			<tr>
				<td style="width: 300px;" align="center">
					<p class="myFont1">Select observed currency:</p>
				</td>
				<td style="width: 300px;" align="center">
					<p class="myFont1">Select sorting type:</p>
				</td>
			</tr>



			<tr>
				
				<td style="width: 300px;"><select name="selectCurrencyType"
					style="width: 295px;" class="myButton">
						<%
							String selectCurrencyType = (String) request
									.getAttribute("selectCurrencyType");
							// Filling CurrencyType
							CurrencyType[] currencyTypes = CurrencyType.values();
							for (CurrencyType currencyType : currencyTypes) {
								String selected = "";
								if (selectCurrencyType.equals(currencyType.toString())) {
									selected = " selected ";
								}
								out.println("<option" + selected + ">"
										+ currencyType.toString() + "</option>");
							}
						%>
				</select></td>

				<td style="width: 300px;"><select name="selectSortingType"
					style="width: 295px;" class="myButton">
						<%
							String selectSortingType = (String) request
									.getAttribute("selectSortingType");
							// Filling SortingType
							SortingType[] sortingTypes = SortingType.values();
							for (SortingType sortingType : sortingTypes) {
								String selected = "";
								if (selectSortingType.equals(sortingType.toString())) {
									selected = " selected ";
								}
								out.println("<option" + selected + ">" + sortingType.toString()
										+ "</option>");
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Get currencies"
					style="width: 600px;" class="myButton"></td>

			</tr>
		</table>

	</form>






	<!-- Table with parsed currencies -->
	<%@include file="CurrenciesTable.html"%>




	<hr width="600px" size="3px">
	<p align="center" class="myFont1">&copy Developed and supported by
		CVA.</p>
	<%@include file="AboutButton.html"%>






</body>
</html>