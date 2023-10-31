package com.example.funcionario

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FuncionarioApplication

fun main(args: Array<String>) {
	runApplication<FuncionarioApplication>(*args)
}
