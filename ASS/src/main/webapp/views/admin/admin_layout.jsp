<%-- 
  File: admin_layout.jsp (CỦA ADMIN)
  Description: Layout chính cho khu vực quản trị
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} - Trang Quản Trị</title>
    <%-- Bootstrap 5 CSS --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <%-- Font Awesome Icons --%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- Tải CSS của Admin -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin_style.css">
</head>
<body>

    <div class="admin-wrapper">
        
        <!-- 1. Sidebar (Menu "phần thân") -->
        <jsp:include page="/views/admin/admin_menu.jsp" />

        <!-- 2. Khu vực nội dung chính -->
        <div class="admin-main">
            
            <!-- 2.1. Header (Phần đầu) -->
            <jsp:include page="/views/admin/_header.jsp" />

            <!-- 2.2. Nội dung thay đổi -->
            <main class="admin-content">
                <div class="content-box">
                    <!-- 
                      Nội dung chính sẽ được nạp vào đây 
                      (ví dụ: dashboard.jsp, news_crud.jsp...)
                    -->
                    <jsp:include page="${view}" />
                </div>
            </main>

        </div>
    </div>

    <%-- Bootstrap 5 JS Bundle --%>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>