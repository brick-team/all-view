package org.tview.visualization.app.web.db;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.model.res.ResultVO;

@RestController
@RequestMapping("/table")
public class TableController {
  @GetMapping("/data_type")
  public ResultVO dataType() {
    return null;
  }
}
