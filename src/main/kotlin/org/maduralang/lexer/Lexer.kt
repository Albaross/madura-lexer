package org.maduralang.lexer

class Lexer {

    fun scan(input: String): List<Token> {
        val tokens = ArrayList<Token>()
        var pos = 0

        while (pos < input.length) {
            val token = createToken(input, pos)
            tokens.add(token)
            pos += token.data.length
        }

        return tokens
    }

    private fun createToken(input: String, pos: Int): Token {
        val c = input[pos]

        if (c.isWhitespace())
            return WhitespaceToken("$c")

        if (c.isLetter())
            return consumeNameOrKeyword(input, pos)

        if (c.isDigit())
            return consumeNumber(input, pos)

        if (c == '@')
            return MetaToken(consume(input, pos) { it.isWordCharacter() })

        if (c.isSymbol())
            return consumeSymbol(input, pos)

        return InvalidToken("$c")
    }

    private fun consumeNameOrKeyword(input: String, pos: Int): Token {
        val lexeme = consume(input, pos) { it.isWordCharacter() }
        return if (lexeme.isKeyword()) KeywordToken(lexeme) else NameToken(lexeme)
    }

    private fun consumeNumber(input: String, pos: Int): Token {
        return NumberToken(consume(input, pos) { it.isDigit() })
    }

    private fun consumeSymbol(input: String, pos: Int): Token {
        return SymbolToken(consume(input, pos) { it.isSymbol() })
    }

    private fun consume(input: String, start: Int, predicate: (Char) -> Boolean): String {
        var pos = start + 1
        while (pos < input.length && predicate(input[pos])) pos += 1
        return input.substring(start, pos)
    }
}