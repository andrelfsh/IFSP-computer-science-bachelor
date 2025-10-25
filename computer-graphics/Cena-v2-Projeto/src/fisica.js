// física da bola - NÂO MEXER

import * as THREE from "three";

// --- CONSTANTES DE FÍSICA ---
const velocidadeMovimento = 2;

const FORCA_CHUTE = 1.5; 
const DISTANCIA_CHUTE = 6; 
const atrito = 0.98;
const FATOR_QUICADA = 0.8; 

const TAMANHO_CAMPO_X = 75; 
const TAMANHO_CAMPO_Z = 40; 

const RaioPersonagem = 1; 
const RaioBola = 1.5;


export function moverEChutar(jogador, bola, bolaVelocidade, teclasMovimento, teclasPressionadas, fatorRotacao) {
    
    if (!jogador || !bola) return;

    const direcao = new THREE.Vector3();
    jogador.getWorldDirection(direcao); 

    // Movimento
    if (teclasPressionadas[teclasMovimento.frente]) { // frente
        jogador.position.addScaledVector(direcao, velocidadeMovimento);
    }
    if (teclasPressionadas[teclasMovimento.tras]) { // trás
        jogador.position.addScaledVector(direcao, -velocidadeMovimento); 
    }

    if (teclasPressionadas[teclasMovimento.esquerda]) { // gira esquerda
        jogador.rotation.y += fatorRotacao*2; 
    }
    if (teclasPressionadas[teclasMovimento.direita]) { // gira direita
        jogador.rotation.y -= fatorRotacao*2; 
    }
    
    // colisão do chute
    const distancia = jogador.position.distanceTo(bola.position);
    if (distancia < DISTANCIA_CHUTE && teclasPressionadas[teclasMovimento.frente]) {
        const direcaoParaBola = new THREE.Vector3().subVectors(bola.position, jogador.position).normalize();
        const angulo = direcao.dot(direcaoParaBola); 
        if (angulo > 0.5) {// Verifica se o jogador está olhando pra bola
            bolaVelocidade.copy(direcao).multiplyScalar(FORCA_CHUTE);
        }
    }
    
    // colisão da borda
    const halfWidth = TAMANHO_CAMPO_X - RaioPersonagem;
    const halfDepth = TAMANHO_CAMPO_Z - RaioPersonagem;
    jogador.position.x = THREE.MathUtils.clamp(jogador.position.x, -halfWidth, halfWidth);
    jogador.position.z = THREE.MathUtils.clamp(jogador.position.z, -halfDepth, halfDepth);
}

// mecaninca da bola - usar como referência exercício de POO do david
export function animarBola(bola, bolaVelocidade) {
    
    // Movimento
    bola.position.add(bolaVelocidade);
    
    // Atrito
    bolaVelocidade.multiplyScalar(atrito);
    if (bolaVelocidade.length() < 0.01) {
        bolaVelocidade.set(0, 0, 0);
    }
    
    // Colisão da Bola com as Bordas
    const colisaoX = TAMANHO_CAMPO_X - RaioBola;
    const colisaoZ = TAMANHO_CAMPO_Z - RaioBola;
    
    if (bola.position.x > colisaoX) {
        bolaVelocidade.x *= -FATOR_QUICADA;
        bola.position.x = colisaoX; 
    } else if (bola.position.x < -colisaoX) {
        bolaVelocidade.x *= -FATOR_QUICADA;
        bola.position.x = -colisaoX; 
    }
    if (bola.position.z > colisaoZ) {
        bolaVelocidade.z *= -FATOR_QUICADA;
        bola.position.z = colisaoZ;
    } else if (bola.position.z < -colisaoZ) {
        bolaVelocidade.z *= -FATOR_QUICADA;
        bola.position.z = -colisaoZ;
    }
}