package com.lfs.admin.controller.product;

import com.lfs.admin.model.entity.ProductInfoEntity;
import com.lfs.admin.model.vo.ProductInfoVo;
import com.lfs.admin.service.ProductInfoService;
import com.lfs.base.dto.Result;
import com.lfs.base.dto.ResultObject;
import com.lfs.base.exception.BusinessException;
import com.lfs.base.util.StringUtils;
import com.lfs.common.annotation.Log;
import com.lfs.common.core.controller.BaseController;
import com.lfs.common.core.domain.AjaxResult;
import com.lfs.common.core.page.TableDataInfo;
import com.lfs.common.enums.BusinessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductInfoController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ProductInfoController.class);

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 查询产品列表接口
     * @param productInfoVo
     * @return
     * @throws Exception
     */
    @PreAuthorize("@ss.hasPermi('product:productInfo:list')")
    @PostMapping("/queryProductList")
    public TableDataInfo queryProductList(ProductInfoVo productInfoVo) {

        if(null == productInfoVo){
            throw new BusinessException("查询产品列表接口参数不能为空!");
        }

        logger.info("根据{},查询产品列表请求参数", productInfoVo.toString());
        startPage();
        List<ProductInfoEntity> productList = productInfoService.queryProductList(productInfoVo);
        return getDataTable(productList);
    }

    /**
     * 查询产品信息接口
     * @param agentInfoVO
     * @return
     * @throws Exception
     */
    /**
     * 根据产品编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:productInfo:query')")
    @GetMapping(value = { "/getProductInfo/{id}"})
    public AjaxResult getProductInfo(@PathVariable(value = "id", required = false) Integer id)
    {
        AjaxResult ajax = AjaxResult.success();
        if (StringUtils.isNotNull(id))
        {
            ajax.put(AjaxResult.DATA_TAG, productInfoService.getProductById(id));
        }
        return ajax;
    }

    /**
     * 更新产品信息
     * @param productInfoVo
     * @return
     */
    @PutMapping("/updateProductInfo")
    @Log(title = "产品管理", businessType = BusinessType.UPDATE)
    public Result<?> updateProductInfo(@RequestBody ProductInfoVo productInfoVo){
        if(null == productInfoVo.getProductNum()){
            throw new BusinessException("请求更新的产品编码不能为空!");
        }
        int result = productInfoService.updateProductInfo(productInfoVo);
        if(result <= 0){
            logger.info("未更新到任意一条记录!");
        }
        return  ResultObject.successMessage("更新成功!");
    }

    /**
     * 新增产品信息
     * @param productInfoVo
     * @return
     */
    @PostMapping("/addProductInfo")
    @Log(title = "产品管理", businessType = BusinessType.INSERT)
    public Result<?> addProductInfo(@RequestBody ProductInfoVo productInfoVo){
        if(null == productInfoVo.getProductNum()){
            throw new BusinessException("产品编码不能为空!");
        }
        if(null == productInfoVo.getTradeFace()){
            throw new BusinessException("产品面额不能为空!");
        }
        if(null == productInfoVo.getProductType()){
            throw new BusinessException("产品类型不能为空!");
        }
        int result = productInfoService.addProductInfo(productInfoVo);
        if(result <= 0){
            throw new BusinessException("新增产品失败!");
        }
        return  ResultObject.successMessage("新增产品成功!");
    }

    /**
     * 删除产品组信息
     * @param productIds
     * @return
     */
    @PreAuthorize("@ss.hasPermi('product:productInfo:remove')")
    @Log(title = "产品管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/delProductInfo/{productIds}")
    public AjaxResult deleteAgentInfo(@PathVariable Integer[] productIds){
        return toAjax(productInfoService.deleteProductInfo(productIds));
    }

}
