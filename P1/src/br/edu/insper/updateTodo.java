package br.edu.insper;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updateTodo
 */
@WebServlet("/updateTodo")
public class updateTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateTodo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit-todo.jsp");
		dispatcher.forward(request, response);
		Tarefas tarefa = new Tarefas();
		try {
		tarefa.setId(Integer.valueOf(request.getParameter("id")));
		tarefa.setNome(request.getParameter("nome"));
		
		String nascimento = request.getParameter("data");
		java.util.Date data = new SimpleDateFormat("dd/MM/yyyy").parse(nascimento);
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.setTime(data);
		tarefa.setData(dataNascimento);
		tarefa.setCategoria(request.getParameter("categoria"));
		tarefa.setTarefa(request.getParameter("tarefa"));
		request.setAttribute("tarefas", tarefa);
	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println(" ");
		System.out.println(request.getParameter("tarefa"));
		System.out.println(" ");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAO dao;
		try {
			dao = new DAO();
			Tarefas tarefa = new Tarefas();
			tarefa.setId(Integer.valueOf(request.getParameter("id")));
			tarefa.setTarefa(request.getParameter("tarefa"));
			tarefa.setNome(request.getParameter("nome"));
			
			String nascimento = request.getParameter("data");
			java.util.Date data = new SimpleDateFormat("dd/MM/yyyy").parse(nascimento);
			Calendar dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(data);
			tarefa.setData(dataNascimento);
			tarefa.setCategoria(request.getParameter("categoria"));
			tarefa.setTarefa(request.getParameter("tarefa"));
			request.setAttribute("tarefas", tarefa);
			
			tarefa.setCategoria(request.getParameter("categoria"));
			dao.updateTodo(tarefa);
			dao.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("Lista");
			dispatcher.forward(request, response);


			response.sendRedirect("Lista");
			
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
