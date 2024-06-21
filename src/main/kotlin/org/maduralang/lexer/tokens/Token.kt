package org.maduralang.lexer.tokens

interface Token {
    val data: String
    val type: TokenType
}

enum class TokenType {

}