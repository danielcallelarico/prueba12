
package com.emergentes.controlador;

import com.emergentes.modelo.Seminarios;
import com.emergentes.utiles.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {


  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try{
           
           String op;
           int id;
           op =(request.getParameter("op") !=null) ? request.getParameter("op"):"list";
           ArrayList<Seminarios>lista=new ArrayList<Seminarios>();
           ConexionDB canal = new ConexionDB();
           Connection conn = canal.conectar();
           PreparedStatement ps;
           ResultSet rs;
           if(op.equals("list")){
               //Para listar los datos
               String sql = "select * from seminarios";
               //Consulta de seleccion y almacenarlo en una coleccion
               ps=conn.prepareStatement(sql);
               rs=ps.executeQuery();
               while(rs.next()){
                   Seminarios s=new Seminarios();
                   s.setId(rs.getInt("id"));
                   s.setTitulo(rs.getString("titulo"));
                   s.setExpositor(rs.getString("expositor"));
                   s.setFecha(rs.getString("fecha"));
                   s.setHora(rs.getString("hora"));
                   s.setCupo(rs.getInt("cupo"));
                   lista.add(s);    
               }
               request.setAttribute("lista", lista);
               //enviar al index.jsp para mostrar la informacion
               request.getRequestDispatcher("index.jsp").forward(request, response);               
           }
           if(op.equals("nuevo")){
               Seminarios li = new Seminarios();
               
               System.out.println(li.toString());
               
               //el objeto se pone como atributo de request
               request.setAttribute("op", op);
               request.setAttribute("semi",li);
               //redireccionar a editar.jsp
               request.getRequestDispatcher("editar.jsp").forward(request, response);
               
           }
           if(op.equals("eliminar")){
               //obtener el id
               id = Integer.parseInt(request.getParameter("id"));
               //realizar la eliminacion en la base de datos
               String sql = "delete from seminarios where id = ?";
               ps=conn.prepareStatement(sql);
               ps.setInt(1,id);
               ps.executeUpdate();
               //redireccionar a MainController
               response.sendRedirect("MainController");
           }
           if(op.equals("editar")){
             request.setAttribute("op", op);
             
             Seminarios s1 = new Seminarios();
               
             id = Integer.parseInt(request.getParameter("id"));
            try{ 
             ps=conn.prepareStatement("select * from seminarios where id=?");
             ps.setInt(1, id);
             rs = ps.executeQuery();
             if(rs.next()){
                 s1.setId(rs.getInt("id"));
                 s1.setTitulo(rs.getString("titulo"));
                 s1.setExpositor(rs.getString("expositor"));
                 s1.setFecha(rs.getString("fecha"));
                 s1.setHora(rs.getString("hora"));
                 s1.setCupo(rs.getInt("cupo"));
             }
             request.setAttribute("semi", s1);
             request.getRequestDispatcher("editar.jsp").forward(request, response);
            }catch(SQLException ex){
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE,null,ex);
            }
             
           }
           
       }catch(SQLException ex){
           System.out.println("ERROR AL CONECTAR"+ex.getMessage());
       }
    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try{
           int id=Integer.parseInt(request.getParameter("id"));
           System.out.println("Valor de ID"+id);
           String titulo = request.getParameter("titulo");
           String expositor = request.getParameter("expositor");
           String fecha = request.getParameter("fecha");
           String hora = request.getParameter("hora");
           int cupo =Integer.parseInt(request.getParameter("cupo")) ;
           
           Seminarios se= new Seminarios();
           se.setId(id);
           se.setTitulo(titulo);
           se.setExpositor(expositor);
           se.setFecha(fecha);
           se.setHora(hora);
           se.setCupo(cupo);
           
           ConexionDB canal=new ConexionDB();
           Connection conn = canal.conectar();
           PreparedStatement ps;
           ResultSet rs;
           
           if(id==0){
               //Nuevo registro
               String sql="Insert into seminarios(titulo,expositor,fecha,hora,cupo) values(?,?,?,?,?)";
               ps=conn.prepareStatement(sql);
               ps.setString(1,se.getTitulo());
               ps.setString(2,se.getExpositor());
               ps.setString(3,se.getFecha());
               ps.setString(4,se.getHora());
               ps.setInt(5,se.getCupo());
               ps.executeUpdate();
           }
           else{
              //String sql="Insert into libros(isbn,titulo,categoria) values(?,?,?)";
              String sql1="UPDATE seminarios SET titulo=?,expositor=?,fecha=?,hora=?,cupo=? where id=?";
              try{ 
              ps=conn.prepareStatement(sql1);
               ps.setString(1,se.getTitulo());
               ps.setString(2,se.getExpositor());
               ps.setString(3,se.getFecha());
               ps.setString(4,se.getHora());
               ps.setInt(5,se.getCupo());
               ps.setInt(6,se.getId());
               ps.executeUpdate(); 
           }catch(SQLException ex){
               Logger.getLogger(MainController.class.getName()).log(Level.SEVERE,null,ex);
           }
           }  
           response.sendRedirect("MainController");
       }catch(SQLException ex){
           System.out.println("Error en SQL"+ex.getMessage());
       }

    }
}


