<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="/WEB-INF/layout/header.jsp"%>

<div class="body">
    <%@include file="/WEB-INF/layout/side_menu.jsp"%>
    <div class="content">
        <form:form commandName="editFlightForm" action="/edit_flight" method="post">

            <c:choose>
                <c:when test="${pageContext.response.locale == 'en'}">
                    <c:set var="countryList" value="${countryListEn}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="countryList" value="${countryListRu}"/>
                </c:otherwise>
            </c:choose>

            <table>
                <tr>
                    <td></td>
                    <td><form:hidden path="id" value="${flight.id}" /></td>
                    <td></td>
                </tr>
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
                <tr>
                    <td><fmt:message key="price"/></td>
                    <td><form:input path="price" value="${flight.price}" /></td>
                    <td><form:errors path="price" cssClass="error" /></td>
                </tr>
                <tr>
                    <td><fmt:message key="country"/></td>
                    <td>
                        <form:select path="country.id">

                            <c:forEach var="country" items="${countryList}">
                                <c:choose>
                                    <c:when test="${flight.country.id eq country.key}">
                                        <form:option value="${country.key}" selected="true">
                                            <c:out value="${country.value}"/>
                                        </form:option>
                                    </c:when>
                                    <c:otherwise>
                                        <form:option value="${country.key}">
                                            <c:out value="${country.value}"/>
                                        </form:option>
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>

                        </form:select>
                    </td>
                    <td><form:errors path="country" cssClass="error" /></td>
                </tr>
                <tr>
                    <td><input type="submit" name="save" value="<fmt:message key="save"/>"></td>
                    <td><input type="submit" name="delete" value="<fmt:message key="delete"/>"
                               onclick="return confirm('<fmt:message key="confirm_delete"/>');"></td>
                    <td></td>
                </tr>
            </table>
        </form:form>
    </div>
</div>

<%@include file="/WEB-INF/layout/footer.jsp"%>