<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="br.com.fiap.entities.*"%>
    <jsp:useBean id="historicoAtividade" class="java.util.ArrayList" scope="request" />
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>Health Track - Atividades</title>
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
                        Histórico de Atividade Física
                </div>
                <div class="information">
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
            </div>
                
            <div class="card shadow p-3 mb-5 bg-white rounded info-hist">
                <div class="card-header text-center font-weight-bold">
                        Cadastrar Atividade
                </div>
                <div class="card-body">
                        <form class="form-cadastro"  method="POST" action="http://localhost:9091/health-track/atividades"> 
                            <div class="form-group input-group-lg">
                                    <label for="atividade">Atividade Física</label>
                                    <select class="form-control form-control-lg" name="tipoAtividade">
                                        <option value="CORRIDA">Corrida</option>
                                        <option  value="CAMINHADA">Caminhada</option>
                                        <option  value="CICLISMO">Ciclismo</option>
                                        <option  value="YOGA">Yoga</option>
                                        <option  value="AEROBICO">Aeróbico</option>
                                    </select>
                            </div>
                            <div class="form-group input-group-lg">
                                    <label for="distancia">Distância Percorrida</label>
                                    <input type="text" class="form-control" id="distancia" placeholder="Ex: 0.0" name="distancia">
                            </div>
                            <div class="form-group input-group-lg">
                                    <label for="atividade">Tipo de Distância</label>
                                    <select class="form-control form-control-lg" name="tipoDistancia">
                                        <option values="KM">KM</option>
                                        <option values="M">M</option>
                                    </select>
                            </div>
                            <div class="form-group input-group-lg">
                                <label for="distancia">Data e Hora de início do exercício</label>
                                <input type="datetime-local" class="form-control" id="distancia" placeholder="Distância" name="dataHoraInicio">
                            </div>
                            <div class="form-group input-group-lg">
                                <label for="tempo">Data e Hora de término do exercício</label>
                                <input type="datetime-local" class="form-control" id="tempo" aria-describedby="emailHelp" placeholder="Tempo" name="dataHoraTermino">
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