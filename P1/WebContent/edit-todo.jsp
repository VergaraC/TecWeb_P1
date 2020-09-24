<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<%@ page import="java.util.*,br.edu.insper.*" %>
<!DOCTYPE html>
<html>
	<head>
		
		<title>Editar Tarefa</title>
	</head>
<body>
		<p>${param.tarefa}</p>
		<p>${param.nome}</p>
		<p> "Salve"</p>
			<form action="updateTodo" method="post">
				<input type='text' name='nome' value="${param.nome}" class="input-text">
				<input type='text' name='tarefa' value="${param.tarefa}" class="input-text">
				<input type='hidden' name='id' value="${param.id}" class="input-text">
				<input type='text' name='data' value="${param.data}" class="input-text">
				<input type='text' name='categoria' value="${param.categoria}" class="input-text">
				<button class="buttons edit-button" type="submit" value =updateTodo >Editar</button>
			</form>
			<body>

</body>
