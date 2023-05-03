package dev.daly.issatsoevents.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ImageData")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_name", nullable = false)
    private String name;
    private String type;
    @Lob
    @Column(name = "imagedata",length = Integer.MAX_VALUE)
    private byte[] imageData;
}