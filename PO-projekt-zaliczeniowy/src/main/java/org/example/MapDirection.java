package org.example;

enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST,
    NORTHEAST,
    SOUTHEAST,
    NORTHWEST,
    SOUTHWEST;

    public String toString(){
        return switch (this) {
            case NORTH -> "Północ";
            case SOUTH -> "Południe";
            case WEST -> "Zachód";
            case EAST -> "Wschód";
            case NORTHEAST -> "Pólnocny wschód";
            case SOUTHEAST -> "Południowy wschód";
            case NORTHWEST -> "Północny zachód";
            case SOUTHWEST -> "Południowy zachód";
        };
    }

    public MapDirection next(){
        return switch (this) {
            case NORTH -> NORTHEAST;
            case SOUTH -> SOUTHWEST;
            case WEST -> NORTHWEST;
            case EAST -> SOUTHEAST;
            case NORTHEAST -> EAST;
            case SOUTHEAST -> SOUTH;
            case NORTHWEST -> NORTH;
            case SOUTHWEST -> WEST;
        };
    }



//    public Vector2d toUnitVector(){
//        return switch (this) {
//            case NORTH -> new Vector2d(0, 1);
//            case SOUTH -> new Vector2d(0, -1);
//            case WEST -> new Vector2d(-1, 0);
//            case EAST -> new Vector2d(1, 0);
//            case NORTHEAST -> new Vector2d(1, 1);
//            case NORTHWEST -> new Vector2d(-1, 1);
//            case SOUTHEAST -> new Vector2d(1, -1);
//            case SOUTHWEST -> new Vector2d(-1, -1);
//        };
//    }
}

