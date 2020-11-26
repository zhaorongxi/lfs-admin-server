package com.lfs.admin.dao;

import com.lfs.admin.model.entity.ProductGroupEntity;
import com.lfs.admin.model.entity.ProductInfoEntity;
import com.lfs.admin.model.vo.ProductGroupVo;
import com.lfs.admin.model.vo.ProductInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductGroupDao {

    List<ProductGroupEntity> queryProductGroupList(ProductGroupVo productGroupVo);

    ProductGroupEntity getProductGroupById(@Param("id") Integer id);

    ProductGroupEntity getProductGroupByCode(@Param("groupNum") String groupNum);

    int updateProductGroup(ProductGroupVo productGroupVo);

    int updateProductGroupStatus(ProductGroupVo productGroupVo);

    int addProductGroup(ProductGroupVo productGroupVo);

    int deleteProductGroup(Integer[] ids);

}
