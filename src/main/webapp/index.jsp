<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Seminarios"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%List<Seminarios> lista=(List<Seminarios>)request.getAttribute("lista");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body >
        <div style="text-align:center;">
        <table border="1" style="margin: 0 auto;">
            <tr>
                <td>
            <label>SEGUNDO PARCIAL TEM-742</label>
            <BR>
            <label>Nombre:Daniel Bernardo Calle Larico</label>
            <br>
            <label>Carnet: 10017632 LP</label>
                </td>
            </tr>
        </table>
        </div>
        <h1 style="text-align: center;">Registro De Seminarios</h1>
        <hr>
        
        <p><a href="MainController?op=nuevo"> Nuevo </a></p>
       
        <table border="1">
            <tr>
                
                <TH>ID</TH>
                <TH>Titulo</TH>
                <TH>Expositor</TH>
                <TH>Fecha</TH>
                <TH>Horas</TH>      
                <TH>Cupos</TH>      
                <TH></TH>      
                <TH></TH>      
            </tr>
            <c:forEach var="item" items="${lista}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.titulo}</td>
                    <td>${item.expositor}</td>
                    <td>${item.fecha}</td>
                    <td>${item.hora}</td>
                    <td>${item.cupo}</td>
                    <td><a href="MainController?op=editar&id=${item.id}">Editar</a></td>
                    <td><a href="MainController?op=eliminar&id=${item.id}"
                           onclick="return(confirm('Esta seguro ?'))">Eliminar</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
