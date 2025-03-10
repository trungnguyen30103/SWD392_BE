package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryID; // ✅ Sử dụng camelCase

    @Column(nullable = false, unique = true, length = 255) // ✅ Giới hạn độ dài
    private String categoryName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // ✅ Tránh vòng lặp vô hạn khi trả về JSON
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Blindbox> blindboxes;

    // Bạn có thể thêm phương thức để dễ dàng truy cập hoặc thực hiện logic thêm nếu cần
}
