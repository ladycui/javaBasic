package validationDemo;

/**
 * @Auther: cyn
 * @Date: 2019-11-28 15:52
 * @Description:
 */
public enum Sex {
    male("男性"),
    female("女性"),
    ;


    private String type;

    Sex(String sex) {
        this.type = sex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
