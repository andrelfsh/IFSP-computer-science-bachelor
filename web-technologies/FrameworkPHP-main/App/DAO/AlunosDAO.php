<?php

namespace App\DAO;

use App\DAO;
use App\Model\AlunosModel;
use FW\Controller\FuncoesGlobais;


class AlunosDAO extends DAO{

    public function inserir($obj) {
        try{

            $nome = $obj->__get('senha');
            $senha = $obj->__get('senha');
            $data_cadastro = date('Y-m-d H:i:s');

            $sql = "INSERT INTO alunos (
                        nome,
                        senha,
                        data_cadastro
                    ) VALUES (
                        nome,
                        senha,
                        data_cadastro
                    )";
            $stmt = $this->getConn()->prepare($sql);
            $stmt->bindValue(':nome', $nome);   
            $stmt->bindValue(':senha', $senha);
            $stmt->bindValue(':data_cadastro', $data_cadastro);
            $stmt->execute();
        }
        catch(\PDOException $ex){
            header('Location:/error103');
            die();
        }
    }

    public function listar(){
           
        try{
            $alunos = array();
            $sql = "SELECT 
                            a.*, 
                            l.log_email 
                        FROM 
                            alunos a,
                            login l
                        WHERE
                            ad.fk_login_log_id = l.log_id
                    ";
            $stmt = $this->getConn()->prepare($sql);
            $stmt->execute();
            $result = $stmt->fetchAll(\PDO::FETCH_ASSOC);

            foreach($result as $row){
                $alunosModel = new AlunosModel();
                
                $global = new FuncoesGlobais();
                $global->popularModel($alunosModel, $row);

                array_push($alunos, $alunosModel);
            }
            return $alunos;
        }
        catch(\PDOException $ex){
            header('Location:/error103');
            die();
        }    
    }

    public function excluir($obj) {}
    public function alterar($obj) {}
    public function buscarPorId($id){ }
    public function buscarPorLogado($id){}
}
