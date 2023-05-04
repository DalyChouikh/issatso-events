package dev.daly.issatsoevents.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "event")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    private String location;
    private LocalDate date;
    private LocalTime time;
    private String organizer;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_data_id", referencedColumnName = "id")
    private ImageData imageData;

}
