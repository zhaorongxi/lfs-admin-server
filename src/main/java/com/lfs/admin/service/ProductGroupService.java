package com.lfs.admin.service;

import com.lfs.admin.model.entity.ProductGroupEntity;
import com.lfs.admin.model.entity.ProductInfoEntity;
import com.lfs.admin.model.vo.ProductGroupVo;
import com.lfs.admin.model.vo.ProductInfoVo;

import java.util.List;

public interface ProductGroupService {

    ProductGroupEntity getProductGroupByCode(String groupNum);

    List<ProductGroupEntity> queryProductGroupList(ProductGroupVo productGroupVo);

    int updateProductGroup(ProductGroupVo productGroupVo);

    int updateProductStatus(ProductGroupVo productGroupVo);

    int addProductGroup(ProductGroupVo productGroupVo);

    int deleteProductGroup(Integer[] ids);
}
