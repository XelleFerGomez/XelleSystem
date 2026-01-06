/**
 * system-app.js - Lógica del Sistema Central (Dashboard/Menu)
 * Ubicación: static/
 */

const API_BASE = "http://localhost:8081/api";

// --- AUTENTICACIÓN ---
function checkAuth() {
    const user = JSON.parse(localStorage.getItem('xelle_user'));
    if (!user) {
        window.location.href = 'index.html';
        return null;
    }
    // Mostrar nombre usuario si existe elemento
    const elUser = document.getElementById('u-name');
    if(elUser) elUser.innerText = user.nombre;
    return user;
}

function login(username, password) {
    fetch(API_BASE + "/auth/login", {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    })
    .then(res => res.json())
    .then(data => {
        if (data.status === 'ok') {
            localStorage.setItem('xelle_user', JSON.stringify(data));
            window.location.href = 'MenuPrincipal.html';
        } else {
            alert("Credenciales incorrectas");
        }
    })
    .catch(err => alert("Error de conexión con servidor"));
}

function logout() {
    localStorage.removeItem('xelle_user');
    window.location.href = 'index.html';
}

// --- NAVEGACIÓN DASHBOARD ---
let CURRENT_TAB = '';

function initDashboard() {
    const user = checkAuth();
    if(!user) return;

    const module = new URLSearchParams(window.location.search).get('modulo');
    
    // Ocultar todos los menús primero
    document.querySelectorAll('[id^="nav-"]').forEach(el => el.classList.add('hidden'));
    document.getElementById('panel-filtros').classList.add('hidden');

    // Mostrar menú según módulo
    if (module === 'banco') {
        document.getElementById('titulo-modulo').innerText = "Banco de Células";
        document.getElementById('nav-banco').classList.remove('hidden');
        switchTab('RES'); // Default Resumen
    } else if (module === 'calidad') {
        document.getElementById('titulo-modulo').innerText = "Lab. de Calidad";
        document.getElementById('nav-calidad').classList.remove('hidden');
        document.getElementById('panel-filtros').classList.remove('hidden'); // Filtros para docs
        switchTab('DOC');
    } else if (module === 'almacen') {
        document.getElementById('titulo-modulo').innerText = "Control Almacén";
        document.getElementById('nav-almacen').classList.remove('hidden');
        document.getElementById('panel-filtros').classList.remove('hidden');
        switchTab('INV');
    } else if (module === 'sgc') {
        document.getElementById('titulo-modulo').innerText = "Biblioteca SGC";
        document.getElementById('nav-sgc').classList.remove('hidden');
        document.getElementById('panel-filtros').classList.remove('hidden');
        switchTab('DOC');
    }
}

function switchTab(tabName, btnElement) {
    CURRENT_TAB = tabName;

    // Actualizar estilo botones sidebar
    if (btnElement) {
        document.querySelectorAll('.nav-btn').forEach(b => b.classList.remove('active'));
        btnElement.classList.add('active');
    }

    // Ocultar vistas y acciones
    ['v-res', 'v-tab', 'v-inv', 'v-trz'].forEach(id => document.getElementById(id).classList.add('hidden'));
    ['btn-new', 'btn-up', 'btn-mov'].forEach(id => document.getElementById(id).classList.add('hidden'));

    // Título Vista
    const titleEl = document.getElementById('titulo-vista');

    // Lógica por Tab
    if (tabName === 'RES') {
        titleEl.innerText = "Resumen Operativo";
        document.getElementById('v-res').classList.remove('hidden');
        // Aquí podrías llamar fetchStats()
    } else if (tabName === 'DOC') {
        titleEl.innerText = "Documentación";
        document.getElementById('v-tab').classList.remove('hidden');
        document.getElementById('btn-up').classList.remove('hidden');
        loadDocumentos();
    } else if (tabName === 'INV') {
        titleEl.innerText = "Inventario Actual";
        document.getElementById('v-inv').classList.remove('hidden');
        document.getElementById('btn-mov').classList.remove('hidden');
        loadInventario();
    } else if (tabName === 'TRZ') {
        titleEl.innerText = "Búsqueda y Trazabilidad";
        document.getElementById('v-trz').classList.remove('hidden');
    } else {
        // Es un formato numérico (17, 22, 31...)
        titleEl.innerText = `Gestión de Registros FO-LC-${tabName}`;
        document.getElementById('v-tab').classList.remove('hidden');
        document.getElementById('btn-new').classList.remove('hidden');
        loadDatosFormato(tabName);
    }
}

// --- CARGA DE DATOS ---
async function loadDocumentos() {
    try {
        const res = await fetch(API_BASE + '/documentos');
        const docs = await res.json();
        
        // Aplicar filtros
        const ft = document.getElementById('ft') ? document.getElementById('ft').value : 'TODOS';
        const fa = document.getElementById('fa') ? document.getElementById('fa').value : 'TODAS';
        
        const filtered = docs.filter(d => (ft === 'TODOS' || d.tipo === ft) && (fa === 'TODAS' || d.area === fa));
        
        let html = `<thead><tr><th>Código</th><th>Nombre</th><th>Tipo</th><th>Acción</th></tr></thead><tbody>`;
        filtered.forEach(d => {
            html += `<tr>
                <td><b>${d.codigo}</b></td>
                <td>${d.nombre}</td>
                <td><span class="tag">${d.tipo}</span></td>
                <td>
                    <a href="${API_BASE}/documentos/download/${d.nombreArchivo}" target="_blank" class="btn btn-blue" style="padding:5px 10px; font-size:10px;">Ver PDF</a>
                    <button class="btn btn-red" style="padding:5px 10px; font-size:10px;" onclick="deleteDoc(${d.id})">X</button>
                </td>
            </tr>`;
        });
        document.getElementById('tbl-main').innerHTML = html + "</tbody>";
    } catch (e) { console.error(e); }
}

async function loadInventario() {
    try {
        const res = await fetch(API_BASE + '/almacen/items');
        const items = await res.json();
        const q = document.getElementById('q-inv').value.toLowerCase();
        
        let html = "";
        items.filter(i => i.nombreProducto.toLowerCase().includes(q)).forEach(i => {
            html += `<tr>
                <td>${i.nombreProducto}</td>
                <td>${i.presentacion || '-'}</td>
                <td>${i.lote || '-'}</td>
                <td>${i.caducidad || '-'}</td>
                <td style="font-weight:bold; color:${i.cantidadActual < 5 ? 'red' : 'green'}">${i.cantidadActual}</td>
                <td><button onclick="deleteItem(${i.id})" class="btn btn-red" style="padding:2px 5px;">X</button></td>
            </tr>`;
        });
        document.getElementById('tbl-inv').querySelector('tbody').innerHTML = html;
    } catch (e) { console.error(e); }
}

function loadDatosFormato(n) {
    // Si tienes endpoints para datos estructurados, úsalos aquí.
    // Si no, muestra mensaje de que los registros son PDFs.
    const tbody = document.getElementById('tbl-main');
    tbody.innerHTML = `
        <thead><tr><th>Información</th></tr></thead>
        <tbody>
            <tr><td style="text-align:center; padding:20px; color:#777;">
                Los registros de <b>FO-LC-${n}</b> se guardan como documentos PDF.<br>
                Ve a la pestaña <b>Documentación</b> para consultarlos o usa <b>+ Nuevo</b> para crear uno.
            </td></tr>
        </tbody>
    `;
}

// --- ACCIONES DE BOTONES ---
function nuevoReg() {
    // Abre el HTML del formato en ventana nueva
    window.open(`Formatos/FO-LC-${CURRENT_TAB}.html`, '_blank', 'width=1100,height=800,scrollbars=yes');
}

function modalUp() { document.getElementById('m-sgc').classList.remove('hidden'); }
function modalMov() { alert("Funcionalidad de Movimiento de Almacén (Pendiente de Modal)"); }

// --- ELIMINAR ---
async function deleteDoc(id) {
    if(confirm("¿Eliminar documento permanentemente?")) {
        await fetch(API_BASE + '/documentos/' + id, { method: 'DELETE' });
        loadDocumentos();
    }
}
async function deleteItem(id) {
    if(confirm("¿Eliminar insumo del inventario?")) {
        await fetch(API_BASE + '/almacen/items/' + id, { method: 'DELETE' });
        loadInventario();
    }
}