package com.mosope.expenseTracker.wallet;

import com.mosope.expenseTracker.user.Users;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @Column(name = "status", nullable = false)
    private String status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, updatable = false)
    private LocalDateTime updatedAt;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @PreUpdate
    public void preUpdate() {
        if(this.getBalance() < 1000.00){
            this.setStatus("Owo ti ku waso oo");
        } else if (this.getBalance() > 1000.00) {
            this.setStatus("You are on track");
        }
    }
}
