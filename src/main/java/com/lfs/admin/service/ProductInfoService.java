package com.lfs.admin.service;

import com.lfs.admin.model.entity.AgentInfoEntity;
import com.lfs.admin.model.entity.ChannelInfoEntity;
import com.lfs.admin.model.entity.OrderInfoEntity;
import com.lfs.admin.model.entity.ProductInfoEntity;
import com.lfs.admin.model.vo.AgentInfoVO;
import com.lfs.admin.model.vo.ChannelVO;
import com.lfs.admin.model.vo.ProductInfoVo;
import com.lfs.dao.entity.PageBean;
import com.lfs.interfaces.model.Product;
import com.lfs.interfaces.model.vo.OrderInfoVO;
import com.lfs.interfaces.model.vo.ProductVo;

import java.util.List;

public interface ProductInfoService {

    ProductInfoEntity getProductByCode(Long productCode);

    List<ProductInfoEntity> queryProductList(ProductInfoVo productInfoVo);

    int updateProductInfo(ProductInfoVo productInfoVo);

    int updateProductStatus(ProductInfoVo productInfoVo);

    int addProductInfo(ProductInfoVo productInfoVo);

    int deleteProductInfo(Integer[] ids);
}
