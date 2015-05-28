<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Control Panel</title>
<style type="text/css">
  body {background-color:white;margin:0px;padding:0px;}
  #left,#right {width:10%;margin:0px;padding:0px;}
  #left,#center,#right {float:left;}
  #center {width:80%;}
  #top {height:150px;}
  #main {height:600px;background-color:white;}
</style>
</head>
<body>
<div id="left">&nbsp;</div>
<div id="center">
  <div id="top"><jsp:include page="top.jsp"/></div>
  <div id="main"><jsp:include page="view.jsp"/></div>
  <div id="bottom"></div>
</div>
<div id="right">&nbsp;</div>
</body>
</html>