package com.lfs.admin.dao;

import com.lfs.admin.model.entity.TransferInfoEntity;
import com.lfs.admin.model.vo.TransferInfoVO;

import java.util.List;

public interface TransferInfoDao {

    List<TransferInfoEntity> queryTransferList(TransferInfoVO transferInfoVO);

    List<TransferInfoEntity> querySelectList(TransferInfoVO transferInfoVO);

    int updateTransferInfo(TransferInfoVO transferInfoVO);

    int addTransferInfo(TransferInfoVO transferInfoVO);
}
