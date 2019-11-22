package validationDemo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Auther: cyn
 * @Date: 2019-11-05 11:01
 * @Description:
 */
@Data
public class User2 {

    @NotNull(groups = {GroupA.class}, message = "id is null")
    Integer id;

    @NotNull(message = "name is null")
    String name;

    @NotNull @Valid
    Person leader;
}
