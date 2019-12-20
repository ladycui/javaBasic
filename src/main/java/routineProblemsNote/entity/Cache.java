package routineProblemsNote.entity;

import lombok.Data;

/**
 * @Auther: cyn
 * @Date: 2019-06-05 15:31
 * @Description:
 */
@Data
public class Cache {
    String type;
    String rule;
    int expired;
    Boolean followOrigin;
}
