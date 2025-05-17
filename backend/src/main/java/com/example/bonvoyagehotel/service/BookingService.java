package com.example.bonvoyagehotel.service;

import com.example.bonvoyagehotel.entity.Booking;
import com.example.bonvoyagehotel.entity.Room;
import com.example.bonvoyagehotel.entity.User;
import com.example.bonvoyagehotel.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;

    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
}
