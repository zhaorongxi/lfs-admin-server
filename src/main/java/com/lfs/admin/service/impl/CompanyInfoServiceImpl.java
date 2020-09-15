package com.lfs.admin.service.impl;

import com.lfs.admin.dao.CompanyInfoDao;
import com.lfs.admin.model.entity.CompanyInfoEntity;
import com.lfs.admin.model.vo.CompanyInfoVO;
import com.lfs.admin.service.CompanyInfoService;
import com.lfs.dao.entity.PageBean;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {

    @Autowired
    private CompanyInfoDao companyInfoDao;

    @Override
    public PageBean<CompanyInfoEntity> queryCompanyList(CompanyInfoVO companyInfoVO) {
        List<CompanyInfoEntity> companyList = new ArrayList<>();
        try {
            PageHelper.startPage(companyInfoVO.getCurrentPage(), companyInfoVO.getPageSize());
            companyList = companyInfoDao.queryCompanyList(companyInfoVO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageBean<CompanyInfoEntity>(companyList);
    }

    @Override
    public List<CompanyInfoEntity> querySelectList(CompanyInfoVO companyInfoVO) {
        return companyInfoDao.queryCompanyList(companyInfoVO);
    }

    @Override
    public int updateCompanyInfo(CompanyInfoVO companyInfoVO) {
        return companyInfoDao.updateCompanyInfo(companyInfoVO);
    }

    @Override
    public int addCompanyInfo(CompanyInfoVO companyInfoVO) {
        return companyInfoDao.addCompanyInfo(companyInfoVO);
    }
}
