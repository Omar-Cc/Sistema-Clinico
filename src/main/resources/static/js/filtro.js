// Función para filtrar y ordenar los datos
function filterAndSort() {
    const estado = document.getElementById('sort1').value; // Obtener el estado seleccionado
    const orden = document.getElementById('sort').value; // Obtener el criterio de orden

    // Filtrar los datos
    let filteredData = data.filter(appointment => {
        // Comprobar si se debe mostrar todos los estados o solo el seleccionado
        return estado === 'todos' || appointment.estado.toLowerCase() === estado.toLowerCase();
    });

    // Ordenar los datos filtrados
    filteredData.sort((a, b) => {
        if (orden === 'medico') {
            // Extraer los números de las cadenas de los médicos
            const numA = parseInt(a.medico.split(' ')[1]) || 0; // Extraer el número del nombre del médico
            const numB = parseInt(b.medico.split(' ')[1]) || 0;
            return numA - numB; // Comparar numéricamente
        } else {
            // Comparar según el criterio seleccionado
            const aValue = a[orden] ? a[orden].toString().toLowerCase() : '';
            const bValue = b[orden] ? b[orden].toString().toLowerCase() : '';
            if (aValue < bValue)
                return -1; // A es menor que B
            if (aValue > bValue)
                return 1;  // A es mayor que B
            return 0; // Son iguales
        }
    });

    // Restablecer la página actual a la primera al filtrar
    currentPage = 1;

    // Cargar la tabla con los datos filtrados
    loadTable(filteredData);
}

// Escuchar eventos de cambio en los selectores
document.getElementById('sort1').addEventListener('change', filterAndSort);
document.getElementById('sort').addEventListener('change', filterAndSort);

// Cargar la tabla inicialmente con todos los datos
loadTable(data);
