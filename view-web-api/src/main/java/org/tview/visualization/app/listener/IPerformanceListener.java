package org.tview.visualization.app.listener;

import org.tview.visualization.model.enums.PerformanceEnums;

public interface IPerformanceListener {

  void createWork(String name, ConfigInterface absConfig, IListenerWork work,
      PerformanceEnums performanceEnums);


  Object get(String name, PerformanceEnums performanceEnums);


  void remove(String name);


}
