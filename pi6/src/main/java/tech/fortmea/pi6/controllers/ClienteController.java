package tech.fortmea.pi6.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.fortmea.pi6.model.Cliente;
@RestController
@RequestMapping("/cliente")
public class ClienteController {
    List<Cliente> listaClientes = new ArrayList<Cliente>();
    @GetMapping("/mensagem")
    public String cliente(){
        return "Bem vindo";
    }
    @GetMapping("/")
    public String inicio(){
        return "caraca";
    }

    @PostMapping("/incluir")
    public ResponseEntity<String> incluirCliente(@RequestBody Cliente cli){
        System.out.println("Nome: "+ cli.getNome());
        listaClientes.add(cli);
        return ResponseEntity.ok("Cliente recebido com sucesso: " + cli.getNome());
    }
    @GetMapping("/listar")
    public List<Cliente> listarClientes(){
        return listaClientes;
    }
    @GetMapping("/id/{id}")
    public Cliente retornaCliente(@PathVariable int id ){
        Cliente _cliente = new Cliente();
        for(Cliente c: listaClientes){
            if(c.getId() == id){
                _cliente = c;
            }
        }
        return _cliente;
    }
    @GetMapping("/{any}")
    public String retorna(@PathVariable String any){
        return any;
    }

}


