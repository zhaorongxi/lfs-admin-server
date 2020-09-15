package com.lfs.admin.service.impl;

import com.lfs.admin.dao.TransferInfoDao;
import com.lfs.admin.model.entity.TransferInfoEntity;
import com.lfs.admin.model.vo.TransferInfoVO;
import com.lfs.admin.service.TransferInfoService;
import com.lfs.dao.entity.PageBean;
import com.lfs.dao.service.SystemService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransferInfoServiceImpl implements TransferInfoService {

    @Autowired
    private TransferInfoDao transferInfoDao;

    @Override
    public List<TransferInfoEntity> querySelectList(TransferInfoVO transferInfoVO) {
        return transferInfoDao.querySelectList(transferInfoVO);
    }

    @Override
    public PageBean<TransferInfoEntity> queryTransferList(TransferInfoVO transferInfoVO) {
        List<TransferInfoEntity> transferList = new ArrayList<>();
        try {
            PageHelper.startPage(transferInfoVO.getCurrentPage(), transferInfoVO.getPageSize());
            transferList = transferInfoDao.queryTransferList(transferInfoVO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageBean<TransferInfoEntity>(transferList);
    }

    @Override
    public int updateTransferInfo(TransferInfoVO transferInfoVO) {
        transferInfoVO.setOperatorId(SystemService.getCurrentUser().getId());
        return transferInfoDao.updateTransferInfo(transferInfoVO);
    }

    @Override
    public int addTransferInfo(TransferInfoVO transferInfoVO) {
        transferInfoVO.setOperatorId(SystemService.getCurrentUser().getId());
        transferInfoVO.setOperatorName(SystemService.getCurrentUser().getLoginName());
        return transferInfoDao.addTransferInfo(transferInfoVO);
    }
}
