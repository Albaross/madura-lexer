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

    @Test
    fun `lexer should recognize whitespaces`() {
        val result = lexer.scan(" ")
        assertEquals(listOf(WhitespaceToken(" ")), result)
    }

    @Test
    fun `lexer should recognize line breaks`() {
        val result = lexer.scan("\n")
        assertEquals(listOf(WhitespaceToken("\n")), result)
    }

    @Test
    fun `lexer should recognize single letter names`() {
        val result = lexer.scan("x")
        assertEquals(listOf(NameToken("x")), result)
    }

    @Test
    fun `lexer should recognize multi letter names`() {
        val result = lexer.scan("len")
        assertEquals(listOf(NameToken("len")), result)
    }

    @Test
    fun `lexer should recognize keywords`() {
        val result = lexer.scan(Keyword.IF.toString())
        assertEquals(listOf(KeywordToken(Keyword.IF.toString())), result)
    }

    @Test
    fun `lexer should recognize single digit numbers`() {
        val result = lexer.scan("3")
        assertEquals(listOf(NumberToken("3")), result)
    }

    @Test
    fun `lexer should recognize multi digit numbers`() {
        val result = lexer.scan("1337")
        assertEquals(listOf(NumberToken("1337")), result)
    }

    @Test
    fun `lexer should recognize simple symbols`() {
        val result = lexer.scan("+")
        assertEquals(listOf(SymbolToken("+")), result)
    }

    @Test
    fun `lexer should recognize annotations`() {
        val result = lexer.scan("@Data")
        assertEquals(listOf(MetaToken("@Data")), result)
    }
}