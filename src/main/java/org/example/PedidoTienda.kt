package org.example


/**
 * Singleton que maneja lo que ocurre en cada estado
 */
object PedidoTienda {
    var currentState: PedidoTiendaState = PedidoTiendaState.Carrito
    var monedero: Int = 0
    var quieres: Boolean = true
    var tracking_id: String = ""
    var nombreProducto: Producto =Producto("Ford Focus", 20000)

    fun gestorEstados() {

        when (val state = currentState) {
            is PedidoTiendaState.Carrito -> state.onEnter()

            is PedidoTiendaState.Pendiente_Pago -> state.onEnter()

            is PedidoTiendaState.Pagado -> state.onEnter()

            is PedidoTiendaState.Enviado -> state.onEnter()

            is PedidoTiendaState.Entregado -> state.onEnter()

            is PedidoTiendaState.Cancelado -> state.onEnter()

        }
    }
}