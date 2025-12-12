<%-- 
  File: news_crud.jsp
  Description: Giao diện CRUD Tin tức (Đã nâng cấp Upload ảnh)
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

<h2>Quản lý Tin tức</h2>
<hr>

<div class="crud-container">
    
    <!-- === FORM (Thêm/Sửa) === -->
    <div class="crud-form">
        <c:set var="isEdit" value="${not empty newsItem}" />
        <h3>${isEdit ? 'Cập nhật Bản tin' : 'Thêm Bản tin mới'}</h3>
        
        <!-- SỬA LỖI: Thêm enctype="multipart/form-data" để hỗ trợ upload file -->
        <form action="${pageContext.request.contextPath}/admin/news" method="post" enctype="multipart/form-data">
            
            <c:choose>
                <c:when test="${isEdit}">
                    <input type="hidden" name="action" value="update" />
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="action" value="create" />
                </c:otherwise>
            </c:choose>

            <div class="form-group ${isEdit ? '' : 'full-width'}">
                <label for="id">Mã bản tin (Id)</label>
                <input type="text" name="id" value="${newsItem.id}" ${isEdit ? 'readonly' : 'required'}>
            </div>

            <div class="form-group ${isEdit ? '' : 'full-width'}">
                <label for="categoryId">Loại tin</label>
                <select name="categoryId" required>
                    <option value="">-- Chọn loại tin --</option>
                    <c:forEach var="cat" items="${categoriesList}">
                        <option value="${cat.id}" ${cat.id == newsItem.categoryId ? 'selected' : ''}>
                            ${cat.name}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group full-width">
                <label for="title">Tiêu đề</label>
                <input type="text" name="title" value="${newsItem.title}" required>
            </div>

            <div class="form-group full-width">
                <label for="summary">Tóm tắt</label>
                <textarea name="summary">${newsItem.summary}</textarea>
            </div>
            
            <div class="form-group full-width">
                <label for="content">Nội dung</label>
                <textarea name="content" style="height: 150px;">${newsItem.content}</textarea>
            </div>
            
            <!-- SỬA LỖI: Đổi thành input file -->
            <div class="form-group full-width">
                <label for="imageFile">Hình ảnh:</label>
                
                <!-- Input chọn file -->
                <input type="file" name="imageFile" accept="image/*">
                
                <!-- Input ẩn để giữ lại đường dẫn ảnh cũ nếu không chọn ảnh mới -->
                <input type="hidden" name="image" value="${newsItem.image}">
                
                <!-- Hiển thị ảnh hiện tại (nếu có) -->
                <c:if test="${not empty newsItem.image}">
                    <div style="margin-top: 10px;">
                        <img src="${newsItem.image}" alt="Ảnh hiện tại" style="max-height: 100px; border: 1px solid #ccc; padding: 3px;">
                        <br>
                        <small>Ảnh hiện tại (chọn file mới để thay đổi)</small>
                    </div>
                </c:if>
            </div>
            
            <div class="form-group-checkbox">
                <input type="checkbox" id="home" name="home" value="true" ${newsItem.home ? 'checked' : ''}>
                <label for="home">Hiển thị trên Trang nhất</label>
            </div>
            
            <!-- Nút bấm -->
            <div class="button-group">
                <button type="submit" class="btn ${isEdit ? 'btn-update' : 'btn-create'}">
                    ${isEdit ? 'Cập nhật' : 'Thêm mới'}
                </button>
                <a href="${pageContext.request.contextPath}/admin/news" class="btn btn-reset">Làm mới</a>
            </div>
        </form>
    </div>

    <!-- === TABLE (Danh sách) === -->
    <div class="crud-list">
        <h3>Danh sách Bản tin</h3>
        <div class="table-responsive">
            <table class="crud-table">
                <thead>
                    <tr>
                        <th>Tiêu đề</th>
                        <th>Ngày đăng</th>
                        <th>Tác giả</th>
                        <th>Lượt xem</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="news" items="${newsList}">
                        <tr>
                            <td>${news.title}</td>
                            <td><fmt:formatDate value="${news.postedDate}" pattern="dd/MM/yyyy"/></td>
                            <td>${news.author}</td>
                            <td>${news.viewCount}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/news?action=edit&id=${news.id}" 
                                   class="btn btn-sm btn-update">Sửa</a>
                                <a href="${pageContext.request.contextPath}/admin/news?action=delete&id=${news.id}" 
                                   class="btn btn-sm btn-delete" 
                                   onclick="return confirm('Xóa tin này?')">Xóa</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty newsList}">
                        <tr>
                            <td colspan="5">Không có bản tin nào.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>