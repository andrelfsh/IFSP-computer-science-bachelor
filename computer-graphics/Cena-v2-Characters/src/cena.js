/*
* A estrutura básica do arquivo é:
* 1) Importar bibliotecas; 2) Criar a cena; 3) Criar e configurar a câmera;
* 4) Configurar renderizador e anexar câmera; 5) adicionar iluminação;
* 6) criar objetos e montar a cena; 7) renderizar a cena
*/

import * as THREE from "three";
import { OrbitControls } from "three/examples/jsm/controls/OrbitControls.js";
import { OBJLoader } from "three/examples/jsm/Addons.js";
import { EXRLoader } from "three/examples/jsm/Addons.js";

// 1. criar uma cena básica
const cena = new THREE.Scene();
cena.backgroundColor = 0xffffff;


// 2. criar e configurar câmera
const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);
camera.position.x = -3;
camera.position.y = 8;
camera.position.z = 2;

// 4. configurar renderizador e anexar câmera
const renderizador = new THREE.WebGLRenderer({ antialias: true });
renderizador.outputColorSpace = THREE.SRGBColorSpace;
renderizador.shadowMap.enabled = true;
renderizador.shadowMap.type = THREE.VSMShadowMap;
renderizador.setSize(window.innerWidth, window.innerHeight);
renderizador.setClearColor(0xffffff);
document.body.appendChild(renderizador.domElement);

// 5. adicionar iluminação
const luzDirecional = new THREE.DirectionalLight(0xffffff);
luzDirecional.position.set(5, 12, 8);
luzDirecional.castShadow = true;
luzDirecional.shadow.bias = -0.0005;
cena.add(luzDirecional);

// add orbitcontrols
const controlador = new OrbitControls(camera, renderizador.domElement);
controlador.maxPolarAngle = 90 * (Math.PI / 180);


// carregar um OBJ com textura
const objLoader = new OBJLoader();
const texturaLoader = new THREE.TextureLoader();

const textura = texturaLoader.load('assets/kenney_mini-characters/Models/OBJ format/Textures/colormap.png', () => {
    objLoader.load('assets/kenney_mini-characters/Models/OBJ format/character-male-b.obj', (objeto) => {
      
        const material = new THREE.MeshPhongMaterial({
            map: textura, // <--- Aplicando a textura!
            color: 0xffffff // cor branca para não colorir a textura
        });

        // 4. Aplicar o Material em todas as sub-malhas do objeto
        objeto.traverse((child) => {
            if (child.isMesh) {
                child.material = material;
            }
        });

        // 5. Adicionar e Escalar o Objeto
        cena.add(objeto);
        objeto.scale.set(100, 100, 100);
    });
});


// criar um plano
const chaoGeometria = new THREE.PlaneGeometry(100, 100);
const chaoMaterial = new THREE.MeshLambertMaterial({ color: 0x008000 });
const chao = new THREE.Mesh(chaoGeometria, chaoMaterial);
chao.position.set(0, -2, 0);
chao.rotation.set(-Math.PI / 2, 0, 0);
chao.receiveShadow = true;
cena.add(chao);

// 7. renderizar a cena
function renderizar() {
  requestAnimationFrame(renderizar);
  renderizador.render(cena, camera);
  controlador.update();
}
renderizar();
