package org.maduralang.lexer

class Lexer {

    fun scan(input: String): List<Token> {
        val tokens = ArrayList<Token>()
        var pos = 0

        while (pos < input.length) {
            val token = when (val c = input[pos]) {
                ' ', '\t', in '\n'.. '\r' -> Whitespace("$c")
                in 'a'..'z', in 'A'..'Z' -> Name(consume(input, pos, ::isWordCharacter))
                else -> Invalid("$c")
            }

            tokens.add(token)
            pos += 1
        }

        return tokens
    }

    private fun consume(input: String, start: Int, predicate: (Char) -> Boolean): String {
        var pos = start + 1
        while (pos < input.length && predicate(input[pos])) pos += 1
        return input.substring(start, pos)
    }

    private fun isWordCharacter(c: Char): Boolean = when (c) {
        in 'a'..'z', in 'A'..'Z', in '0'..'9', '_' -> true
        else -> false
    }
}