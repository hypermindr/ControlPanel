<%@taglib prefix="h" uri="http://java.sun.com/jsf/html" %> 
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"  %>
<%@taglib prefix="a4j" uri="http://richfaces.org/a4j" %> 
<%@taglib prefix="rich" uri="http://richfaces.org/rich" %>      
<%@include file="../../sec/checkasec.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="stylesheet" type="text/css" href="../../css/default.css" />
        <title>Endpoints</title>
    </head>
    <body>
    <f:loadBundle basename="resources" var="msg"/>
    <f:view>
    
	    <div class="titulo">Endpoints</div> 
	    <div class="conteudo">
		    <table width="400" align="left">
		        <tr>
		            <td>
		            <h:form id="execCadastroEndpoint" >
	                    <table width="400" border="0">
	                        <tr>
	                            <td class="label">Nome do endpoint: </td>
	                            <td>&nbsp;<h:inputText id="name" value="#{endpointBean.name}" tabindex="1" size="40" maxlength="40" required="true" requiredMessage="Campo Requerido"/></td>
	                            <td>
		                         	<a4j:outputPanel ajaxRendered="true">   
    										<rich:message for="name" styleClass="textoVermelho"></rich:message>   
									</a4j:outputPanel> 
								</td>
	                        </tr>
		                    <tr>
		                        <td class="label" >Nome canonico do endpoint: </td>
		                        <td>&nbsp;<h:inputText id="cname" value="#{endpointBean.cname}" tabindex="1"size="40"  maxlength="40" required="true"  requiredMessage="Campo Requerido"/></td>
		                        <td>
		                         	<a4j:outputPanel ajaxRendered="true">   
    										<rich:message for="cname" styleClass="textoVermelho"></rich:message>   
									</a4j:outputPanel> 
								</td>	
		                    </tr>
		                     
		                    <tr>
		                        <td>
		                            <br><br><h:commandButton id="submit" action="#{endpointBean.criar}" styleClass="botao" value="Cadastrar endpoint" >
		                            </h:commandButton> 
		                        </td>
		                    </tr>
		                    <tr>
	                         <h:messages globalOnly="true" styleClass="message"/>
	                    	</tr>
	                	</table>
	                	       </h:form>
	               </td>
	             </tr>
	       
      
		   <tr>
		   <td>
		    <div style="padding-left: 15px" class="label">Endpoints</div>
       		<div class="conteudo" id="endpoints">
          
		           		 <br/>
		  	 			<br/>
		  	 			  <h:form id="execDeleteEndpoint" >
                       <rich:dataTable id="endpoints" headerClass="cabecalho"
                                    value="#{sessionScope['endpoints']}"
                                    var="endpoint"
                                    onRowMouseOver="this.style.backgroundColor='#FFFFCD'"
                                    onRowMouseOut="this.style.backgroundColor='#DDE2E6'"
                                    cellpadding="2" cellspacing="2" width="580px" border="0">

	                      <h:column  id="name">
	                          <f:facet name="header"><h:outputText styleClass="headerText" value="Nome" /></f:facet>
	                          <h:outputText value="#{endpoint.name}" />
	                      </h:column>
	                      <h:column  id="cname">
	                          <f:facet name="header"><h:outputText styleClass="headerText" value="Nome Canonico" /></f:facet>
	                          <h:outputText value="#{endpoint.cname}" />
	                      </h:column>
	                      <h:column  id="acao">
	                          <f:facet name="header"><h:outputText styleClass="headerText" value="Ação" /></f:facet>
	                          <h:commandLink action="#{endpoint.apagar}" value="Apagar">
	                          	 <f:param name="id" value="#{endpoint.id}" />
	                          </h:commandLink>
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
