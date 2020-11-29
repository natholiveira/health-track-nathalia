<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="br.com.fiap.entities.*"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>Health Track - Login</title>
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
                <div class="login col-md-10" >
                    <p>Bem Vindo!</p>
                    <form class="form-login" method="POST" action="http://localhost:9091/health-track/login"> 
                        <div class="form-group input-group-lg">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Seu email" name="email">
                            <small id="emailHelp" class="form-text text-muted">Nunca vamos compartilhar seu email, com ninguém.</small>
                        </div>
                        <div class="form-group input-group-lg">
                            <label for="senha">Senha</label>
                            <input type="password" class="form-control" id="senha" placeholder="Senha" name="senha">
                            <small id="esqueci-senha" class="form-text">Esqueci minha senha</small>
                        </div>
                        <input type="submit" class="btn btn-padrao btn-lg btn-block" tabindex="-1" role="button" value="Entrar">
                        <a href="http://localhost:9091/health-track/cadastro" class="btn btn-outline-dark btn-lg btn-block" tabindex="-1" 
                        role="button" >Cadastre-se<a>
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