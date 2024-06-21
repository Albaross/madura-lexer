package org.maduralang.lexer

interface Token {
    val data: String
    val type: TokenType
}

data class Whitespace(override val data: String) : Token {
    override val type: TokenType get() = TokenType.WHITESPACE
    override fun toString(): String = "$type"
}

data class Name(override val data: String) : Token {
    override val type: TokenType get() = TokenType.NAME
    override fun toString(): String = "$data : $type"
}

data class Keyword(override val data: String) : Token {
    override val type: TokenType get() = TokenType.KEYWORD
    override fun toString(): String = "$data : $type"
}

data class Number(override val data: String) : Token {
    override val type: TokenType get() = TokenType.NUMBER
    override fun toString(): String = "$data : $type"
}

data class Symbol(override val data: String) : Token {
    override val type: TokenType get() = TokenType.SYMBOL
    override fun toString(): String = "$data : $type"
}

data class Meta(override val data: String) : Token {
    override val type: TokenType get() = TokenType.META
    override fun toString(): String = "$data : $type"
}

data class Invalid(override val data: String) : Token {
    override val type: TokenType get() = TokenType.INVALID
    override fun toString(): String = "$type"
}

enum class TokenType {
    WHITESPACE,
    NAME,
    KEYWORD,
    NUMBER,
    SYMBOL,
    META,
    INVALID
}