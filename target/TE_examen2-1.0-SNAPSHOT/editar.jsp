<%@page import="com.emergentes.modelo.Seminarios"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    Seminarios prod=(Seminarios)request.getAttribute("semi");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> <c:if test="${requestScope.op=='nuevo'}" var="res" scope="request">
                Registro de
            </c:if>
            <c:if test="${requestScope.op=='editar'}" var="res" scope="request">    
            Editar
            </c:if>
            Seminario
        </h1>
        <form action="MainController" method="post">
            <table>
                <input type="hidden" name="id" value="${semi.id}">
                <tr>
                    <td>Titulo</td>
                    <td><input type="text" name="titulo" value="${semi.titulo}"></td>
                </tr>
                <tr>
                    <td>Expositor</td>
                    <td><input type="text"  name="expositor" value="${semi.expositor}"></td>
                </tr>
                <tr>
                    <td>Fecha</td>
                    <td><input type="text" name="fecha" value="${semi.fecha}"></td>
                </tr>
                <tr>
                    <td>Horas</td>
                    <td><input type="text" name="hora" value="${semi.hora}"></td>
                </tr>
                <tr>
                    <td>Cupos</td>
                    <td><input type="number" name="cupo" value="${semi.cupo}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                     <input type="hidden" name="opg" value="${requestScope.op}"/>
                     <input type="hidden" name="op" value="grabar"/>
                    </td>
                    <td><input type="submit" value="Enviar"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
