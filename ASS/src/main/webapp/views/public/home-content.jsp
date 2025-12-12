<%-- 
  File: home-content.jsp
  Description: CHỈ chứa phần nội dung của trang chủ (tin trang nhất)
  File này sẽ được nạp vào ${view} trong layout.jsp
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
  
<!-- Tiêu đề Trang chủ -->
<h2 style="border-bottom: 2px solid #28a745; padding-bottom: 10px; margin-bottom: 20px;">
    Tin Trang Nhất
</h2>

<c:choose>
    <%-- 1. Nếu có tin (Danh sách không rỗng) --%>
    <c:when test="${not empty homeNews}">
        <div class="news-list">
            <c:forEach var="news" items="${homeNews}">
                <article class="news-item">
                    <h3>
                        <a href="${pageContext.request.contextPath}/detail?id=${news.id}" 
                           style="text-decoration: none; color: #333; font-size: 1.2em;">
                            ${news.title}
                        </a>
                    </h3>
                    
                    <div class="news-meta" style="color: #777; font-size: 0.9em; margin-bottom: 10px;">
                        <span><i class="date-icon">📅</i> <fmt:formatDate value="${news.postedDate}" pattern="dd/MM/yyyy HH:mm"/></span>
                        <span style="margin: 0 5px;">|</span>
                        <span><i class="author-icon">✍️</i> ${news.author}</span>
                    </div>
                    
                    <div class="news-content-preview" style="display: flex; gap: 15px;">
                        <%-- Hiển thị ảnh nếu có --%>
                        <c:if test="${not empty news.image}">
                             <div class="news-thumb" style="flex: 0 0 150px;">
                                 <img src="${news.image}" alt="${news.title}" 
                                      style="width: 100%; border-radius: 4px; object-fit: cover;"
                                      onerror="this.style.display='none'">
                             </div>
                        </c:if>

                        <div class="news-summary" style="flex: 1;">
                            <p style="margin: 0; line-height: 1.5;">${news.summary}</p>
                            <a href="${pageContext.request.contextPath}/detail?id=${news.id}" 
                               style="color: #28a745; font-size: 0.9em; display: inline-block; margin-top: 5px;">
                               Xem chi tiết &rarr;
                            </a>
                        </div>
                    </div>
                </article>
            </c:forEach>
        </div>
    </c:when>
    
    <%-- 2. Nếu KHÔNG có tin nào --%>
    <c:otherwise>
        <div class="alert-box" style="padding: 20px; background-color: #f8f9fa; border: 1px solid #ddd; border-radius: 5px; text-align: center;">
            <p>Hiện tại chưa có bản tin nổi bật nào.</p>
            
            <%-- Gợi ý cho Admin --%>
            <c:if test="${not empty sessionScope.user}">
                <p style="margin-top: 10px;">
                    <a href="${pageContext.request.contextPath}/admin/news" class="btn-create" 
                       style="padding: 5px 10px; text-decoration: none; color: white; border-radius: 3px; font-size: 0.9em;">
                       Quản lý Tin tức ngay
                    </a>
                </p>
            </c:if>
        </div>
    </c:otherwise>
</c:choose>