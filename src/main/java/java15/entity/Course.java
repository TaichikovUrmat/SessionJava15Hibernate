package java15.entity;

import jakarta.persistence.*;
import java15.enums.StudyFormat;
import lombok.*;

import java.math.BigDecimal;
@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Course {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "courses_id_gen"
    )
    @SequenceGenerator(
            name = "courses_id_gen",
            sequenceName = "courses_seq_name",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "course_name")
    private String courseName;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;

    public Course(String courseName, BigDecimal price, StudyFormat studyFormat) {
        this.courseName = courseName;
        this.price = price;
        this.studyFormat = studyFormat;
    }
}
