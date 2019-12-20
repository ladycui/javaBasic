package routineProblemsNote.entity;

import lombok.Data;

import java.util.List;

/**
* @program: anubis-adapter-qiniu
*
* @description: 
*
* @author: wangchao535
*
* @create: 2019-08-26 09:46
**/
@Data
public class IpAcl {
    private String status;
    private String type;
    private List<String> values;
}