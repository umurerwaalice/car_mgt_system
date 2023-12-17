package com.car.service;

import com.car.model.Booking;
import com.car.repository.BookingRepository;

import java.util.List;

public interface BookingService {
    List<Booking> findByAllBooking();
    void SaveBooking(Booking booking);
    Booking getBookingById(Long booking_Id);
    void deleteBookingById(Long booking_Id);



}
