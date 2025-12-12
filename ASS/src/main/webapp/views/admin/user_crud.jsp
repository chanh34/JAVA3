<%-- 
  File: user_crud.jsp
  Description: Giao diện CRUD User
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<!-- CSS đã có trong admin_style.css -->

<div class="message-container">
    <c:if test="${not empty message}">
        <div class="message-success">${message}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="message-error">${error}</div>
    </c:if>
</div>

<h2>Quản lý Người dùng</h2>
<hr>

<div class="crud-container">
    
    <!-- FORM -->
    <div class="crud-form">
        <c:set var="isEdit" value="${not empty userItem}" />
        <h3>${isEdit ? 'Cập nhật User' : 'Thêm User mới'}</h3>
        
        <form action="${pageContext.request.contextPath}/admin/users" method="post">
            <c:choose>
                <c:when test="${isEdit}">
                    <input type="hidden" name="action" value="update" />
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="action" value="create" />
                </c:otherwise>
            </c:choose>

            <div class="form-group">
                <label>Username (ID):</label>
                <input type="text" name="id" value="${userItem.id}" ${isEdit ? 'readonly' : 'required'}>
            </div>

            <div class="form-group">
                <label>Mật khẩu:</label>
                <input type="password" name="password" value="${userItem.password}" required>
            </div>

            <div class="form-group">
                <label>Họ và tên:</label>
                <input type="text" name="fullname" value="${userItem.fullname}" required>
            </div>
            
            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" value="${userItem.email}" required>
            </div>
            
            <div class="form-group">
                <label>Ngày sinh:</label>
                <!-- Format ngày để hiển thị trong input date -->
                <fmt:formatDate value="${userItem.birthday}" pattern="yyyy-MM-dd" var="formattedDob"/>
                <input type="date" name="birthday" value="${formattedDob}">
            </div>
            
            <div class="form-group">
                <label>Giới tính:</label>
                <select name="gender">
                    <option value="true" ${userItem.gender ? 'selected' : ''}>Nam</option>
                    <option value="false" ${!userItem.gender ? 'selected' : ''}>Nữ</option>
                </select>
            </div>
            
            <div class="form-group">
                <label>Vai trò:</label>
                <select name="role">
                    <option value="false" ${!userItem.role ? 'selected' : ''}>Phóng viên</option>
                    <option value="true" ${userItem.role ? 'selected' : ''}>Quản trị viên</option>
                </select>
            </div>

            <div class="button-group">
                <button type="submit" class="btn ${isEdit ? 'btn-update' : 'btn-create'}">
                    ${isEdit ? 'Cập nhật' : 'Thêm mới'}
                </button>
                <a href="${pageContext.request.contextPath}/admin/users" class="btn btn-reset">Làm mới</a>
            </div>
        </form>
    </div>

    <!-- TABLE -->
    <div class="crud-list">
        <h3>Danh sách Người dùng</h3>
        <div class="table-responsive">
            <table class="crud-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Họ tên</th>
                        <th>Email</th>
                        <th>Vai trò</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="u" items="${userList}">
                        <tr>
                            <td>${u.id}</td>
                            <td>${u.fullname}</td>
                            <td>${u.email}</td>
                            <td>
                                <span style="color: ${u.role ? 'red' : 'blue'}; font-weight: bold;">
                                    ${u.role ? 'Admin' : 'Phóng viên'}
                                </span>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/users?action=edit&id=${u.id}" 
                                   class="btn btn-sm btn-update">Sửa</a>
                                <a href="${pageContext.request.contextPath}/admin/users?action=delete&id=${u.id}" 
                                   class="btn btn-sm btn-delete"
                                   onclick="return confirm('Xóa user này?')">Xóa</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>