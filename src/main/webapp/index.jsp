<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Graph detector</title>
    <link href="stylesheets/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<header class="header">
    Абдуллин Игорь Эдуардович, P3208, вариант №777
</header>
<aside class="aside">
    <div class="wrapper-table">
        <table class="table">
            <thead>
            <tr>
                <th>Request time (milli)</th>
                <th>Current time</th>
                <th>In range</th>
                <th>X</th>
                <th>Y</th>
                <th>R</th>
            </tr>
            </thead>
            <tbody id="tableData">
            <jsp:include page="check-result.jsp"/>
            </tbody>
        </table>
    </div>
    <button class="button" id="clearButton" role="button">Очистить</button>
</aside>
<div class="content">
    <section class="interactive">
        <canvas id="graph" width="300" height="300"></canvas>
        <div class="interactive-element">
            <label for="select-X">
                X:
            </label>
            <select id="select-X" name="X" class="x-select">
                <option value="none" selected disabled hidden></option>
                <option value="-3">-3</option>
                <option value="-2.5">-2.5</option>
                <option value="-2">-2</option>
                <option value="-1.5">-1.5</option>
                <option value="-1">-1</option>
                <option value="-0.5">-0.5</option>
                <option value="0">0</option>
                <option value="0.5">0.5</option>
                <option value="1">1</option>
                <option value="1.5">1.5</option>
                <option value="2">2</option>
                <option value="3">3</option>
            </select>
        </div>

        <div class="interactive-element">
            <label for="Y-input">Y:</label>
            <input type="number" id="Y-input">
        </div>

        <div class="interactive-element">
            <fieldset>
                <legend>Radius:</legend>
                <div id="radio-radius">
                    <label for="choiceOne"></label>
                    <input
                            type="radio" name="R"
                            value="1"
                            id="choiceOne"
                            checked/>
                    <span>1</span>

                    <label for="choiceOneAndHalf"></label>
                    <input
                            type="radio"
                            name="R"
                            value="1.5"
                            id="choiceOneAndHalf"/>
                    <span>1.5</span>

                    <label for="choiceTwo"></label>
                    <input
                            type="radio"
                            name="R"
                            value="2"
                            id="choiceTwo"/>
                    <span>2</span>

                    <label for="choiceTwoAndHalf"></label>
                    <input
                            type="radio"
                            name="R"
                            value="2.5"
                            id="choiceTwoAndHalf"/>
                    <span>2.5</span>

                    <label for="choiceThree"></label>
                    <input
                            type="radio"
                            name="R"
                            value="3"
                            id="choiceThree"/>
                    <span>3</span>
                </div>
            </fieldset>
        </div>
        <button class="button" role="button" id="checkButton">Проверить</button>
    </section>
    <script src="webpack-ts/dist/script.js"></script>
</div>
<footer class="footer"> &copy; 2023 ИТМО ПИиКТ. Создано с помощью HTML/CSS, JS. Все права защищены.</footer>
</body>
</html>