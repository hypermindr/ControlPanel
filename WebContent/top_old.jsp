<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script type="text/javascript">

function quadroAlertas() {
window.open( "http://www.google.com/", "myWindow", 
"status = 1, height = 300, width = 600, resizable = 0" );
}

</script>
</head>
<body>
<br/>  
<div style="float: left;"  id="logo">
<img src="images/logo.svg">
</div>
<div style="float: left;" id="menu">
<p onclick="quadroAlertas()">
<br/>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<b><u>Quadro de Alertas</u></b>
	</p>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<textarea id="criticaltext" style="overflow:scroll" rows="3" cols="100">

</textarea> 

</div>

</body>
</html>