<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>

<html>
    <head>
        <title>Calculator Results</title>
    </head>
    <body>
        <h2>The answer is:  "<%= request.getAttribute("result")%>"</h2>
         <form action="BigCalc" method="GET">
            <label for="letter">Input two numbers: </label><br>
            x: <input type="text" name="numberX" value="" /><br>
            y: <input type="text" name="numberY" value="" /><br>
            <select name="operator">
                <option value = "add">Add</option>
                <option value = "multiply">Multiply</option>
                <option value = "relativelyPrime">Relatively Prime</option>
                <option value = "mod">Modulo</option>
                <option value = "modInverse">Modular Inverse</option>
                <option value = "power">Raise x to y</option>
            </select><br><br>
            <input type="submit" value="Click Here" />
        </form>
    </body>
</html>

