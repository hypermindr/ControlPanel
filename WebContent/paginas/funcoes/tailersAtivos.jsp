<%@page import="com.hypermindr.controlpanel.MongoProcessor"%>
<%@page import="com.hypermindr.controlpanel.bean.HolderBean"%>
<%@page import="com.hypermindr.controlpanel.entities.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html" %> 
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"  %>
<%@taglib prefix="a4j" uri="http://richfaces.org/a4j" %> 
<%@taglib prefix="rich" uri="http://richfaces.org/rich" %>      
<%@include file="../../sec/checkasec.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="stylesheet" type="text/css" href="../../css/default.css" />
        <title>Tailers</title>
    </head>
    <body>
    <%
    List<Tailer> tailers = new MongoProcessor().getActiveTailers();
	((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).setAttribute("tailers",tailers);
    %>
    <f:loadBundle basename="resources" var="msg"/>
    <f:view>
    
	    <div class="titulo">Tailers</div> 
	    <div class="conteudo">
		    <table width="400" align="left">
		        <tr>
		            <td>
			<div style="padding-left: 15px" class="label">Atividade dos Tailers (Ultima hora)</div>
       		<div class="conteudo" id="endpoints">
          
		           		 <br/>
		  	 			<br/>
		  	 			  <h:form id="execDeleteEndpoint" >
                       <rich:dataTable id="endpoints" headerClass="cabecalho"
                                    value="#{sessionScope['tailers']}"
                                    var="tailer"
                                    onRowMouseOver="this.style.backgroundColor='#FFFFCD'"
                                    onRowMouseOut="this.style.backgroundColor='#DDE2E6'"
                                    cellpadding="2" cellspacing="2" width="580px" border="0">

	                      <h:column  id="name">
	                          <f:facet name="header"><h:outputText styleClass="headerText" value="Servidor" /></f:facet>
	                          <h:outputText value="#{tailer._id}" />
	                      </h:column>
	                      <h:column  id="cname">
	                          <f:facet name="header"><h:outputText styleClass="headerText" value="Ultima atividade" /></f:facet>
	                          <h:outputText value="#{tailer.lastEvent}" />
	                      </h:column>
	                      </rich:dataTable>
                 		       </h:form>
                 		</div>
                 			</td>
                 	</tr>
		  	 
		  	 </table>
	      
	        
        </div>

       <br/>
       <br/>
      
		  	 
       </f:view>
       </body>
</html>