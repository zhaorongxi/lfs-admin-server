package com.lfs.admin.dao;

import com.lfs.admin.model.entity.AgtAccessEntity;
import com.lfs.admin.model.entity.ProductInfoEntity;
import com.lfs.admin.model.vo.AgentInfoVO;
import com.lfs.admin.model.vo.ProductInfoVo;
import com.lfs.interfaces.model.AgtAccess;
import com.lfs.interfaces.model.AgtSecurity;
import com.lfs.interfaces.model.AgtWallet;
import com.lfs.interfaces.model.Product;
import com.lfs.interfaces.model.vo.AgtAccessVo;
import com.lfs.interfaces.model.vo.ProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductInfoDao {

    List<ProductInfoEntity> queryProductList(ProductInfoVo productInfoVo);

    ProductInfoEntity getProductByCode(@Param("productCode") Long productCode);

    ProductInfoEntity getProductById(@Param("id") Integer id);

    int updateProductInfo(ProductInfoVo productInfoVo);

    int updateProductStatus(ProductInfoVo productInfoVo);

    int addProductInfo(ProductInfoVo productInfoVo);

    int deleteProductInfo(Integer[] ids);

}
