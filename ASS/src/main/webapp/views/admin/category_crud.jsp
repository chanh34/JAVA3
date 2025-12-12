<%-- 
  File: category_crud.jsp
  Description: Giao diện CRUD Loại tin
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<!-- CSS đã có trong admin_style.css -->

<h2>Quản lý Loại tin</h2>
<hr>

<div class="crud-container">

    <!-- FORM -->
    <div class="crud-form">
        <c:set var="isEdit" value="${not empty categoryItem}" />
        <h3>${isEdit ? 'Cập nhật Loại tin' : 'Thêm Loại tin mới'}</h3>
        
        <form action="${pageContext.request.contextPath}/admin/categories" method="post">
            <c:choose>
                <c:when test="${isEdit}">
                    <input type="hidden" name="action" value="update" />
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="action" value="create" />
                </c:otherwise>
            </c:choose>

            <div class="form-group">
                <label for="id">Mã loại (Id)</label>
                <input type="text" name="id" value="${categoryItem.id}" ${isEdit ? 'readonly' : 'required'}>
            </div>

            <div class="form-group">
                <label for="name">Tên loại tin</label>
                <input type="text" name="name" value="${categoryItem.name}" required>
            </div>
            
            <div class="button-group">
                <button type="submit" class="btn ${isEdit ? 'btn-update' : 'btn-create'}">
                    ${isEdit ? 'Cập nhật' : 'Thêm mới'}
                </button>
                <a href="${pageContext.request.contextPath}/admin/categories" class="btn btn-reset">Làm mới</a>
            </div>
        </form>
    </div>

    <!-- TABLE -->
    <div class="crud-list">
        <h3>Danh sách Loại tin</h3>
        <div class="table-responsive">
            <table class="crud-table">
                <thead>
                    <tr>
                        <th>Mã loại</th>
                        <th>Tên loại tin</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cat" items="${categoriesList}">
                        <tr>
                            <td>${cat.id}</td>
                            <td>${cat.name}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/categories?action=edit&id=${cat.id}" 
                                   class="btn btn-sm btn-update">Sửa</a>
                                <a href="${pageContext.request.contextPath}/admin/categories?action=delete&id=${cat.id}" 
                                   class="btn btn-sm btn-delete" 
                                   onclick="return confirm('Xóa loại tin này?')">Xóa</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>