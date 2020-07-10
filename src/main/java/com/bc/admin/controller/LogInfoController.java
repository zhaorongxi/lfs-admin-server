package com.bc.admin.controller;

import com.bc.admin.model.entity.PointLogEntity;
import com.bc.admin.model.vo.PointLogVO;
import com.bc.base.dto.Result;
import com.bc.base.dto.ResultObject;
import com.bc.base.exception.BusinessException;
import com.bc.base.util.StringUtils;
import com.bc.dao.entity.PageBean;
import com.bc.interfaces.log.service.LogFileService;
import com.bc.interfaces.model.LogFileEntity;
import com.bc.interfaces.model.vo.LogFileVO;
import com.bc.mongo.MongoBase;
import com.bc.mongo.entity.Page;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logInfo")
public class LogInfoController {

    private Logger logger = LoggerFactory.getLogger(LogInfoController.class);

    @Autowired
    private LogFileService logFileService;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询操作日志
     * @param logFile
     * @return
     * @throws Exception
     */
    @PostMapping("/queryLogFileList")
    public Result<?> queryLogFileList(@RequestBody LogFileVO logFile) {

        if(null == logFile){
            throw new BusinessException("请求客户列表参数不能为空!");
        }

        logger.info("根据{},查询客户列表请求参数", logFile.toString());
        PageBean<LogFileEntity> logList = logFileService.getLogFileList(logFile);
        return ResultObject.successObject(logList, "查询列表成功");
    }

    /**
     * 查询操作日志
     * @param pointLogVO
     * @return
     * @throws Exception
     */
    @PostMapping("/queryPointLog")
    public Result<?> queryPointLog(@RequestBody PointLogVO pointLogVO) throws Exception {

        if(null == pointLogVO){
            throw new BusinessException("请求客户列表参数不能为空!");
        }
        Map<String,Object> map = new HashMap<>();
        BeanUtils.populate(pointLogVO,map);
        Page page = new Page();
        page.setPageNumber(pointLogVO.getCurrentPage());
        page.setPageSize(pointLogVO.getPageSize());
        page = MongoBase.getMongo(PointLogEntity.class).findPage(page,map);
        logger.info("根据{},查询客户列表请求参数", pointLogVO.toString());
        return ResultObject.successObject(page, "查询列表成功");
    }

    /**
     * 查询埋点操作日志
     * @param pointLogVO
     * @return
     * @throws Exception
     */
    @PostMapping("/queryCountPointLog")
    public Result<?> queryCountPointLog(@RequestBody PointLogVO pointLogVO) throws Exception {

        if(null == pointLogVO) {
            throw new BusinessException("请求埋点列表参数不能为空!");
        }
        Criteria criteria = new Criteria();
        if(StringUtils.isNotBlank(pointLogVO.getStartTime())){
            criteria.and("time").gte(pointLogVO.getStartTime())
                    .lte(pointLogVO.getEndTime());
        }
        if(StringUtils.isNotBlank(pointLogVO.getDevice())){
            criteria.and("device").is(pointLogVO.getDevice());
        }
        if(StringUtils.isNotBlank(pointLogVO.getAgtPhone())){
            criteria.and("agtPhone").is(pointLogVO.getAgtPhone());
        }
        if(StringUtils.isNotBlank(pointLogVO.getOperate())){
            criteria.and("operate").is(pointLogVO.getOperate());
        }
        if(StringUtils.isNotBlank(pointLogVO.getMachine())){
            criteria.and("machine").is(pointLogVO.getMachine());
        }
        if(StringUtils.isNotBlank(pointLogVO.getPage())){
            criteria.and("page").is(pointLogVO.getPage());
        }

        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(criteria), Aggregation.group("$agtPhone","$machine","$page","$operate").count().as("count"));
        AggregationResults results = mongoTemplate.aggregate(aggregation, "point_log_collection", PointLogEntity.class);

//        logger.info(mapGroupByResults.toString());
        logger.info("根据{},查询埋点列表请求参数", pointLogVO.toString());
        return ResultObject.successObject((List<T>) results.getRawResults().get("result"), "查询列表成功");
    }

    /**
     * 新增埋点操作日志
     * @param pointLogEntity
     * @return
     * @throws Exception
     */
    @PostMapping("/addPointLog")
    public Result<?> addPointLog(@RequestBody PointLogEntity pointLogEntity) {
        if(null == pointLogEntity){
            throw  new BusinessException("新增埋点日志参数为空!");
        }
        MongoBase.getMongo(PointLogEntity.class).save(pointLogEntity);
        return ResultObject.successMessage("新增埋点日志成功!");
    }

}
