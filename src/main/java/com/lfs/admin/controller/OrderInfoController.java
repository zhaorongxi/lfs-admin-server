package com.lfs.admin.controller;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.lfs.admin.model.entity.OrderInfoEntity;
import com.lfs.admin.model.report.OrderTrade;
import com.lfs.admin.service.OrderInfoService;
import com.lfs.admin.util.ExeclUtil;
import com.lfs.base.dto.Result;
import com.lfs.base.dto.ResultObject;
import com.lfs.base.exception.BusinessException;
import com.lfs.base.util.StringUtils;
import com.lfs.dao.entity.PageBean;
import com.lfs.interfaces.model.vo.OrderInfoVO;
import com.lfs.interfaces.order.service.OrderBaseService;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderInfoController {

    private Logger logger = LoggerFactory.getLogger(OrderInfoController.class);

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private OrderBaseService orderBaseService;

    /**
     * 查询订单接口
     * @param orderInfoVO
     * @return
     * @throws Exception
     */
    @PostMapping("/queryQrCodeInfo")
    public Result<?> queryQrCodeInfo(@RequestBody OrderInfoVO orderInfoVO) {
        if(null == orderInfoVO){
            throw new BusinessException("请求的参数不能为空!");
        }
        if(StringUtils.isEmpty(orderInfoVO.getOrderNo()) && StringUtils.isEmpty(orderInfoVO.getReqStreamId())){
            throw new BusinessException("请求查询的订单id不能为空!");
        }
        logger.info("根据{}查询订单请求参数",orderInfoVO.toString());
        OrderInfoEntity orderQueryEntity = orderInfoService.queryQrCodeInfo(orderInfoVO);
        return ResultObject.successObject(orderQueryEntity, "查询成功");
    }

    /**
     * 查询订单接口
     * @param orderInfoVO
     * @return
     * @throws Exception
     */
    @PostMapping("/queryOrderInfo")
    public Result<?> queryOrderInfo(@RequestBody OrderInfoVO orderInfoVO) {

        logger.info("根据{}查询订单请求参数",orderInfoVO.toString());
        PageBean<OrderInfoEntity> orderQueryEntity = orderInfoService.queryOrderInfo(orderInfoVO);
        if(null == orderQueryEntity){
            throw new BusinessException("根据订单号未查询到订单记录!");
        }
        return ResultObject.successObject(orderQueryEntity, "查询成功");
    }

    @PostMapping("/queryAliPayOrderInfo")
    public Result<?> queryAliPayOrderInfo(@RequestBody OrderInfoVO orderInfoVO){
        if(null == orderInfoVO){
            throw new BusinessException("请求的参数不能为空!");
        }
        if(StringUtils.isBlank(orderInfoVO.getUpOrderNo()) && StringUtils.isBlank(orderInfoVO.getOrderNo())){
            throw new BusinessException("请求的支付宝订单号或平台订单号不能为空!");
        }
        if(StringUtils.isBlank(orderInfoVO.getChargeAddr())){
            throw new BusinessException("请求的收款方账号不能为空!");
        }
        logger.info("支付宝查询订单请求参数:{}",orderInfoVO.toString());
        AlipayTradeQueryResponse tradeQueryResponse = orderInfoService.queryAliPayOrderInfo(orderInfoVO);
        return ResultObject.successObject(tradeQueryResponse,"查询支付宝订单成功");
    }

    @PostMapping("/queryAliPayDataBill")
    public Result<String> queryAliPayDataBill(@RequestBody OrderInfoVO orderInfoVO){
        if(null == orderInfoVO){
            throw new BusinessException("请求的参数不能为空!");
        }
        if(StringUtils.isBlank(orderInfoVO.getEndTime())){
            throw new BusinessException("请求的账单日期不能为空!");
        }
        if(StringUtils.isBlank(orderInfoVO.getChargeAddr())){
            throw new BusinessException("请求的收款方账号不能为空!");
        }
        logger.info("支付宝查询订单请求参数:{}",orderInfoVO.toString());
        String downLoadUrl = orderInfoService.queryAliPayDataBill(orderInfoVO);
        return ResultObject.successObject(downLoadUrl,"查询支付宝对账单成功");
    }

    @PostMapping("/queryChargeList")
    public Result queryChargeList(@RequestBody OrderInfoVO orderInfoVO){
        if(null == orderInfoVO){
            throw new BusinessException("请求的参数不能为空!");
        }
        logger.info("查询即时转账列表请求参数:{}",orderInfoVO.toString());
        PageBean<OrderTrade> chargeList = orderInfoService.getChargeList(orderInfoVO);
        return ResultObject.successObject(chargeList,"查询即时转账列表请求成功");
    }

    @PostMapping("/getOrderInfoByOrderNo")
    public Result<?> getOrderInfoByOrderNo(@RequestBody OrderInfoVO orderInfoVO) {
        if(null == orderInfoVO){
            throw new BusinessException("请求查询的参数为空!");
        }
        if(StringUtils.isBlank(orderInfoVO.getOrderNo()) && StringUtils.isBlank(orderInfoVO.getReqStreamId())){
            throw new BusinessException("请求查询的订单号不能为空!");
        }
        logger.info("根据{}查询订单请求参数",orderInfoVO.toString());
        Map<String,String> resutlMap = orderBaseService.getOrderInfoByOrderNo(orderInfoVO);
        if(resutlMap.isEmpty()){
            throw new BusinessException("根据订单号未查询到订单记录!");
        }
        return ResultObject.successObject(resutlMap, "查询成功");
    }

    @PostMapping("/querySumAmount")
    public Result querySumAmount(@RequestBody OrderInfoVO orderInfoVO){
        if(null == orderInfoVO){
            throw new BusinessException("请求的参数不能为空!");
        }
        if(StringUtils.isBlank(orderInfoVO.getChargeAddr())){
            throw new BusinessException("请求的收款方账号不能为空!");
        }
        logger.info("查询账户收款统计请求参数:{}",orderInfoVO.toString());
        Integer sumAmout = orderInfoService.querySumAmount(orderInfoVO);
        return ResultObject.successObject(sumAmout,"查询账户收款汇总记录成功");
    }




    @RequestMapping(value={"getChargeExportExcel"}, method={RequestMethod.GET})
    public void getChargeExportExcel(String startTime, String endTime, String productType, HttpServletResponse res)
    {
        OutputStream fout = null;
        XSSFWorkbook workbook = new XSSFWorkbook();
        try {
            String fileName = "Charge-" + startTime + ".xlsx";
            res.setContentType("application/vnd.ms-excel");
            res.setHeader("Content-disposition", fileName);
            XSSFSheet sheet = workbook.createSheet("交易汇总信息");
            ExeclUtil.createSumExportHead(sheet);
            List orderList = orderInfoService.getOrderTradeList(productType, startTime, endTime);
            if ((null != orderList) && (orderList.size() >= 1)) {
                ExeclUtil.createSumExportContext(workbook, sheet, orderList);
            }
            fout = res.getOutputStream();
            workbook.write(fout);
        }
        catch (Exception e)
        {
            this.logger.info("查询报表并下载异常,cause={}", e.getMessage());
            throw new BusinessException("查询报表并下载异常!");
        }
        finally
        {
            try {
                if (fout != null)
                {
                    fout.flush();
                    fout.close();
                }
                if (workbook != null)
                {
                    workbook.close();
                }
            }
            catch (IOException e)
            {
                this.logger.info("查询报表并下载异常,IoException={}", e.getMessage());
                throw new BusinessException("查询报表并下载io异常!");
            }
        }
    }

    @RequestMapping(value={"getFinanceExportExcel"}, method={RequestMethod.GET})
    public void getFinanceExportExcel(String startTime, String endTime, HttpServletResponse res)
    {
        OutputStream fout = null;
        XSSFWorkbook workbook = new XSSFWorkbook();
        try {
            String fileName = "Finance-" + startTime + ".xlsx";
            res.setContentType("application/vnd.ms-excel");
            res.setHeader("Content-disposition", fileName);
            XSSFSheet sheet = workbook.createSheet("财务汇总信息");
            ExeclUtil.createFinanceExportHead(sheet);
            List financeList = orderInfoService.getFinanceBillList(startTime, endTime);
            if ((null != financeList) && (financeList.size() >= 1)) {
                ExeclUtil.createFinanceContext(workbook, sheet, financeList);
            }
            fout = res.getOutputStream();
            workbook.write(fout);
        }
        catch (Exception e)
        {
            this.logger.info("查询报表并下载异常,cause={}", e.getMessage());
            throw new BusinessException("查询报表并下载异常!");
        }
        finally
        {
            try {
                if (fout != null)
                {
                    fout.flush();
                    fout.close();
                }
                if (workbook != null)
                {
                    workbook.close();
                }
            }
            catch (IOException e)
            {
                this.logger.info("查询报表并下载异常,IoException={}", e.getMessage());
                throw new BusinessException("查询报表并下载io异常!");
            }
        }
    }
}
