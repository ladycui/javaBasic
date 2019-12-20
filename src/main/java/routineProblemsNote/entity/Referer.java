package routineProblemsNote.entity;

import lombok.Data;

import java.util.List;

/**
 * @Auther: cyn
 * @Date: 2019-06-10 19:42
 * @Description:
 */
@Data
public class Referer {
    String allowEmpty;
    String type;
    List<String> values;
}
