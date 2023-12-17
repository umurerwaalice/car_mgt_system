package com.car.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long booked_Id;
    private String startedDate;
    private String returnDate;
    private LocalDate localDate;
    private String status;
    @ManyToOne
    @JoinColumn(name = "driver_code")
    private Car car;
    @ManyToOne
    @JoinColumn(name = "client_code")
    private Client client;

}
