<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>Let's see if the word you provide is a palindrome.</p>
        <form action="Palin" method="GET">
            <label for="letter">Type a word: </label>
            <input type="text" name="searchWord" value="" /><br>
            <input type="submit" value="Click Here" />
        </form>
    </body>
</html>
