package com.lfs.admin.service;

import com.lfs.admin.model.entity.TransferInfoEntity;
import com.lfs.admin.model.vo.TransferInfoVO;
import com.lfs.dao.entity.PageBean;

import java.util.List;

public interface TransferInfoService {

    List<TransferInfoEntity> querySelectList(TransferInfoVO transferInfoVO);

    PageBean<TransferInfoEntity> queryTransferList(TransferInfoVO transferInfoVO);

    int updateTransferInfo(TransferInfoVO transferInfoVO);

    int addTransferInfo(TransferInfoVO transferInfoVO);
}
