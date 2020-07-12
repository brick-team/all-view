package org.tview.visualization.app.web.db;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.inter.db.mysq.MysqlCharSetOperation;
import org.tview.visualization.model.res.ResultVO;
import org.tview.visualization.mysql.impl.MysqlCharSetOperationImpl;

@RestController
@RequestMapping("/mysql")
public class CharsetController {

  MysqlCharSetOperation mysqlCharSetOperation = new MysqlCharSetOperationImpl();

  @GetMapping("/char_set_list")
  public ResultVO charSetList() {
    return new ResultVO("ok", mysqlCharSetOperation.charSetList(), 200);
  }

  @GetMapping("/default_collection")
  public ResultVO defaultCollection(@RequestParam String charSet) {
    return new ResultVO("ok", mysqlCharSetOperation.defaultCollection(charSet), 200);
  }

  @GetMapping("/charset/collections")
  public ResultVO collections(@RequestParam String charSet) {
    return new ResultVO("ok", mysqlCharSetOperation.collections(charSet), 200);
  }

  @GetMapping("/collections")
  public ResultVO collections() {
    return new ResultVO("ok", mysqlCharSetOperation.collections(), 200);
  }
}
