package org.tview.visualization.app.web.zk;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.inter.zk.ZkNodeOperation;
import org.tview.visualization.model.res.ResultVO;
import org.tview.visualization.model.zk.ZkNodeCreateParam;
import org.tview.visualization.zookeeper.ZookeeperNodeOperationImpl;

@RestController
@RequestMapping("/zk/node")
public class ZkController {
  ZkNodeOperation zkNodeOperation = new ZookeeperNodeOperationImpl();

  /**
   * 创建节点
   *
   * @param createParam 创建参数
   * @param hostPort zk地址
   * @return true or false
   */
  @PostMapping("/create")
  public ResultVO create(@RequestBody ZkNodeCreateParam createParam, @RequestBody String hostPort) {
    return new ResultVO("ok", zkNodeOperation.createNode(createParam, hostPort), 200);
  }

  /**
   * 获取节点信息
   *
   * @param path 节点地址
   * @param hostPort zk 地址
   * @return 节点信息
   * @throws Exception
   */
  @GetMapping("/get/info")
  public ResultVO info(
      @RequestParam(value = "path") String path, @RequestParam(value = "host_port") String hostPort)
      throws Exception {
    return new ResultVO("ok", zkNodeOperation.getNode(path, hostPort), 200);
  }

  /**
   * 获取 zk 树节点
   *
   * @param hostPort zk 地址
   * @return zk 树结构
   * @throws Exception
   */
  @GetMapping("/tree")
  public ResultVO tree(@RequestParam(value = "host_port") String hostPort) throws Exception {
    return new ResultVO("ok", zkNodeOperation.tree(hostPort), 200);
  }

  /**
   * @param path
   * @param hostPort
   * @return
   */
  @GetMapping("/del")
  public ResultVO del(
      @RequestParam(value = "path") String path,
      @RequestParam(value = "host_port") String hostPort) {
    return new ResultVO("ok", zkNodeOperation.del(path, hostPort), 200);
  }
}
