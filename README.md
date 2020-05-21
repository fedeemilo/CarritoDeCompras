# Ejercico de Carrito de Compras

## Stack

- Java 11

- Springboot

## Base de Datos

- H2 in memory database

## Documentación

- https://documenter.getpostman.com/view/6596696/Szt5fWnT?version=latest

## Tipos de Carrito:

    - Normal: tipo de carrito normal (tipo = "CARRITO_NORMAL")

    - Promo por fecha especial: los días 15 de cada mes es una fecha de promoción especial (tipo = "CARRITO_FECHA_PROMO")

    - Vip: tipo de carrito con usuario Vip (tipo = "CARRITO_VIP")

## API Carrito de Compras:

### Usuarios:

    - Obtener todos los usuarios         -> GET    /obtenerUsuarios

    - Crear un usuario                   -> POST   /agregarUsuario

            Body:
                - nombreUsuario
                - dniUsuario
                - esVip

    - Actualizar usuario por ID          -> PUT    /actualizarUsuario/{usuarioId}

    - Eliminar usuario por ID            -> DELETE /eliminarUsuario/{usuarioId}

### Productos:

    - Obtener todos los productos        -> GET    /obtenerProductos

    - Obtener producto por ID            -> GET    /encontrarProducto/{productoId}

    - Crear un producto                  -> POST   /agregarProducto

            Body:
                - nombre
                - precio
                - cantidad

    - Actualizar producto por ID         -> PUT    /actualizarProducto/{productoId}

    - Eliminar producto por ID           -> DELETE /borrarProducto/{productoId}

### Carritos:

    - Obtener todos los carritos         -> GET    /obtenerCarritos

    - Consultar estado total ($)         -> GET    /consultarEstadoTotal/{carritoId}

    - Calcular valor del carrito ($)     -> GET    /calcularValorDelCarrito/{carritoId}

    - Crear un carrito nuevo             -> POST   /crearCarrito/{carritoId}

    - Aregar un producto al carrito      -> PUT    /agregarProducto/{carritoId}?productoId={productoId}

    - Eliminar producto del carrito      -> PUT    /eliminarProdDelCarrito/{carritoId}?productoId={productoId}

    - Realizar pago del carrito          -> PUT    /realizarPagoDelCarrito/{carritoId}

    - Eliminar carrito por ID            -> DELETE /eliminarCarrito/{carritoId}
