package org.maduralang.lexer

class Lexer {

    fun scan(input: String): List<Token> {
        val tokens = ArrayList<Token>()
        var pos = 0

        while (pos < input.length) {
            tokens.add(createToken(input, pos))
            pos += 1
        }

        return tokens
    }

    private fun createToken(input: String, pos: Int): Token {
        val c = input[pos]
        when (c) {
            ' ', '\t', in '\n'..'\r' ->
                return Whitespace("$c")

            in 'a'..'z', in 'A'..'Z' ->
                return consumeNameOrKeyword(input, pos)

            in '0'..'9' ->
                return consumeNumber(input, pos)

            in '!'..'/', in ':'..'@', in '['..'`', in '{'..'~' ->
                return consumeSymbol(input, pos)

            else ->
                return Invalid("$c")
        }
    }

    private fun consumeNameOrKeyword(input: String, pos: Int) = Name(consume(input, pos, ::isWordCharacter))

    private fun consumeNumber(input: String, pos: Int) =
        Number(consume(input, pos) { it in '0'..'9' })

    private fun consumeSymbol(input: String, pos: Int) =
        Symbol(consume(input, pos)
        { it in '!'..'/' || it in ':'..'@' || it in '['..'`' || it in '{'..'~' })

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