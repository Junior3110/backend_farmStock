package com.FarmStock_Backend.FarmStock.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.FarmStock_Backend.FarmStock.Model.Usuario;
import com.FarmStock_Backend.FarmStock.Repository.UsuarioRepository;

@Service
public class UsuarioLogica {

    private final UsuarioRepository usuarioRepository;
    
    public UsuarioLogica(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    // Crear usuario
    public Usuario crearUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    // Eliminar usuario (devuelve void o el usuario eliminado)
    public Usuario eliminarUsuario(Integer id ){
        if(usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se encontró el usuario con id: " + id);
        }
        return null;
    }
    
    // Ver todos los usuarios
    public List<Usuario> verUsuarios(){
        return usuarioRepository.findAll();
    }

    // Buscar usuario por ID
    public Usuario buscarUsuario(Integer id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el usuario con id: " + id));
    }

    // Actualizar usuario
    public Usuario actualizarUsuario(Integer id, Usuario usuario){
        Optional<Usuario> usuOptional = usuarioRepository.findById(id);

        if(usuOptional.isPresent()){
            Usuario usuario1 = usuOptional.get();

            usuario1.setNombres(usuario.getNombres());
            usuario1.setApellidos(usuario.getApellidos());
            usuario1.setCorreo(usuario.getCorreo());
            usuario1.setTelefono(usuario.getTelefono());
            usuario1.setNombreFormacion(usuario.getNombreFormacion());
            usuario1.setNumeroFicha(usuario.getNumeroFicha());
            usuario1.setNumeroDocumento(usuario.getNumeroDocumento());
            usuario1.setContrasena(usuario.getContrasena());
            usuario1.setCargo(usuario.getCargo());
            usuario1.setTipoDocumento(usuario.getTipoDocumento());

            return usuarioRepository.save(usuario1);
        } else {
            throw new IllegalArgumentException("No se encontró el usuario con id: " + id);
        }
    }


    public String login(String numeroDocumento, String contrasena){
        Usuario usuario1 = usuarioRepository.findByNumeroDocumento(numeroDocumento)
            .orElseThrow(() -> new IllegalArgumentException("Numero no encontrado"));
        
        if (!usuario1.getContrasena().equals(contrasena)) {
            throw new IllegalArgumentException("Contraseña Incorrecta");
        }

        return "Iniciando sesión";
    }

    


}


