package com.car.service.impl;

import com.car.model.Booking;
import com.car.model.Booking;
import com.car.model.Car;
import com.car.repository.BookingRepository;
import com.car.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository){
        this.bookingRepository=bookingRepository;
    }


    @Override
    public List<Booking> findByAllBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public void SaveBooking(Booking booking) {
        this.bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long booking_Id) {
        Optional<Booking> optional=bookingRepository.findById(booking_Id);
        Booking booking=null;
        if (optional.isPresent()){
            booking=optional.get();
        }else {
            throw new RuntimeException("teacher id not found "+booking_Id);
        }
        return booking;
        
    }

    @Override
    public void deleteBookingById(Long booking_Id) {
        this.bookingRepository.deleteById(booking_Id);
    }

}
