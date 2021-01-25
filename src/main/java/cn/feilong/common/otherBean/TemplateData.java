package cn.feilong.common.otherBean;

//TemplateData 用来定义消息的内容
public class TemplateData {
    private String value;//

    public TemplateData(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}