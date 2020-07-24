package org.tview.visualization.app.web;

import java.io.IOException;
import javax.management.MalformedObjectNameException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.app.monitor.MonitorUtils;
import org.tview.visualization.model.res.ResultVO;

@RestController
@RequestMapping("/jvm/monitor")
public class JvmMonitorController {
    @GetMapping("/gcInfoEntities")
    public ResultVO gcInfoEntities() throws IOException, MalformedObjectNameException {
        return new ResultVO("ok", MonitorUtils.gcInfoEntities(), 200);
    }

    @GetMapping("/findCpuInfo")
    public ResultVO findCpuInfo() throws IOException {
        return new ResultVO("ok", MonitorUtils.findCpuInfo(), 200);
    }

    @GetMapping("/deadlockCheck")
    public ResultVO deadlockCheck() throws IOException {
        return new ResultVO("ok", MonitorUtils.deadlockCheck(), 200);
    }

    @GetMapping("/javaThreadEntity")
    public ResultVO javaThreadEntity() throws IOException {

        return new ResultVO("ok", MonitorUtils.javaThreadEntity(), 200);
    }

    @GetMapping("/jvmInfo")
    public ResultVO jvmInfo() throws IOException {
        return new ResultVO("ok", MonitorUtils.jvmInfo(), 200);
    }
}
