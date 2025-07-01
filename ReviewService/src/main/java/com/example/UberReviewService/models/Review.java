package com.example.UberReviewService.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter //Automatically generates a getter method for a field.
@Setter //Automatically generates a setter method for a field.
@Builder //The @Builder annotation in Lombok is used to implement the Builder Pattern in Java — a design pattern that helps in constructing complex objects step-by-step with better readability and flexibility.
@NoArgsConstructor // Generates a no-argument constructor (i.e., a constructor with no parameters).
@AllArgsConstructor //Generates a constructor with one argument for each field in the class.
//@ToString //Automatically generates a toString() method that includes all fields. Helpful for debugging/logging DTOs.
@EntityListeners(AuditingEntityListener.class) //The annotation @EntityListeners(AuditingEntityListener.class) in Spring Data JPA is used to enable auditing on an entity. It hooks the entity into Spring’s auditing infrastructure so that fields annotated with @CreatedDate, @LastModifiedDate, @CreatedBy, and @LastModifiedBy can be automatically populated.
@Entity //marks a Java class as a JPA entity, which means it will be mapped to a table in the database. Tells JPA to treat the class as a database entity (i.e., map it to a DB table).
@Table(name = "bookingreview") //lets you customize the name and schema of the table to which the entity is mapped. Customizes the table name, schema, and other properties.
@Inheritance(strategy = InheritanceType.JOINED)

public class Review extends BaseModel  {

    @Column(nullable = false)
    private String content;

    private Double rating;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Booking booking; // we have defined a 1:1 relationship between booking and review

    @Override
    public String toString() {
        return "Review: " + this.content + " " + this.rating + " " + " booking: " + this.booking.getId() + " " + this.createdAt;
    }

}

// new Review(content, rating);