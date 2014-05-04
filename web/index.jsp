<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="gameManager" scope="application" class="chess.GameManager" />
<html>
  <head>
    <title></title>
  </head>
  <body>
       <form action="newgame" method="post">
           <input type="text" name="name" value="">
           <input type="radio" name="color" value="white" checked>
           <span>White</span>
           <input type="radio" name="color" value="black">
           <span>Black</span>
           <button type="submit">Create new game</button>
       </form>
       <c:forEach items="${gameManager.gameMap}" var="games" varStatus="x">
           <form action="joinGame" method="post">
               <input type="hidden" name="name" value="sfsf">
               <input type="hidden" name="id" value="${games.key}">
               <button type="submit">join</button>
           </form>
       </c:forEach>
  </body>
</html>