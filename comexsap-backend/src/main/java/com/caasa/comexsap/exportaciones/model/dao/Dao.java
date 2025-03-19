package com.caasa.comexsap.exportaciones.model.dao;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.exception.NotFoundException;

public interface Dao<T> {
    
    int generateId() ;
    
    T get(Integer id) throws NotFoundException ;
    
    List<T> getAll();
    
    T save(T entidad, String usuario);
    
    T update(T entidad, String usuario) throws NotFoundException ;
    
    void delete(T entidad, String usuario) throws NotFoundException ;
    
}
