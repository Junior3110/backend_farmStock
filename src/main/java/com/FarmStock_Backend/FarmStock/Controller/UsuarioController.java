package com.FarmStock_Backend.FarmStock.Controller;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FarmStock_Backend.FarmStock.Model.Usuario;
import com.FarmStock_Backend.FarmStock.Service.UsuarioLogica;

import jakarta.validation.Valid;



@CrossOrigin(origins="*")
@RestController

@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioLogica Service;

    public UsuarioController(UsuarioLogica Service){
        this.Service = Service;
    }

    @PostMapping // esta bien 
    public Usuario crearUsuario(@Valid @RequestBody Usuario usuario){
        return Service.crearUsuario(usuario);
    }

    @GetMapping // esta bien
    public List<Usuario> lista(){
        return Service.verUsuarios();
    }
    
    @DeleteMapping("/{id}") // esta bien
    public Usuario eliminarUsuario(@PathVariable int id){
        return Service.eliminarUsuario(id);
    }
}

