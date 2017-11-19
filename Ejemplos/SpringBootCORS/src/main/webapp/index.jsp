<%@ include file="common/header.jspf"%>
<script type="text/javascript" language="javascript">
	function send() {
	    $.ajax({
	        url: "https://httpbin.org/get"
	    }).then(function(data) {
		   	console.log(data);
	    });
	}
</script>
<div class="container">
	<div class="page-header">
	  <h1>Maldiny - Configuración del COORS en SB</h1>
	</div>
	<p>Bienvenido!!</p>
	
	<p>Pulsa <a href="https://httpbin.org/get">aquí</a> para enviar una petición.</p>
	
	<button type="submit" onclick="send()">Search</button>
	
	<p>Ahora pulsa F12</p>
</div>
<%@ include file="common/footer.jspf"%>