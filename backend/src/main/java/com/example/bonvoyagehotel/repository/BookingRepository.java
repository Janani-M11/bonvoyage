package com.example.bonvoyagehotel.repository;

import com.example.bonvoyagehotel.entity.Booking;
import com.example.bonvoyagehotel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
}
