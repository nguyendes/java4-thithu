package org.xdev.repository;

import org.xdev.entity.PhieuGiamGia;

import java.util.List;

public interface PhieuGiamGiaRepo {
    List<PhieuGiamGia> findAll();
    PhieuGiamGia getPhieuGiamGiaByName(String name);
    PhieuGiamGia getPhieuGiamGiaById(int id);
    PhieuGiamGia save(PhieuGiamGia phieuGiamGia);
    void delete(PhieuGiamGia phieuGiamGia);
}
