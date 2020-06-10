package com.bc.admin.dao;

import com.bc.admin.model.entity.BankInfoEntity;
import com.bc.admin.model.vo.BankInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BankInfoDao {

    List<BankInfoEntity> queryBankList(BankInfoVO bankInfoVO);

    List<BankInfoEntity> querySelectList(BankInfoVO bankInfoVO);

    BankInfoEntity getBankInfoById(BankInfoVO bankInfoVO);

    int getBankInfoByCardNo(BankInfoVO bankInfoVO);

    BankInfoEntity getBankInfoByAgtPhone(BankInfoVO bankInfoVO);

    int updateBankInfo(BankInfoVO bankInfoVO);

    int addBankInfo(BankInfoVO bankInfoVO);

    int deleteBankInfo(@Param("id") Integer id);
}
