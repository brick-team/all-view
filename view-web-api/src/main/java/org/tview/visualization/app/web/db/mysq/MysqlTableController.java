package org.tview.visualization.app.web.db.mysq;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.model.res.ResultVO;

/**
 * mysql 表操作
 */
@RestController
@RequestMapping("/mysql/table")
public class MysqlTableController {

  @GetMapping("/data_type")
  public ResultVO dataType() {
    return null;
  }
}
