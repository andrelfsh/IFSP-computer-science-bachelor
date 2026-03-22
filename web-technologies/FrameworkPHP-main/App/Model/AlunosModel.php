<?php

namespace App\Model;

class AlunosModel
{
    private $id;
    private $nome;
    private $data;
    private $data_cadastro;


    

    public function __set($nome, $valor) # toda funcao iniciada por __ é um metodo magico
    {
        $this->$nome = $valor;
    }

    public function __get($nome)
    {
        return $this->$nome;
    }
}
