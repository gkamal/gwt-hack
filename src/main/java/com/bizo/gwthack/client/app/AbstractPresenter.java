package com.bizo.gwthack.client.app;

import org.gwtmpv.dispatch.client.DispatchAsync;
import org.gwtmpv.presenter.BasicPresenter;
import org.gwtmpv.widgets.IsWidget;

import com.bizo.gwthack.client.AppRegistry;
import com.bizo.gwthack.client.model.GClientRepository;

public abstract class AbstractPresenter<D extends IsWidget> extends BasicPresenter<D> {

  protected final AppRegistry registry;
  protected final DispatchAsync async;
  protected final GClientRepository repository;

  public AbstractPresenter(final D display, final AppRegistry registry) {
    super(display, registry.getEventBus());
    this.registry = registry;
    async = registry.getAsync();
    repository = registry.getRepository();
  }

}
