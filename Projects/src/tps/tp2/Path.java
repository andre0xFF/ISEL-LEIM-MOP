package tps.tp2;

import tps.tp2.pack2Percursos.PercursoSimples;

import java.lang.reflect.Array;

public interface Path {
    final static String INVALID_DISTANCE = "Invalid distance";
    final static String INVALID_PATH_NAME = "Invalid path name";
    final static String INVALID_LOCATION = "Invalid location name";
    final static String INVALID_SEQUENCE = "Beginning and end sequence does not match";
    final static String INVALID_QUANTITY = "Invalid paths QUANTITY";
    final static String REPEATED_LOCATIONS = "There are paths with repeated locations";

    public String get_beginning();
    public String get_ending();

    /**
     * Valida uma determinada localidade de acordo com as regras de locais
     * @param location Nome da localidade. Aceita apenas numeros e letras
     * @return Resultado da validacao
     */
    public static boolean validate(String location) {
        if (location.trim().length() == 0) {
            return false;
        }

        return location.matches("^[0-9A-Za-z\\s]+$");
    }

    /**
     * Valida a sequencia de dois percursos
     * @param p1 Primeiro percurso
     * @param p2 Segundo percurso
     * @return Resultado da validacao
     */
    public static boolean validate(Path p1, Path p2) {
        return (
                p1.get_ending().equalsIgnoreCase(p2.get_beginning()) &&
                !p1.get_ending().equalsIgnoreCase(p2.get_ending()) &&
                !p1.get_beginning().equalsIgnoreCase(p2.get_beginning())
        );
    }

    /**
     * Valida um conjunto de percursos de acordo com as regras de validacao
     * @param paths Conjunto de percursos
     * @param max Numero maximo de percursos permitidos
     * @return Resultado da validacao
     * @throws IllegalArgumentException Caso haja alguma caracteristica invalida
     */
    public static boolean validate(Path[] paths, int max) throws IllegalArgumentException {

//        paths.length == 0
        if (paths.length > max) {
            throw new IllegalArgumentException(INVALID_QUANTITY);
        }

        for (int i = 1; i < paths.length; i++) {

            if (!Path.validate(paths[i - 1], paths[i])) {
                throw new IllegalArgumentException(INVALID_SEQUENCE);
            }

            String beg = paths[i].get_beginning();
            String end = paths[i].get_ending();

            for (int j = i + 1; j < paths.length; j++) {

                if (beg.equalsIgnoreCase(paths[j].get_beginning())) {
                    throw new IllegalArgumentException(REPEATED_LOCATIONS);
                }

                if (end.equalsIgnoreCase(paths[j].get_ending())) {
                    throw new IllegalArgumentException(REPEATED_LOCATIONS);
                }
            }
        }

        return true;
    }

    /**
     * Insere um percurso a um conjunto de percursos
     * @param paths Conjunto de percursos
     * @param max Numero maximo de percursos permitidos
     * @param idx Indice no qual o percurso sera inserido
     * @param path Percurso a ser inserido
     * @return Novo conjunto de percursos
     */
    public static Path[] insert(Path[] paths, int max, int idx, Path path) {
        int n = paths.length;

        if (n + 1 > max) {
            return null;
        }

        if (idx == -1) {
            idx = n;
        }

        Path[] new_paths = new Path[++n];

        for (int i = 0, j = 0; i < n; i++) {
            if (i == idx) {
                new_paths[i] = path;
                continue;
            }

            new_paths[i] = paths[j++];
        }

        return  new_paths;
    }

    /**
     * Remove uma localidade a um conjunto de percursos
     * @param paths Conjunto de percursos
     * @param factor Factor que dita se a remocao sera feita no inicio ou no final
     *               do conjunto. True caso seja para fazer remocao ao inicio do percurso.
     *               False caso seja para fazer a remocao ao fim do percurso.
     * @param local Localidade a ser removida
     * @return
     */
    public static Path[] remove(Path[] paths, boolean factor, String local) {
        int n = -1;

        for (int i = 0; i < paths.length; i++) {
            if (paths[i].get_beginning().equalsIgnoreCase(local)) {
                n = i;
                break;
            }

        }

        if (n == -1) {
            return null;
        }

        Path[] new_paths = new Path[n];
        Path[] old_paths = new Path[paths.length - n];

        for (int i = 0; i < n; i++) {
            new_paths[i] = paths[i];
        }

        for (int i = n; i < paths.length; i++) {
            old_paths[i - n] = paths[i];
        }

        return factor ? old_paths : new_paths;
    }

    /**
     * Validacao de dois conjuntos de percursos
     * @param p1 Primeiro conjunto de percursos
     * @param max1 Numero maximo de percursos permitidos no primeiro conjunto
     * @param p2 Segundo conjunto de percursos
     * @param max2 Numero maximo de percursos permitidos no segundo conjunto
     * @return Resultado da validacao
     */
    public static boolean validate(Path[] p1, int max1, Path[] p2, int max2) {
        boolean eval = true;

        eval = Path.validate(p1, max1);
        eval = Path.validate(p2, max2);

        if (!eval) {
            return false;
        }

        for (int i = 0; i < p1.length; i++) {
            for (int j = 0; j < p1.length; j++) {
                eval = Path.validate(p1[i], p2[j]);

                if (!eval) {
                     return false;
                }
            }
        }

        return true;
    }
}
