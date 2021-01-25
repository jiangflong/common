package cn.feilong.common.otherBean;

import lombok.Getter;
import lombok.Setter;

/**
 *  mybatis where 查询条件
 */
@Setter
@Getter
public class Clause {
    private String column;
    private String operator;
    private Object value;

    public Clause(String column, String operator, Object value) {
        this.column = column;
        this.operator = operator;
        this.value = value;
    }


    public static Builder Builder() {
        return new Builder();
    }
    public static final class Builder {
        private String column;
        private String operator;
        private Object value;

        private Builder() {
        }

        public Builder column(String column) {
            this.column = column;
            return this;
        }

        public Builder operator(String operator) {
            this.operator = operator;
            return this;
        }

        public Builder value(Object value) {
            this.value = value;
            return this;
        }

        public Clause build() {
            if(this.operator == null){
                this.operator = "=";
            }
            return new Clause(column, operator,value);
        }
    }
}
