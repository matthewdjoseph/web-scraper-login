<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Web Scraper</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<h2>Welcome to Web Scraper</h2>


	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">

		<input type="submit" value="Logout" />
	</form:form>

	<div id="wrapper">

		<div id="container">

			<div id="head">
				<h2>Stock Scraper</h2>
			</div>

			<div id="content">

				<div>
					<input type="button" value="Scrape Data"
						onclick="window.location.href='scrapeData'; return false;" />

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
								<td>${tempStock.latestPrice}</td>
								<td>${tempStock.change}</td>
								<td>${tempStock.scrape_time}</td>
							</tr>

						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>

</html>