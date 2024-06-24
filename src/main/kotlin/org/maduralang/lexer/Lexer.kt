package org.maduralang.lexer

class Lexer {

    fun scan(input: String, ignoreWhitespaces: Boolean = true): List<Token> {
        val tokens = ArrayList<Token>()
        var pos = 0

        while (pos < input.length) {
            val token = createToken(input, pos)
            pos += token.data.length

            if (!ignoreWhitespaces || token !is WhitespaceToken)
                tokens.add(token)
        }

        return tokens
    }

    private fun createToken(input: String, pos: Int): Token {
        val c = input[pos]

        if (isWhitespace(c))
            return WhitespaceToken("$c")

        if (isLetter(c))
            return consumeNameOrKeyword(input, pos)

        if (isDigit(c))
            return consumeNumber(input, pos)

        if (c == '.' && lookahead(input, pos + 1, ::isDigit))
            return NumberToken(consume(input, pos, ::isDigitOrSeparator))

        if (isQuoteChar(c))
            return StringToken(consumeEnclosed(input, pos, delimiter = c))

        if (c == '@')
            return MetaToken(consume(input, pos, ::isWordChar))

        if (isSymbol(c))
            return consumeSymbol(input, pos)

        return InvalidToken("$c")
    }

    private fun consumeNameOrKeyword(input: String, pos: Int): Token {
        val lexeme = consume(input, pos, ::isWordChar)
        return if (lexeme.isKeyword()) KeywordToken(lexeme) else NameToken(lexeme)
    }

    private fun consumeNumber(input: String, pos: Int): Token {
        val integerPart = consume(input, pos, ::isDigitOrSeparator)
        val intermediatePos = pos + integerPart.length

        if (lookahead(input, intermediatePos) { it == '.' }
            && lookahead(input, intermediatePos + 1, ::isDigit)) {

            val fractionPart = consume(input, intermediatePos, ::isDigitOrSeparator)
            return NumberToken(integerPart + fractionPart)
        }

        return NumberToken(integerPart)
    }

    private fun consumeSymbol(input: String, pos: Int): Token {
        return SymbolToken(consume(input, pos, ::isSymbol))
    }


}