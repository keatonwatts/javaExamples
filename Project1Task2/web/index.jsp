<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculator</title>
    </head>
    <body>
        <p>Let's do some math.</p>
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
