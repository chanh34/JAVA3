<%-- 
  File: newsletter_crud.jsp
  Description: Giao diện Quản lý Newsletter
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>

<!-- CSS đã có trong admin_style.css -->

<h2>Quản lý Newsletter</h2>
<hr>

<div class="crud-container">
    
    <!-- Chỉ có danh sách (Vì newsletter chủ yếu do người dùng tự đăng ký) -->
    <div class="crud-list" style="width: 100%;">
        <h3>Danh sách Email đăng ký nhận tin</h3>
        <div class="table-responsive">
            <table class="crud-table">
                <thead>
                    <tr>
                        <th>Email</th>
                        <th>Trạng thái</th>
                        <th>Ngày đăng ký</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="n" items="${newsletterList}">
                        <tr>
                            <td>${n.email}</td>
                            <td>
                                <span style="color: ${n.enabled ? 'green' : 'gray'}; font-weight: bold;">
                                    ${n.enabled ? 'Đang hoạt động' : 'Đã hủy'}
                                </span>
                            </td>
                            <td><fmt:formatDate value="${n.subscribedDate}" pattern="dd/MM/yyyy"/></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/newsletters?action=delete&email=${n.email}" 
                                   class="btn btn-sm btn-delete"
                                   onclick="return confirm('Xóa email này?')">Xóa</a>
                                   
                                <a href="${pageContext.request.contextPath}/admin/newsletters?action=toggle&email=${n.email}" 
                                   class="btn btn-sm btn-update">
                                   ${n.enabled ? 'Hủy' : 'Kích hoạt'}
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty newsletterList}">
                        <tr><td colspan="4">Chưa có email nào đăng ký.</td></tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>