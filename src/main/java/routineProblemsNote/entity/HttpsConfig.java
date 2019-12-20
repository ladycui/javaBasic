package routineProblemsNote.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname HttpsVO
 * @Description TODO
 * @Date 2019/6/13 18:03
 * @Created by micy
 */
@Data
@NoArgsConstructor
public class HttpsConfig {

    private String status;

    private String name;

    private String content;

    private String key;

    private String forceHttps;

}
