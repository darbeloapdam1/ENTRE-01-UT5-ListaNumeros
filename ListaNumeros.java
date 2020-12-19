
/**
 * La clase representa a una lista de 
 * números enteros
 * 
 * @author - Diego Arbeloa
 * 
 */
import java.util.Arrays;

public class ListaNumeros 
{
    // definir atributos
    private int pos;
    private int[] lista;
    /**
     * Constructor de la clase ListaNumeros 
     * Crea e inicializa adecuadamente los
     * atributos
     * 
     * @param n el tamaño máximo de la lista
     */
    public ListaNumeros(int n) {
        pos = 0;
        lista = new int[n];
    }

    /**
     * Añade un valor siempre al principio de la lista
     * 
     * @param numero el valor que se añade. No se hace nada si la lista está
     *               completa
     * @return true si se ha podido añadir, false en otro caso
     */
    public boolean addElemento(int numero) {
        if(!estaCompleta()){
            for(int i = pos; i > 0; i--){
                lista[i] = lista[i - 1];
            }
            lista[0] = numero;
            pos++;
            return true;
        }
        return false;
    }

    /**
     * devuelve true si la lista está completa, false en otro caso
     * Hacer sin if
     */
    public boolean estaCompleta() {
        return lista.length == pos;
    }

    /**
     * devuelve true si la lista está vacía, false en otro caso. 
     * Hacer sin if
     */
    public boolean estaVacia() {
        return pos == 0;
    }

    /**
     * devuelve el nº de elementos realmente guardados en la lista
     */
    public int getTotalNumeros() {
        return pos;
    }

    /**
     * Vacía la lista
     */
    public void vaciarLista() {
        pos = 0;
    }

    /**
     * Representación textual de la lista de la forma indicada 
     * (leer enunciado)
     * 
     * Si la lista está vacía devuelve ""
     */
    public String toString() {
        String rep = "";
        if(!estaVacia()){
            for(int i = 0; i < pos; i++){
                String str = String.format("%8d", lista[i]);
                rep += str;
            }
            rep += "\n";
            int num = 0;
            for(int j = 0; j < pos; j++){
                rep += String.format("%8d", num);
                num++;
            }
        }
        return rep;
    }

    /**
     * Mostrar en pantalla la lista
     */
    public void escribirLista() {
        System.out.println(this.toString());
    }

    /**
     * Búsqueda lineal de numero en la lista
     * @param numero el nº a buscar
     * @return un array con las posiciones en las que se ha encontrado
     *  
     */
    public int[] buscarPosicionesDe(int numero) {
        int cantidadNumero = 0;
        for(int i = 0; i < lista.length; i++){
            if(lista[i] == numero){
                cantidadNumero++;
            }
        }
        int posicion = 0;
        int[] aPosicion = new int[cantidadNumero];
        for(int j = 0; j < lista.length; j++){
            if(lista[j] == numero){
                aPosicion[posicion] = j;
                posicion++;
            }
        }
        return aPosicion;
    }

    /**
     * Hace una búsqueda binaria del numero indicado devolviendo -1 si no se
     * encuentra o la posición en la que aparece
     * 
     * El array original lista no se modifica 
     * Para ello crea previamente una copia
     * de lista y trabaja con la copia
     * 
     * Usa exclusivamente métodos de la clase Arrays
     * 
     */
    public int buscarBinario(int numero) {
        int[] copia = new int[lista.length];
        copia = Arrays.copyOf(lista, (lista.length - 1));
        Arrays.sort(copia);
        int posicion = Arrays.binarySearch(copia, numero);
        if(posicion > 0){
            return posicion;
        }else{
            return -1;
        }
    }

    /**
     * borra el primer elemento de la lista
     */
    public void borrarPrimero() {
        pos--;
        for(int i = 0; i < pos; i++){
            lista[i] = lista[i + 1];
        }        
    }

    /**
     *  Invierte cada uno de los grupos de n elementos que hay en lista
     *  
     *  Si el nº de elementos en lista no es divisible entre n los elementos restantes 
     *  quedan igual
     *  
     *  (leer enunciado)
     *  
     */
    public void invertir(int n) {
        int grupos = lista.length / n;
        int izq = 0;
        int dch = n - 1;
        for(int i = 1; i <= grupos; i++){
            for(int j = 0; j < n / 2; j++){
                int aux = lista[izq];
                lista[izq] = lista[dch];
                lista[dch] = aux;
                izq++;
                dch--;
            }
            izq = n * i;
            dch = (n * (i + 1)) - 1;
        }
    }

    /**
     * devuelve un ragged array de 2 dimensiones con tantas filas como valores
     * tenga el atributo lista y rellena el array de la forma indicada
     * (leer enunciado)
     * 
     */
    public int[][] toArray2D() {
        int[][] array = new int[lista.length][];
        if(lista.length == 1){
            array[0] = new int[1];
            array[0][0] = 1;
        }else if(lista.length == 2){
            array[0] = new int[1];
            array[1] = new int[2];
            array[0][0] = 1;
            array[1][0] = 1;
            array[1][1] = 1;
        }else{
            array[0] = new int[1];
            array[1] = new int[2];
            array[0][0] = 1;
            array[1][0] = 1;
            array[1][1] = 1;
            for(int i = 2; i < lista.length; i++){
                array[i] = new int[i + 1];
                array[i][0] = 1;
                array[i][i] = 1;
                for(int j = 1; j < i; j++){
                    array[i][j] = array[i - 1][j - 1] + array[i - 1][j];
                }
            }
        }
        return array;
    }

    /**
     * Punto de entrada a la aplicación 
     * Contiene código para probar los métodos de ListaNumeros
     */
    public static void main(String[] args) {
        ListaNumeros lista = new ListaNumeros(20);

        System.out.println("--- addElemento() y toString() -------");
        int[] valores = {21, -5, 6, -7, 21, -17, 21, 15, 22, 21, 77};
        for (int i = 0; i < valores.length; i++) {
            lista.addElemento(valores[i]);
        }
        System.out.println(lista.toString());

        System.out.println("--- buscarPosiciones() -------");
        int numero = 21;
        System.out.println(lista.toString());
        System.out.println("\t" + numero + " aparece en posiciones ");
        // seguir completando

    }
}
