package com.bc.admin.dao;

import com.bc.admin.model.entity.CompanyInfoEntity;
import com.bc.admin.model.vo.CompanyInfoVO;

import java.util.List;

public interface CompanyInfoDao {

    List<CompanyInfoEntity> queryCompanyList(CompanyInfoVO companyInfoVO);

    List<CompanyInfoEntity> querySelectList(CompanyInfoVO companyInfoVO);

    int updateCompanyInfo(CompanyInfoVO companyInfoVO);

    int addCompanyInfo(CompanyInfoVO companyInfoVO);
}
