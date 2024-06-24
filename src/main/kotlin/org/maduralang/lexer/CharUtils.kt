package org.maduralang.lexer

inline fun isWhitespace(c: Char): Boolean = when (c) {
    ' ', in '\t'..'\r' -> true
    else -> false
}

inline fun isLetter(c: Char): Boolean = when (c) {
    in 'a'..'z', in 'A'..'Z' -> true
    else -> false
}

inline fun isDigit(c: Char): Boolean = when (c) {
    in '0'..'9' -> true
    else -> false
}

inline fun isDigitOrSeparator(c: Char): Boolean = when (c) {
    in '0'..'9', '_' -> true
    else -> false
}

inline fun isHexDigitOrSeparator(c: Char): Boolean = when (c) {
    in '0'..'9', in 'a'..'f', in 'A'..'F', '_' -> true
    else -> false
}

inline fun isBinDigitOrSeparator(c: Char): Boolean = when (c) {
    '0', '1', '_' -> true
    else -> false
}

inline fun isWordChar(c: Char): Boolean = when (c) {
    in 'a'..'z', in 'A'..'Z', in '0'..'9', '_' -> true
    else -> false
}

inline fun isSymbol(c: Char): Boolean = when (c) {
    in '!'..'/', in ':'..'@', in '['..'`', in '{'..'~' -> true
    else -> false
}

inline fun consume(input: String, start: Int, predicate: (Char) -> Boolean): String {
    var pos = start + 1
    while (pos < input.length && predicate(input[pos])) pos += 1
    return input.substring(start, pos)
}

inline fun lookahead(input: String, pos: Int, predicate: (Char) -> Boolean): Boolean =
    pos < input.length && predicate(input[pos])