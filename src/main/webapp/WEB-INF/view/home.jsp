<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<title>Web Scraper</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<body>
	<h2>Yahoo! Finance Scraper</h2>
	<hr />
	<div id="wrapper">
		<form:form action="${pageContext.request.contextPath}/logout"
			method="POST">
			<input type="button" value="Scrape Data"
				onclick="window.location.href='scrapeData'; return false;" />
			<input type="button" value="About"
				onclick="window.location.href='about'; return false;" />
			<input type="submit" value="Logout" />
		</form:form>
		<div id="container">

			<div id="content">

				<div>
					<br />
					<table>
						<tr>
							<th>Symbol</th>
							<th>Latest Price</th>
							<th>Change Percentage</th>
							<th>Scrape Time</th>
						</tr>

						<c:forEach var="tempStock" items="${stocks}">

							<tr>
								<td>${tempStock.symbol}</td>
								<td><fmt:setLocale value="en_US" /> <fmt:formatNumber
										value="${fn:replace(tempStock.latestPrice, ',', '')}" type="currency" /></td>
								<td>${tempStock.change}</td>
								<td><fmt:formatDate value="${tempStock.scrape_time}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>

						</c:forEach>
					</table>
				</div>
			</div>
		</div>

	</div>
</body>

</html>