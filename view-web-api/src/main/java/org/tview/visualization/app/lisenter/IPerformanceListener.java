package org.tview.visualization.app.lisenter;

import org.tview.visualization.model.label.AbsConfig;

public interface IPerformanceListener {

  void createWork(String name, ConfigInterface absConfig);


  Object get(String name);


  void remove(String name);


}
