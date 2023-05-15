package com.shop.shopHive.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Meal implements Serializable {
    private static final long serialVersionUID = 4353636567787L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    private Double price;

    private String preparationTime;

    private String description;

    @JsonFormat(pattern="dd-MM-yyyy")
    @CreationTimestamp
    private LocalDate dateCreated;

    public Meal(String name, double price, String preparationTime, String description) {
        this.name = name;
        this.price = price;
        this.preparationTime = preparationTime;
        this.description = description;
    }

}
