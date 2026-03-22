<?php

namespace App\Controller;

use FW\Controller\Action;
use App\DAO\AlunosDAO;
use App\Model\AlunosModel;


class AlunosController extends Action{

    public function cadastro(){
        $title = "alunos";
        $title_pagina = "Alunos";

        

        $this->getView()->title = $title;
        $this->getView()->title_pagina = $title_pagina;

        $this->render('../dashboard/alunos_cadastro', ''); # carrega index, no template dashboard
    }

        public function index(){
        $title = "alunos";
        $title_pagina = "Alunos";

        

        $this->getView()->title = $title;
        $this->getView()->title_pagina = $title_pagina;

        $this->render('index', 'dashboard'); # carrega index, no template dashboard
    }
    
    
     public function contato(){
        $title = "alunos";
        $title_pagina = "Alunos";

        

        $this->getView()->title = $title;
        $this->getView()->title_pagina = $title_pagina;

        
        $this->render('../contato/index', 'site');
    }

    public function cadastrar(){
        $nome = $_POST['name'];
        $senha = $_POST['senha'];

        $alunosModel = new AlunosModel();
        $alunosModel->__set("nome", $nome);
        $alunosModel->__set("senha", hash('sha256', $senha));


        var_dump($alunosModel->__get("nome"));
        var_dump($alunosModel->__get("senha"));

        $alunosDAO = new AlunosDAO();
        $alunosDAO->inserir($alunosModel);
        

    
        #var_dump($nome); 
        # dia();
        #$variavel = $_POST['variavel']; # erro aqui
        #var_dump(hash('sha256', $variavel)); # erro aqui

    }
    

    public function validaAutenticacao() {

        
    }
}
