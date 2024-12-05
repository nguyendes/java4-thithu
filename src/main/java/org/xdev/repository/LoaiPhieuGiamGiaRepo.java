package org.xdev.repository;

import org.xdev.entity.LoaiPhieuGiamGia;

import java.util.List;

public interface LoaiPhieuGiamGiaRepo {
    List<LoaiPhieuGiamGia> findAll();
    LoaiPhieuGiamGia findById(int id);
}
