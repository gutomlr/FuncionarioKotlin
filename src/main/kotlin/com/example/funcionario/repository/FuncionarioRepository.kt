package com.example.funcionario.repository

import com.example.funcionario.model.Funcionario
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FuncionarioRepository: JpaRepository<Funcionario, Long> {

    fun findByNome(nomeFuncionario: String, paginacao: Pageable): List<Funcionario>

}