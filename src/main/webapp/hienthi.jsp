<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tucuteprovjp
  Date: 12/4/2024
  Time: 6:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/phieu-giam-gia/search" method="post">
    <input type="text" name="search">
    <button type="submit">search</button>
    <input type="text" value="${phieuGiamGia.id}">
    <input type="text" value="${phieuGiamGia.loaiPhieuGiamGia.ten}">
    <input type="text" value="${phieuGiamGia.ma}">
    <input type="text" value="${phieuGiamGia.ten}">
    <input type="text" value="${phieuGiamGia.soLuong}">
</form>
    <table>
        <thead>
        <tr>
            <td>ID</td>
            <td>Loai phieu</td>
            <td>Ma</td>
            <td>Ten</td>
            <td>So luong</td>
            <td>Loai giam</td>
            <td>Dieu kien</td>
            <td>Gia tri toi da</td>
            <td>Hanh dong</td>
        </tr>
        </thead>
        <c:forEach var="pgg" items="${phieuGiamGiaList}">
            <tr>
                <td>${pgg.id}</td>
                <td>${pgg.loaiPhieuGiamGia.ten}</td>
                <td>${pgg.ma}</td>
                <td>${pgg.ten}</td>
                <td>${pgg.soLuong}</td>
                <td>${pgg.loaiGiam}</td>
                <td>${pgg.dieuKienToiThieu}</td>
                <td>${pgg.giaTriToiDa}</td>
                <td><a href="/phieu-giam-gia/xoa?id=${pgg.id}">xoa</a></td>
            </tr>
        </c:forEach>
    </table>
    <form action="/phieu-giam-gia/them" method="post">
        ID: <input type="number" name="id"> <br>
        Loai: <select name="loai" id="">
        <c:forEach var="loaipgg" items="${loaiPhieuGiamGiaList}">
            <option value="${loaipgg.id}">${loaipgg.ten}</option>
        </c:forEach>
            </select><br>
        Ma: <input type="text" name="ma"> <br>
        Ten: <input type="text" name="ten"> <br>
        So luong: <input type="number" name="soLuong"> <br>
        Loai giam: <input type="text" name="loaiGiam"> <br>
        Dieu kien toi thieu: <input type="text" name="dieuKienToiThieu"> <br>
        Gia tri toi da: <input type="text" name="giaTriToiDa"> <br>
        <button type="submit">Ok</button>
    </form>
</body>
</html>
