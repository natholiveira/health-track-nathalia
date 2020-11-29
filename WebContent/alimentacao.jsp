<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="br.com.fiap.entities.*"%>
     <jsp:useBean id="historicoAlimentacao" class="java.util.ArrayList" scope="request" />
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>Health Track - Alimentação</title>
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
                        Histórico de Alimentação
                </div>
                <div class="information">
                  <%
					AlimentoConsumido alimentoConsumido;
					for( int i = 0; i < historicoAlimentacao.size(); i++) {
						alimentoConsumido = (AlimentoConsumido) historicoAlimentacao.get(i);
					%>
                    <div class="circle rounded-circle"></div>
                    <div class="data"><%= alimentoConsumido.getData() %></div>
                    <div class="atividade font-weight-bold"><%= alimentoConsumido.getTipoAlimento().getNome() %> - <%= alimentoConsumido.getAlimento() %></div>
                    <div class="detalhe-atividade"><%= alimentoConsumido.getCalorias() %> Calorias</div>
                    <br>
                   <% } // end for %>
                </div>
            </div>
                
            <div class="card shadow p-3 mb-5 bg-white rounded info-hist">
                <div class="card-header text-center font-weight-bold">
                        Cadastrar consumo de Alimentação
                </div>
                <div class="card-body">
                        <form class="form-cadastro"  method="POST" action="http://localhost:9091/health-track/alimentacao"> 
                            <div class="form-group input-group-lg">
                                    <label for="atividade">Tipo de Alimento</label>
                                    <select class="form-control form-control-lg" name="tipoAlimento">
                                        <option value="FRUTA">Fruta</option>
                                        <option value="LANCHE">Lanche</option>
                                        <option value="LANCHE_NATURAL">Lanche Natural</option>
                                        <option value="MASSA">Massa</option>
                                        <option value="DOCE">Outros</option>
                                    </select>
                            </div>
                            <div class="form-group input-group-lg">
                                    <label for="distancia">Alimento consumido</label>
                                    <input type="text" class="form-control" id="distancia" placeholder="Alimento" name="alimento">
                            </div>
                            <div class="form-group input-group-lg">
                                <label for="distancia">Calorias</label>
                                <input type="text" class="form-control" id="distancia" placeholder="ex: 10.0" name="calorias">
                            </div>
                            <div class="form-group input-group-lg">
                                <label for="tempo">Data e Hora de consumo do alimento</label>
                                <input type="date" class="form-control" id="tempo" aria-describedby="emailHelp" placeholder="Tempo" name="data">
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