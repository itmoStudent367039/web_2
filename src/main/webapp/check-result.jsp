<%@ page import="com.example.utils.Rounder" %>
<%@ page import="com.example.utils.DateTimePrettierFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:useBean id="pointDAO" class="com.example.dao.PointDAO" scope="session"/>
<c:if test="${not empty pointDAO}">
    <c:forEach items="${pointDAO.pointList}" var="point">
        <tr>
            <td>${point.requestTime}</td>
            <td>${DateTimePrettierFormatter.doFormat(point.currentTime)}</td>
            <td>${point.inRange}</td>
            <td>${Rounder.round(point.x)}</td>
            <td>${Rounder.round(point.y)}</td>
            <td>${Rounder.round(point.radius)}</td>
        </tr>
    </c:forEach>
</c:if>


