package tps.tp2;

import tps.tp2.pack2Percursos.PercursoSimples;

public interface Path {
    final static String INVALID_DISTANCE = "Invalid distance";
    final static String INVALID_PATH_NAME = "Invalid path name";
    final static String INVALID_LOCATION = "Invalid location name";
    final static String INVALID_SEQUENCE = "Beginning and end sequence does not match";
    final static String INVALID_QUANTITY = "Invalid paths quantity";
    final static String REPEATED_LOCATIONS = "There are paths with repeated locations";

    public String get_beginning();
    public String get_ending();

    public static boolean validate(Path p1, Path p2) {
        return p1.get_ending().equalsIgnoreCase(p2.get_beginning());
    }

    public static Path[] validate(Path[] paths, int max) throws IllegalArgumentException {

        if (paths.length == 0 || paths.length > max) {
            throw new IllegalArgumentException(INVALID_QUANTITY);
        }

        String[] beginnings = new String[paths.length];
        String[] endings = new String[paths.length];

        for (int i = 0; i < paths.length; i++) {

            if (i < paths.length - 1 && !Path.validate(paths[i], paths[i + 1])) {
                throw new IllegalArgumentException(INVALID_SEQUENCE);
            }

            beginnings[i] = paths[i].get_beginning();
            endings[i] = paths[i].get_ending();

            for (int j = i + 1; j < paths.length; j++) {
                if (beginnings[i].equalsIgnoreCase(paths[j].get_beginning())
                        || endings[i].equalsIgnoreCase(paths[j].get_ending())
                        ) {
                    throw new IllegalArgumentException(REPEATED_LOCATIONS);
                }
            }
        }

        return paths;
    }

    public static Path[] insert(Path[] paths, int max, int idx, PercursoSimples path) {
        int n = paths.length;

        if (n + 1 > max) {
            return null;
        }

        if (idx == -1) {
            idx = n;
        }

        Path[] new_paths = new PercursoSimples[++n];

        for (int i = 0, j = 0; i < n; i++) {
            if (i == idx) {
                new_paths[i] = path;
                continue;
            }

            new_paths[i] = paths[j++];
        }

        Path.validate(new_paths, max);
        return new_paths;
    }

    /**
     *
     * @param factor Caso seja True remove no inicio do array, caso contratrio no final
     * @param local
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

        Path[] new_paths = new PercursoSimples[n];
        Path[] old_paths = new PercursoSimples[n];

        for (int i = 0; i < n; i++) {
            new_paths[i] = paths[i];
        }

        for (int i = n; i < paths.length; i++) {
            old_paths[i - n] = paths[i];
        }

        if (factor) {
            paths = new_paths;
            return old_paths;
        }
        else {
            paths = old_paths;
            return new_paths;
        }

    }
}
