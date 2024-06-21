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
        assertEquals(listOf(Whitespace(" ")), result)
    }

    @Test
    fun `lexer should recognize line breaks`() {
        val result = lexer.scan("\n")
        assertEquals(listOf(Whitespace("\n")), result)
    }

    @Test
    fun `lexer should recognize single letter names`() {
        val result = lexer.scan("x")
        assertEquals(listOf(Name("x")), result)
    }

    @Test
    fun `lexer should recognize multi letter names`() {
        val result = lexer.scan("len")
        assertEquals(listOf(Name("len")), result)
    }

    @Test
    fun `lexer should recognize keywords`() {
        val result = lexer.scan(Keywords.IF.toString())
        assertEquals(listOf(Keyword(Keywords.IF.toString())), result)
    }

    @Test
    fun `lexer should recognize single digit numbers`() {
        val result = lexer.scan("3")
        assertEquals(listOf(Number("3")), result)
    }

    @Test
    fun `lexer should recognize multi digit numbers`() {
        val result = lexer.scan("1337")
        assertEquals(listOf(Number("1337")), result)
    }

    @Test
    fun `lexer should recognize simple symbols`() {
        val result = lexer.scan("+")
        assertEquals(listOf(Symbol("+")), result)
    }

    @Test
    fun `lexer should recognize annotations`() {
        val result = lexer.scan("@Data")
        assertEquals(listOf(Meta("@Data")), result)
    }
}