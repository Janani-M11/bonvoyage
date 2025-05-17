package com.example.bonvoyagehotel.controller;

import com.example.bonvoyagehotel.dto.BookingRequest;
import com.example.bonvoyagehotel.entity.Booking;
import com.example.bonvoyagehotel.entity.Room;
import com.example.bonvoyagehotel.entity.User;
import com.example.bonvoyagehotel.repository.BookingRepository;
import com.example.bonvoyagehotel.repository.RoomRepository;
import com.example.bonvoyagehotel.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> bookRoom(@Valid @RequestBody BookingRequest request,
                                      @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        Room room = roomRepository.findById(request.getRoomId()).orElse(null);
        if (room == null || !room.isAvailable()) {
            return ResponseEntity.badRequest().body("Room not available");
        }

        if (request.getCheckOutDate().isBefore(request.getCheckInDate()) || request.getCheckInDate().isBefore(LocalDate.now())) {
            return ResponseEntity.badRequest().body("Invalid check-in or check-out dates");
        }

        Booking booking = Booking.builder()
                .user(user)
                .room(room)
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .adults(request.getAdults())
                .children(request.getChildren())
                .totalPrice(room.getPricePerNight() * (request.getCheckOutDate().toEpochDay() - request.getCheckInDate().toEpochDay()))
                .build();

        bookingRepository.save(booking);

        return ResponseEntity.ok("Room booked successfully");
    }

    @GetMapping
    public List<Booking> getUserBookings(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElse(null);
        if (user == null) {
            return List.of();
        }
        return bookingRepository.findByUser(user);
    }
}
