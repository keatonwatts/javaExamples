<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>

<html>
    <head>
        <title>Hash Results</title>
    </head>
    <body>
        <h2>Hashes of the String "<%= request.getAttribute("originalWord")%>"</h2>
        <p><%= request.getAttribute("searchResultHex")%></p>
        <p><%= request.getAttribute("searchResultBase")%></p>
         <form action="ComputeHashes" method="GET">
            <label for="letter">Type a word: </label>
            <input type="text" name="searchWord" value="" /><br>
            <input type="radio" name="hashType" value="md5" checked> MD5<br>
            <input type="radio" name="hashType" value="sha"> SHA-1<br><br>
            <input type="submit" value="Click Here" />
        </form>
    </body>
</html>

