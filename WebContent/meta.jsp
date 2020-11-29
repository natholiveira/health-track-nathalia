<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="br.com.fiap.entities.*"%>
    <jsp:useBean id="faltaMeta" class="java.lang.String" scope="request" />
<jsp:useBean id="meta" class="java.lang.String" scope="request" />
<jsp:useBean id="semMeta" class="java.lang.String" scope="request" />
<jsp:useBean id="pesoMeta" class="java.lang.String" scope="request" />
<jsp:useBean id="peso" class="java.lang.String" scope="request" />
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>Health Track - Peso</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/home.css" title="Estilos principais">
        <script src="js/grafico-peso.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="cabecalho.html" />
            <div class="card-body shadow p-3 mb-5 bg-white rounded info-perfil">
                <div class="card-header text-center font-weight-bold">
                        Meta atual
                </div>
                <div class="info-meta font-weight-bold">Peso atual: <%= peso %></div>
                <div class="info-meta font-weight-bold">Meta de peso: <%= pesoMeta %></div>
                <div class="information" id="peso-info">
             
                </div>
            </div>
                
            <div class="card shadow p-3 mb-5 bg-white rounded info-hist">
                <div class="card-header text-center font-weight-bold">
                        Cadastrar meta de peso
                </div>
                <div class="card-body">
                        <form class="form-cadastro" method="POST" action="http://localhost:9091/health-track/meta"> 
                            <div class="form-group input-group-lg">
                                <label for="tempo">Peso a ser atingido</label>
                                <input type="text" class="form-control" id="tempo" aria-describedby="emailHelp" placeholder="ex: 70.0" name="peso">
                            </div>
                            <div class="form-group input-group-lg">
                                    <label for="distancia">Data em que pretende completar a meta</label>
                                    <input type="date" class="form-control" id="distancia" placeholder="Distância" name="data">
                            </div>
                            <button type="submit" class="btn btn-padrao btn-lg btn-block">Adicionar</button>
                        </form>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
        
          var faltaMeta= parseFloat(<%=faltaMeta%>);
          var meta= parseFloat(<%=meta%>);
          var semMeta= parseFloat(<%=semMeta%>);
    
          google.charts.load('current', {'packages':['corechart']});
    
          google.charts.setOnLoadCallback(drawChart);
    
          function drawChart() {
        	  
        	if (semMeta==1.0) {
        		var data = new google.visualization.DataTable();
                data.addColumn('string', 'Topping');
                data.addColumn('number', 'Slices');
                data.addRows([
                     ['Você ainda não possuí uma meta', semMeta]
                ]);
        
                // Set chart options
                var options = {
                               'width':450,
                               'height':240,
                               'pieHole': 0.4,
                               colors: ['#A9A9A9']
                               };
                var chart = new google.visualization.PieChart(document.getElementById('peso-info'));
                
                chart.draw(data, options);
        	}  else {
        		console.log('entrou aq');
	            var data = new google.visualization.DataTable();
	            data.addColumn('string', 'Topping');
	            data.addColumn('number', 'Slices');
	            data.addRows([
	            	 ['Perdeu '+faltaMeta+' KG', faltaMeta],
	                 ['Restam '+meta+' KG', meta],
	            ]);
	    
	            // Set chart options
	            var options = {
	                           'width':450,
	                           'height':240,
	                           'pieHole': 0.4,
	                           colors: ['#008000', '#B22222']
	                           };
	    
	            var chart = new google.visualization.PieChart(document.getElementById('peso-info'));
	            
	            chart.draw(data, options);
        	}
            
           
          }
        </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
        </script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js">
        </script>
    </body>
</html>