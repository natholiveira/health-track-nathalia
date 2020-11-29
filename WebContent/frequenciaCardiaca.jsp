<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="br.com.fiap.entities.*"%>
    <jsp:useBean id="historicoFrequencia" class="java.util.ArrayList" scope="request" />
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
            <div class="card shadow p-3 mb-5 bg-white rounded info-perfil">
                <div class="card-header text-center font-weight-bold">
                        Histórico de Frequência cardíaca
                </div>
                <div class="card-body">
                  <%
                    FrequenciaCardiaca frequenciaCardiaca;
					for( int i = 0; i < historicoFrequencia.size(); i++) {
						frequenciaCardiaca = (FrequenciaCardiaca) historicoFrequencia.get(i);
					%>
                    <div class="circle rounded-circle"></div>
                    <div class="data"><%= frequenciaCardiaca.getData() %></div>
                    <div class="atividade font-weight-bold"><%= frequenciaCardiaca.getFrequencia() %> BPM</div>
                    <br>
                  <% } // end for %>
                </div>
                <a href="metas.html" class="btn btn-info" tabindex="-1" role="button" >Ver mais</a>
            </div>
                
            <div class="card shadow p-3 mb-5 bg-white rounded info-hist">
                <div class="card-header text-center font-weight-bold">
                        Cadastrar Frequência Cardíaca
                </div>
                <div class="card-body">
                        <form class="form-cadastro" method="POST" action="http://localhost:9091/health-track/frequencia-cardiaca"> 
                            <div class="form-group input-group-lg">
                                <label for="tempo">Frequência Cardíaca</label>
                                <input type="text" class="form-control" id="tempo" aria-describedby="emailHelp" placeholder="Ex: 70" name="frequencia">
                            </div>
                            <div class="form-group input-group-lg">
                                    <label for="distancia">Data</label>
                                    <input type="date" class="form-control" id="distancia" placeholder="Distância" name="data">
                            </div>
                            <button type="submit" class="btn btn-padrao btn-lg btn-block">Adicionar</button>
                        </form>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
        </script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js">
        </script>
    </body>
</html>