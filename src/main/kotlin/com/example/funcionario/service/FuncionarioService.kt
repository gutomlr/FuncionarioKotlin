package com.example.funcionario.service

import com.example.funcionario.converters.FuncionarioConverter
import com.example.funcionario.dtos.FuncionarioDTO
import com.example.funcionario.dtos.FuncionarioResponseDTO
import com.example.funcionario.exceptions.NotFoundException
import com.example.funcionario.model.Funcionario
import com.example.funcionario.repository.FuncionarioRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

private const val FUNCIONARIO_NOT_FOUND_MESSAGE = "Funcionário não encontrado!"


@Service
class FuncionarioService (
    private val repository: FuncionarioRepository,
    private val converter: FuncionarioConverter) {

    fun listar(nomeFuncionario: String?, paginacao: Pageable): List<FuncionarioResponseDTO> {
        val funcionarios = if (nomeFuncionario == null) {
            repository.findAll() }
            else {
                repository.findByNome(nomeFuncionario, paginacao)
        }
            return funcionarios.map(converter::toFuncionarioResponseDTO)
    }

    fun buscarPorId(id: Long): FuncionarioResponseDTO {
        val funcionario = repository.findById(id)
            .orElseThrow { NotFoundException(FUNCIONARIO_NOT_FOUND_MESSAGE)}
        return converter.toFuncionarioResponseDTO(funcionario)
    }

    fun cadastrar(dto: FuncionarioDTO): FuncionarioResponseDTO {
        return converter.toFuncionarioResponseDTO(
            repository.save(converter.toFuncionario(dto)))
    }

    fun atualizar(id: Long, dto: FuncionarioDTO): FuncionarioResponseDTO {
        val funcionario = repository.findById(id)
            .orElseThrow {NotFoundException(FUNCIONARIO_NOT_FOUND_MESSAGE)}
            .copy(
                nome = dto.nome,
                cargo = dto.cargo,
                status = dto.status
            )
        return converter.toFuncionarioResponseDTO(repository.save(funcionario))
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }
}