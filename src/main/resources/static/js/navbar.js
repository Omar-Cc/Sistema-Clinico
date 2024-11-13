document.addEventListener('DOMContentLoaded', function () {
    const offcanvasEl = document.getElementById('offcanvasNavbar');
    const offcanvas = new bootstrap.Offcanvas(offcanvasEl);
    const arrowLeft = document.getElementById('arrowLeft');
    const userModalElement = document.getElementById('userModal');
    const userModal = userModalElement ? new bootstrap.Modal(userModalElement) : null;

    // Mostrar el offcanvas y ocultar la flecha izquierda
    if (arrowLeft) {
        arrowLeft.addEventListener('click', function () {
            offcanvas.show();
            offcanvasEl.classList.add('show-offcanvas');
            arrowLeft.style.display = 'none';  // Oculta la flecha izquierda
        });
    }

    // Mostrar la flecha izquierda al cerrar el offcanvas
    if (offcanvasEl) {
        offcanvasEl.addEventListener('hidden.bs.offcanvas', function () {
            arrowLeft.style.display = 'block';  // Muestra la flecha izquierda
        });
    }

    // Mostrar el modal de usuario
    if (userModalElement) {
        document.querySelector('.navbar-brand')?.addEventListener('click', function () {
            userModal.show();
        });
    }

});