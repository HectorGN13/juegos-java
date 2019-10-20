import java.util.*;
/**
 * Clase tablero, es la encargada de devolver si la combinacion introducida ha sido corrcta asi como generar las pistas
 * en funcion de la combinacion introducida. Esta clase se crea a partir de un CodigoSecreto.
 * @version 0.1
 * @author Hector E. Garcia Nuñez
 */
public class Tablero {

    private LinkedHashMap<CodigoSecreto, CodigoSecreto> tablero =
            new LinkedHashMap<>(0,0.75F,false);
    private CodigoSecreto combinacionSecreta;


    //Mapa del Tablero.
    int[][] mapa = {{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
                    {2,1,1,3,1,1,2,2,2,1,4,1,1,1,2},
                    {2,1,1,3,1,1,2,2,2,1,4,1,1,1,2},
                    {2,1,1,3,1,1,2,2,2,1,4,1,1,1,2},
                    {2,1,1,3,1,1,2,2,2,1,4,1,1,1,2},
                    {2,1,1,3,1,1,2,2,2,1,4,1,1,1,2},
                    {2,1,1,3,1,1,2,2,2,1,4,1,1,1,2},
                    {2,1,1,3,1,1,2,2,2,1,4,1,1,1,2},
                    {2,1,1,3,1,1,2,2,2,1,4,1,1,1,2},
                    {2,1,1,3,1,1,2,2,2,1,4,1,1,1,2},
                    {2,1,1,1,1,1,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,1,1,1,1,1,2},
                    {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
                    {2,1,1,1,1,1,1,1,1,1,1,6,1,1,1,1,1,1,1,1,1,1,2},
                    {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}};

    /**
     * Constructor para la clase tablero solo necesita una combinación secreta para crear el tablero.
     * @param combinacionSecreta CodigoSecreto.
     * @see CodigoSecreto
     */
    Tablero(CodigoSecreto combinacionSecreta) {
        try {
            setCombinacionSecreta(combinacionSecreta);
        } catch (NullPointerException n){
            n.printStackTrace();
        }
    }

    //////////////
    // MUTADOR //
    /////////////

    /**
     * Mutador para el atributo combinacion secreta.
     * @param combinacionSecreta CodigoSecreto
     * @throws NullPointerException Arroja esta excepcion cuando el parametro introducido es null.
     */
    private void setCombinacionSecreta(CodigoSecreto combinacionSecreta) throws NullPointerException {
        if (combinacionSecreta == null)
            throw new NullPointerException("La combinacion secreta no puede ser nula.");
        this.combinacionSecreta = combinacionSecreta;
    }

    ////////////////
    // ACCESORES //
    ///////////////

    /**
     * Metodo accesor que devuelve la combinacion secreta.
     * @return CodigoSecreto
     */
    public CodigoSecreto getCombinacionSecreta() {
        return combinacionSecreta;
    }

    /**
     * Metodo accesor que devuelve el tablero
     * @return TreeMap\<CodigoSecreto, CodigoSecreto>
     */
    public LinkedHashMap<CodigoSecreto, CodigoSecreto> getTablero() {
        return tablero;
    }

    public int[][] getMapa() {
        return mapa;
    }

    ///////////////////
    // OTROS METODOS //
    ///////////////////

    /**
     * Metodo privado encangado de generar la pista para descifrar el codigo secreto, si alguna de las piezas esta
     * colocada en su sitio generará una ficha de color blanco, si esa ficha no esta colocada en su sitio pero esta
     * dentro de la combinacion generara una ficha de color negro, no generara nada si no hay coincidencias.
     * @param c CodigoSecreto
     * @return CodigoSecreto generado a partir de las coincidencias
     * @throws NullPointerException Lanzara esta excepcion en el caso que se introduzca como parametro un null.
     */
    private CodigoSecreto generarPista(CodigoSecreto c) throws NullPointerException {
        if (c == null)
            throw new NullPointerException("Error el parametro introducido no puede ser null");
        Ficha[] arrayFichas = new Ficha[c.getCombinacion().length];
        for (int i = 0; i < c.getCombinacion().length; i++) {
            if (c.getCombinacion()[i] == null){
                if (this.combinacionSecreta.getCombinacion()[i] == null){
                    arrayFichas[i] = new Ficha(TipoColor.BLANCO);
                } else {
                    for (int j = 0; j < c.getCombinacion().length; j++) {
                        if (c.getCombinacion()[j] == null){
                            arrayFichas[i] = new Ficha(TipoColor.NEGRO);
                        }
                    }
                }
            } else if (c.getCombinacion()[i].equals(this.combinacionSecreta.getCombinacion()[i])){
                arrayFichas[i] = new Ficha(TipoColor.BLANCO);
            } else {
                for (int j = 0; j < c.getCombinacion().length; j++) {
                    if (c.getCombinacion()[i].equals(this.combinacionSecreta.getCombinacion()[j])){
                        arrayFichas[i] = new Ficha(TipoColor.NEGRO);
                    }
                }
            }
        }
        return new CodigoSecreto(arrayFichas);
    }



    /**
     * Metodo que devuelve un boolean true si el ultimo elemento introducido en tablero coincide con el CodigoSecreto.
     * @return boolean, true en el caso de que el ultimo elemento añadido al tablero coincida con la combinacion secreta.
     */
    boolean esVictoria() {
        Set<Map.Entry<CodigoSecreto, CodigoSecreto>> entradas = tablero.entrySet();
        Iterator<Map.Entry<CodigoSecreto, CodigoSecreto>> it = entradas.iterator();
        boolean result = false;
        while (it.hasNext()) {
            Map.Entry<CodigoSecreto, CodigoSecreto> ultimoElemento = it.next();
            result = ultimoElemento.getKey().equals(combinacionSecreta);
        }
        return result;
    }

    /**
     * Metodo que devuelve un boolean true si el ultimo elemento introducido en tablero supera la longitud de 9 intentos.
     * @return boolean, true en el caso de que el ultimo elemento añadido al tablero sea el 10 elemento.
     */
    boolean esDerrota(){
        Set<Map.Entry<CodigoSecreto, CodigoSecreto>> entradas = tablero.entrySet();
        boolean result = false;
        if(entradas.size()>8){
            result = true;
        }
        return result;
    }

    /**
     * Metodo publico que agrega valores al tablero y su resultado.
     * @param clave CodigoSecreto
     */
    void agregarValores(CodigoSecreto clave){
        try {
            tablero.put(clave, generarPista(clave));
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    /**
     * Representacion grafica a traves de consola de la clase tablero. Se representa con la clave que es el codigo
     * introducida por el usuario y por el valor que es el codigo resultante segun los aciertos obtenidos.
     * @return String Clave.toString # Valor.toString
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");

        Set<Map.Entry<CodigoSecreto,CodigoSecreto>> entrada = tablero.entrySet();
        List<CodigoSecreto> listaDeValores = new ArrayList<>(tablero.values());
        List<CodigoSecreto> listaDeKeys = new ArrayList<>(tablero.keySet());

        modificadorArrayMapa(entrada);
        try {
           result = new StringBuilder(dibujarTablero(listaDeValores, listaDeKeys));
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return String.valueOf(result);
    }

    /**
     * Metodo que se encarga de dibujar el mapa que representa al tablero de la partida
     * como usaremos modo texto para fururas modificaciones intentare que el tablero mida distinto ancho
     * segun el nivel de dificultad de la partida. De momento esta hecho para 5 fichas
     * @return String con el tablero modificado segun los elementos del LinkedHashMap(tablero)
     */
    private String dibujarTablero(List<CodigoSecreto> listaDeValores, List<CodigoSecreto> listaDeClaves) throws NullPointerException{
        if (listaDeClaves == null){
            throw new NullPointerException("La lista de claves no puede ser null");
        } else if (listaDeValores == null){
            throw new NullPointerException("La lista de valores no puede ser null");
        }

        StringBuilder datos = new StringBuilder();
        int numeritosY = 0;


        for (int y = 0; y < mapa.length; y++) {
            if (y > 0 && y < 10){
                datos.append(numeritosY).append(" ");
            } else
                datos.append("  ");
            for (int x = 0; x < mapa[y].length; x++) {
                switch (mapa[y][x]) {
                    case 0:
                        datos.append(listaDeClaves.get(y - 1));
                        break;
                    case 1:
                        datos.append(" ");
                        break;
                    case 2:
                        datos.append("\033[33m" + "\uA671" + "\033[0m");
                        break;
                    case 3:
                        datos.append("\u25E6\u25E6\u25E6\u25E6\u25E6");
                        break;
                    case 4:
                        datos.append("\u25E6\u25E6\u25E6\u25E6\u25E6");
                        break;
                    case 5:
                        datos.append(listaDeValores.get(y - 1));
                        break;
                    case 6:
                        datos.append("\u25E6\u25E6\u25E6\u25E6\u25E6");
                        break;
                    case 7:
                        datos.append(combinacionSecreta.toString());
                        break;
                    default:
                        assert false;
                }
            }
            datos.append("\n");
            numeritosY++;
        }
        return datos.toString();
    }

    /**
     * Este metodo se encarga de modificar el array mapa para que luego el metodo dibujar tablero
     * pinte lo que sea necesario. Tambien en caso de que sea victoria o derrota pintara el codigo secreto debajo del
     * tablero.
     * @param entrada un conjunto que contiene una entrada de mapas.
     * @throws NullPointerException en el caso de que la entrada de elementos sea nula.
     */
    private void modificadorArrayMapa(Set<Map.Entry<CodigoSecreto,CodigoSecreto>> entrada) throws NullPointerException{
        if (entrada == null)
            throw new NullPointerException("La entrada de elementos no puede ser null");
        if (!entrada.isEmpty()){
            for (int i = 1; i < entrada.size() + 1 ; i++) {
                for (int j = 0; j < mapa[i].length; j++) {
                    if (mapa[i][j] == 3)
                        mapa[i][j] = 0;
                    if (mapa[i][j] == 4)
                        mapa[i][j] = 5;
                }
            }
        }
        if (esDerrota() || esVictoria()){
            for (int i = 0; i < mapa.length; i++) {
                for (int j = 0; j < mapa[i].length; j++) {
                    if (mapa[i][j] == 6)
                        mapa[i][j] = 7;
                }
            }
        }
    }


}


