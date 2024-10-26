package com.mosope.expenseTracker.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mosope.expenseTracker.transaction.Transaction;
import com.mosope.expenseTracker.wallet.Wallet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NonNull
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NonNull
    private String lastName;

    @Column(name = "username", nullable = false)
    @NonNull
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    @NonNull
    private String email;

    @Column(name = "password", nullable = false)
    @NonNull
    private String password;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, updatable = false)
    private LocalDateTime updatedAt;

//    @JsonIgnore
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Transaction> transactions;

//    @JsonIgnore
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Wallet wallet;
}
