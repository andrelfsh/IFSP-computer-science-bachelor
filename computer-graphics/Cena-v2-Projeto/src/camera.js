// src/CameraManager.js

import * as THREE from "three";
import { OrbitControls } from "three/examples/jsm/controls/OrbitControls.js";

// --- CONSTANTES DE CÂMERA ---
const OFFSET_3P = new THREE.Vector3(0, 15, 20); // Altura e distância da câmera 3ª pessoa
const POS_PANORAMICA = new THREE.Vector3(0, 90, 60);

// Câmeras
export const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000); 
export const cameraP1 = new THREE.PerspectiveCamera(75, 0.5 * window.innerWidth / window.innerHeight, 0.1, 1000);
export const cameraP2 = new THREE.PerspectiveCamera(75, 0.5 * window.innerWidth / window.innerHeight, 0.1, 1000);

// Estado e Controles
export let modoVisaoDividida = false;
let controlador = null;
const linhaDivisoria = document.getElementById('linha-divisoria');

/**
 * Configura a câmera principal (Panorâmica) e o OrbitControls.
 */
export function setupCamerasAndControls(renderizadorDomElement) {
    camera.position.copy(POS_PANORAMICA);
    camera.lookAt(0, 0, 0);

    controlador = new OrbitControls(camera, renderizadorDomElement);
    controlador.target.set(0, 0, 0); 
    controlador.maxPolarAngle = 90 * (Math.PI / 180);
    controlador.enableDamping = true;
    controlador.dampingFactor = 0.05;
    controlador.update();

    // Configura os listeners dos botões (idealmente em outro lugar, mas mantido aqui por conveniência)
    document.getElementById('btn-panoramica').addEventListener('click', setVisaoPanoramica);
    document.getElementById('btn-individual').addEventListener('click', setVisaoIndividual);
}

export function setVisaoPanoramica() {
    modoVisaoDividida = false;
    camera.position.copy(POS_PANORAMICA);
    camera.lookAt(0, 0, 0);
    controlador.enabled = true; 
    controlador.target.set(0, 0, 0);
    controlador.update();
    
    if (linhaDivisoria) {
        linhaDivisoria.style.display = 'none';
    }
    // O redimensionamento do renderizador é feito na função handleResize/main.js
}

export function setVisaoIndividual() {
    modoVisaoDividida = true;
    controlador.enabled = false; 
    
    // Atualiza a proporção das câmeras divididas
    handleResize(window.innerWidth, window.innerHeight);

    if (linhaDivisoria) {
        linhaDivisoria.style.display = 'block';
    }
}

/**
 * Atualiza a posição da câmera em 3ª pessoa para seguir o jogador.
 */
function atualizarCameraIndividual(playerCamera, player) {
    if (!player) return;

    const offset = OFFSET_3P;
    
    const playerDirecao = new THREE.Vector3();
    player.getWorldDirection(playerDirecao);
    
    // Define a posição da câmera
    playerCamera.position.copy(player.position);
    playerCamera.position.sub(playerDirecao.multiplyScalar(offset.z));
    playerCamera.position.y += offset.y;
    
    // Faz a câmera olhar para o jogador (ou um ponto ligeiramente acima)
    playerCamera.lookAt(player.position.x, player.position.y + 5, player.position.z);
}

/**
 * Funçao principal para renderizar a cena, manipulando viewports para o modo dividido.
 */
export function renderCameras(renderizador, cena, jogador1, jogador2) {
    const width = window.innerWidth;
    const height = window.innerHeight;

    if (modoVisaoDividida) {
        // --- MODO TELA DIVIDIDA ---
        
        atualizarCameraIndividual(cameraP1, jogador1);
        atualizarCameraIndividual(cameraP2, jogador2);

        // Renderiza a Câmera P1 (Lado Esquerdo)
        renderizador.setViewport(0, 0, width / 2, height);
        renderizador.setScissor(0, 0, width / 2, height);
        renderizador.setScissorTest(true);
        renderizador.render(cena, cameraP1);

        // Renderiza a Câmera P2 (Lado Direito)
        renderizador.setViewport(width / 2, 0, width / 2, height);
        renderizador.setScissor(width / 2, 0, width / 2, height);
        renderizador.render(cena, cameraP2);
        
    } else {
        // --- MODO PANORÂMICO (PADRÃO) ---
        renderizador.setViewport(0, 0, width, height);
        renderizador.setScissorTest(false);
        controlador.update(); 
        renderizador.render(cena, camera);
    }
}

/**
 * Lida com o redimensionamento da janela e ajusta as câmeras.
 */
export function handleResize(newWidth, newHeight) {
    
    camera.aspect = newWidth / newHeight;
    camera.updateProjectionMatrix();

    if (modoVisaoDividida) {
        // Atualiza a proporção das câmeras divididas
        cameraP1.aspect = (newWidth / 2) / newHeight;
        cameraP2.aspect = (newWidth / 2) / newHeight;
        cameraP1.updateProjectionMatrix();
        cameraP2.updateProjectionMatrix();
    }
}