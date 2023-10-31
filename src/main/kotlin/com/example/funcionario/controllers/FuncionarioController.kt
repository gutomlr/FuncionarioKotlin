package com.example.funcionario.controllers

import com.example.funcionario.dtos.FuncionarioDTO
import com.example.funcionario.dtos.FuncionarioResponseDTO
import com.example.funcionario.model.Funcionario
import com.example.funcionario.service.FuncionarioService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriBuilder
import org.springframework.web.util.UriComponentsBuilder
import org.springframework.data.domain.Pageable

@RestController
@RequestMapping("/funcionarios")
class FuncionarioController (val service: FuncionarioService) {
    @GetMapping
    fun listar(@RequestParam(required = false) nomeFuncionario: String?, paginacao: Pageable): List<FuncionarioResponseDTO> {
        return service.listar(nomeFuncionario, paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): FuncionarioResponseDTO {
        return service.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastra(@RequestBody @Valid dto: FuncionarioDTO,
                 uriBuilder: UriComponentsBuilder):
    ResponseEntity<FuncionarioResponseDTO> {
        val funcionarioResponse = service.cadastrar(dto)
        val uri = uriBuilder.path("/funcionarios/${funcionarioResponse.id}")
            .build().toUri()
        return ResponseEntity.created(uri).body(funcionarioResponse)
    }

    @PutMapping("/{id}")
    @Transactional
    fun atualizar(@PathVariable id: Long, @RequestBody @Valid dto: FuncionarioDTO): FuncionarioResponseDTO {
        return service.atualizar(id, dto)
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}