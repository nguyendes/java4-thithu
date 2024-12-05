package org.xdev.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "phieu_giam_gia")
public class PhieuGiamGia {
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "loai_phieu_id")
    private LoaiPhieuGiamGia loaiPhieuGiamGia;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name= "so_luong")
    private int soLuong;

    @Column(name = "loai_giam")
    private String loaiGiam;

    @Column(name= "dieu_kieu_toi_thieu")
    private String dieuKienToiThieu;

    @Column(name = "gia_tri_toi_da")
    private String giaTriToiDa;
}
