package com.avdhoot.batch;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "comment_data")
@NoArgsConstructor
@AllArgsConstructor
public class CommentData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String body;
}
