package com.bc.admin.service;

import com.bc.admin.model.entity.TransferInfoEntity;
import com.bc.admin.model.vo.TransferInfoVO;
import com.bc.dao.entity.PageBean;

import java.util.List;

public interface TransferInfoService {

    List<TransferInfoEntity> querySelectList(TransferInfoVO transferInfoVO);

    PageBean<TransferInfoEntity> queryTransferList(TransferInfoVO transferInfoVO);

    int updateTransferInfo(TransferInfoVO transferInfoVO);

    int addTransferInfo(TransferInfoVO transferInfoVO);
}
