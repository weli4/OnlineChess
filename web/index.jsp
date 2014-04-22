<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
  </body>
</html>