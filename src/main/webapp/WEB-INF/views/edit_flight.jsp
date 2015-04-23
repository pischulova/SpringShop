<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="/WEB-INF/layout/header.jsp"%>

<div class="body">
    <%@include file="/WEB-INF/layout/side_menu.jsp"%>
    <div class="content">
        <form:form commandName="editFlightForm" action="/editflight" method="post">
            <table>
                <tr>
                    <td><fmt:message key="product_name_en"/></td>
                    <td><form:input path="nameEn" value="${flight.nameEn}" /></td>
                    <td><form:errors path="nameEn" cssClass="error" /></td>
                </tr>
                <tr>
                    <td><fmt:message key="product_name_ru"/></td>
                    <td><form:input path="nameRu" value="${flight.nameRu}" /></td>
                    <td><form:errors path="nameRu" cssClass="error" /></td>
                </tr>
                    <%--<tr>--%>
                    <%--<td><fmt:message key="country_name_en"/></td>--%>
                    <%--<td><springForm:input path="countryNameEn" value="${flight.country.nameEn}" /></td>--%>
                    <%--<td><springForm:errors path="countryNameEn" cssClass="error" /></td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                    <%--<td><fmt:message key="country_name_ru"/></td>--%>
                    <%--<td><springForm:input path="countryNameRu" value="${flight.country.nameRu}" /></td>--%>
                    <%--<td><springForm:errors path="countryNameRu" cssClass="error" /></td>--%>
                    <%--</tr>--%>
                <tr>
                    <td><fmt:message key="price"/></td>
                    <td><form:input path="price" value="${flight.price}" /></td>
                    <td><form:errors path="price" cssClass="error" /></td>
                </tr>
                <tr>
                    <td><fmt:message key="country"/></td>
                    <td>
                        <form:select path="country">
                            <form:option value="NONE" label="------------" />

                            <%--<c:forEach var="factory" items="${factories}" >--%>
                                <%--<option value="${factory.id}" label="${factory.address.city} ${factory.address.street}"/>--%>
                            <%--</c:forEach>--%>



                            <c:choose>
                                <c:when test="${pageContext.response.locale == 'en'}">
                                    <form:options items="${countryListEn}"/>
                                </c:when>
                                <c:otherwise>
                                    <form:options items="${countryListRu}"/>
                                </c:otherwise>
                            </c:choose>



                        </form:select>
                    </td>
                    <td><form:errors path="country" cssClass="error" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="<fmt:message key="save"/>"></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </form:form>


    </div>
</div>

<%@include file="/WEB-INF/layout/footer.jsp"%>