package com.lfs.admin.service;

import com.lfs.admin.model.entity.CompanyInfoEntity;
import com.lfs.admin.model.vo.CompanyInfoVO;
import com.lfs.dao.entity.PageBean;

import java.util.List;

public interface CompanyInfoService {

    PageBean<CompanyInfoEntity> queryCompanyList(CompanyInfoVO companyInfoVO);

    List<CompanyInfoEntity> querySelectList(CompanyInfoVO companyInfoVO);

    int updateCompanyInfo(CompanyInfoVO companyInfoVO);

    int addCompanyInfo(CompanyInfoVO companyInfoVO);
}
