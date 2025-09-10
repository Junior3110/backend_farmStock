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

    public Usuario crearUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario eliminarUsuario(Integer id ){
        if(usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
        }else{
            throw new IllegalArgumentException("No se encontro el usuario");
        }
        return null;

    }
    
    public List<Usuario> verUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuario(Integer id){
        return usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no se encontro al usuario"));
    }

    public Usuario actualizarUsuario(Integer id, Usuario usuario){
    
        Optional<Usuario> usuOptional = usuarioRepository.findById(id);
        if(usuOptional.isPresent()){
            Usuario usuario1 = usuOptional.get();
            usuario1.setNombres(usuario.getNombres());
            usuario1.setApellidos(usuario.getApellidos());
            usuario1.setCorreo(usuario.getCorreo());
            usuario1.setTelefono(usuario.getTelefono());
            usuario1.setNombre_formacion(usuario.getNombre_formacion());
            usuario1.setNumero_ficha(usuario.getNumero_ficha());
            usuario1.setNumero_documento(usuario.getNumero_documento());
            usuario1.setContrasena(usuario.getContrasena());
            usuario1.setCargo(usuario.getCargo());
            usuario1.setTipoDocumento(usuario.getTipoDocumento());
            return usuarioRepository.save(usuario);
            
        }else{
            return null;
        }

    }




}
