<%-- Đây là file hiển thị thông báo lỗi --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lỗi</title>
    <%-- Chúng ta vẫn dùng CSS chung --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
    <%-- Và layout chung --%>
    <jsp:include page="/views/common/_header.jsp" />
    <jsp:include page="/views/common/_menu.jsp" />
    
    <div class="wrapper container">
        <main class="main-content">
            <h1>Đã có lỗi xảy ra!</h1>
            <p>Hệ thống vừa gặp một lỗi không mong muốn. Vui lòng thử lại sau.</p>
            <p><a href="${pageContext.request.contextPath}/home">Quay về trang chủ</a></p>
            
            <%-- 
              Phần này để debug, bạn có thể bật lên để xem lỗi
            <hr>
            <h3>Chi tiết lỗi (dành cho lập trình viên):</h3>
            <pre><% 
                if (exception != null) {
                    exception.printStackTrace(new java.io.PrintWriter(out));
                } else {
                    out.println("Không có thông tin lỗi chi tiết.");
                }
            %></pre>
            --%>
        </main>
    </div>

    <jsp:include page="/views/common/_footer.jsp" />
</body>
</html>