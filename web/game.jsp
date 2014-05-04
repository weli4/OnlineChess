<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="game" scope="session" class="chess.entity.Game" />
<jsp:useBean id="player" scope="session" class="chess.entity.Player" />
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" media="screen" href="resources/css/style.css" />
    <script type="text/javascript" src="resources/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery-ui-1.10.4.min.js"></script>
    <script type="text/javascript" src="resources/js/pieces.js"></script>
</head>
<body>
<input type="hidden" name="color" value="${player.color}">
<input type="hidden" name="update" value="${game.update}">
<div id="time"><c:out value="${player.timer.total}"></c:out></div>
<div id="board">
    <c:forEach items="${game.pieces}" var="pieces" varStatus="x">
        <div class="row">
      <c:forEach items="${pieces}" var = "p" varStatus="y">
          <div id="${x.index}${y.index}" class="cell<c:if test="${not empty p.name or not empty p.color}"> ${p.name} ${p.color}</c:if>"></div>
      </c:forEach>
        </div>
    </c:forEach>
</div>
</body>
</html>