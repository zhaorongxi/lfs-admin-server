package com.lfs.admin.service.impl;

import com.lfs.admin.dao.AgtWalletInfoDao;
import com.lfs.admin.model.entity.AgtWalletInfoEntity;
import com.lfs.admin.model.vo.AgtWalletInfoVO;
import com.lfs.admin.service.AgtWalletInfoService;
import com.lfs.dao.entity.PageBean;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgtWalletInfoServiceImpl implements AgtWalletInfoService {

    @Autowired
    private AgtWalletInfoDao agtWalletInfoDao;

    @Override
    public PageBean<AgtWalletInfoEntity> queryAgtWallet (AgtWalletInfoVO agtWalletInfoVO) {
        List<AgtWalletInfoEntity> walletApproveList = new ArrayList<>();
        try {
            PageHelper.startPage(agtWalletInfoVO.getCurrentPage(), agtWalletInfoVO.getPageSize());
            walletApproveList = agtWalletInfoDao.queryAgtWallet(agtWalletInfoVO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageBean<AgtWalletInfoEntity>(walletApproveList);
    }

}
