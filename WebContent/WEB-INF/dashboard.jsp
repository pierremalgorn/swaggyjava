<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="include/header.jsp" />

<%-- On initialise l'appel ajax --%>
<script>

var xmlhttp = new XMLHttpRequest();

function getTab(nbPage) {
	var search = document.getElementById("searchbox").value;
	var pageSize = document.getElementById("pageSize").value;

        
        xmlhttp.onreadystatechange = function() {
                document.getElementById("reload").innerHTML = xmlhttp.responseText;
        }
        xmlhttp.open("GET", "ComputerServlet?search=" + search + "&pageSize=" + pageSize + "&page=" + nbPage + "&update=true", true);
        xmlhttp.send();
}
</script>

<div class="container-fluid">
	<div class="row" id="actions">
		<div class="col-md-6">
		<form action="/swaggyjava/ComputerServlet" method="GET" class="form-inline" role="form">
			<input type="search" id="searchbox" name="search" class="form-control"
				value="" placeholder="Search name">
			<input onclick="getTab(1)" type="button" id="searchsubmit"
				value="Filter by name"
				class="btn btn-primary">
				</div>
				
				<div class="col-md-2 form-group">
				  <label for="sel1">Number of elements:</label>
				  <select class="form-control" name="pageSize" id="pageSize">
				    <option>5</option>
				    <option>10</option>
				    <option>20</option>
				    <option>50</option>
				  </select>
				</div>
		
		</form>
		
		<div class="col-md-2">
		<a id="add" href="AddComputerServlet" role="button" class="btn btn-success pull-right">Add Computer</a>
		</div>
	</div>
	<div id="reload" class="row">
		
		
	</div>
		
</div>


<%-- On affiche ici le tableau récupéré en Ajax --%>
<script>
	getTab(1);
</script>

<jsp:include page="include/footer.jsp" />
