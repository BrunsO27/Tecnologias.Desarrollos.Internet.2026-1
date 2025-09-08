let toDo = [];
let ejemplo = [
    {id: 1, tarea: "Tarea 1", estado: 0},
    {id: 2, tarea: "Tarea 2", estado: 0},
    {id: 3, tarea: "Tarea 3", estado: 1},
];

ejemplificarToDo = () => {
    toDo = ejemplo;
    mostrarTareas();
}

agregarTarea = () => {
    let id = toDo.length + 1;
    let tarea = document.getElementById("ta1").value;

    toDo.push({id, tarea, estado: 0});

    mostrarTareas();
}

eliminarTarea = (id) => {
    const index = toDo.find(t => t.id === id);
    if (index !== -1) {
        toDo.splice(index, 1);
    }

    mostrarTareas();
}

completarTarea = (id) => {
    let tarea = toDo.find(t => t.id === id);
    if (tarea) {
        tarea.estado = 1;
    }

    mostrarTareas();
}

descompletarTarea = (id) => {
    let tarea = toDo.find(t => t.id === id);
    if (tarea) {
        tarea.estado = 0;
    }

    mostrarTareas();
}

eliminarLista = () => {
    toDo = [];

    mostrarTareas();
}

mostrarTareas = () => {
    const ul = document.getElementById("ulT1");
    ul.innerHTML = "";
    toDo.forEach(t => {
        ul.innerHTML += `
            <li>
                <span>${t.tarea}</span>
                <input type="checkbox" ${t.estado ? 'checked' : ''} onclick="${t.estado ? 'descompletarTarea' : 'completarTarea'}(${t.id})"/>
                <button onclick="eliminarTarea(${t.id})">Eliminar</button>
            </li>
        `
    })
}