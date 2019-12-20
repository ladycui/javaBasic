package routineProblemsNote.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Auther: cyn
 * @Date: 2019-06-03 15:02
 * @Description:
 */
@Data
public class Origin {
    @NotEmpty
    String type;

    String host;
    String bucketName;
    String protocol;
    List<Source> source;
}
