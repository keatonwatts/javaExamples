<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Convert String</title>
    </head>
    <body>
        <p>Let's convert a string of text to a hash value.</p>
        <form action="ComputeHashes" method="GET">
            <label for="letter">Type a word: </label>
            <input type="text" name="searchWord" value="" /><br>
            <input type="radio" name="hashType" value="md5" checked> MD5<br>
            <input type="radio" name="hashType" value="sha"> SHA-1<br><br>
            <input type="submit" value="Click Here" />
        </form>
    </body>
</html>
