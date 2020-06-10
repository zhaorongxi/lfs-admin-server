package com.bc.admin.service;

import com.bc.admin.model.entity.CompanyInfoEntity;
import com.bc.admin.model.vo.CompanyInfoVO;
import com.bc.dao.entity.PageBean;

import java.util.List;

public interface CompanyInfoService {

    PageBean<CompanyInfoEntity> queryCompanyList(CompanyInfoVO companyInfoVO);

    List<CompanyInfoEntity> querySelectList(CompanyInfoVO companyInfoVO);

    int updateCompanyInfo(CompanyInfoVO companyInfoVO);

    int addCompanyInfo(CompanyInfoVO companyInfoVO);
}
