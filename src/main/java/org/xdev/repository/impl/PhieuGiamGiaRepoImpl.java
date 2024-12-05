package org.xdev.repository.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.xdev.entity.PhieuGiamGia;
import org.xdev.repository.PhieuGiamGiaRepo;
import org.xdev.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class PhieuGiamGiaRepoImpl implements PhieuGiamGiaRepo {

    @Override
    public List<PhieuGiamGia> findAll() {
        List<PhieuGiamGia> list = new ArrayList<PhieuGiamGia>();
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            Query query = session.createQuery("from PhieuGiamGia");
            list = (List<PhieuGiamGia>) query.list();
        }catch(Exception e){
            e.printStackTrace();
        }return list;
    }

    @Override
    public PhieuGiamGia getPhieuGiamGiaByName(String ten) {
        PhieuGiamGia phieuGiamGia = null;
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            Query query=session.createQuery("from PhieuGiamGia where ten=:ten");
            query.setParameter("ten",ten);
            phieuGiamGia=(PhieuGiamGia)query.uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }return phieuGiamGia;
    }

    @Override
    public PhieuGiamGia getPhieuGiamGiaById(int id) {
        PhieuGiamGia phieuGiamGia = null;
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            phieuGiamGia= session.get(PhieuGiamGia.class, id);
        }catch(Exception e){
            e.printStackTrace();
        }return phieuGiamGia;
    }

    @Override
    public PhieuGiamGia save(PhieuGiamGia phieuGiamGia) {
        Transaction tx = null;
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            session.save(phieuGiamGia);
            tx.commit();
        }catch(Exception e){
            e.printStackTrace();
            if(tx!=null){
                tx.rollback();
            }
        }return phieuGiamGia;
    }

    @Override
    public void delete(PhieuGiamGia phieuGiamGia) {
        Transaction tx = null;
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            session.delete(phieuGiamGia);
            tx.commit();
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
        }
    }
}
