package com.FarmStock_Backend.FarmStock.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FarmStock_Backend.FarmStock.Model.Usuario;
import com.FarmStock_Backend.FarmStock.Service.UsuarioLogica;


@CrossOrigin(origins="*")
@RestController

@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioLogica Service;

    public UsuarioController(UsuarioLogica Service){
        this.Service = Service;
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        return Service.crearUsuario(usuario);
    }

    
}
