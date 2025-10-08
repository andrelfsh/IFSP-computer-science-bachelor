import * as THREE from "three";
import { GUI } from "three/addons/libs/lil-gui.module.min.js";

// Configurações iniciais
const cena = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(
    75, window.innerWidth / window.innerHeight, 0.1, 100
);
const renderizador = new THREE.WebGLRenderer({ antialias: true });

renderizador.setSize(window.innerWidth, window.innerHeight);
renderizador.setClearColor(0xdddddd);
document.body.appendChild(renderizador.domElement);

// Posicionamento da câmera
camera.position.set(0, 5, 10);
camera.lookAt(0, 0, 0);

// Luzes
const luzAmbiente = new THREE.AmbientLight(0x404040, 0.4);
cena.add(luzAmbiente);

const luzDirecional = new THREE.DirectionalLight(0xffffff, 1);
luzDirecional.position.set(5, 10, 7.5);
cena.add(luzDirecional);

// Geometria e materiais
const geometria = new THREE.BoxGeometry(2.0, 0.5, 0.5);

// Objeto Euler
const eulerMaterial = new THREE.MeshLambertMaterial({ color: 0xff0000 });
const objetoEuler = new THREE.Mesh(geometria, eulerMaterial);
objetoEuler.position.set(-3, 0, 0);
cena.add(objetoEuler);

// Objeto Quaternion
const quarterMaterial = new THREE.MeshLambertMaterial({ color: 0x0000ff });
const objetoQuarter = new THREE.Mesh(geometria, quarterMaterial);
objetoQuarter.position.set(3, 0, 0);
cena.add(objetoQuarter);

// Parâmetros
const parametros = {
    eulerX: 0,
    eulerY: 0,
    eulerZ: 0,
    quarAlvoX: 0,
    quarAlvoY: 0,
    quarAlvoZ: 0,
    veloSLERP: 0.2
};

// Quaternions
const quarternionInicial = new THREE.Quaternion();
const quarternionFinal = new THREE.Quaternion();
const quarternionAtual = new THREE.Quaternion();

// Funções de atualização
function updateAlvoQuartenion() {
    quarternionInicial.copy(quarternionAtual);
    quarternionFinal.setFromEuler(
        new THREE.Euler(
            parametros.quarAlvoX,
            parametros.quarAlvoY,
            parametros.quarAlvoZ
        )
    );
}

function updateRotacao() {
    objetoEuler.rotation.set(
        parametros.eulerX,
        parametros.eulerY,
        parametros.eulerZ
    );
}

// GUI
const gui = new GUI();

// Pasta Euler
const eulerPasta = gui.addFolder("Ângulos de Euler");
eulerPasta.add(parametros, "eulerX", -Math.PI, Math.PI, 0.01)
    .name("Rotação X")
    .onChange(updateRotacao);
eulerPasta.add(parametros, "eulerY", -Math.PI, Math.PI, 0.01)
    .name("Rotação Y")
    .onChange(updateRotacao);
eulerPasta.add(parametros, "eulerZ", -Math.PI, Math.PI, 0.01)
    .name("Rotação Z")
    .onChange(updateRotacao);

// Pasta Quaternion
const quarterPasta = gui.addFolder("Ângulos de Quaternion");
quarterPasta.add(parametros, "quarAlvoX", -Math.PI, Math.PI, 0.01)
    .name("Alvo X")
    .onChange(updateAlvoQuartenion);
quarterPasta.add(parametros, "quarAlvoY", -Math.PI, Math.PI, 0.01)
    .name("Alvo Y")
    .onChange(updateAlvoQuartenion);
quarterPasta.add(parametros, "quarAlvoZ", -Math.PI, Math.PI, 0.01)
    .name("Alvo Z")
    .onChange(updateAlvoQuartenion);

// Pasta SLERP
const slerpPasta = gui.addFolder("Slerp");
slerpPasta.add(parametros, "veloSLERP", 0.001, 1, 0.001)
    .name("Velocidade");

// Animação
function animate() {
    requestAnimationFrame(animate);
    quarternionAtual.slerp(quarternionFinal, parametros.veloSLERP);
    objetoQuarter.quaternion.copy(quarternionAtual);
    renderizador.render(cena, camera);
}

animate();
