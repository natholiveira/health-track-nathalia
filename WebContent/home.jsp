<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="br.com.fiap.entities.*"%>
    
<jsp:useBean id="nome" class="java.lang.String" scope="request" />
<jsp:useBean id="peso" class="java.lang.String" scope="request" />
<jsp:useBean id="frequenciaCardiaca" class="java.lang.String" scope="request" />
<jsp:useBean id="calorias" class="java.lang.String" scope="request" />
<jsp:useBean id="imc" class="java.lang.String" scope="request" />
<jsp:useBean id="faltaMeta" class="java.lang.String" scope="request" />
<jsp:useBean id="meta" class="java.lang.String" scope="request" />
<jsp:useBean id="historicoAtividade" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="semMeta" class="java.lang.String" scope="request" />
<jsp:useBean id="pesoMeta" class="java.lang.String" scope="request" />
<jsp:useBean id="imcTipo" class="java.lang.String" scope="request" />
    
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>Health Track - Home</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/home.css" title="Estilos principais">
        <script src="js/grafico-peso.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="cabecalho">
                <a href="http://localhost:9091/health-track/home">
                    <img src="img/logo.svg" class="img-logo" href="http://localhost:9091/health-track/home">
                </a>
                <a  href="http://localhost:9091/health-track/perfil">
                    <img src="img/user-cabecalho.svg" class="perfil rounded-circle">
                </a>    
                <div class="menu">
                    <ul class="nav nav-pills justify-content-center">
                        <li class="nav-item">
                            <a class="nav-link selected"  href="http://localhost:9091/health-track/home">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="http://localhost:9091/health-track/atividades">Atividades</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="http://localhost:9091/health-track/peso">Peso</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="http://localhost:9091/health-track/alimentacao">Alimentação</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="http://localhost:9091/health-track/frequencia-cardiaca">Freq. Cardíaca</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="http://localhost:9091/health-track/meta">Metas</a>
                        </li>
                    </ul>
                </div>     
            </div>
            <div class="card shadow p-3 mb-5 bg-white rounded info-perfil" style="width: 42rem;">
                <img class="card-img-top" src="img/card-image.jpg" alt="Card image cap" style="height: 10rem;">
                <div class="card-img-overlay">
                    <h2 class="card-text text-center titulo">Bem Vindo(a) <%=nome %>!</h2>
                  </div>
                    <div class="information">
                        <div class="img-dash"></div>
                    
                        <div class="row peso">
                            <div class="icon">
                                <img src="img/balanca.png">
                            </div>
                            <div class="texto">
                                <span>Peso: </span><span class="font-weight-bold"> <%=peso!="0.0" ? peso : "--"%></span>
                            </div>
                        </div>
    
                        <div class="row cardio">
                            <div class="icon">
                                <img src="img/heart.png" >
                            </div>
                            <div class="texto">
                                <span>Frequência cardíaca: </span><span class="font-weight-bold"> <%=frequenciaCardiaca!="0.0" ? frequenciaCardiaca : "--"%></span>
                            </div>
                        </div>
    
                        <div class="row caloria">
                            <div class="icon">
                                <img src="img/comida.png">
                            </div>
                            <div class="texto">
                                <span>Calorias ingeridas: </span><span class="font-weight-bold"> <%=calorias!="0.0" ? calorias : "--"%></span>
                            </div>
                        </div>
                        <div class="row imc">
                                <div class="icon">
                                    <img src="img/medida.png">
                                </div>
                                <div class="texto">
                                    <span>IMC: </span><span class="font-weight-bold"> <%=imc!="0.0" ? imc : "--"%> |  <%=imcTipo%></span>
                                </div>
                            </div>
                    </div>
        
            </div>
            <div class="card shadow p-3 bg-white rounded info-hist" style="width: 34rem;">
                <div class="card-header font-weight-bold">
                        Meta de Peso
                </div>
                 <div class="info-meta font-weight-bold">Peso atual: <%= peso %></div>
                 <div class="info-meta font-weight-bold">Meta de peso: <%= pesoMeta %></div>
                <div class="card-body peso-info" id="peso-info">
                
                </div>
            </div>
            
            <div class="card shadow p-3 mb-7 bg-white rounded info-hist" style="width: 34rem;">
                    <div class="card-header text font-weight-bold">
                        Histórico de Atividades
                    </div>
                    <div class="card-body" id="alimento-info">
                    <%
					AtividadeFisica atividadeFisica;
					for( int i = 0; i < historicoAtividade.size(); i++) {
						atividadeFisica = (AtividadeFisica) historicoAtividade.get(i);
					%>
                        <div class="circle rounded-circle"></div>
                        <div class="data"><%= atividadeFisica.getDataHoraInicio() %> | <%= atividadeFisica.getDataHoraTermino() %></div>
                        <div class="atividade font-weight-bold"><%= atividadeFisica.getTipoAtividade().getNome() %></div>
                        <div class="detalhe-atividade"><%= atividadeFisica.getDistanciaPercorrida() %> <%= atividadeFisica.getTipoDistancia().getNome() %></div>
                        <br>
                    <% } // end for %>
                    </div>
                    <a href="atividade.html" class="btn btn-info" tabindex="-1" role="button" >Ver mais</a>
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