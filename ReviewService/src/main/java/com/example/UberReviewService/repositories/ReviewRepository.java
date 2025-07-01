package com.example.UberReviewService.repositories;

import com.example.UberReviewService.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository //Marks a class as a Data Access Object (DAO). Indicates that the class will interact with the database.
//Enables exception translation — converting low-level exceptions (like JDBC or JPA exceptions) into Spring’s DataAccessException hierarchy.
//Allows automatic component scanning, so Spring can detect and manage the class as a Spring Bean.
//Long:	The type of the primary key (@Id) field in the Review entity.
//review is the name of entity which is to be managed by this repo
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Integer countAllByRatingIsLessThanEqual(Integer givenRating);

    List<Review> findAllByRatingIsLessThanEqual(Integer givenRating);

    List<Review> findAllByCreatedAtBefore(Date date);

    @Query("select r from Booking b inner join Review r where b.id = :bookingId")
    Review findReviewByBookingId(Long bookingId);
}
