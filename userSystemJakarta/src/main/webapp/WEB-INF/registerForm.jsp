<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>ma page formulaire d'inscription</h1>
	<c:if test="${username_taken != null}">
		<h2><c:out value="le nom demandé existe déjà"></c:out></h2>
	</c:if>
	<!-- le formulaire transmet habituellement ses informations par la méthode HTTP POST -->
	<form action="register" method="post">
		<div>
		<!-- le nom d'utilisateur -->
			<label for="username">User</label>
			<!-- ne jamais oublier l'attribut name sur un champ dont on veut récupérer le contenu -->
			<input id="username" type="text" name="username"/>
			<c:if test="${error_empty_name != null}">
				<label><c:out value="veuillez entrer un nom d'utilisateur"></c:out></label>
			</c:if>
		</div>
		<div>
		<!-- le mot de passe -->
			<label for="password">Password</label>
			<input id="password" type="password" name="password"/>
			<c:if test="${error_empty_pass != null}">
				<label><c:out value="veuillez entrer un mot de passe"></c:out></label>
			</c:if>
		</div>
		<div>
		<!-- le mot de passe confirmé -->
			<label for="password2">Password</label>
			<input id="password2" type="password" name="password2"/>
			<c:if test="${error_empty_pass2 != null || pass_not_confirmed != null}">
				<label><c:out value="veuillez répéter le mot de passe"></c:out></label>
			</c:if>
		</div>
		<!-- le champ submit permet de valider le formulaire -->
		<input type="submit" value="Se Connecter"/>
	</form>

</body>
</html>