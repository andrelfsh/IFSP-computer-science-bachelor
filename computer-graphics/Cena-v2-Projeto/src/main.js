import * as THREE from "three";

import { setupRenderer, setupScene, setupLighting, cena } from "./cena.js";
import { setupCamerasAndControls, camera, cameraP1, cameraP2, modoVisaoDividida, renderCameras, handleResize } from "./camera.js";
import { criarBola, carregarPersonagemOBJ } from "./entidades.js";
import { animarBola, moverEChutar } from "./fisica.js";
import { setupEventListeners, teclasPressionadas } from "./input.js";

// variaveis globais
let jogador1 = null; 
let jogador2 = null; 
let bola = null;
const bolaVelocidade = new THREE.Vector3(0, 0, 0);


// cena, luzes e o renderizador
setupScene();
setupLighting();
const renderizador = setupRenderer();

// câmeras e controles
setupCamerasAndControls(renderizador.domElement);

// manipulação de entrada (telcado)
setupEventListeners();

// carrega as entidades bola
bola = criarBola(cena); 

// JOGADOR 1 (WASD) - Branco
carregarPersonagemOBJ(
    cena,
    'assets/kenney_mini-characters/Models/OBJ format/character-male-b.obj',
    'assets/kenney_mini-characters/Models/OBJ format/Textures/colormap.png',
    -40, 0, 
    true, // isPlayer1
    (objeto) => { jogador1 = objeto; }
);

// JOGADOR 2 (Setas) - Azul
carregarPersonagemOBJ(
    cena,
    'assets/kenney_mini-characters/Models/OBJ format/character-male-b.obj',
    'assets/kenney_mini-characters/Models/OBJ format/Textures/colormap.png',
    40, 0, 
    false, // isPlayer1
    (objeto) => { jogador2 = objeto; }
);

// --- LÓGICA DE MOVIMENTO NO LOOP ---
function manipularMovimento() {
    const teclasJ1 = { frente: 'w', tras: 's', esquerda: 'a', direita: 'd' };
    moverEChutar(jogador1, bola, bolaVelocidade, teclasJ1, teclasPressionadas, 0.05);

    const teclasJ2 = { frente: 'ArrowUp', tras: 'ArrowDown', esquerda: 'ArrowLeft', direita: 'ArrowRight' };
    moverEChutar(jogador2, bola, bolaVelocidade, teclasJ2, teclasPressionadas, 0.05);
}

function renderizar() { // LOOP PRINCIPAL
    
    requestAnimationFrame(renderizar);

    manipularMovimento(); 
    animarBola(bola, bolaVelocidade); 

    renderCameras(renderizador, cena, jogador1, jogador2);
}

window.addEventListener('resize', () => {
    const newWidth = window.innerWidth;
    const newHeight = window.innerHeight;
    
    renderizador.setSize(newWidth, newHeight);
    handleResize(newWidth, newHeight);
});


renderizar();