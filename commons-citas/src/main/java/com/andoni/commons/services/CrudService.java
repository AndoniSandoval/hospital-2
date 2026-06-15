package com.andoni.commons.services;

import java.util.List;

public interface CrudService <RQ, RS>{

    List<RS> listar();

    RS obtenerPorId(Long id);
    RS registar (RQ request);
    RS actualizar (RQ request, Long id);
    void eliminar(Long id);
}
