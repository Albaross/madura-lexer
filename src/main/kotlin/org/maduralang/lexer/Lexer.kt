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

        if (c.isWhitespace())
            return Whitespace("$c")

        if (c.isLetter())
            return consumeNameOrKeyword(input, pos)

        if (c.isDigit())
            return consumeNumber(input, pos)

        if (c.isSymbol())
            return consumeSymbol(input, pos)

        return Invalid("$c")
    }

    private fun consumeNameOrKeyword(input: String, pos: Int): Token {
        return Name(consume(input, pos) { it.isWordCharacter() })
    }

    private fun consumeNumber(input: String, pos: Int): Token {
        return Number(consume(input, pos) { it.isDigit() })
    }

    private fun consumeSymbol(input: String, pos: Int): Token {
        return Symbol(consume(input, pos) { it.isSymbol() })
    }

    private fun consume(input: String, start: Int, predicate: (Char) -> Boolean): String {
        var pos = start + 1
        while (pos < input.length && predicate(input[pos])) pos += 1
        return input.substring(start, pos)
    }
}