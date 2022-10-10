package cm.pak.data;

import java.io.Serializable;
import java.util.Objects;

public class FilterData implements Serializable {
    private String field;
    private Object value;
    private String operator;

    public FilterData() {
    }

    public FilterData(String field, Object value, String operator) {
        this.field = field;
        this.value = value;
        this.operator = operator;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterData that = (FilterData) o;
        return field.equals(that.field) && value.equals(that.value) && operator.equals(that.operator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, value, operator);
    }

    @Override
    public String toString() {
        return "FilterData{" +
                "field='" + field + '\'' +
                ", value=" + value +
                ", operator='" + operator + '\'' +
                '}';
    }
}
