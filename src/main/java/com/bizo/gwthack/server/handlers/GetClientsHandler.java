package com.bizo.gwthack.server.handlers;

import java.util.ArrayList;
import java.util.List;

import com.bizo.gwthack.client.messages.GetClientsAction;
import com.bizo.gwthack.client.messages.GetClientsResult;
import com.bizo.gwthack.server.data.Clients;
import com.bizo.gwthack.shared.model.GClientDto;

public class GetClientsHandler extends AbstractHandler<GetClientsAction, GetClientsResult> {

  @Override
  public GetClientsResult execute(final GetClientsAction arg0) {
    final List<GClientDto> clients = new ArrayList<GClientDto>();
    for (int i = arg0.getStart(); i < arg0.getEnd(); i++) {
      clients.add(Clients.get().get(i));
    }
    return new GetClientsResult(clients);
  }

  @Override
  public Class<GetClientsAction> getActionType() {
    return GetClientsAction.class;
  }

}
