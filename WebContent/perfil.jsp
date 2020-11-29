<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="br.com.fiap.entities.*"%>
    <jsp:useBean id="usuario" class="br.com.fiap.entities.Usuario" scope="request" />
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>Health Track - Perfil</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/home.css" title="Estilos principais">
        <script src="js/grafico-peso.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="cabecalho.html" />
            <div class="card-body shadow p-3 mb-5 bg-white rounded perfil-box">
                <div class="information-perfil col-md-10">
                        <img src="img/user.svg" class="perfil-img rounded-circle">
                        <form class="form-cadastro" method="POST" action="http://localhost:9091/health-track/perfil"> 
                                <div class="form-group input-group-lg">
                                        <label for="nome">Nome</label>
                                        <input type="nome" class="form-control" id="nome" aria-describedby="nome" value="<%=usuario.getNome()%>" name="nome">
                                </div>
                                <div class="form-group input-group-lg">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" id="email" aria-describedby="emailHelp" value="<%=usuario.getEmail()%>" name="email">
                                </div>
                                <div class="form-group input-group-lg">
                                        <label for="altura">Altura</label>
                                        <input type="text" class="form-control" id="altura" value="<%=usuario.getAltura()%>" name="altura">
                                    </div>
                                <div class="form-group input-group-lg">
                                    <label for="senha">Senha</label>
                                    <input type="password" class="form-control" id="senha" value="senha" value="<%=usuario.getSenha()%>" name="senha">
                                </div>
                                <button type="submit" class="btn btn-padrao btn-lg btn-block">Editar</button>
                                <a href="http://localhost:9091/health-track/logout" class="btn btn-outline-dark btn-lg btn-block" tabindex="-1" 
                                role="button" >Sair<a>
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