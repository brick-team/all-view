package org.tview.visualization.app.web.zk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.zookeeper.common.X509Exception.SSLContextException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.inter.zk.ZkNodeOperation;
import org.tview.visualization.inter.zk.ZookeeperStateService;
import org.tview.visualization.model.res.ResultVO;
import org.tview.visualization.model.res.VueTableEntity;
import org.tview.visualization.model.zk.ZkNodeCreateParam;
import org.tview.visualization.zookeeper.ZookeeperNodeOperationImpl;
import org.tview.visualization.zookeeper.ZookeeperStateServiceImpl;

@RestController
@RequestMapping("/zk/node")
public class ZkController {
  ZkNodeOperation zkNodeOperation = new ZookeeperNodeOperationImpl();
  ZookeeperStateService zookeeperStateService = new ZookeeperStateServiceImpl();

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

  @GetMapping("/state")
  public ResultVO state(@RequestParam String ip, @RequestParam Integer port) throws Exception {
    return new ResultVO("ok", zookeeperStateService.srvr(ip, port), 200);
  }

  /**
   * 列表
   *
   * @return
   * @throws IOException
   * @throws SSLContextException
   */
  @PostMapping("/mntr")
  public ResultVO mntr() throws Exception {
    List<String> hosts = new ArrayList<>();
    hosts.add("localhost");
    hosts.add("127.0.0.1");
    hosts.add("127.0.0.1");
    hosts.add("127.0.0.1");
    return new ResultVO("ok", clusterInfo(hosts), 200);
  }

  /** 单个节点的配置 */
  @PostMapping("conf")
  public ResultVO conf(@RequestParam String ip, @RequestParam Integer port) throws Exception {
    return new ResultVO("ok", zookeeperStateService.conf(ip, port), 200);
  }

  /** 单个节点的环境 */
  @PostMapping("envi")
  public ResultVO envi(@RequestParam String ip, @RequestParam Integer port) throws Exception {

    return new ResultVO("ok", zookeeperStateService.envi(ip, port), 200);
  }

  private VueTableEntity clusterInfo(List<String> hosts) throws Exception {
    List<Map<String, String>> query = new ArrayList<>();
    List<String> head = new ArrayList<>();
    head.add("配置");

    for (String host : hosts) {
      Map<String, String> localhost1 = zookeeperStateService.mntr(host, 2181);
      query.add(localhost1);
      head.add(host);
    }

    Map<String, List<String>> body = new HashMap<>();
    Map<String, String> map = query.get(0);
    for (String s : map.keySet()) {
      List<String> one = new ArrayList<>();
      one.add(s);
      body.put(s, one);
    }

    for (Map<String, String> stringStringMap : query) {
      stringStringMap.forEach(
          (k, v) -> {
            boolean b = body.containsKey(k);
            if (b) {
              body.get(k).add(v);
            }
          });
    }

    Collection<List<String>> values = body.values();

    List<List<String>> res = new ArrayList<>();

    for (List<String> value : values) {
      res.add(value);
    }
    return new VueTableEntity(head, res);
  }
}
