package org.maduralang.lexer

fun Char.isWordCharacter(): Boolean = when (this) {
    in 'a'..'z', in 'A'..'Z', in '0'..'9', '_' -> true
    else -> false
}

fun Char.isSymbol(): Boolean = when (this) {
    in '!'..'/', in ':'..'@', in '['..'`', in '{'..'~' -> true
    else -> false
}