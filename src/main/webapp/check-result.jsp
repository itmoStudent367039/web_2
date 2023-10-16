<%@ page import="com.example.utils.JsonWorker" %>
<%@ page import="com.example.models.Point" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.example.utils.Rounder" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Enumeration<String> names = session.getAttributeNames();
    while (names.hasMoreElements()) {
        String name = names.nextElement();
        Point point = JsonWorker.toObject(Point.class, session.getAttribute(name).toString());
%>
<tr>
    <td><%=point.getRequestTime()%>
    </td>
    <td><%=point.getCurrentTime()%>
    </td>
    <td><%=point.isInRange()%>
    </td>
    <td><%=Rounder.round(point.getX())%>
    </td>
    <td><%=Rounder.round(point.getY())%>
    </td>
    <td><%=Rounder.round(point.getRadius())%>
    </td>
</tr>

<%
    }
%>

