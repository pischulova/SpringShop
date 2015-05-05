<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<ul class="pages-menu">
    <c:if test="${page != 1 && message != 'nothing_found'}">
        <li class="menu-item"><a href="/${param.link}page=1"><fmt:message key="first"/></a></li>
    </c:if>

    <c:choose>
        <c:when test="${pageNumber == 1 || message =='nothing_found'}">
        </c:when>

        <c:when test="${pageNumber < 5}">
            <c:forEach begin="1" end="${pageNumber}" var="p">
                <c:choose>
                    <c:when test="${page == p}">
                        <li class="menu-item selected">
                            <a href="/${param.link}page=${p}"> ${p} </a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="menu-item"><a href="/${param.link}page=${p}"> ${p} </a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:when>

        <c:otherwise>
            <c:choose>
                <c:when test="${page < 4}">
                    <c:forEach begin="1" end="5" var="p">
                        <c:choose>
                            <c:when test="${page == p}">
                                <li class="menu-item selected">
                                    <a href="/${param.link}page=${p}"> ${p} </a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="menu-item">
                                    <a href="/${param.link}page=${p}"> ${p} </a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:when>
                <c:when test="${page > pageNumber - 3}">
                    <c:forEach begin="${pageNumber - 4}" end="${pageNumber}" var="p">
                        <c:choose>
                            <c:when test="${page == p}">
                                <li class="menu-item selected">
                                    <a href="/${param.link}page=${p}"> ${p} </a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="menu-item">
                                    <a href="/${param.link}page=${p}"> ${p} </a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach begin="${page - 2}" end="${page + 2}" var="p">
                        <c:choose>
                            <c:when test="${page == p}">
                                <li class="menu-item selected">
                                    <a href="/${param.link}page=${p}"> ${p} </a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="menu-item">
                                    <a href="/${param.link}page=${p}"> ${p} </a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>

    <c:if test="${page != pageNumber && message != 'nothing_found'}">
        <li class="menu-item">
            <a href="/${param.link}page=${pageNumber}"><fmt:message key="last"/></a></li>
    </c:if>
</ul>