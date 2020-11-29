<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="br.com.fiap.entities.*"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>Health Track - Cadastro</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/style.css" title="Estilos principais">
    </head>
    <body>
        <div class="container-fluid">
          <div class="col-md-6 h-100 black" style="float:left">
                <div class="col-md-6 logo">
                    <img src="img/logo.svg" class="img-logo">
                </div>
            </div>
            <div class="col-md-6 h-100" style="background: white; float: left;">
                <div class="cadastro col-md-10" >
                    <p>Faça seu cadastro:</p>
                      <div class="mensagem-erro font-weight-bold" id="mensagem-erro">
                            
                        </div>
                    <form class="form-cadastro" method="POST" action="http://localhost:9091/health-track/cadastro"> 
                        <div class="form-group input-group-lg">
                                <label for="nome">Nome</label>
                                <input type="nome" class="form-control" id="nome" aria-describedby="nome" placeholder="Seu nome" name="nome">
                        </div>
                        <div class="form-group input-group-lg">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Seu email" name="email">
                        </div>
                        <div class="form-group input-group-lg">
                            <label for="senha">Senha</label>
                            <input type="password" class="form-control" id="senha" placeholder="Senha" name="senha">
                        </div>
                        <div class="form-group input-group-lg">
                                <label for="confirmarsenha">Confirmar senha</label>
                                <input type="password" class="form-control" id="confirmar-senha" placeholder="Confirmar senha" name="confirmarSenha">
                        </div>
                        <input type="submit" class="btn btn-padrao btn-lg btn-block" tabindex="-1" role="button" value="Cadastrar">
                        <a href="http://localhost:9091/health-track/login" class="btn btn-outline-dark btn-lg btn-block" tabindex="-1" 
                        role="button" >Voltar<a>
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