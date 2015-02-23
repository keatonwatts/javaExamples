<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>

<html>
    <head>
        <title>Palindrome Check</title>
    </head>
    <body>
        <h2>The word you searched  <%= request.getAttribute("searchResult")%></h2><br>
         <form action="Palin" method="GET">
            <label for="letter">Type another word: </label>
            <input type="text" name="searchWord" value="" /><br>
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>

