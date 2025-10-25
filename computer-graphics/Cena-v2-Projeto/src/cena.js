// src/Cena.js

import * as THREE from "three";
import { FBXLoader } from 'three/examples/jsm/loaders/FBXLoader.js'; 


// --- CONSTANTES DE CENA ---
const TAMANHO_CAMPO_X = 75; 
const TAMANHO_CAMPO_Z = 40; 
const textureLoader = new THREE.TextureLoader(); // 💡 NOVO: Loader de texturas


export const cena = new THREE.Scene();

/**
 * Configura a cena base (fundo, névoa e chão) e carrega os modelos.
 */
export function setupScene() {

    cena.backgroundColor = 0xfff8ff;
    cena.background = new THREE.Color(0xfff8ff);

    // criar os planos do chão
    const chaoGeometria = new THREE.PlaneGeometry(TAMANHO_CAMPO_X * 2, TAMANHO_CAMPO_Z * 2); 
    const chaoMaterial = new THREE.MeshLambertMaterial({ color: 0x008000 }); 
    const chao = new THREE.Mesh(chaoGeometria, chaoMaterial);
    chao.position.set(0, 0, 0);
    chao.rotation.set(-Math.PI / 2, 0, 0);
    chao.receiveShadow = true;
    cena.add(chao);

    // linhas meio do campo 
    const linhasGeometria = new THREE.PlaneGeometry(1, 80); 
    const linhasMaterial = new THREE.MeshBasicMaterial({ color: 0xffffff, side: THREE.DoubleSide });
    const linhas = new THREE.Mesh(linhasGeometria, linhasMaterial);
    linhas.position.set(0, 0.01, 0);
    linhas.rotation.set(-Math.PI / 2, 0, 0);
    cena.add(linhas);

    // círculos centrais
    const circuloGeometria = new THREE.RingGeometry(12, 13, 32);  
    const circuloMaterial = new THREE.MeshBasicMaterial({ color: 0xffffff, side: THREE.DoubleSide });
    const circulo = new THREE.Mesh(circuloGeometria, circuloMaterial);
    circulo.position.set(0, 0.02, 0);
    circulo.rotation.set(-Math.PI / 2, 0, 0);
    cena.add(circulo);

    const fbxLoader = new FBXLoader();

    // ------------------------------------------------------------------
    // LÓGICA DE CARREGAMENTO DO GOL
    // ------------------------------------------------------------------
    fbxLoader.load('assets/soccer-goal/goal.fbx', (objeto) => {
        
        objeto.traverse((child) => {
            if (child.isMesh) {
                child.castShadow = true;
                child.receiveShadow = true;
            }
        });

        const escalaGol = 0.04; 
        
        // --- GOL DA ESQUERDA (Lado -X) ---
        const golEsquerda = objeto;
        golEsquerda.scale.set(escalaGol, escalaGol, escalaGol); 
        golEsquerda.position.set(-TAMANHO_CAMPO_X, 0, 0); 
        cena.add(golEsquerda);


        // --- GOL DA DIREITA (Lado +X) ---
        const golDireita = objeto.clone(); 
        golDireita.scale.set(escalaGol, escalaGol, escalaGol);
        golDireita.position.set(TAMANHO_CAMPO_X, 0, 0); 
        golDireita.rotation.set(0, Math.PI, 0);
        cena.add(golDireita);
        
    }, undefined, (error) => {
        console.error('Erro ao carregar o gol:', error);
    });

    // ------------------------------------------------------------------
    // LÓGICA DE CARREGAMENTO DA ARQUIBANCADA (COM TEXTURA)
    // ------------------------------------------------------------------
    const TEXTURA_ARQUIBANCADA = 'assets/low-quality-soccer-stadium/stadium-4k.png';

    textureLoader.load(TEXTURA_ARQUIBANCADA, (textura) => {
        // Carrega a textura primeiro, depois o modelo FBX
        fbxLoader.load('assets/low-quality-soccer-stadium/stadium.fbx', (objeto) => {
            
            // Cria um material com a textura
            const materialArquibancada = new THREE.MeshPhongMaterial({ map: textura });

            objeto.traverse((child) => {
                if (child.isMesh) {
                    // 💡 APLICA A TEXTURA NO MATERIAL DO MESH
                    child.material = materialArquibancada; 
                    child.castShadow = true;
                    child.receiveShadow = true;
                }
            });

            const escalaArquibancada = 0.02;
            
            const arquibancada = objeto;
            arquibancada.scale.set(escalaArquibancada, escalaArquibancada, escalaArquibancada);
            arquibancada.position.set(0, -10, -TAMANHO_CAMPO_Z + 40); 
            cena.add(arquibancada);
        
        }, undefined, (error) => { 
            console.error('Erro ao carregar a arquibancada:', error);
        });
    }, undefined, (error) => {
         console.error('Erro ao carregar a textura da arquibancada:', error);
    });
}

/**
 * Cria e configura o renderizador.
 * @returns O objeto THREE.WebGLRenderer.
 */
export function setupRenderer() {
    const renderizador = new THREE.WebGLRenderer({ antialias: true });
    renderizador.outputColorSpace = THREE.SRGBColorSpace;
    renderizador.shadowMap.enabled = true;
    renderizador.shadowMap.type = THREE.VSMShadowMap;
    renderizador.setSize(window.innerWidth, window.innerHeight);
    renderizador.setClearColor(0xffffff);
    document.body.appendChild(renderizador.domElement);
    return renderizador;
}

/**
 * Adiciona iluminação à cena.
 */
export function setupLighting() { 

    // luz ambinente
    const luzAmbiente = new THREE.AmbientLight(0xffffff, 0.3);
    cena.add(luzAmbiente);

    // luz direcional
    const luzDirecional = new THREE.DirectionalLight(0xffffff);
    luzDirecional.position.set(0, 100, 0); 
    luzDirecional.castShadow = true; 
    
    // resolucao sombra
    luzDirecional.shadow.mapSize.width = 2048; 
    luzDirecional.shadow.mapSize.height = 2048; 

    // area (arredondado pra cima)
    luzDirecional.shadow.camera.left = -100; 
    luzDirecional.shadow.camera.right = 100;
    luzDirecional.shadow.camera.top = 100;
    luzDirecional.shadow.camera.bottom = -100;

    luzDirecional.intensity = 0.3; // intensidade da luz

    // Target para luz direcional (adicionado para garantir que a luz aponte para o centro)
    const targetDirecional = new THREE.Object3D();
    targetDirecional.position.set(0, 0, 0);
    luzDirecional.target = targetDirecional;

    cena.add(luzDirecional);
    cena.add(targetDirecional);


    // luz pontual (360 graus)
    const altura = 50;
    const intensidade = 2000;
    const distancia = 1000;
    const sombra_map_size = 1024;
    
    // LUZ 3: Centro Superior (lateral Z positiva)
    const luzPonto3 = new THREE.PointLight(0xffffff, intensidade, distancia); 
    luzPonto3.position.set(0, altura, TAMANHO_CAMPO_Z); 
    luzPonto3.castShadow = true; 
    luzPonto3.shadow.mapSize.width = sombra_map_size;
    luzPonto3.shadow.mapSize.height = sombra_map_size;
    luzPonto3.shadow.camera.far = distancia;
    cena.add(luzPonto3);

    // LUZ 4: Centro Inferior (lateral Z negativa)
    const luzPonto4 = new THREE.PointLight(0xffffff, intensidade, distancia); 
    luzPonto4.position.set(0, altura, -1 * TAMANHO_CAMPO_Z); 
    luzPonto4.castShadow = true; 
    luzPonto4.shadow.mapSize.width = sombra_map_size;
    luzPonto4.shadow.mapSize.height = sombra_map_size;
    luzPonto4.shadow.camera.far = distancia;
    cena.add(luzPonto4);

    // LUZ 5: Centro Direito (lateral X positiva)
    const luzPonto5 = new THREE.PointLight(0xffffff, intensidade, distancia); 
    luzPonto5.position.set(TAMANHO_CAMPO_X, altura, 0); 
    luzPonto5.castShadow = true; 
    luzPonto5.shadow.mapSize.width = sombra_map_size;
    luzPonto5.shadow.mapSize.height = sombra_map_size;
    luzPonto5.shadow.camera.far = distancia;
    cena.add(luzPonto5);

    // LUZ 6: Centro Esquerdo (lateral X negativa)
    const luzPonto6 = new THREE.PointLight(0xffffff, intensidade, distancia); 
    luzPonto6.position.set(-1 * TAMANHO_CAMPO_X, altura, 0); 
    luzPonto6.castShadow = true; 
    luzPonto6.shadow.mapSize.width = sombra_map_size;
    luzPonto6.shadow.mapSize.height = sombra_map_size;
    luzPonto6.shadow.camera.far = distancia;
    cena.add(luzPonto6);

}