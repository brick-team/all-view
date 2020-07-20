package org.tview.visualization.app.listener;

import org.tview.visualization.model.label.AbsConfig;

public interface ConfigInterface<T extends AbsConfig> {

  T get();
}
