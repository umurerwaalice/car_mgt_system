package com.car.repository;

import com.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car ,Long> {

    @Query(value = "select * from Car c where c.name like %:keyword% or c.manufacture like %:keyword%", nativeQuery = true)
    List<Car> findByKeyword(@Param("keyword") String keyword);

//    to handler image download
    Optional<Car> findById(Long carId);
}
