<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Booking</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h1>Add Booking</h1>
    <form action="insert" method="post">
        <label for="guestName">Guest Name:</label>
        <input type="text" name="guestName" id="guestName" required><br><br>
        <label for="roomNumber">Room Number:</label>
        <input type="number" name="roomNumber" id="roomNumber" required><br><br>
        <label for="checkInDate">Check-in Date:</label>
        <input type="date" name="checkInDate" id="checkInDate" required><br><br>
        <label for="checkOutDate">Check-out Date:</label>
        <input type="date" name="checkOutDate" id="checkOutDate" required><br><br>
        <input type="submit" value="Submit">
    </form>
    <a href="list">Back to List</a>
</body>
</html>
