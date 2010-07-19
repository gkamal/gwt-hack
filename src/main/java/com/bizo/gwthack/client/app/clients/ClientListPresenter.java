package com.bizo.gwthack.client.app.clients;

import java.util.ArrayList;

import org.gwtmpv.model.properties.StringProperty;
import org.gwtmpv.presenter.BasicPresenter;

import com.bizo.gwthack.client.AppRegistry;
import com.bizo.gwthack.client.model.GClientModel;
import com.bizo.gwthack.client.model.GClientModelBinding;
import com.bizo.gwthack.client.model.GClientRepository;
import com.bizo.gwthack.client.widgets.IsCellTable;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ClientListPresenter extends BasicPresenter<IsClientListView> {

  private final AppRegistry registry;
  private final GClientRepository repository;
  private int lastColumn;
  private boolean lastAsc;
  private IsCellTable<GClientModel> table;

  public ClientListPresenter(final AppRegistry registry) {
    super(registry.getAppViews().getClientListView(), registry.getEventBus());
    this.registry = registry;
    repository = registry.getRepository();
  }

  @Override
  protected void onBind() {
    super.onBind();

    GClientModelBinding cb = new GClientModelBinding();
    table = registry.getAppWidgets().newCellTable();
    table.addColumn(BoundColumn.of(cb.id(), new StringPropertyCell()));
    table.addColumn(BoundColumn.of(cb.name(), new StringPropertyCell()));
    table.addColumn(BoundColumn.of(cb.id(), new AbstractCell<StringProperty>() {
      @Override
      public void render(StringProperty value, Object viewData, StringBuilder sb) {
        sb.append("<a href=\"#client;id=" + value.get() + "\">view</a>");
      }
    }));
    view.clientsPanel().add(table);

    repository.getClients(0, 10, new OnClientsCallback());
  }

  private class OnClientsCallback implements AsyncCallback<ArrayList<GClientModel>> {
    public void onSuccess(final ArrayList<GClientModel> clients) {
      table.setData(0, clients.size(), clients);
      revealDisplay();
    }

    @Override
    public void onFailure(final Throwable caught) {
      caught.printStackTrace();
    }
  }

  public void sort(final int column) {
    final boolean asc = column != lastColumn || !lastAsc;
    // view.sort(column, asc);
    lastColumn = column;
    lastAsc = asc;
  }

  IsCellTable<GClientModel> getTable() {
    return table;
  }
}
