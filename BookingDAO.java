package com.hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/hotel_booking_system";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Kapil@8009";
    private Connection jdbcConnection;

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertBooking(Booking booking) throws SQLException {
        String sql = "INSERT INTO bookings (guest_name, room_number, check_in_date, check_out_date) VALUES (?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, booking.getGuestName());
        statement.setInt(2, booking.getRoomNumber());
        statement.setString(3, booking.getCheckInDate());
        statement.setString(4, booking.getCheckOutDate());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Booking> listAllBookings() throws SQLException {
        List<Booking> listBooking = new ArrayList<>();

        String sql = "SELECT * FROM bookings";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String guestName = resultSet.getString("guest_name");
            int roomNumber = resultSet.getInt("room_number");
            String checkInDate = resultSet.getString("check_in_date");
            String checkOutDate = resultSet.getString("check_out_date");

            Booking booking = new Booking();
            booking.setId(id);
            booking.setGuestName(guestName);
            booking.setRoomNumber(roomNumber);
            booking.setCheckInDate(checkInDate);
            booking.setCheckOutDate(checkOutDate);
            listBooking.add(booking);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listBooking;
    }

    public boolean deleteBooking(Booking booking) throws SQLException {
        String sql = "DELETE FROM bookings where id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, booking.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateBooking(Booking booking) throws SQLException {
        String sql = "UPDATE bookings SET guest_name = ?, room_number = ?, check_in_date = ?, check_out_date = ?";
        sql += " WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, booking.getGuestName());
        statement.setInt(2, booking.getRoomNumber());
        statement.setString(3, booking.getCheckInDate());
        statement.setString(4, booking.getCheckOutDate());
        statement.setInt(5, booking.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public Booking getBooking(int id) throws SQLException {
        Booking booking = null;
        String sql = "SELECT * FROM bookings WHERE id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String guestName = resultSet.getString("guest_name");
            int roomNumber = resultSet.getInt("room_number");
            String checkInDate = resultSet.getString("check_in_date");
            String checkOutDate = resultSet.getString("check_out_date");

            booking = new Booking();
            booking.setId(id);
            booking.setGuestName(guestName);
            booking.setRoomNumber(roomNumber);
            booking.setCheckInDate(checkInDate);
            booking.setCheckOutDate(checkOutDate);
        }

        resultSet.close();
        statement.close();

        return booking;
    }
}
