package com.bc.admin.controller;

import com.bc.base.dto.Result;
import com.bc.base.dto.ResultObject;
import com.bc.base.exception.BusinessException;
import com.bc.dao.entity.PageBean;
import com.bc.interfaces.log.service.LogFileService;
import com.bc.interfaces.model.LogFileEntity;
import com.bc.interfaces.model.vo.LogFileVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logInfo")
public class LogInfoController {

    private Logger logger = LoggerFactory.getLogger(LogInfoController.class);

    @Autowired
    private LogFileService logFileService;

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

}
