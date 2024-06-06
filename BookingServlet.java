package com.hotel;
import java.sql.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingDAO bookingDAO;

    public void init() {
        bookingDAO = new BookingDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertBooking(request, response);
                    break;
                case "/delete":
                    deleteBooking(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateBooking(request, response);
                    break;
                default:
                    listBooking(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }catch(ClassNotFoundException ec) {
        	ec.printStackTrace();
        }
    }

    private void listBooking(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException,ClassNotFoundException {
        List<Booking> listBooking = bookingDAO.listAllBookings();
        request.setAttribute("listBooking", listBooking);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-bookings.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException,ClassNotFoundException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("add-booking.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException,ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        Booking existingBooking = bookingDAO.getBooking(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("update-booking.jsp");
        request.setAttribute("booking", existingBooking);
        dispatcher.forward(request, response);
    }

    private void insertBooking(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException,ClassNotFoundException {
        String guestName = request.getParameter("guestName");
        int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");

        Booking newBooking = new Booking();
        newBooking.setGuestName(guestName);
        newBooking.setRoomNumber(roomNumber);
        newBooking.setCheckInDate(checkInDate);
        newBooking.setCheckOutDate(checkOutDate);
        bookingDAO.insertBooking(newBooking);
        response.sendRedirect("list");
    }

    private void updateBooking(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException,ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        String guestName = request.getParameter("guestName");
        int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");

        Booking booking = new Booking();
        booking.setId(id);
        booking.setGuestName(guestName);
        booking.setRoomNumber(roomNumber);
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        bookingDAO.updateBooking(booking);
        response.sendRedirect("list");
    }

    private void deleteBooking(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException,ClassNotFoundException{
        int id = Integer.parseInt(request.getParameter("id"));

        Booking booking = new Booking();
        booking.setId(id);
        bookingDAO.deleteBooking(booking);
        response.sendRedirect("list");
    }
}
