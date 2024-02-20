public class Main {
    public static void main(String[] args) {

    }
/**Estructuras-de-Datos-en-Java */
    /**
     * Implementación de una lista enlazada simple
     * Operaciones soportadas son:
     * - Insertar un elemento en la lista - Esto puede ser al principio, al final o en una posición dada.
     * - Recorrer la lista enlazada.
     * - Comprobar el tamaño de la lista.
     * - Comprobar si la lista está vacía.
     * - Buscar un elemento por índice.
     * - Buscar un elemento por valor.
     * - Eliminar un elemento de la lista - Esto puede ser nuevamente al principio, al final o en una posición dada.
     * - Convertir un Array en una lista enlazada.
     */
    public static class ListaEnlazada<E> {
        /** Se necesita la cabeza para llevar el seguimiento del primer nodo */
        private Node<E> cabeza;
        /** Tamaño para llevar el seguimiento del número de elementos en la lista. Debe aumentar 1 cuando se agrega un elemento y debería reducirse en 1 cuando se elimina un elemento */
        private int tamaño = 0;
        /** Inserta un elemento en una lista enlazada en la posición de la cabeza.  @param value  */
        public void insertarEnCabeza(E value) {
            Node<E> nuevoNodo = new Node<E>(value);
            nuevoNodo.next = cabeza;
            cabeza = nuevoNodo;
            tamaño++;
        }
        /** Inserta un elemento en una lista enlazada en la posición de la cola.   @param value*/
        public void insertarEnCola(E value) {
            Node<E> nuevoNodo = new Node<E>(value);
            nuevoNodo.next = null;
            /* Dado que esta inserción está en la cola, este nuevo nodo apuntará a null */
            if (null == cabeza) {
                /* Cuando la lista está vacía */
                cabeza = nuevoNodo;
            } else {
                Node<E> temporalNodo = cabeza;
                while (null != temporalNodo.next) {
                    temporalNodo = temporalNodo.next;
                }
                temporalNodo.next = nuevoNodo;
            }
            tamaño++;
        }
        /** Inserta un elemento en una lista enlazada en una posición dada. @param value @param position */
        public void insertarEnPosicion(E value, int posicion) {
            if (posicion < 0 || posicion > tamaño) {
                throw new IllegalArgumentException("La posición no es válida");
            }
            /* Las condiciones de verificación pasaron, vamos a insertar el nodo */
            Node<E> nuevoNodo = new Node<E>(value);
            if (posicion == 0) {
                nuevoNodo.next = cabeza;
            } else {
                Node<E> temporalNodo = cabeza;
                for (int i = 0; i < posicion - 1; i++) {
                    temporalNodo = temporalNodo.next;
                }
                Node<E> nodoSiguienteAlNuevoNodo = temporalNodo.next;
                temporalNodo.next = nuevoNodo;
                nuevoNodo.next = nodoSiguienteAlNuevoNodo;
            }
            tamaño++;
        }
        /** Recorre la lista enlazada e imprime los elementos */
        public void recorrerLista() {
            Node<E> temporal = cabeza;
            while (temporal != null) {
                System.out.println(temporal.item);
                temporal = temporal.next;
            }
        }
        /** Devuelve el tamaño de la lista enlazada  * @return {@link int} */
        public int tamaño() {
            return tamaño;
        }
        /** Devuelve true si la lista está vacía * @return {@link boolean} */
        public boolean estaVacio() {
            return tamaño == 0;
        }
        /**
         * Devuelve el nodo que contiene el elemento de datos después de buscar por un índice dado.
         * @param indice
         * @return {@link Node<E>}
         */
        public Node<E> buscarPorIndice(int indice) {
            if (indice < 0 || indice >= tamaño) {
                throw new IndexOutOfBoundsException("Se pasó un índice inválido al buscar un valor");
            }
            /* Validación pasada, vamos a buscar el valor usando el índice */
            Node<E> temp = cabeza;
            for (int i = 0; i < indice; i++) {
                /* Comenzamos desde 0 y vamos hasta uno menos que el índice
                 * porque estamos saltando al siguiente nodo dentro del bucle */
                temp = temp.next;
            }
            return temp;
        }

        /**
         * Devuelve el nodo que contiene el elemento de datos después de buscar por un valor dado. Si hay varios valores iguales, se devolverá el primero.
         * @param value
         * @return {@link Node<E>} */
        public Node<E> buscarPorValor(E value) {
            /* Recorre cada nodo hasta que se encuentre este valor */
            Node<E> temporal = cabeza;
            while (null != temporal.next && temporal.item != value) {
                temporal = temporal.next;
            }
            if (temporal.item == value
            ) {
                return temporal;
            }
            return null;
        }

        /** Elimina el elemento presente en el nodo de la cabeza */
        public void eliminaNodoCabeza() {
            /* Si la lista está vacía, retorna */
            if (null == cabeza) {
                return;
            }
            /* Actualiza la cabeza y reduce el tamaño */
            cabeza = cabeza.next;
            tamaño--;
        }
        /** Elimina el elemento presente en el nodo de la cola */
        public void eliminaNodoCola() {
            /* Si la cabeza es nula, no hay nada que eliminar */
            if (null == cabeza) {
                return;
            }
            /* Mantén un puntero en el nodo de la cabeza y el siguiente nodo. Sigue avanzando hasta que el siguiente se convierta en nulo */
            Node<E> nodoActual = cabeza;
            Node<E> nodoSiguiente = nodoActual.next;
            while (nodoActual.next != null && nodoSiguiente.next != null) {
                nodoActual = nodoActual.next;
                nodoSiguiente = nodoSiguiente.next;
            }
            /* Ahora estamos eliminando desde la cola, así que el nodo de cola - 1 apuntará a null */
            nodoActual.next = null;
            tamaño--;
        }
        /** Elimina el elemento presente en la posición del índice @param position */
        public void eliminaPorPosicion(int posicion) {
            if (posicion < 0 || posicion >= tamaño) {
                throw new IllegalArgumentException("La posición no es válida");
            }
            /* Las condiciones de verificación pasaron, vamos a eliminar el nodo */
            Node<E> nodoAEliminar = cabeza;
            for (int i = 0; i < posicion; i++) {
                nodoAEliminar = nodoAEliminar.next;
            }
            if (nodoAEliminar.next == null) {
                /* Si este es el último nodo */
                eliminaNodoCola();
            } else {
                nodoAEliminar.item = nodoAEliminar.next.item;
                nodoAEliminar.next = nodoAEliminar.next.next;
            }
        }
        /** Devuelve un array que contiene cada elemento de la lista desde el principio hasta el final
         * @return {@link Object[]} */
        public Object[] paraArray() {
            /* Crea un array de objetos del mismo tamaño */
            Object[] resultado = new Object[tamaño];
            int i = 0;
            for (Node<E> x = cabeza; x != null; x = x.next) {
                /* Toma cada nodo y agrégalo al array en la posición apropiada */
                resultado[i++] = x.item;
            }
            return resultado;
        }
        /**Clase de nodo de una lista enlazada
         * Esto es necesario ya que toda la lista enlazada es una colección de nodos conectados entre sí a través de enlaces
         * Lo mantenemos genérico para que pueda ser usado con Integer, String u otra cosa
         * Cada nodo contiene un elemento de datos y un puntero al siguiente nodo. Dado que esta es una lista enlazada simple y cada nodo apunta en
         * una dirección, mantenemos solo un puntero a un (siguiente) nodo. @author Deepak @param <T>*/
        public class Node<T> {
            /* Elemento de datos en el nodo */
            T item;
            /* Puntero al siguiente nodo */
            Node<T> next;
            /* Constructor para crear un nodo */
            public Node(T item) {
                this.item = item;
            }
            /* Implementación de toString para imprimir solo los datos */
            @Override
            public String toString() {
                return "Elemento de datos = " + item;
            }
        }
    }
}