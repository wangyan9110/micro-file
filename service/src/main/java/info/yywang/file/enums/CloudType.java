package info.yywang.file.enums;

/**
 * @author yanyan.wang
 * @date 2016-09-08 17:13
 */
public enum CloudType {

    Local(1),

    Upyun(2);

    /**
     * 文件类型
     */
    private int value;

    CloudType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
