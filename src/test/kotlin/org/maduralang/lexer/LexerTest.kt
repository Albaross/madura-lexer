package org.maduralang.lexer

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class LexerTest {

    private val lexer = Lexer()

    @Test
    fun `lexer should return an empty list on empty text input`() {
        val result = lexer.scan("")
        assertEquals(emptyList(), result)
    }
}