package com.example.funcionario.converters

import com.example.funcionario.dtos.FuncionarioDTO
import com.example.funcionario.dtos.FuncionarioResponseDTO
import com.example.funcionario.model.Funcionario
import org.springframework.stereotype.Component

@Component
class FuncionarioConverter {

    fun toFuncionarioResponseDTO(funcionario: Funcionario): FuncionarioResponseDTO {
        return FuncionarioResponseDTO(
            id = funcionario.id,
            nome = funcionario.nome,
            cargo = funcionario.cargo,
            status = funcionario.status
        )
    }



    fun toFuncionario(dto: FuncionarioDTO): Funcionario {
        return Funcionario(
            nome = dto.nome,
            cargo = dto.cargo,
            status = dto.status
        )
    }
}