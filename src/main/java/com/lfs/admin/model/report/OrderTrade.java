package com.lfs.admin.model.report;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

@Alias("OrderTrade")
public class OrderTrade {
    private String agtName;    //商户名
    private String agtPhone;   //商户账号
    private String chargeAddr;
    private Long succTotal;    // 成功订单总数
    private BigDecimal succTotalPrice;     //成功订单总金额
    private BigDecimal succtotalOutMoney;    //应付下游成功总金额
    private BigDecimal succtotalInMoney;
    private Long failTotal;   // 失败总订单数
    private BigDecimal failTotalPrice;     //失败订单总金额
    private BigDecimal failTotalOutMoney;     //应付下游失败总金额
    private BigDecimal failTotalInMoney;

    private Long sumCount;     // 订单总数
    private BigDecimal profit;
    private BigDecimal profitRate;
    private BigDecimal successRate;

    private String totalType;
    private BigDecimal productProfite;
    private String channelName;


    public BigDecimal getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(BigDecimal successRate) {
        this.successRate = successRate;
    }

    public Long getSumCount() {
        return sumCount;
    }

    public void setSumCount(Long sumCount) {
        this.sumCount = sumCount;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public String getAgtName() {
        return agtName;
    }

    public void setAgtName(String agtName) {
        this.agtName = agtName;
    }

    public String getAgtPhone() {
        return agtPhone;
    }

    public void setAgtPhone(String agtPhone) {
        this.agtPhone = agtPhone;
    }

    public Long getSuccTotal() {
        return succTotal;
    }

    public void setSuccTotal(Long succTotal) {
        this.succTotal = succTotal;
    }

    public BigDecimal getSuccTotalPrice() {
        return succTotalPrice;
    }

    public void setSuccTotalPrice(BigDecimal succTotalPrice) {
        this.succTotalPrice = succTotalPrice;
    }

    public BigDecimal getSucctotalOutMoney() {
        return succtotalOutMoney;
    }

    public void setSucctotalOutMoney(BigDecimal succtotalOutMoney) {
        this.succtotalOutMoney = succtotalOutMoney;
    }

    public BigDecimal getSucctotalInMoney() {
        return succtotalInMoney;
    }

    public void setSucctotalInMoney(BigDecimal succtotalInMoney) {
        this.succtotalInMoney = succtotalInMoney;
    }

    public Long getFailTotal() {
        return failTotal;
    }

    public void setFailTotal(Long failTotal) {
        this.failTotal = failTotal;
    }

    public BigDecimal getFailTotalPrice() {
        return failTotalPrice;
    }

    public void setFailTotalPrice(BigDecimal failTotalPrice) {
        this.failTotalPrice = failTotalPrice;
    }

    public BigDecimal getFailTotalOutMoney() {
        return failTotalOutMoney;
    }

    public void setFailTotalOutMoney(BigDecimal failTotalOutMoney) {
        this.failTotalOutMoney = failTotalOutMoney;
    }

    public BigDecimal getFailTotalInMoney() {
        return failTotalInMoney;
    }

    public void setFailTotalInMoney(BigDecimal failTotalInMoney) {
        this.failTotalInMoney = failTotalInMoney;
    }

    public String getTotalType() {
        return totalType;
    }

    public void setTotalType(String totalType) {
        this.totalType = totalType;
    }

    public String getChargeAddr() {
        return chargeAddr;
    }

    public void setChargeAddr(String chargeAddr) {
        this.chargeAddr = chargeAddr;
    }

    public BigDecimal getProductProfite() {
        return productProfite;
    }

    public void setProductProfite(BigDecimal productProfite) {
        this.productProfite = productProfite;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public String toString() {
        return "OrderTrade{" +
                "agtName='" + agtName + '\'' +
                ", agtPhone='" + agtPhone + '\'' +
                ", chargeAddr='" + chargeAddr + '\'' +
                ", succTotal=" + succTotal +
                ", succTotalPrice=" + succTotalPrice +
                ", succtotalOutMoney=" + succtotalOutMoney +
                ", succtotalInMoney=" + succtotalInMoney +
                ", failTotal=" + failTotal +
                ", failTotalPrice=" + failTotalPrice +
                ", failTotalOutMoney=" + failTotalOutMoney +
                ", failTotalInMoney=" + failTotalInMoney +
                ", sumCount=" + sumCount +
                ", profit=" + profit +
                ", profitRate=" + profitRate +
                ", successRate=" + successRate +
                ", totalType='" + totalType + '\'' +
                ", productProfite=" + productProfite +
                ", channelName='" + channelName + '\'' +
                '}';
    }
}
