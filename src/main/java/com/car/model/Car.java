package com.car.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String plateNumber;
    private String name;
    private String manufacture;
    private Long year;
    private Long price;
    private Long seat;
    @ManyToOne
    @JoinColumn(name = "driver_code")
    private Driver driver;

    @Lob
    @Column(name = "image" ,columnDefinition = "LONGBLOB")
    private byte[] image;

    @Transient
    private String imageData;

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }


}
