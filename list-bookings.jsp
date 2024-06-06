<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>List of Bookings</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h1>List of Bookings</h1>
    <a href="new">Add New Booking</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Guest Name</th>
            <th>Room Number</th>
            <th>Check-in Date</th>
            <th>Check-out Date</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="booking" items="${listBooking}">
            <tr>
                <td>${booking.id}</td>
                <td>${booking.guestName}</td>
                <td>${booking.roomNumber}</td>
                <td>${booking.checkInDate}</td>
                <td>${booking.checkOutDate}</td>
                <td>
                    <a href="edit?id=${booking.id}">Edit</a>
                    <a href="delete?id=${booking.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
