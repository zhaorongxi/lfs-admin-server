package com.lfs.admin.controller.product;

import com.lfs.admin.model.entity.ProductGroupEntity;
import com.lfs.admin.model.entity.ProductInfoEntity;
import com.lfs.admin.model.vo.ProductGroupVo;
import com.lfs.admin.model.vo.ProductInfoVo;
import com.lfs.admin.service.ProductGroupService;
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
@RequestMapping("/productGroup")
public class ProductGroupController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ProductGroupController.class);

    @Autowired
    private ProductGroupService productGroupService;

    /**
     * 查询产品组列表接口
     * @param productGroupVo
     * @return
     * @throws Exception
     */
    @PreAuthorize("@ss.hasPermi('product:productGroup:list')")
    @PostMapping("/queryProductGroupList")
    public TableDataInfo queryProductGroupList(ProductGroupVo productGroupVo) {

        if(null == productGroupVo){
            throw new BusinessException("查询产品列表接口参数不能为空!");
        }

        logger.info("根据{},查询产品列表请求参数", productGroupVo.toString());
        startPage();
        List<ProductGroupEntity> productList = productGroupService.queryProductGroupList(productGroupVo);
        return getDataTable(productList);
    }

    /**
     * 查询产品组信息
     * @param agentInfoVO
     * @return
     * @throws Exception
     */
    /**
     * 根据产品组编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:productGroup:query')")
    @GetMapping(value = { "/getProductGroup/{id}"})
    public AjaxResult getProductInfo(@PathVariable(value = "id", required = false) String groupNum)
    {
        AjaxResult ajax = AjaxResult.success();
        if (StringUtils.isNotNull(groupNum))
        {
            ajax.put(AjaxResult.DATA_TAG, productGroupService.getProductGroupByCode(groupNum));
        }
        return ajax;
    }

    /**
     * 更新产品组信息
     * @param productGroupVo
     * @return
     */
    @PutMapping("/updateProductGroup")
    @Log(title = "产品组管理", businessType = BusinessType.UPDATE)
    public Result<?> updateProductGroup(@RequestBody ProductGroupVo productGroupVo){
        if(null == productGroupVo.getGroupNum()){
            throw new BusinessException("请求更新的产品编码不能为空!");
        }
        int result = productGroupService.updateProductGroup(productGroupVo);
        if(result <= 0){
            logger.info("未更新到任意一条记录!");
        }
        return  ResultObject.successMessage("更新成功!");
    }

    /**
     * 新增产品信息
     * @param productGroupVo
     * @return
     */
    @PostMapping("/addProductGroup")
    @Log(title = "产品组管理", businessType = BusinessType.INSERT)
    public Result<?> addProductGroup(@RequestBody ProductGroupVo productGroupVo){
        if(null == productGroupVo.getGroupNum()){
            throw new BusinessException("产品组编码不能为空!");
        }
        if(null == productGroupVo.getGroupName()){
            throw new BusinessException("产品组名称不能为空!");
        }
        if(null == productGroupVo.getGroupState()){
            throw new BusinessException("产品组状态不能为空!");
        }
        int result = productGroupService.addProductGroup(productGroupVo);
        if(result <= 0){
            throw new BusinessException("新增产品失败!");
        }
        return  ResultObject.successMessage("新增产品成功!");
    }

    /**
     * 删除产品组信息
     * @param groupIds
     * @return
     */
    @PreAuthorize("@ss.hasPermi('product:productGroup:remove')")
    @Log(title = "产品组管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/delProductGroup/{groupIds}")
    public AjaxResult deleteProductGroup(@PathVariable Integer[] groupIds){
        return toAjax(productGroupService.deleteProductGroup(groupIds));
    }

}
