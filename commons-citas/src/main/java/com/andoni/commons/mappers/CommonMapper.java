package com.andoni.commons.mappers;
public interface CommonMapper <RQ,RS,E>{

    E requestAEntidad(RQ request);
    RS entidadAResponse (E request);
}
