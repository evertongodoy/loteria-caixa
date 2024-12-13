package com.everton.loterias.entrypoint.api.controller;

import com.everton.loterias.core.domain.Cliente;
import com.everton.loterias.core.domain.Clientes;
import com.everton.loterias.core.usecase.cliente.ClienteUseCase;
import com.everton.loterias.entrypoint.api.dto.ClienteDTO;
import com.everton.loterias.entrypoint.api.dto.ClientesDTO;
import com.everton.loterias.entrypoint.api.mapper.ClienteMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteUseCase clienteUseCase;

    public ClienteController(final ClienteUseCase clienteUseCase){
        this.clienteUseCase = clienteUseCase;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getCliente(@PathVariable String id) {
        var cliente = clienteUseCase.getClienteById(id);
        return ResponseEntity.ok(ClienteMapper.INSTANCE.toClienteDTO(cliente));
    }

    @GetMapping("/web")
    public ResponseEntity<ClientesDTO> getClientesWeb() {
        Clientes clientes = clienteUseCase.getClientesFromWeb();

        List<ClienteDTO> clientesDto = clientes.getClientes().stream()
                .map(ClienteMapper.INSTANCE::toClienteDTO)
                .toList();

        var clientesDTO = new ClientesDTO();
        clientesDTO.setClientesDTO(clientesDto);
        return ResponseEntity.ok(clientesDTO);
    }


    @PostMapping("/criar")
    public ResponseEntity<Void> criarCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = ClienteMapper.INSTANCE.toCliente(clienteDTO);
        clienteUseCase.criarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/todos-db")
    public ResponseEntity<ClientesDTO> getClientesDb() {
        Clientes clientes = clienteUseCase.getTodosClientesDB();

        List<ClienteDTO> clientesDto = clientes.getClientes().stream()
                .map(ClienteMapper.INSTANCE::toClienteDTO)
                .toList();

        var clientesDTO = new ClientesDTO();
        clientesDTO.setClientesDTO(clientesDto);
        return ResponseEntity.ok(clientesDTO);
    }

}