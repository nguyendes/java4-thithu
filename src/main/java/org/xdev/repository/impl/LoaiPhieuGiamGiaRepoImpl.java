package org.xdev.repository.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.xdev.entity.LoaiPhieuGiamGia;
import org.xdev.repository.LoaiPhieuGiamGiaRepo;
import org.xdev.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class LoaiPhieuGiamGiaRepoImpl implements LoaiPhieuGiamGiaRepo {


    @Override
    public List<LoaiPhieuGiamGia> findAll() {
        List<LoaiPhieuGiamGia> list = new ArrayList<LoaiPhieuGiamGia>();
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            Query query = session.createQuery("from LoaiPhieuGiamGia");
            list = (List<LoaiPhieuGiamGia>) query.list();
        }catch(Exception e){
            e.printStackTrace();
        }return list;
    }

    @Override
    public LoaiPhieuGiamGia findById(int id) {
        LoaiPhieuGiamGia loaiPhieuGiamGia = null;
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            return loaiPhieuGiamGia = session.get(LoaiPhieuGiamGia.class, id);
        }catch(Exception e){
            e.printStackTrace();
        }return loaiPhieuGiamGia;
    }
}
