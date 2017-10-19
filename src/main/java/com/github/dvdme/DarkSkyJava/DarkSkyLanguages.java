package com.github.dvdme.DarkSkyJava;
// TODO: move to utils
public enum DarkSkyLanguages {
    BOSNIAN("bs"),
    GERMAN("de"),
    ENGLISH("en"),
    SPANISH("es"),
    FRENCH("fr"),
    ITALIAN("it"),
    DUTCH ("nl"),
    POLISH ("pl"),
    PORTUGUESE("pt"),
    TETUM("tet"),
    PIG_LATIN("x-pig-latin"),
    RUSSIAN ("ru")
    ;

    private final String lang;

    private DarkSkyLanguages(final String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return lang;
    }
}
