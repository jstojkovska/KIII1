package com.example.vezba1.listeners;

import com.example.vezba1.events.HostCreatedEvent;
import com.example.vezba1.service.domain.HostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandler {
    private final HostService hostService;

    public HostEventHandler(HostService hostService) {
        this.hostService = hostService;
    }

    @EventListener
    public void onHostCreated(HostCreatedEvent h) {
        hostService.refreshMaterialized2();
    }
}
