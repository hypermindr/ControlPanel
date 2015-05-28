<%@page
	import="com.hypermindr.controlpanel.bean.GlobalDatasourceSelector"%>
<%@page import="com.hypermindr.controlpanel.MongoProcessor"%>
<%@page import="com.hypermindr.controlpanel.bean.HolderBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.math.BigDecimal"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<%@taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@include file="../../sec/checkasec.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	  xmlns:p="http://primefaces.prime.com.tr/ui">
<head>
<meta http-equiv="pragma" content="no-cache" />
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
	src="../../js/jquery.maskedinput-1.3.1.min.js"></script>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>


<script type="text/javascript">

		google.load("visualization", "1", {packages:["corechart"]});
		<%MongoProcessor processor = new MongoProcessor();
		Hashtable<Integer,TreeMap<Long, HolderBean>> dataTable = processor.getData();
		TreeMap<Long, HolderBean> dataSeries = null;
		for(Integer seriesKey: dataTable.keySet()) {
			dataSeries = dataTable.get(seriesKey);
		
		%>
		
		 google.setOnLoadCallback(drawVisualization<%=seriesKey%>);
		 var data<%=seriesKey%>;
		 var chart<%=seriesKey%>;
		
	     function drawVisualization<%=seriesKey%>() {
    	 data<%=seriesKey%> = new google.visualization.DataTable();
      	
      	 <%
      		out.println("data"+seriesKey+".addColumn('datetime', 'ID'); ");
      		out.println("data"+seriesKey+".addColumn('number', 'Tempo total decorrido');");
      		LinkedList<String> ll = new LinkedList<String>();
      		Long sampleKey = new Long(0);
      		for (Long key : dataSeries.keySet()) {
	      		for (String m: dataSeries.get(key).getMethodsTime().keySet()) {
	      			sampleKey = new Long(key);
	      			if (!m.trim().equalsIgnoreCase("TODOS")) {
	      				if (!ll.contains(m))
	      					out.println("data"+seriesKey+".addColumn('number', '"+m+"');");
	      				
	      				ll.push(m);
	      			}
	      		}
      		 }
      		out.println("data"+seriesKey+".addColumn({type: 'string', role: 'annotationText', p: {html:true}});");
      		out.println("data"+seriesKey+".addRows([");
			
            
            Double deviation = processor.getDeviation();
            String deviationStr = null;
			 	 
	             for (Long key : dataSeries.keySet()) {
	            
	            	 String timeSeries = ",";
	            	 for (String method: dataSeries.get(key).getMethodsTime().keySet()) {
	            		 if (dataSeries.get(key) != null)	{
	            		 	timeSeries += dataSeries.get(key).getMethodsTime().get(method)+",";
	            		 }
	            	 }
	            	
	            	 out.println("[ new Date("+key+"),"+ dataSeries.get(key).getTimeElapsed()+timeSeries+"'"+ dataSeries.get(key).getDetails() + "'],");
	            	 
	         
             }%>]);

      		
			var numColumns<%=seriesKey%> = <%=dataSeries.get(sampleKey).getMethodsTime().keySet().size()%>;
			
			numColumns<%=seriesKey%> = numColumns<%=seriesKey%>+2;
	        //data.setColumnProperty(3, 'role', 'tooltip');
        	//var showEvery = parseInt(data.getNumberOfRows() / 1);
            var options = {
            	    title : 'Tempo por chamada',
            	    vAxis: {title: "Segundos",viewWindow: {min:0}},
            	    hAxis: {title: "Hora"},
            	    seriesType: "line",
            	    lineWidth: 1, pointSize: 4,
            	    tooltip: {isHtml: true, trigger: 'both'},
            	    curveType: 'none',
            	    
                      interpolateNulls: true,
                      is3D: true,
                      explorer: {} 
                      
            	  };
        
            
            chart<%=seriesKey%> = new google.visualization.ScatterChart(document.getElementById('chart_div<%=seriesKey%>'));
           

			if (data<%=seriesKey%>.getNumberOfRows() > 0 ) {
         		chart<%=seriesKey%>.draw(data<%=seriesKey%>, options);
            	google.visualization.events.addListener(chart<%=seriesKey%>, 'select', onSelection<%=seriesKey%>);
			}else{
				$('#message_div<%=seriesKey%>').html("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<b> Não existem dados para mostrar. </b>");
	
			}
		
            function onSelection<%=seriesKey%> () {
                var selection<%=seriesKey%> = chart<%=seriesKey%>.getSelection();
                console.log(selection<%=seriesKey%>);
                if ((selection<%=seriesKey%> instanceof Array) && (selection<%=seriesKey%>.length > 0)) {
                    
                    item = selection<%=seriesKey%>[0];
					console.log(item);
                    if (item.row && (typeof item.row == 'number' ||  item.row == 0 )) {
                    	console.log("inside if");
                    	$('#tooltip_div').html(data<%=seriesKey%>.getValue(item.row, numColumns<%=seriesKey%>));
                    }else{
                        // ???
                        console.log("outside if");
                    	$('#tooltip_div').html(data<%=seriesKey%>.getValue(item.row, numColumns<%=seriesKey%>));   
					}
                }else{
                	$('#tooltip_div').html("");
                }
            }
           
         }

        
       
         <%}  %>

         function functionSubmit() {
             document.querySelectorAll("input[type=submit]")[0].click();
         }
           
         window.onload = function(){
          var date = new Date();
          var day = date.getDate();
          var month = date.getMonth() + 1;
          var year = date.getFullYear();

          var hour = date.getHours();
          var minutes = date.getMinutes();
          if (month < 10) month = "0" + month;
          if (day < 10) day = "0" + day;

          var today = day+"/"+ month + "/" + year +" "+hour+":"+minutes;
          var todayInit = day+"/"+ month + "/" + year +" 00:00";
          
          if (document.getElementById("execPlotter:datainicial").value.length == 0) {
        	  document.getElementById("execPlotter:datainicial").value = todayInit;
          }
          
          if (document.getElementById("execPlotter:datafinal").value.length == 0) {
       	  	document.getElementById("execPlotter:datafinal").value = today;
          }
       	}
         
      </script>

</head>
<body>
	<script type="text/javascript">// <![CDATA[
      jQuery(function($) {
    	$.mask.definitions['~']='[+-]';
    	$("#execPlotter\\:datainicial").mask("99/99/9999 99:99");
    	$("#execPlotter\\:datafinal").mask("99/99/9999 99:99");
      });
   // ]]>
      </script>
	<div>
	<p>
		<b>Banco de dados:
		<%=GlobalDatasourceSelector.getDatasource()%></b><br />
		<%="Pesquisa gerou em "+dataTable.keySet().size()+" grafico(s)."%>
	</p>
	</div>
	
		<%for(Integer seriesKey: dataTable.keySet()) { int j = dataTable.get(seriesKey).size();%>
			
		<div align="left" id="chart_div<%=seriesKey%>" style="width: 1700px; height: 700px;"></div>
		
		
	<div>	
		<p>Numero de ocorrências:
			<%=j%></p>
		<div style="float: left;" id="message_div<%=seriesKey%>"></div>
			<%}%>
	</div>
	
	<f:view>
		<h:form id="execPlotter">
			<div style="padding-left: 200px;">
				
				
					Data inicial:
					<h:inputText id="datainicial"
						value="#{mongoProcessorBean.beginDate}">
					</h:inputText>
				</p>

				<p>
					Data final:&nbsp&nbsp&nbsp
					<h:inputText id="datafinal" value="#{mongoProcessorBean.endDate}">
					</h:inputText>
				</p>
				<p>
					Usar datas de inicio e fim: 
					<h:selectBooleanCheckbox id="useBeginEndValues" value="#{useTimeSlice}">
					</h:selectBooleanCheckbox>
				</p>
				<p>
					Intervalo :
					<h:selectOneMenu style="vertical-align: middle;" id="idInterval"
						value="#{interval}" tabindex="0">
						<f:selectItems id="intervals"
							value="#{mongoProcessorBean.intervals}" />
					</h:selectOneMenu>
					&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Servers:
					<h:selectOneMenu style="vertical-align: middle;" id="idServer"
						value="#{server}" tabindex="0">
						<f:selectItems id="servers" value="#{mongoProcessorBean.servers}" />
					</h:selectOneMenu>
					&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Endpoint:
					<h:selectOneMenu style="vertical-align: middle;" id="idEndpoint"
						value="#{endpoint}" tabindex="0">
						<f:selectItems id="endPoints" value="#{utilBean.endPoints}" />
					</h:selectOneMenu>
					
					&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
					<h:commandButton id="submit"
						action="#{mongoProcessorBean.atualiza()}" styleClass="botao"
						value="Atualizar">
						<f:setPropertyActionListener
							target="#{mongoProcessorBean.currentInterval}"
							value="#{interval}" />
						<f:setPropertyActionListener
							target="#{mongoProcessorBean.currentEndpoint}"
							value="#{endpoint}" />
						<f:setPropertyActionListener
							target="#{mongoProcessorBean.currentServer}" value="TODOS" />
						<f:setPropertyActionListener
							target="#{mongoProcessorBean.useTimeSlice}"
						value="#{useTimeSlice}" />
					</h:commandButton>
				</p>
				<p></p>
			</div>
		</h:form>
	</f:view>

	<div title="Detalhes" id="tooltip_div"
		style="width: 1700px; height: 700px;"></div>
	<div title="Detalhes" id="chart_details"
		style="width: 1700px; height: 700px;"></div>
</body>
</html>



