<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="/WEB-INF/layout/header.jsp"%>

<div class="body">
    <%@include file="/WEB-INF/layout/side_menu.jsp"%>
    <div class="content">
        <c:if test="${not empty message}">
            <p><spring:message code="${message}"/></p>
        </c:if>

        <h2><fmt:message key="type_country"/></h2>
        <form:form method="post" commandName="chooseCountryForm" action="/flights">
            <form:input type="text" path="countryName"/>
            <input type="submit" value="<fmt:message key="search"/>"> <br>
            <form:errors path="countryName" cssClass="error" />
        </form:form><br>

        <table width="100%">
            <c:if test="${not empty flightList}">
                <thead>
                <tr>
                    <th><fmt:message key="country"/></th>
                    <th><fmt:message key="destination"/></th>
                    <th><fmt:message key="price"/></th>
                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <th></th>
                        </c:when>
                    </c:choose>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="flight" items="${flightList.pageList}">
                    <tr>
                        <c:choose>
                            <c:when test="${pageContext.response.locale == 'en'}">
                                <td><c:out value="${flight.country.nameEn}"/></td>
                            </c:when>
                            <c:otherwise>
                                <td><c:out value="${flight.country.nameRu}"/></td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${pageContext.response.locale == 'en'}">
                                <td><c:out value="${flight.nameEn}"/></td>
                            </c:when>
                            <c:otherwise>
                                <td><c:out value="${flight.nameRu}"/></td>
                            </c:otherwise>
                        </c:choose>

                        <td><c:out value="${flight.price}"/></td>

                        <c:choose>
                            <c:when test="${not empty sessionScope.user}">
                                <td>
                                    <c:if test="${sessionScope.user.userRole=='ADMIN'}">
                                        <a href="<c:url value="/edit_flight/?id=${flight.id}"/>">
                                            <fmt:message key="edit"/>
                                        </a>
                                    </c:if>
                                    <c:if test="${sessionScope.user.userRole=='CLIENT'}">
                                        <a href="<c:url value="/add_to_cart/?id=${flight.id}"/>">
                                            <fmt:message key="add_to_cart"/>
                                        </a>
                                    </c:if>
                                </td>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:forEach>
                </tbody>
            </c:if>
        </table><br>

        <ul class="pages-menu">
            <c:if test="${!flightList.firstPage}">
                <li class="menu-item"><a href="/flights?page=1&search=${search}"><fmt:message key="first"/></a></li>
            </c:if>

            <c:choose>
                <c:when test="${pageNumber == 1}">
                </c:when>

                <c:when test="${pageNumber < 5}">
                    <c:forEach begin="1" end="${pageNumber}" var="p">
                        <c:choose>
                            <c:when test="${flightList.page + 1 == p}">
                                <li class="menu-item selected"><a href="/flights?page=${p}&search=${search}"> ${p} </a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="menu-item"><a href="/flights?page=${p}&search=${search}"> ${p} </a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:when>

                <c:otherwise>
                    <c:choose>
                        <c:when test="${flightList.page < 3}">
                            <c:forEach begin="1" end="5" var="p">
                                <c:choose>
                                    <c:when test="${flightList.page + 1 == p}">
                                        <li class="menu-item selected"><a href="/flights?page=${p}&search=${search}"> ${p} </a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="menu-item"><a href="/flights?page=${p}&search=${search}"> ${p} </a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:when>
                        <c:when test="${flightList.page > pageNumber - 3}">
                            <c:forEach begin="${pageNumber - 4}" end="${pageNumber}" var="p">
                                <c:choose>
                                    <c:when test="${flightList.page + 1 == p}">
                                        <li class="menu-item selected"><a href="/flights?page=${p}&search=${search}"> ${p} </a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="menu-item"><a href="/flights?page=${p}&search=${search}"> ${p} </a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach begin="${flightList.page - 1}" end="${flightList.page + 3}" var="p">
                                <c:choose>
                                    <c:when test="${flightList.page + 1 == p}">
                                        <li class="menu-item selected"><a href="/flights?page=${p}&search=${search}"> ${p} </a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="menu-item"><a href="/flights?page=${p}&search=${search}"> ${p} </a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>

            <c:if test="${!flightList.lastPage}">
                <li class="menu-item"><a href="/flights?page=${pageNumber}&search=${search}"><fmt:message key="last"/></a></li>
            </c:if>
        </ul>

    </div>
</div>
<%@include file="/WEB-INF/layout/footer.jsp"%>