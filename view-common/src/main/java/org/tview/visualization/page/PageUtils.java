package org.tview.visualization.page;

import org.tview.visualization.model.req.PageVO;

public class PageUtils {
    private PageUtils() {
    }

    /**
     * 计算分页的参数
     *
     * @param pageVO
     *
     * @return
     */
    public static PageVO calc(PageVO pageVO) {
        if (pageVO == null) {
            pageVO = new PageVO();
        }
        pageVO.setNum((pageVO.getNum() - 1) * pageVO.getSize());
        pageVO.setSize(pageVO.getSize());
        return pageVO;
    }
}
