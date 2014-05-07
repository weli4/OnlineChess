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
       Your rating: Rating ${player.rating}/Win ${player.win}/Loose ${player.loose}/Withdraw ${player.withdraw}
       <form action="newgame" method="post">
           <input type="radio" name="color" value="white" checked>
           <span>White</span>
           <input type="radio" name="color" value="black">
           <span>Black</span>
           <button type="submit">Create new game</button>
       </form>
       <c:forEach items="${gameManager.gameMap}" var="game" varStatus="x">
           <c:if test="${fn:length(game.value.players) eq 1}">
               <form action="joinGame" method="post">
                   <strong>${game.value.players[0].name}</strong> : Rating ${game.value.players[0].rating}
                   <input type="hidden" name="id" value="${game.key}">
                   <button type="submit">join</button>
               </form>
           </c:if>
       </c:forEach>
  </body>
</html>