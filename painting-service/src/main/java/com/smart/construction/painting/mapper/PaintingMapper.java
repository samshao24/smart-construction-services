package com.smart.construction.painting.mapper;

import com.smart.construction.painting.entity.ProjectEntity;
import com.smart.construction.painting.entity.RoomEntity;
import com.smart.construction.painting.model.Project;
import com.smart.construction.painting.model.Room;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PaintingMapper {

    @Autowired
    private MapperFactory factory;

    private MapperFacade facade;

    @PostConstruct
    public void init() {

        registerCustomMaps();
        facade = factory.getMapperFacade();
    }

    private void registerCustomMaps() {
        factory.classMap(ProjectEntity.class, Project.class)
                .field("type.type", "type")
                .field("status.status", "status")
                .byDefault()
                .register();

        factory.classMap(RoomEntity.class, Room.class)
                .byDefault()
                .register();
    }

    public <T> T map(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }

        return facade.map(source, targetClass);
    }

    public MapperFacade getMapper() {
        return factory.getMapperFacade();
    }

    public MapperFactory getFactory() {
        return factory;
    }

    public void setFactory(MapperFactory factory) {
        this.factory = factory;
    }
}
