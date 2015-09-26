<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

  	<div class="col-md-12">
	<h3 id="homeTitle">	<%=	request.getAttribute("size") %>	Computers found with your parameters</h3>
	</div>

<div class="col-md-12">
		<table class="computers table table-striped table-bordered">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th>Computer Name</th>
					<th>Introduced Date</th>
					<!-- Table header for Discontinued Date -->
					<th>Discontinued Date</th>
					<!-- Table header for Company -->
					<th>Company</th>
					<th>Delete?</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.computers}" var="computer">
					<tr>
						<td><a href="#" onclick="">${computer.name}</a></td>
						<td>${computer.introduced}</td>
						<td>${computer.discontinued}</td>
						<td>${computer.company.name}</td>
						<td><a href="ComputerServlet?delete=true&id=${computer.id}"><img src="img/trash.png"></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		
		<div class="text-center">
			<ul class="pagination">
				<c:forEach var="i" begin="1" end="${requestScope.nbPages}">
				  <li<c:if test="${i == requestScope.page}"> class="active" </c:if> >
				  	<a href="#" onclick="getTab(<c:out value="${i}"/>)"><c:out value="${i}"/></a>
				  </li>
				</c:forEach>
			</ul>
		</div>