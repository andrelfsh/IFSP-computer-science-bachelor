import * as THREE from 'three';

// cria a cena
const cena = new THREE.Scene();

const sunGeometry = new THREE.SphereGeometry(3, 100, 100);
const sunMaterial = new THREE.MeshBasicMaterial({ color: 0xffff00 });
const sun = new THREE.Mesh(sunGeometry, sunMaterial);

const earthGeometry = new THREE.SphereGeometry(1, 100, 100);
const earthMaterial = new THREE.MeshBasicMaterial({ color: 0x0000ff });
const earth = new THREE.Mesh(earthGeometry, earthMaterial);

const moonGeometry = new THREE.SphereGeometry(0.3, 100, 100);
const moonMaterial = new THREE.MeshBasicMaterial({ color: 0x888888 });
const moon = new THREE.Mesh(moonGeometry, moonMaterial);

cena.add(sun);
sun.add(earth);
earth.add(moon);

earth.position.x = 10;
moon.position.x = 2;

// cria a camera
const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);
camera.position.z = 20;

// cria o renderizador
const renderizador = new THREE.WebGLRenderer();
renderizador.setSize(window.innerWidth, window.innerHeight); // ocupa toda a tela
document.body.appendChild(renderizador.domElement); // adiciona o canvas ao HTML

// animação para renderizar a cena
function animate() {
    requestAnimationFrame(animate);
    renderizador.render(cena, camera);
    sun.rotation.y += 0.01;
    earth.rotation.y += 0.05;
}

animate();
