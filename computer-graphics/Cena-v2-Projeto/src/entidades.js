// src/Entidades.js

import * as THREE from "three";
import { OBJLoader } from "three/examples/jsm/loaders/OBJLoader.js"; 

// --- CONSTANTES DE ENTIDADES ---
const RAIO_BOLA = 1.5;
const ESCALA_PERSONAGEM = 10;
const texturaLoader = new THREE.TextureLoader();
const objLoader = new OBJLoader();


/**
 * Cria e adiciona a bola na cena.
 */
export function criarBola(cena) {
    const raio = RAIO_BOLA;
    const geometria = new THREE.SphereGeometry(raio, 32, 32);
    const material = new THREE.MeshPhongMaterial({ color: 0xffa500 }); 
    const bola = new THREE.Mesh(geometria, material);
    bola.position.set(0, raio, 0);
    bola.castShadow = true;
    bola.receiveShadow = true;
    cena.add(bola);
    return bola;
}

/**
 * Carrega o modelo OBJ de um personagem.
 * @returns O objeto THREE.Mesh do personagem.
 */
export function carregarPersonagemOBJ(cena, objPath, texturePath, x, z, isPlayer1, callback) {
    
    texturaLoader.load(texturePath, (textura) => {
        objLoader.load(objPath, (objeto) => {
            
            const material = new THREE.MeshPhongMaterial({ map: textura, color: isPlayer1 ? 0xffffff : 0x0000ff }); // Branco / Azul

            objeto.traverse((child) => {
                if (child.isMesh) {
                    child.material = material;
                    child.castShadow = true;
                    child.receiveShadow = true;
                }
            });

            objeto.rotation.y = Math.PI;
            objeto.scale.set(ESCALA_PERSONAGEM, ESCALA_PERSONAGEM, ESCALA_PERSONAGEM);
            objeto.position.set(x, 0, z); // Assume y=0, a altura será ajustada pelo modelo se necessário
            cena.add(objeto);
            
            // Chama o callback com o objeto carregado
            if (callback) {
                callback(objeto);
            }
        });
    });
}