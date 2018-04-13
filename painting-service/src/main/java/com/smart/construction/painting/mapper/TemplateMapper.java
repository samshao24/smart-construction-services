package com.smart.construction.painting.mapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TemplateMapper {

    @Autowired
    private MapperFactory factory;

    private MapperFacade facade;

    @PostConstruct
    public void init() {

        registerCustomMaps();
        facade = factory.getMapperFacade();
    }

    private void registerCustomMaps() {

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
