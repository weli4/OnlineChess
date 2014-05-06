<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="gameManager" scope="application" class="chess.GameManager" />
<jsp:useBean id="player" scope="session" class="chess.entity.Player" />
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
       <c:forEach items="${gameManager.gameMap}" var="game" varStatus="x">
           <c:if test="${fn:length(game.value.players) eq 2}">
               ${game.value.players[0].name} : ${game.value.players[0].rating}
               <form action="joinGame" method="post">
                   <input type="hidden" name="id" value="${game.key}">
                   <button type="submit">join</button>
               </form>
           </c:if>
       </c:forEach>
  </body>
</html>