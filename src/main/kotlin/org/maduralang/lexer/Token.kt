package org.maduralang.lexer

interface Token {
    val data: String
    val type: TokenType
}

data class WhitespaceToken(override val data: String) : Token {
    override val type: TokenType get() = TokenType.WHITESPACE
    override fun toString(): String = "$type"
}

interface WordToken : Token

data class NameToken(override val data: String) : WordToken {
    override val type: TokenType get() = TokenType.NAME
    override fun toString(): String = "$data : $type"
}

data class KeywordToken(override val data: String) : WordToken {
    constructor(keyword: Keyword) : this(keyword.name.lowercase())

    override val type: TokenType get() = TokenType.KEYWORD
    override fun toString(): String = "$data : $type"
    fun asKeyword(): Keyword = Keyword.valueOf(data.uppercase())
}

data class NumberToken(override val data: String) : Token {
    constructor(number: Number) : this(number.toString())

    override val type: TokenType get() = TokenType.NUMBER
    override fun toString(): String = "$data : $type"
}

data class StringToken(override val data: String) : Token {
    override val type: TokenType get() = TokenType.STRING
    override fun toString(): String = "$data : $type"
}

data class SymbolToken(override val data: String) : Token {
    override val type: TokenType get() = TokenType.SYMBOL
    override fun toString(): String = "$data : $type"
}

data class CommentToken(override val data: String) : Token {
    override val type: TokenType get() = TokenType.COMMENT
    override fun toString(): String = "$data : $type"
}

data class AnnotationToken(override val data: String) : Token {
    override val type: TokenType get() = TokenType.ANNOTATION
    override fun toString(): String = "$data : $type"
}

data class InvalidToken(override val data: String) : Token {
    override val type: TokenType get() = TokenType.INVALID
    override fun toString(): String = "$type"
}

enum class TokenType {
    WHITESPACE,
    NAME,
    KEYWORD,
    NUMBER,
    STRING,
    SYMBOL,
    COMMENT,
    ANNOTATION,
    INVALID
}