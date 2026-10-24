package org.example

import org.example.PedidoTienda.currentState
import org.example.PedidoTienda.monedero
import org.example.PedidoTienda.nombreProducto
import org.example.PedidoTienda.quieres

sealed interface PedidoTiendaState {
    object Carrito : PedidoTiendaState {
        fun onEnter(){
            currentState = Pendiente_Pago(type = monedero)
            println("Bienvenido a la tienda")
            Thread.sleep(2000)
            println("Pasando al pago del producto...")
            Thread.sleep(2000)

        }
        override fun toString(): String {
            return "Carrito"
        }
    }

    data class Pendiente_Pago(val type: Int) : PedidoTiendaState {
        fun onEnter(){
            currentState = Pagado(type = quieres)
            if (!quieres){
                println("No quieres comprar...")
                currentState = Carrito
            }
            else{
                println("Pagando...")
                Thread.sleep(2000)
                if(monedero < nombreProducto.precio){
                    println("Dinero insuficiente")
                    Thread.sleep(2000)
                    currentState = Cancelado("Insuficiente dinero")
                }
                else{
                    Thread.sleep(2000)
                }
            }
        }
        override fun toString(): String {
            return "Pendiente pago"
        }
    }

    data class Pagado(val type: Boolean) : PedidoTiendaState {
        fun onEnter(){
            currentState = Enviado(type = nombreProducto)
            println("Producto pagado")
            Thread.sleep(2000)
            println("Enviando producto...")
            Thread.sleep(2000)

        }
        override fun toString(): String {
            return "Pagado"
        }
    }

    data class Enviado(val type: Producto) : PedidoTiendaState {
        fun onEnter(){
            currentState = Entregado(type = nombreProducto)
            println("El producto a sido enviado")
            Thread.sleep(5000)
            println("Producto siendo entregado...")
            Thread.sleep(2000)

        }
        override fun toString(): String {
            return "Enviado"
        }
    }

    data class Entregado(val type: Producto) : PedidoTiendaState {
        fun onEnter(){
            currentState = Carrito
            println("El producto a sido entregado")
            Thread.sleep(2000)
            println("Gracias por la compra...")
            Thread.sleep(2000)

        }
        override fun toString(): String {
            return "Entregado"
        }
    }

    data class Cancelado(val type: String) : PedidoTiendaState {
        fun onEnter(){
            println("A fallado algo, motivo: "+ String)
            currentState = Carrito

        }
        override fun toString(): String {
            return "Cancelado"
        }
    }
}