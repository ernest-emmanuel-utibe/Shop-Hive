package com.shop.shopHive.models;

import com.shop.shopHive.utils.CityJpaConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class ServiceProvider implements Serializable {

    private static final long serialVersionUID = 43536367737L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
//    @Column(unique = true)
    private String name;

    @NotNull
//    @Column(unique = true)
    private String email;

    private String address;

    private String phoneNumber;

    @NotNull
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @JsonIgnore
    private AppUser appUser;

    @Convert(converter = CityJpaConverter.class)
    private City city;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    //@LazyCollection(LazyCollectionOption.EXTRA)
    //@JoinColumn(referencedColumnName = "id")
    private List<Meal> listOfMeals;

    //@Transactional(readOnly = true)
    public void addMeal(Meal meal){
        if(listOfMeals == null){
            listOfMeals = new ArrayList<>();
        }
        listOfMeals.add(meal);
    }

    @JsonFormat(pattern="dd/MM/yyyy")
    @CreationTimestamp
    private LocalDate dateCreated;
}
