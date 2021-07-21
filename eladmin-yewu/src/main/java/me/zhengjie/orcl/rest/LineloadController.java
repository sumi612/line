package me.zhengjie.orcl.rest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.AnonymousAccess;
import me.zhengjie.annotation.Log;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.orcl.service.LineloadService;
import me.zhengjie.utils.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Api(tags = "线路负载率")
@RequestMapping("/api/line")
public class LineloadController {
    private final LineloadService lineloadService;


//    @AnonymousAccess
//    @RequestMapping("/test")
//    public String test(){
//        return "123";
//    }
    @Log("线路负载率")
    @ApiOperation("线路负载率")
    @RequestMapping("/queryload")
    public JSONArray queryBdzTq(String ycid){
        JSONArray resultJsonArr = new JSONArray();  //返回的数组对象
        JSONObject resultJson = null;
        if(StringUtils.isNotBlank(ycid)) {
            List<Map<String,String>> resultSet = lineloadService.queryall(ycid);
            if(resultSet.size() != 0 && resultSet != null){
                for(int i=0; i<resultSet.size(); i++){
                    resultJson = new JSONObject();
                    Map<String, String> resultMap = resultSet.get(i);
                    resultJson.put("YC_ID", resultMap.get("YC_ID"));
                    resultJson.put("FAC_ID", resultMap.get("FAC_ID"));
                    resultJson.put("HISTORY_TABLE_NAME", resultMap.get("HISTORY_TABLE_NAME"));
                    resultJson.put("HISTORY_COLUMN_NAME", resultMap.get("HISTORY_COLUMN_NAME"));
                    resultJson.put("SAMPLE_INTERVAL", resultMap.get("SAMPLE_INTERVAL"));
                    resultJsonArr.add(resultJson);
                }
            }else {
                throw new RuntimeException("取数为空！");
            }
        }else {
            throw new BadRequestException("参数不能为空！");
        }
        return resultJsonArr;
    }
}
