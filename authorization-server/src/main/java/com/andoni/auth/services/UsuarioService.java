package com.andoni.auth.services;

import java.util.Set;

import com.andoni.auth.dto.UsuarioRequest;
import com.andoni.auth.dto.UsuarioResponse;

public interface UsuarioService {

    Set<UsuarioResponse> listar();

    UsuarioResponse registrar(UsuarioRequest request);

    UsuarioResponse eliminar(String username);
}
