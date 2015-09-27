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

$(document).keypress(function(e) {
  if(e.which == 13) {
    getTab(1);
  }
  
});

</script>

<div class="container-fluid">
	<div class="row" id="actions">
		<div class="col-md-5 border-right">
			<h2 class="titles"> Parameters </h2>
			<div class="col-md-5">
				<label for="sel1">Computer</label>
				
				<input class="form-control computer-name" type="search" id="searchbox" name="search" value="" placeholder="Search name...">
				
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
			<label class="add-computer-col"> <i>Add a computer to the list</i></label>
			<button type="button" class="btn btn-success add-computer-col" data-toggle="modal" data-target="#modalAdd">
			  Add Computer
			</button>
		</div>
	
	
		<div class="col-md-3 img-computer">
			<img src="img/Logo_pc.png" height="150">
		</div>
	</div>
	
	<div id="reload" class="row">
	</div>
		
</div>

<!-- Modal -->
<div class="modal fade" id="modalAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Add a computer</h4>
      </div>
      <div class="modal-body">
        <form role="form" action="/swaggyjava/ComputerServlet" method="POST">
		<div class="form-group">
			<label for="name">Computer name</label> <input type="text"
				class="form-control" name="name" placeholder="Enter name">
			<p class="help-block">Required</p>
		</div>
		<div class="form-group">
			<label for="introduced">Introduced date</label> <input type="date"
				class="form-control" name="introduced" pattern="YY-MM-dd"
				placeholder="Introduced"> <span class="help-block">YYYY-MM-DD</span>
		</div>
		<div class="form-group">
			<label for="discontinued">Discontinued date</label> <input
				type="date" class="form-control" name="discontinued"
				pattern="YY-MM-dd" placeholder="Discontinued"> <span
				class="help-block">YYYY-MM-DD</span>
		</div>
		<div class="form-group">
			<label for="company">Company Name:</label>
			<div class="input">
				<select name="company" class="form-control">
					<c:forEach items="${requestScope.companies}" var="company">
						<option>${company.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Submit</button>
      </div>
      </form>
    </div>
  </div>
</div>


<%-- On affiche ici le tableau récupéré en Ajax --%>
<script>
	getTab(1);
</script>

<jsp:include page="include/footer.jsp" />
