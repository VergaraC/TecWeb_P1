<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<%@ page import="java.util.*,br.edu.insper.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listagem</title>
</head>
<body>
<form action="addTodo" method="post">
	<input type='text' name='nome' placeholder="Qual o requisitante?" class="input-text">
	<input type='text' name='tarefa' placeholder="Qual a sua nova tarefa?" class="input-text">
	<input type='date' name='data' placeholder="Qual a data?" value="<fmt:formatDate value="" pattern="dd/MM/yyyy"/>" class="input-text"/>
	<input type='text' name='categoria' placeholder="Qual a categoria?" class="input-text">
	
	<button class="buttons add-button" type="submit">Adicionar</button>
</form>
<table border="1">
	<tr>
		<th>Requisitante</th>
		<th>Tarefa</th>
		<th>Data</th>
		<th>Categoria</th>
	</tr>
	<c:forEach var="tarefa" items="${ tarefas }">
		<tr>
			<td>${ tarefa.nome }</td>
			<td> ${ tarefa.tarefa }</td>
			<td>  <fmt:formatDate value="${tarefa.data.time}" pattern="dd/MM/yyyy"/></td>
			<td> ${ tarefa.categoria }</td>
			<td><form action="updateTodo" method="get">
			
			<input type="hidden" name="id" value="${tarefa.id}" />
                <input type="hidden" name="nome" value="${tarefa.nome}" />
                <input type="hidden" name="tarefa" value="${tarefa.tarefa}" />
                <input type="hidden" name="data" value="<fmt:formatDate value="${tarefa.data.time}" pattern="dd/MM/yyyy"/>" /> 
                <input type="hidden" name="categoria" value="${tarefa.categoria}" />
				<button class="buttons edit-button" type="submit"name="id" value="${tarefa.id}">Editar</button>
				</form>
				
				<form action="removeTodo" method="post">
				<button class="buttons edit-button" type="submit" name="id" value="${tarefa.id}">Deletar</button>
				</form>
		</tr>
		
	</c:forEach>
</table>


</body>
</html>