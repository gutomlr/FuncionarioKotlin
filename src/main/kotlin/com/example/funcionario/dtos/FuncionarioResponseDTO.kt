package com.example.funcionario.dtos

data class FuncionarioResponseDTO (
    val id: Long?,
    val nome: String,
    val cargo: String,
    val status: String
)