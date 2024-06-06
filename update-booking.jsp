<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Booking</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h1>Update Booking</h1>
    <form action="update" method="post">
        <input type="hidden" name="id" value="${booking.id}">
        <label for="guestName">Guest Name:</label>
        <input type="text" name="guestName" id="guestName" value="${booking.guestName}" required><br><br>
        <label for="roomNumber">Room Number:</label>
        <input type="number" name="roomNumber" id="roomNumber" value="${booking.roomNumber}" required><br><br>
        <label for="checkInDate">Check-in Date:</label>
        <input type="date" name="checkInDate" id="checkInDate" value="${booking.checkInDate}" required><br><br>
        <label for="checkOutDate">Check-out Date:</label>
        <input type="date" name="checkOutDate" id="checkOutDate" value="${booking.checkOutDate}" required><br><br>
        <input type="submit" value="Submit">
    </form>
    <a href="list">Back to List</a>
</body>
</html>
