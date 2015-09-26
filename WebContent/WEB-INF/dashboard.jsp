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
		<div class="col-md-5 border-right">
			<h2 class="titles"> Parameters </h2>
			<div class="col-md-5">
				<label for="sel1">Computer</label>
				<form action="/swaggyjava/ComputerServlet" method="GET" class="form-inline" role="form">
				<input class="form-control computer-name" type="search" id="searchbox" name="search" value="" placeholder="Search name...">
				</form>
			</div>
			
			<div class="col-md-3">
				<label for="sel1">Per page</label>
				<select class="form-control computer-number" name="pageSize" id="pageSize">
			    	<option>20</option>
			    	<option>50</option>
			    	<option>100</option>
			  	</select>
			</div>  

			<div class="col-md-4 button-modify">		
				<input onclick="getTab(1)" type="button" id="searchsubmit"
					value="Apply Parameters"
					class="btn btn-primary">
			</div>
		</div>

				
		<div class="col-md-3 border-right">
			<h2 class="titles"> Functions </h2>
			<label class="add-computer-col"> <i>This will lead you to another page</i></label>
			<a id="add" href="AddComputerServlet" role="button" class="btn btn-success add-computer-col">Add Computer</a>
		</div>
	
	
		<div class="col-md-3 img-computer">
			<img src="img/Logo_pc.png" height="150">
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
