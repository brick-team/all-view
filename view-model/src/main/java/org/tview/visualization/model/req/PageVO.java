package org.tview.visualization.model.req;

public class PageVO {
    public static final int DEFAULT_SIZE = 20;

    public static final int DEFAULT_NO = 1;

    private Integer num;
    private Integer size;

    public PageVO() {
        this(null, null);
    }

    public PageVO(Integer num, Integer size) {
        if (size != null && size > 0) {
            this.size = size;
        } else {
            this.size = DEFAULT_SIZE;
        }
        if (num != null && num > 0) {
            this.num = num;
        } else {
            this.num = DEFAULT_NO;
        }
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
