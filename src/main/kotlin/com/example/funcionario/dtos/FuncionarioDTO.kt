package com.example.funcionario.dtos

import jakarta.validation.constraints.NotEmpty

data class FuncionarioDTO (
    @field:NotEmpty(message = "Funcionário deve ter um nome")
    val nome: String,
    @field:NotEmpty(message = "Funcionário deve ter um cargo")
    val cargo: String,
    @field:NotEmpty(message = "Funcionário deve estar Ligado ou Desligado da empresa")
    val status: String
)