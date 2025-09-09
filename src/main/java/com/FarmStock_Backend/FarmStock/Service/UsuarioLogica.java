package com.FarmStock_Backend.FarmStock.Service;
import org.springframework.stereotype.Service;

import com.FarmStock_Backend.FarmStock.Model.Usuario;
import com.FarmStock_Backend.FarmStock.Repository.UsuarioRepository;

@Service
public class UsuarioLogica {
    private final UsuarioRepository usuarioRepository;
    
    public UsuarioLogica(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    
}
