package validationDemo;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: cyn
 * @Date: 2019-11-05 15:44
 * @Description:
 */
@Data
@NoArgsConstructor
public class Result {
    int code = 200;
    String msg = "success";

}
