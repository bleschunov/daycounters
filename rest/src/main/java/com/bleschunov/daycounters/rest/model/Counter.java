package com.bleschunov.daycounters.rest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * @author Bleschunov Dmitry
 */
@Entity
@Setter
@Getter
@Table(
        name = "counter",
        indexes = { @Index(name = "title_index", columnList = "title", unique = true) }
)
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false, length = 256)
    private String title;

    @Column(name = "max_value", nullable = false)
    private long maxValue;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AppUser owner;

    @Column(name = "countdown_time", nullable = false)
    private LocalDateTime countdownTime = LocalDateTime.now();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
