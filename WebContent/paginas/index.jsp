<%@taglib prefix="h" uri="http://java.sun.com/jsf/html" %> 
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>.::HYPERMINDR::.</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<link rel="stylesheet" type="text/css" href="../css/login.css" />

</head>
<body>
<f:view>
<div class="tarjatrasparente">&nbsp;</div>
<div class="tarja">
	<div id="boxtitulo">RaaS - Hypermindr</div>
	<div id="boxlogin">
		 <h:form id="execAdminLogin" >
			<table width="250px">
				<tr>
					<td>usuário:</td>
					<td><h:inputText id="login" value="#{loginBean.login}" tabindex="1"/></td>
				</tr>
				<tr>
					<td>senha:</td>
					<td><h:inputSecret id="senha" value="#{loginBean.senha}" tabindex="1" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<br /><br />
						<h:commandButton id="submit" action="#{loginBean.executaLogin}" value="Login">
      
                        </h:commandButton>
					</td>
				</tr>
				<table align="center" width="200">
              <tr>
                  <td><br /><br /><h:messages globalOnly="true" styleClass="message"/>
                  </td>
              </tr>
              </table>
          </table>
			
		 </h:form>
	</div>
</div>

<div class="logofooter">
<label title="EXTRANET" /><a href="http://www.hypermindr.com" target="_blank">
&nbsp;
</a>
</div>
</f:view>
</body>
</html>
