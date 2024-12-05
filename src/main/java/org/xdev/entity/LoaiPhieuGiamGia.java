package org.xdev.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "loai_phieu_giam_gia")
public class LoaiPhieuGiamGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;
}
