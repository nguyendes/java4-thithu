package org.xdev.controller;

import com.mchange.v2.beans.BeansUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.xdev.entity.LoaiPhieuGiamGia;
import org.xdev.entity.PhieuGiamGia;
import org.xdev.repository.impl.LoaiPhieuGiamGiaRepoImpl;
import org.xdev.repository.impl.PhieuGiamGiaRepoImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


@WebServlet({"/hien-thi","/them","/xoa","/search"})
public class PhieuGiamGiaController extends HttpServlet {
    LoaiPhieuGiamGiaRepoImpl repo = new LoaiPhieuGiamGiaRepoImpl();
    PhieuGiamGiaRepoImpl phieuGiamGiaRepo = new PhieuGiamGiaRepoImpl();
    List<PhieuGiamGia> phieuGiamGiaList=new ArrayList<>();
    List<LoaiPhieuGiamGia>  loaiPhieuGiamGiaList=new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if(uri.contains("/hien-thi")){
            phieuGiamGiaList=phieuGiamGiaRepo.findAll();
            loaiPhieuGiamGiaList=repo.findAll();
            req.setAttribute("phieuGiamGiaList",phieuGiamGiaList);
            req.setAttribute("loaiPhieuGiamGiaList",loaiPhieuGiamGiaList);
            req.getRequestDispatcher("hienthi.jsp").forward(req, resp);
        }
        if(uri.contains("/xoa")){
            int id = Integer.parseInt(req.getParameter("id"));
            PhieuGiamGia pgg= phieuGiamGiaRepo.getPhieuGiamGiaById(id);
            System.out.println(id);
            phieuGiamGiaRepo.delete(pgg);
            resp.sendRedirect("/phieu-giam-gia/hien-thi");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if(uri.contains("/them")){
            PhieuGiamGia phieuGiamGia = new PhieuGiamGia();
            try {
                BeanUtils.populate(phieuGiamGia, req.getParameterMap());
                int loai=Integer.parseInt(req.getParameter("loai"));
                LoaiPhieuGiamGia loaiPhieuGiamGia= repo.findById(loai);
                phieuGiamGia.setLoaiPhieuGiamGia(loaiPhieuGiamGia);
                phieuGiamGiaRepo.save(phieuGiamGia);

            } catch (Exception e){
                e.printStackTrace();
            }
            resp.sendRedirect("/phieu-giam-gia/hien-thi");
        }
        if(uri.contains("/search")){
            String search = req.getParameter("search");
            PhieuGiamGia phieuGiamGia= phieuGiamGiaRepo.getPhieuGiamGiaByName(search);
            req.setAttribute("phieuGiamGia",phieuGiamGia);
            req.getRequestDispatcher("hienthi.jsp").forward(req, resp);
        }
    }
}
