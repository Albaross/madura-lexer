package org.maduralang.lexer

enum class Keyword {
    AS, BREAK, CATCH, CLASS, CONTINUE, CONST, DO, ELSE, ENUM, EXPORT, FALSE, FOR, FN, FUN, FUNC,
    IF, IMPORT, IN, INLINE, IS, JUMP, LET, MATCH, NULL, OUT, PRIVATE, PROTECTED, PUBLIC, RETURN,
    SHARED, SUPER, THIS, THROW, TRUE, TRY, TYPEALIAS, VAR, WHILE;

    override fun toString(): String = name.lowercase()
}

fun String.isKeyword(): Boolean = this in KEYWORDS

private val KEYWORDS = Keyword.entries.map { it.toString() }.toSet()